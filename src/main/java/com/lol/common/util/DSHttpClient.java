package com.lol.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * http客户端类
 * 
 * @author cunxing
 *
 */
public class DSHttpClient {

    private String charset = "utf-8";

    private boolean autoclose = true;// 默认访问后直接关闭

    private CloseableHttpClient httpclient;

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setAutoclose(boolean autoclose) {
        this.autoclose = autoclose;
    }

    public DSHttpClient() {
        this.httpclient = HttpClients.createDefault();
    }

    public DSHttpClient(RequestConfig requestConfig) {
        this.httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
    }

    /**
     * 请求执行方法
     * 
     * @param httpClient
     * @param request
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    private String getResult(CloseableHttpClient httpClient, HttpUriRequest request) throws ClientProtocolException, IOException {
        String result = null;
        CloseableHttpResponse response = httpClient.execute(request);
        try {
            if(response.getStatusLine().getStatusCode() != 200) {
                request.abort();
                return result;
            }
            HttpEntity entity = response.getEntity();
            if(entity != null) {
                long len = entity.getContentLength();
                if(len != -1 && len < 2048) {
                    result = EntityUtils.toString(entity, charset);
                } else {
                    BufferedReader bufferedReader = null;
                    InputStream instream = entity.getContent();
                    try {
                        StringBuffer stringBuffer = new StringBuffer();
                        bufferedReader = new BufferedReader(new InputStreamReader(instream));
                        String tmp = "";
                        while((tmp = bufferedReader.readLine()) != null) {
                            stringBuffer.append(tmp);
                        }
                        result = stringBuffer.toString();
                    } catch(Exception e) {
                        e.printStackTrace();
                    } finally {
                        bufferedReader.close();
                        instream.close();
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
        return result;
    }

    /**
     * post提交(body类型参数)
     * 
     * @param url
     * @param body
     * @return
     */
    public String post(String url, String body) {
        String result = null;
        try {
            // 创建httppost
            HttpPost httppost = new HttpPost(url);
            // 创建参数队列
            if(body != null) {
                httppost.setEntity(new StringEntity(body, charset));
            }
            result = getResult(this.httpclient, httppost);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(this.autoclose) {
                this.close();
            }
        }
        return result;
    }

    /**
     * post提交(键值对类型参数)
     * 
     * @param url
     * @param parameters
     * @return
     */
    public String post(String url, Map<String, String> parameters) {
        String result = null;
        try {
            // 创建httppost
            HttpPost httppost = new HttpPost(url);
            // 创建参数队列
            if(parameters != null) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for(Entry<String, String> parm: parameters.entrySet()) {
                    params.add(new BasicNameValuePair(parm.getKey(), parm.getValue()));
                }
                httppost.setEntity(new UrlEncodedFormEntity(params, charset));
            }
            result = getResult(this.httpclient, httppost);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(this.autoclose) {
                this.close();
            }
        }
        return result;
    }

    /**
     * get请求
     * 
     * @param url
     * @return
     */
    public String get(String url) {
        return get(url, null);
    }

    /**
     * get请求(键值对参数)
     * 
     * @param url
     * @param parameters
     * @return
     */
    public String get(String url, Map<String, String> parameters) {
        String result = null;
        try {
            URI uri = null;
            try {
                URIBuilder uriBuilder = new URIBuilder(url);
                // 创建参数队列
                if(parameters != null) {
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    for(Entry<String, String> parm: parameters.entrySet()) {
                        params.add(new BasicNameValuePair(parm.getKey(), parm.getValue()));
                    }
                    uriBuilder.addParameters(params);
                    uriBuilder.setCharset(Charset.forName(charset));
                }
                uri = uriBuilder.build();
            } catch(URISyntaxException e) {
                e.printStackTrace();
            }
            HttpGet httpget = new HttpGet(uri);
            result = getResult(this.httpclient, httpget);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(this.autoclose) {
                this.close();
            }
        }
        return result;
    }

    public void close() {
        try {
            if(this.httpclient != null) {
                this.httpclient.close();
                this.httpclient = null;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(start);
        String url = "http://www.lol.com/api/common/order/info/getPriceList.jsp";
        DSHttpClient dsHttpClient = new DSHttpClient();
        dsHttpClient.setAutoclose(false);
        String result = null;
        result = dsHttpClient.get(url);
        System.out.println(result);
        result = dsHttpClient.get(url);
        System.out.println(result);
        result = dsHttpClient.get(url);
        System.out.println(result);
        result = dsHttpClient.get(url);
        System.out.println(result);
        dsHttpClient.close();
        System.out.println(System.currentTimeMillis() - start);
    }
}
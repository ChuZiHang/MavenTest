package com.lol.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * http协议工具类
 * 
 * @author cunxing
 *
 */
public class HttpUtils {

    private CloseableHttpClient httpclient;

    private RequestConfig requestConfig;

    public HttpUtils() {
        this.httpclient = HttpClients.createDefault();
        this.requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
    }

    public HttpUtils(int _timeout) {
        this.httpclient = HttpClients.createDefault();
        this.requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).setConnectTimeout(_timeout).build();
    }

    public String getBodyAsString(String url, String charset, Map<String, String> parameters, String methodtype) {
        if("post".equals(methodtype.toLowerCase())) {
            return post(url, charset, parameters);
        } else {
            return get(url, charset, parameters);
        }
    }

    public String post(String url, String charset, Map<String, String> parameters) {
        String result = "";
        try {
            // 创建httppost
            HttpPost httppost = new HttpPost(url);
            httppost.setConfig(requestConfig);
            // 创建参数队列
            if(parameters != null) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for(Entry<String, String> parm: parameters.entrySet()) {
                    params.add(new BasicNameValuePair(parm.getKey(), parm.getValue()));
                }
                httppost.setEntity(new UrlEncodedFormEntity(params, charset));
            }
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
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
                        } finally {
                            bufferedReader.close();
                            instream.close();
                        }
                    }
                }
            } finally {
                response.close();
            }
        } catch(ClientProtocolException e) {
            e.printStackTrace();
        } catch(UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String postJson(String url, String charset, String json) {
        String result = "";
        try {
            // 创建httppost
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json, charset);
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
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
                        } finally {
                            bufferedReader.close();
                            instream.close();
                        }
                    }
                }
            } finally {
                response.close();
            }
        } catch(ClientProtocolException e) {
            e.printStackTrace();
        } catch(UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String postFile(String url, String charset, Map<String, String> parameters, File file) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        String result = "";
        try {
            HttpPost httppost = new HttpPost(url);

            FileBody media = new FileBody(file);

            // StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);
            // HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();

            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            multipartEntityBuilder.addPart("media", media);
            Set<String> keyset = parameters.keySet();
            Iterator<String> iterator = keyset.iterator();

            String key = "";
            while(iterator.hasNext()) {
                key = iterator.next();
                multipartEntityBuilder.addPart(key, new StringBody(parameters.get(key)));
            }

            HttpEntity reqEntity = multipartEntityBuilder.build();

            httppost.setEntity(reqEntity);

            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                // System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                HttpEntity entity = response.getEntity();

                if(entity != null) {
                    long len = entity.getContentLength();
                    // System.out.println("len----------" + len);
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
                        } finally {
                            bufferedReader.close();
                            instream.close();
                        }
                    }
                }
            } finally {
                response.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public String get(String url, String charset, Map<String, String> parameters) {
        String result = "";
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
            httpget.setConfig(requestConfig);
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                if(entity != null) {
                    long len = entity.getContentLength();
                    // System.out.println("len----------" + len);
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
                        } finally {
                            bufferedReader.close();
                            instream.close();
                        }
                    }
                }
            } finally {
                response.close();
            }
        } catch(ClientProtocolException e) {
            e.printStackTrace();
        } catch(UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) throws HttpException, Exception {
        HttpUtils httpUtils = new HttpUtils();
        System.out.println(httpUtils.getBodyAsString("http://www.lol.com/api/im/profile.jsp?id=18939", "utf-8", null, "get"));
        // System.out.println(httpUtils.getBodyAsString("http://www.lol.com/", "utf-8", null, "post"));
    }
}
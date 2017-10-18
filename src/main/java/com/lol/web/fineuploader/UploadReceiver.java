package com.lol.web.fineuploader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lol.common.util.ImageConfig;
import com.lol.common.util.ImageUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//commented code blocks are only used for CORS environments
public class UploadReceiver extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static File TEMP_DIR = new File(ImageConfig.IMG_TEMP);

	private static int SUCCESS_RESPONSE_CODE = 200;

	final Logger log = LoggerFactory.getLogger(UploadReceiver.class);

	@Override
	public void init() throws ServletException {
		//UPLOAD_DIR.mkdirs();
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String filename = req.getPathInfo().replaceAll("/", "");

		handleDeleteFileRequest(filename, resp);
	}

	private void handleDeleteFileRequest(String filename, HttpServletResponse resp) throws IOException {
		String fullName = ImageUtils.getImgFullName(filename, true);
		FileUtils.deleteQuietly(new File(fullName));

		if (new File(fullName).exists()) {
			log.warn("couldn't find or delete " + filename);
		} else {
			log.info("deleted " + filename);
		}

		resp.setStatus(SUCCESS_RESPONSE_CODE);
		// resp.addHeader("Access-Control-Allow-Origin", "*");
	}

	@Override
	public void doOptions(HttpServletRequest req, HttpServletResponse resp) {
		resp.setStatus(SUCCESS_RESPONSE_CODE);
		resp.addHeader("Access-Control-Allow-Origin", "http://192.168.130.118:8080");
		// resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.addHeader("Access-Control-Allow-Methods", "POST, DELETE");
		resp.addHeader("Access-Control-Allow-Headers", "x-requested-with, cache-control, content-type");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		RequestParser requestParser = null;
		try {
			// resp.setContentType(isIframe ? "text/html" : "text/plain");
			resp.setContentType("text/plain");
			resp.setStatus(SUCCESS_RESPONSE_CODE);

			// resp.addHeader("Access-Control-Allow-Origin", "http://192.168.130.118:8080");
			// resp.addHeader("Access-Control-Allow-Credentials", "true");
			// resp.addHeader("Access-Control-Allow-Origin", "*");

			if (ServletFileUpload.isMultipartContent(req)) {
				MultipartUploadParser multipartUploadParser = new MultipartUploadParser(req, TEMP_DIR,
						getServletContext());
				requestParser = RequestParser.getInstance(req, multipartUploadParser);
				writeFile(requestParser.getUploadItem().getInputStream(), new File(ImageUtils.getImgFullName(requestParser.getCustomFilename(), true)), null);
				
				writeResponse(resp.getWriter(), requestParser.generateError() ? "Generated error" : null, false,
						requestParser);
			} else {
				requestParser = RequestParser.getInstance(req, null);

				// handle POST delete file request
				if (requestParser.getMethod() != null && requestParser.getMethod().equalsIgnoreCase("DELETE")) {
//					String uuid = requestParser.getUuid();
					handleDeleteFileRequest(requestParser.getCustomFilename(), resp);
				} else {
					//暂不处理
				}
			}
		} catch (Exception e) {
			log.error("Problem handling upload request", e);
			if (e instanceof MergePartsException) {
				writeResponse(resp.getWriter(), e.getMessage(), true, requestParser);
			} else {
				writeResponse(resp.getWriter(), e.getMessage(), false, requestParser);
			}
		}
	}

	private File writeFile(InputStream in, File out, Long expectedFileSize) throws IOException {
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(out);

			IOUtils.copy(in, fos);

			if (expectedFileSize != null) {
				Long bytesWrittenToDisk = out.length();
				if (!expectedFileSize.equals(bytesWrittenToDisk)) {
					log.warn("Expected file {} to be {} bytes; file on disk is {} bytes",
							new Object[] { out.getAbsolutePath(), expectedFileSize, 1 });
					out.delete();
					throw new IOException(
							String.format("Unexpected file size mismatch. Actual bytes %s. Expected bytes %s.",
									bytesWrittenToDisk, expectedFileSize));
				}
			}

			return out;
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			IOUtils.closeQuietly(fos);
		}
	}

	private void writeResponse(PrintWriter writer, String failureReason, boolean restartChunking,
			RequestParser requestParser) {
		if (failureReason == null) {
			Gson gson = new GsonBuilder().create();
			Map<String, String> map = new HashMap<String, String>();
			map.put("success", "true");
			map.put("url", ImageUtils.getImgFullName(requestParser.getCustomFilename(), false));
			writer.print(gson.toJson(map));
		} else {
			if (restartChunking) {
				writer.print("{\"error\": \"" + failureReason + "\", \"reset\": true}");
			} else {
				writer.print("{\"error\": \"" + failureReason + "\"}");
			}
		}
	}

	private class MergePartsException extends Exception {
		private static final long serialVersionUID = 1L;

		@SuppressWarnings("unused")
		MergePartsException(String message) {
			super(message);
		}
	}
}

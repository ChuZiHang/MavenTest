package com.lol.common.util;

import java.io.File;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

/**
 * 图片上传通用获取地址类,与app图片上传相对应,不能更改
 */
public class ImageUtils {

	/**
	 * 根据图片名找到图片并按高度和宽度切小图
	 *
	 * @param img
	 *            图片地址
	 * @param w
	 *            宽度
	 * @param h
	 *            高度
	 * @return 图片完整web地址
	 */
	public static String getThumImgFullName(String img, int w, int h) {
		int dotPos = img.lastIndexOf('.');
		int pathPos = img.lastIndexOf('/');
		if (dotPos <= 0 || dotPos <= pathPos)
			return "";

		byte bytes[] = img.getBytes();
		Checksum checksum = new CRC32();
		checksum.update(bytes, 0, bytes.length);
		long hashCode = Math.abs(checksum.getValue());

		long code = hashCode % 10000;
		StringBuilder sb = new StringBuilder();
		// 构造目录
		sb.append(File.separatorChar).append(code / 100).append(File.separator).append(code % 100);
		// 检查并创建目录
		File f = new File(ImageConfig.IMG_THUMB_STORE + sb.toString());
		if (!f.exists()) {
			f.mkdirs();
		}
		return ImageConfig.IMG_THUMB_SERVER + sb.append(File.separatorChar).append(img.substring(pathPos + 1, dotPos))
				.append('_').append(w).append('x').append(h).append(img.substring(dotPos)).toString();
	}

	/**
	 * 根据名称获取存储路劲或者访问图片地址
	 *
	 * @param img
	 * @param isLocal
	 *            false返回访问地址 true 返回存储地址
	 * @return 路劲或地址
	 */

	public static String getImgFullName(String img, boolean isLocal) {
		int dotPos = img.lastIndexOf('.');
		int pathPos = img.lastIndexOf('/');
		if (dotPos <= 0 || dotPos <= pathPos)
			return "";

		byte bytes[];
		if (pathPos != -1) {
			bytes = img.substring(pathPos + 1).getBytes();
		} else {
			bytes = img.getBytes();
		}
		Checksum checksum = new CRC32();
		checksum.update(bytes, 0, bytes.length);
		long hashCode = Math.abs(checksum.getValue());

		long code = hashCode % 10000;
		StringBuilder sb = new StringBuilder();
		if (isLocal) {
			// 构造目录
			sb.append(File.separatorChar).append(code / 100).append(File.separator).append(code % 100);
			// 检查并创建目录
			File f = new File(ImageConfig.IMG_STORE + sb.toString());
			if (!f.exists()) {
				f.mkdirs();
			}
			return ImageConfig.IMG_STORE + sb.append(File.separatorChar).append(img.substring(pathPos + 1, dotPos))
					.append(img.substring(dotPos)).toString();
		} else {
			// 构造目录
			sb.append("/").append(code / 100).append("/").append(code % 100);
			return ImageConfig.IMG_SERVER + sb.append("/").append(img.substring(pathPos + 1, dotPos))
					.append(img.substring(dotPos)).toString();
		}
	}

	public static void main(String[] args) {
		// 482k
		// http://img.lol.com/img/61/37/f05e763cde2ea7312cdae44b70e3f95e.jpg
		// http://www.lol.com/data/imgthumb/9/2/402990902_154429853878_500x500.JPG
		// http://img.lol.com/thumbimg/4/9/42494fb123d03417f956aa8e7b0a3bcf_80x80.jpeg
		// http://img.lol.com/img/25/0/9220b86302232944247ce85da81a2b09.jpeg
		System.out.println(getImgFullName("f05e763cde2ea7312cdae44b70e3f95e.jpg", false));
		System.out.println(getImgFullName("9220b86302232944247ce85da81a2b09.jpeg", true));
	}
}
package com.lol.common.util;

import java.util.Arrays;

/**
 * 数组工具类
 * 
 * @author cunxing
 *
 */
public class ArrayUtils {

	/**
	 * 合并
	 * 
	 * @param first
	 * @param args
	 * @return
	 */
	public static byte[] concatAll(byte[] first, byte[]... args) {
		int totalLength = first.length;
		for (byte[] array : args) {
			totalLength += array.length;
		}
		byte[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (byte[] array : args) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

	/**
	 * 去掉指定长度的开头部分
	 * 
	 * @param a
	 * @param start
	 * @return
	 */
	public static byte[] subtractLeft(byte[] a, int start) {
		int len = a.length - start;
		byte[] dest = new byte[len];
		System.arraycopy(a, len, dest, 0, dest.length);
		return dest;
	}

	public static void main(String[] args) {
		byte[] a = "xxxxxx".getBytes();
		byte[] b = "yyyyyy".getBytes();
		byte[] c = concatAll(a, b);

		byte[] d = subtractLeft(c, a.length);
		System.out.println(new String(c));
		System.out.println(new String(d));

	}

}

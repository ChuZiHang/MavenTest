package com.lol.common.shiro;

import java.io.Serializable;

import org.apache.shiro.util.SimpleByteSource;

/**
 * 实现Serializable使类可以序列化
 * 
 * @author cunxing
 *
 */
public class MySimpleByteSource extends SimpleByteSource implements Serializable {

	/**
	 * 序列ID
	 */
	private static final long serialVersionUID = 1L;

	public MySimpleByteSource(String string) {
		super(string);
	}

}

package com.inred.media.util;


import java.util.UUID;

public class RandomUtil {
	/**
	 * 得到一个UUID
	 * @return
	 */
	public static String getUUID() {
		return StringUtil.replace("-", "", UUID.randomUUID().toString()) ;
	}
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
}

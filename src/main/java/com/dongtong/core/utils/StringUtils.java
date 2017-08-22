package com.dongtong.core.utils;

import java.util.List;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class StringUtils {
	
	public static List<String> split(String content, String beginStr,
			String endStr) {
		List<String> strs = Lists.newArrayList();
		if (Strings.isNullOrEmpty((beginStr)) || Strings.isNullOrEmpty((endStr))) {
			throw new IllegalArgumentException(" beginStr or endStr is empty!");
		}
		if(Strings.isNullOrEmpty(content)){
			return strs;
		}
		int pos = content.indexOf(beginStr);
		int beginLen = beginStr.length();

		while (pos != -1) {
			int prepos = pos + beginLen;
			pos = content.indexOf(endStr, pos + beginLen);
			if (pos != -1) {
				strs.add(content.substring(prepos, pos));
			} else {
				break;
			}
			pos = content.indexOf(beginStr, pos);
		}
		return strs;
	}
	
	public static String splitFirst(String content, String beginStr,
			String endStr){
		List<String> strs = split(content, beginStr, endStr);
		if(strs.size() > 0){
			return strs.get(0);
		}else{
			return "";
		}
	}
}

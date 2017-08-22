package com.dongtong.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MiscUtils {

	private static final Logger logger = LoggerFactory.getLogger(MiscUtils.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	
	/**
     * 将指定的对象转换为json格式字符串。如果对象为null或者在转换过程中发生错误，返回空字符串。
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return "";
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            mapper.writeValue(baos, obj);
            return baos.toString();
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error("Error occurred while transforming object to json string.", e);
            }
            return "";
        }
    }
}

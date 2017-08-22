package com.dongtong.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.dongtong.core.ApiException;
import static com.dongtong.core.ErrorStatus.STATUS_1001;

public class DateUtils {
	
	public static String formatDate(Date date, String pattern) {  
        if (date == null) {  
            date = new Date(System.currentTimeMillis());  
        }  
  
        if (pattern == null) {  
            pattern = "yyyy-MM-dd HH:mm";  
        }  
        return DateFormatUtils.format(date, pattern);  
    }  
      
    public static String defaultFormat(Date date) {  
        return formatDate(date, null);  
    } 

	public static Date defaultParseDateFormat(String source){
    	return parseDateFormat(source, null, false);
    }
	
	public static Date defaultParseDateFormat(String source, String pattern){
    	return parseDateFormat(source, pattern, false);
    }
    
    public static Date parseDateFormat(String source, String pattern, Boolean throwExceptionFlag){
    	if(pattern == null){
    		pattern = "yyyy-MM-dd HH:mm";
    	}
    	SimpleDateFormat fo = new SimpleDateFormat();
    	fo.applyPattern(pattern);
    	try {
			return fo.parse(source);
		} catch (ParseException e) {
			if(throwExceptionFlag){
				throw new ApiException(STATUS_1001.getCode(), STATUS_1001.getMessage());
			}else{
				return new Date();
			}
		}
    }
    
    public static Boolean checkDate(String time, String pattern){
		try {
			parseDateFormat(time, pattern, true);
		} catch (ApiException e) {
			return false;
		}
		return true;
	}
    
    /**
     * true 校验时间在check时间之后
     * false 校验时间再check时间之前
     * @param source
     * @param checkSource
     * @return
     */
    public static Boolean compare(String source, String checkSource){
    	return source.compareTo(checkSource) >= 0;
    }
    
    public static String sourceDateFormat(String source, String fromPattern, String toPattern){
    	try {
    		return formatDate(parseDateFormat(source, fromPattern, true), toPattern);
		} catch (Exception e) {
			return "";
		}
    }
}

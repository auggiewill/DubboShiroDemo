package com.dongtong.core.utils;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void TestFormat(){
		Date d = DateUtils.parseDateFormat("10-08", "MM-dd", true);
		Assert.assertNotNull(d);
	}
	
	@Test
	public void TestFormatString(){
//		Date d = DateUtils.parseDateFormat("43421", "MM-dd", true);
//		Assert.assertNotNull(d);
	}
}

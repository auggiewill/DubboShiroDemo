package com.dongtong.constant;

import java.util.Arrays;
import java.util.List;

public class Constant {

	public final static List<String> BASE_EXPORT_KEY = Arrays.asList("ID","日期","来源","联系人","联系电话","标题","描述", 
			"链接", "区域", "面积", "价格");
	public final static List<String> BASE_EXPORT_VALUE = Arrays.asList("id","date","from","customer","phone","title","maincon", 
			"url", "region", "acreage", "price");
	public final static List<String> PPW_ASK_EXPORT_KEY = Arrays.asList("ID","日期","来源","联系人","联系电话","标题","描述", 
			"链接", "区域", "面积", "价格", "考虑面积");
	public final static List<String> PPW_ASK_EXPORT_VALUE = Arrays.asList("id","date","from","customer","phone","title","maincon", 
			"url", "region", "acreage", "price", "industry");	
	
	public final static List<String> PPW_TRANSFER_EXPORT_KEY = Arrays.asList("ID","日期","来源","联系人","联系电话","标题","描述", 
			"链接", "区域", "面积", "价格", "旺铺编号", "详细地址", "适合经营", "经营状态", "配套设施", "转让费", "目前经营");
	public final static List<String> PPW_TRANSFER_EXPORT_VALUE = Arrays.asList("id","date","from","customer","phone","title","maincon", 
			"url", "region", "acreage", "price", "wpNo", "address", "suitable", "manageStatus", "support", "transferPrice", "managed");
	public final static List<String> PPW_BUSINESS_EXPORT_KEY = Arrays.asList("ID","日期","来源","联系人","联系电话","标题","描述", 
			"链接", "区域", "面积", "价格", "楼盘", "详细地址", "适合经营");
	public final static List<String> PPW_BUSINESS_EXPORT_VALUE = Arrays.asList("id","date","from","customer","phone","title","maincon", 
			"url", "region", "acreage", "price", "premises", "address", "suitable");
	
	
}

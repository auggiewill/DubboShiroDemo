package com.dongtong.core.utils;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.JsonObject;

public class XLSUtils {
	
	/**
	 * 获取当前sheet，某一行的单元格数据作为数据字段
	 * 
	 * @author auggie
	 * @param sheet
	 * @param rowNum
	 * @return
	 */
	public static Map<String, String> getFirstRowData(Sheet sheet, int rowNum) {
		Map<String, String> firstRowData = new HashMap<String, String>();
		Row tab = sheet.getRow(rowNum);
		int cellsNo = tab.getLastCellNum();
		for (int coll = 0; coll < cellsNo; coll++) {
			Cell cel = tab.getCell(coll);
			firstRowData.put(String.valueOf(coll), getValue(cel).toString());
		}
		return firstRowData;
	}
	
	/**
	 * 获取sheet数据
	 * 
	 * @author auggie
	 * @param rowCount
	 * @param sheet
	 * @param firstRowData
	 * @param beginRow
	 * @return
	 */
	public static List<Map<String, Object>> getSheetListData(int rowCount,
			Sheet sheet, Map<String, String> firstRowData, int beginRow) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Set<String> keys = firstRowData.keySet();
		if (rowCount > beginRow) {
			sheet.getMargin(Sheet.TopMargin);
			for (int r = beginRow; r < rowCount; r++) { // 行循环
				Map<String, Object> inputData = new HashMap<String, Object>();
				Row row = sheet.getRow(r);
				if (row != null) {
					int cells = row.getLastCellNum();// 获得列数
					if (cells > 1) {
						for (short c = 0; c < keys.size(); c++) { // 列循环
							Cell cell = row.getCell(c);
							if (cell != null) {
								String value = getValue(cell);
								inputData.put(
										firstRowData.get(String.valueOf(c)),
										value);
							} else {
								inputData
										.put(firstRowData
												.get(String.valueOf(c)),
												"");
							}
						}
						list.add(inputData);
					}
				}
			}
		}

		return list;
	}
	
	
	public static String getValue(Cell cell) {

		String value = "";
		switch (cell.getCellTypeEnum()) {

		case NUMERIC: // 数值型
			if (DateUtil.isCellDateFormatted(cell)) {
				// 如果是date类型则 ，获取该cell的date值
				value = DateUtil.getJavaDate(cell.getNumericCellValue())
						.toString();
			} else {// 纯数字
				value = String.valueOf(cell.getNumericCellValue());
			}
			break;
		/* 此行表示单元格的内容为string类型 */
		case STRING: // 字符串型
			try {
				value = String.valueOf(cell.getNumericCellValue());
			} catch (Exception e) {
				value = cell.getRichStringCellValue().toString().trim();
			}
			break;
		case FORMULA:// 公式型
			// 读公式计算值
			value = String.valueOf(cell.getNumericCellValue());
			if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
				value = cell.getRichStringCellValue().toString();
			}
			// cell.getCellFormula();读公式
			break;
		case BOOLEAN:// 布尔
			value = " " + cell.getBooleanCellValue();
			break;
		/* 此行表示该单元格值为空 */
		case BLANK: // 空值
			value = "";
			break;
		case ERROR: // 故障
			value = "";
			break;
		default:
			value = cell.getRichStringCellValue().toString();
		}

		return value;
	}
	
	
	
	/**
	 * sheet Key 数据填充
	 * 
	 * @author auggie
	 * @param hssfs
	 * @param columeKey
	 * @param normalStyle
	 * @return
	 */
	public static HSSFSheet keySheetData(HSSFSheet hssfs, List<String> columeKey,
			HSSFCellStyle normalStyle) {
		HSSFRichTextString hssfRichString = null;
		// 创建第一行
		HSSFRow hsr = hssfs.createRow(0);
		hsr.createCell(0);
		// 在第一行放置元素名称
		for (int b = 0; b < columeKey.size(); b++) {
			hssfs.setColumnWidth(b, 10000);
			hssfRichString = new HSSFRichTextString(columeKey.get(b).toString());
			HSSFCell cell = hsr.createCell((b));
			if(Objects.nonNull(normalStyle)){
				cell.setCellStyle(normalStyle);
			}
			cell.setCellValue(hssfRichString);
		}
		return hssfs;
	}
	
	
	/**
	 * sheet Value 数据填充
	 * 
	 * @author auggie
	 * @param list
	 * @param hssfs
	 * @param columeKey
	 * @return
	 */
	public static HSSFSheet valueSheetData(List<JsonObject> list,
			HSSFSheet hssfs, List<String> columeValue) {
		HSSFRichTextString hssfRichString = null;
		for (int c = 0; c < list.size(); c++) {
			HSSFRow row = hssfs.createRow(c + 1);
			// 依次放入每个元素对应内容
			for (int d = 0; d < columeValue.size(); d++) {
				String mes = "";
				if (columeValue.get(d) != null) {
					if (list.get(c).get(columeValue.get(d).toString()) != null) {
						mes = list.get(c).get(columeValue.get(d).toString())
								.toString();
					}
				}
				hssfRichString = new HSSFRichTextString(mes);
				row.createCell((d)).setCellValue(hssfRichString);
			}
		}
		return hssfs;
	}
	
	public static HSSFSheet addValueSheetData(List<JsonObject> list,
			HSSFSheet hssfs, List<String> columeValue) {
		int lastRowNo = hssfs.getLastRowNum() + 1;
		HSSFRichTextString hssfRichString = null;
		for (int c = 0; c < list.size(); c++) {
			HSSFRow row = hssfs.createRow(c + lastRowNo);
			// 依次放入每个元素对应内容
			for (int d = 0; d < columeValue.size(); d++) {
				String mes = "";
				if (columeValue.get(d) != null) {
					if (list.get(c).get(columeValue.get(d).toString()) != null) {
						mes = list.get(c).get(columeValue.get(d).toString())
								.getAsString();
					}
				}
				hssfRichString = new HSSFRichTextString(mes);
				row.createCell((d)).setCellValue(hssfRichString);
			}
		}
		return hssfs;
	}
	
	/**
	 * 判断Excel文件类型,xls文件使用HSSFWorkbook,xlsx文件使用XSSFWorkbook
	 * 
	 * @author hyxue
	 * @param file
	 * @return
	 */
	public static Workbook caseWorkBook(InputStream in, String name) throws IOException {
		Workbook workBook = null;
		if (name.endsWith(".xls")) {
			workBook = new HSSFWorkbook(in);
		} else if (name.endsWith(".xlsx")) {
			workBook = new XSSFWorkbook(in);
		}
		return workBook;
	}
	
	private static HSSFSheet getSheet(HSSFWorkbook workBook){
		int sheetNum = workBook.getNumberOfSheets();
		HSSFSheet hssfs = null;
		if(sheetNum == 0){
			hssfs = workBook.createSheet("第1页");
		}else{
			hssfs = workBook.getSheetAt(sheetNum - 1);
		}
		if(hssfs.getLastRowNum() > 1000){
			hssfs = workBook.createSheet(String.format("第%s页", sheetNum + 1));
		}
		return hssfs;
	}
	
	private static HSSFCellStyle getCellStyle(HSSFWorkbook workBook){
		HSSFFont headFont = workBook.createFont();
		headFont.setBold(true);
		headFont.setFontHeightInPoints((short) 10);
		// 设置单元格样式
		HSSFCellStyle normalStyle = workBook.createCellStyle();
		normalStyle.setFont(headFont);
		return normalStyle;
	}
	
	
	public static String buildHSS(String filePath, List<String> columeKey ,List<String> columeValue,List<JsonObject> list){
		try {
			HSSFWorkbook hssfWorkBook = null;
			HSSFSheet hssfs = null;
			FileInputStream in = FileUtils.openInputStream(filePath);
			if(Objects.nonNull(in)){
				hssfWorkBook = new HSSFWorkbook(in);
			}else{
				hssfWorkBook = new HSSFWorkbook();
			}
			// 获取sheet工作表
			hssfs = getSheet(hssfWorkBook);
			if(hssfs.getLastRowNum() == 0){
				// 填充工作表第一行数据
				keySheetData(hssfs, columeKey, getCellStyle(hssfWorkBook));
			}
			// 填充工作表其他数据
			addValueSheetData(list, hssfs, columeValue);

			OutputStream out = new BufferedOutputStream(
					FileUtils.openOutputStream(filePath));
			
			hssfWorkBook.write(out);
			out.flush();
			out.close();
			hssfWorkBook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		} 
		return filePath;
	}
	
}

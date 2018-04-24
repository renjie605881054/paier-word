package com.paier.word.util.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.paier.word.util.StringUtil;



public class ReadExcel {

	
	public static List<?> readExcel(String path, String[] hearders) throws IOException {
        if (isExcel2003(path)) {
            return readXls(path, hearders);
        } else if (isExcel2007(path)) {
            return readXlsx(path, hearders);
        }
        return null;
    }

    /**
     * Excel 2010
     */
    public static List<?> readXlsx(String path, String[] hearders) throws IOException {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<Object> list = new ArrayList<>();
        // 解析Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            //解析Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                
                if (xssfRow != null) {
                	HashMap<String, Object> map = new HashMap<String, Object>();
                	for (int i=0; i<hearders.length;i++) {
                		XSSFCell cell = xssfRow.getCell(i);
                		 if(!StringUtil.isBlank(getValue(cell).trim())){
                			 map.put(hearders[i], getValue(cell));
                		 }
					}
                	if(!map.isEmpty()) list.add(map);
                }
            }
        }
        return list;
    }

    /**
     *Excel 2003-2007
     */
    public static List<?> readXls(String path, String[] hearders) throws IOException {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<Object> list = new ArrayList<>();
        // 解析Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 解析 Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                	HashMap<String, Object> map = new HashMap<String, Object>();
                    for (int i = 0;i < hearders.length;i++) {
                    	 HSSFCell cell = hssfRow.getCell(i);
                    	 if(!StringUtil.isBlank(getValue(cell).trim())){
                    		 map.put(hearders[i], getValue(cell));
                    	 }
					}
                	if(!map.isEmpty()) list.add(map);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("static-access")
    private static String getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    @SuppressWarnings("static-access")
    private static String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
    
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}

package com.orange_hrm.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public static Iterator<Object[]> excelReader(String filePath, String methodName) throws Exception{
		FileInputStream inputStream = new FileInputStream(new File(filePath));
		Workbook workbook = new XSSFWorkbook(inputStream);

		Sheet eachSheet = workbook.getSheetAt(0);
		int rowCount = eachSheet.getLastRowNum()-eachSheet.getFirstRowNum();
		
		List<String> headers = new ArrayList<>();
		List<Map<String, String>> excelData = new ArrayList<>();
		boolean isTCNameFound = false;
		boolean isHeaderRow = false;
		int rowCellCnt = 0;
		for (int i = 0; i < rowCount+1; i++) {
			Map<String, String> eachRowMap = new LinkedHashMap<>();
			List<String> eachRowValues = new ArrayList<>();
			Row row = eachSheet.getRow(i);
			if((row.getCell(rowCellCnt)) != null && (row.getCell(rowCellCnt)).toString().equalsIgnoreCase(methodName)) {
				if(isTCNameFound){
					break;
				}
				isTCNameFound = true; isHeaderRow = true;
				continue;
			}
			if(isTCNameFound) {
				if(isHeaderRow) {
					for (int j = 0; j < row.getLastCellNum(); j++) {
						headers.add((row.getCell(j)) != null ? row.getCell(j).toString() : "null");
					}
					isHeaderRow = false;
					continue;
				}
				if (!headers.isEmpty()) {
					for (int j = 0; j < headers.size(); j++) {
						try {
							eachRowMap.put(headers.get(j), (row.getCell(j)) != null ? (row.getCell(j)).toString() : null);
						} catch (IndexOutOfBoundsException aibe) {
							eachRowMap.put(headers.get(j), "null");
						}
					}
					excelData.add(eachRowMap);
				}
			}else{
				isTCNameFound = false;
			}
		}
	//	System.out.println(excelData);
		if(null != workbook){
			//workbook.close();
		}
		//return excelData;
		return getIterator(excelData);
	}

	private static Iterator<Object[]> getIterator(List<Map<String, String>> testDataList) {
		List<Object[]> iteratorList = new ArrayList<Object[]>();
		for (Map map : testDataList) {
			iteratorList.add(new Object[] { map });
		}
		return iteratorList.iterator();
	}

	public static void main(String[] args) throws Exception {
		String testDataFolderPath = "C:\\Users\\Sreelatha_Kallu\\Desktop\\TestData.xlsx";
		Iterator<Object[]> iterator = excelReader(testDataFolderPath, "testLoginFunctionality");
		for(;iterator.hasNext();){
			//System.out.println(row.values());
			Object[] next = iterator.next();
			System.out.println();
		}
	}
}

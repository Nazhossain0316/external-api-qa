package com.automation.api.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtil {


    public static Object[][] dataSupplier(String fileDir, String sheetName) {

        File file = new File(fileDir);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet sheet = wb.getSheet(sheetName);
        DataFormatter fmt = new DataFormatter();

        int numberOfTestsFlaggedOn = getNumberOfTestsTurnedOn(sheet);

        int lastRowNum = sheet.getLastRowNum() ;
        int lastCellNum = sheet.getRow(0).getLastCellNum();
        Object[][] obj = new Object[numberOfTestsFlaggedOn][1];

        for (int i = 0; i < lastRowNum; i++) {

            Map<Object, Object> datamap = new HashMap<>();

                for (int j = 0; j < lastCellNum; j++) {

                String colName = fmt.formatCellValue(sheet.getRow(0).getCell(j));
                String colValue = fmt.formatCellValue(sheet.getRow(i+1).getCell(j));
                datamap.put(colName,colValue);

            }
                if(datamap.get("Run").equals("Y")){
                    obj[i][0] = datamap;
                }
        }
        return  obj;
    }


    private static int getNumberOfTestsTurnedOn( XSSFSheet sheet){

        DataFormatter fmt = new DataFormatter();

        int runColumn = 1;
        int numberOfTestCasesTurnedOn = 0;

        int noOfRows = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < noOfRows; i++) {
            XSSFRow row = sheet.getRow(i);
            Cell cell = row.getCell(runColumn);
            String yOrN = fmt.formatCellValue(cell);
            if(yOrN.equals("Y")){
                numberOfTestCasesTurnedOn++;
            }
        }

        return numberOfTestCasesTurnedOn;

    }

}

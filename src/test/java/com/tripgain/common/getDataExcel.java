package com.tripgain.common;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class getDataExcel {

    public static List<Map<String, String>> getExcelDataFromSheet(String sheetName) {
        List<Map<String, String>> dataList = new ArrayList<>();
        File classpathRoot = new File(System.getProperty("user.dir"));
        File app = new File(classpathRoot, "src/test/resources/testdata/testdata.xlsx");
        String fileName = app.toString();

        try (FileInputStream fis = new FileInputStream(fileName);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Header row is missing in sheet: " + sheetName);
            }

            int totalCols = headerRow.getLastCellNum();
            DataFormatter formatter = new DataFormatter();

            // Find the index of "TestRun" column
            int testRunColIndex = -1;
            for (int i = 0; i < totalCols; i++) {
                String header = formatter.formatCellValue(headerRow.getCell(i));
                if ("TestRun".equalsIgnoreCase(header)) {
                    testRunColIndex = i;
                    break;
                }
            }

            if (testRunColIndex == -1) {
                throw new RuntimeException("\"TestRun\" column not found in sheet: " + sheetName);
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                // Check if TestRun column value is "SKIP"
                String testRunValue = formatter.formatCellValue(row.getCell(testRunColIndex));
                if ("SKIP".equalsIgnoreCase(testRunValue.trim())) {
                    continue; // Skip this row
                }

                Map<String, String> rowMap = new HashMap<>();
                boolean hasData = false;

                for (int j = 0; j < totalCols; j++) {
                    Cell headerCell = headerRow.getCell(j);
                    if (headerCell == null) continue;

                    String key = formatter.formatCellValue(headerCell);
                    String value = formatter.formatCellValue(row.getCell(j));

                    if (value != null && !value.trim().isEmpty()) {
                        hasData = true;
                    }

                    rowMap.put(key, value);
                }

                if (hasData) {
                    dataList.add(rowMap);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

}

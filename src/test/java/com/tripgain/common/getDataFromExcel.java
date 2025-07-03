package com.tripgain.common;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class getDataFromExcel {
    public static Map<String, String> getExcelData(String sheetName) throws IOException {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File app = new File(classpathRoot.getAbsolutePath() + "//src//test//resources//testdata//testdata1.xlsx");
        String fileName = app.toString();
        FileInputStream fis = new FileInputStream(fileName);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        Map<String, String> dataMap = new HashMap<>();

        Row headerRow = sheet.getRow(0);
        Row dataRow = sheet.getRow(1); // Assuming data is in the second row

        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            String key = headerRow.getCell(i).getStringCellValue().trim();
            Cell cell = dataRow.getCell(i);

            String value = "";
            switch (cell.getCellType()) {
                case STRING:
                    value = cell.getStringCellValue().trim();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        value = cell.getDateCellValue().toString();
                    } else {
                        value = String.valueOf((int) cell.getNumericCellValue());  // Cast to int to remove decimal
                    }
                    break;
                case BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case FORMULA:
                    value = cell.getCellFormula();
                    break;
                case BLANK:
                    value = "";
                    break;
                default:
                    throw new IllegalStateException("Unsupported cell type: " + cell.getCellType());
            }

            dataMap.put(key, value);
        }

        workbook.close();
        fis.close();

        return dataMap;
    }

}

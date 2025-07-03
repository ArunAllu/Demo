package com.tripgain.common;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class DataProviderUtils {

    @DataProvider(name = "sheetBasedData")
    public static Object[][] provideSheetData(Method method) {
        String fullClassName = method.getDeclaringClass().getSimpleName(); // e.g. TC_55_BusinessClass_Verify...

        // Extract only the prefix like "TC_55"
        String sheetName = fullClassName.split("_")[0] + "_" + fullClassName.split("_")[1];

        List<Map<String, String>> testData = getDataExcel.getExcelDataFromSheet(sheetName);
        Object[][] data = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
        }

        return data;
    }
}

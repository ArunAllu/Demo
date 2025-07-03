package com.tripgain.common;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class Getdata {

	    public static String[] getexceldata() throws IOException {
	        // Define the path to the Excel file
	    	
	    	File classpathRoot = new File(System.getProperty("user.dir"));
			File app = new File(classpathRoot.getAbsolutePath() + "//src//test//resources//testdata//testdata.xlsx");
			String fileName = app.toString();

			FileInputStream FileInputStream = new FileInputStream(fileName);
	        
	        // Open the Excel workbook
	        XSSFWorkbook workbook = new XSSFWorkbook(FileInputStream);
	        
	        // Get the sheet named "Sheet1"
	        XSSFSheet sheet = workbook.getSheet("Sheet1");
	        
	        // Retrieve data from row 0 (username) and row 1 (password)
	        XSSFRow row0 = sheet.getRow(0); // First row (username)
	        XSSFCell cell0 = row0.getCell(1); // Second column (thilak@tripgain.com)
	        String validUserName = cell0.getStringCellValue();
	        
	        XSSFRow row1 = sheet.getRow(1); // Second row (password)
	        XSSFCell cell1 = row1.getCell(1); // Second column (Thanks@123)
	        String validUserPassword = cell1.getStringCellValue();
	        
	        // Retrieve data from row 2 (invalid username) and row 3 (invalid password)
	        XSSFRow row2 = sheet.getRow(2); // Third row (invalidusername)
	        XSSFCell cell2 = row2.getCell(1); // Second column (tarun@tripgain.com)
	        String inValidUserName = cell2.getStringCellValue();
	        
	        XSSFRow row3 = sheet.getRow(3); // Fourth row (inpwd)
	        XSSFCell cell3 = row3.getCell(1); // Second column (thanks@123)
	        String inValidUserPassword = cell3.getStringCellValue();
	        
	        // Close workbook and file input stream
	        workbook.close();
	        FileInputStream.close();
	        
	        // Return an array with the values for usn, pwd, inusn, inpwd
	        return new String[] {validUserName, validUserPassword, inValidUserName, inValidUserPassword};
	    }
	    
	    
	    
	  
	    
	    
	}



package utilities;

import java.util.Hashtable;

import initialization.Initialize;

public class TestUtils extends Initialize{
	
	public boolean isExecutable(String testName){
		
		String tcName = "";
		String testSuiteSheet = "TestSuite";
		String runMode = "";
		
		for (int r=1; r<excel.getRowCount(testSuiteSheet); r++){
			tcName = excel.getCellData(testSuiteSheet, r, 0);
			
			if (tcName.equals(testName)){
				runMode = excel.getCellData(testSuiteSheet, r, 1);
				if (runMode.equals("Y")){
					return true;
				}
				else
					return false;	
			}
		}
		
		return false;
		
	}
		
	public Object[][] getParametersFromExcel(String sheetName){
		
		Hashtable <String, String> table = null;
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColCount(sheetName);
		
		Object [][] data = new Object[rows-1][1];
		
		for (int r=1; r<rows; r++){
			
			table = new Hashtable<String, String>();
			
			for (int c=0; c<cols; c++){
				
				String key = excel.getCellData(sheetName, 0, c);
				String value = excel.getCellData(sheetName, r, c);			
				table.put(key, value);
				data [r-1][0] = table;
			}
			
		}		
		return data;
		
	}
	
}

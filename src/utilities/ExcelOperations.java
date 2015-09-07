package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations {
	
	//private String filePath = "";
	private FileInputStream fis =null;
	private Workbook wb = null;
	private Sheet wSheet = null;
	//private XSSFRow row = null;
	//private XSSFCell cell = null;
	
	public ExcelOperations (String filePath){
		
		try {
			fis = new FileInputStream(filePath);
			wb = new XSSFWorkbook(fis);
			fis.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	//Get the row count in a given sheet
	public int getRowCount (String sheetName){
		
		int rowCount = 0;
		wSheet = wb.getSheet(sheetName);
		rowCount = wSheet.getLastRowNum()+1;		
		return rowCount;
	
	}
	
	//Get the column count in a given sheet
	public int getColCount (String sheetName){
		
		int colCount = 0;
		wSheet = wb.getSheet(sheetName);
		colCount = wSheet.getRow(0).getLastCellNum();
		return colCount;
		
	}
	
	//Read Data from a given row and column within a sheet
	public String getCellData (String sheetName, int rowNum, int colNum){
		
		String cellValue = "";
		wSheet = wb.getSheet(sheetName);
		
		Cell cell = wSheet.getRow(rowNum).getCell(colNum);
	    //for (Row row : wSheet) {
	        //for (Cell cell : row) {
	            //CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
	            //System.out.print(cellRef.formatAsString());
	            //System.out.print(" - ");

	            switch (cell.getCellType()) {
	                case Cell.CELL_TYPE_STRING:
	                	cellValue = cell.getRichStringCellValue().getString();
	                    break;
	                case Cell.CELL_TYPE_NUMERIC:
	                    if (DateUtil.isCellDateFormatted(cell)) {
	                    	cellValue = cell.getDateCellValue().toString();
	                    } else {
	                    	cellValue = String.valueOf(cell.getNumericCellValue());
	                    }
	                    break;
	                case Cell.CELL_TYPE_BOOLEAN:
	                	cellValue = String.valueOf(cell.getBooleanCellValue());
	                    break;
	                case Cell.CELL_TYPE_FORMULA:
	                	cellValue = cell.getCellFormula();
	                    break;
	                
	            }
	      //  }
	   // }
		
		return cellValue;
		
	}
	
}

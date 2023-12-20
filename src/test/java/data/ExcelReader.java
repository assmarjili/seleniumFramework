package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	static FileInputStream fis = null;
	
	public FileInputStream getFileInputStream() {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\data\\userData.xlsx";
		File srsFile = new File(filePath);
		try {
			fis= new FileInputStream(srsFile);
		} catch (FileNotFoundException e) {
			System.out.println("error occured " + e.getMessage());		
		}
		return fis;
	}	
	public Object[][] getExcelData() throws IOException{
		
		fis=getFileInputStream();
		XSSFWorkbook wb= new  XSSFWorkbook(fis);
		XSSFSheet sheet= wb.getSheetAt(0);
		int totalNumberOfCols=4;
		int totalNumberOfRows=(sheet.getLastRowNum()+1);
		
		String[][] arrayExcelData= new String [totalNumberOfRows][totalNumberOfCols];
		for(int i=0; i<totalNumberOfRows;i++) {
			for(int j=0; j<totalNumberOfCols; j++) {
				XSSFRow row = sheet.getRow(i);
				arrayExcelData[i][j]= row.getCell(j).toString();
			}
		}
		
		wb.close();

		return arrayExcelData;
		
	}
}

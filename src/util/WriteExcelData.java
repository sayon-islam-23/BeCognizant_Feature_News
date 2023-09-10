package util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelData {
	//This class is responsible for storing the datas inside the excel
	
	public static void saveData(List<String> arr) { 
		//Accepts list as an argument
		//Creates an instance of file class by passing parameter as string which is the file that will be worked upon
		
		//Creates an instance of XSSFWorkbook which will create an excel workbook
		File file = new File("News_Headings.xlsx");
		
		//It will create excel sheet inside the Workbook
		XSSFWorkbook wb = new XSSFWorkbook();
		
		// creating a sheet.
		XSSFSheet sh = wb.createSheet();
		//Creates a row and also a cell in that row and set a cell value with the given name
		sh.createRow(0).createCell(0).setCellValue("BeCognizant News Headings");
		for (int i = 0; i < arr.size(); i++) {
			//creates row and set value inside cells
			sh.createRow(i + 1).createCell(0).setCellValue(arr.get(i));
		}
		try {
			//Creates an instance of the class FileOuputStream and passing file as an argument
			FileOutputStream fos = new FileOutputStream(file);
			//Writes inside the file
			wb.write(fos);
			//closing the file
			wb.close();
		} catch (Exception e) {
			System.out.println("Unable to store Data!");
			e.printStackTrace();
		}
	}
}

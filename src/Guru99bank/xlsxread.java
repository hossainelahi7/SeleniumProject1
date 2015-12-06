package Guru99bank;

import java.io.File;
import java.io.FileInputStream;



import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class xlsxread {
		
		public static void main(String agr[]){
			try {
				xlxfileread("C:/tmp/Book.xlsx");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	    public static String xlxfileread(String excelFilePath) throws IOException {
	        //String excelFilePath = "C:/tmp/Book.xlsx";
	        String output = "";
	    	FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	         
	        Workbook workbook = new XSSFWorkbook(inputStream);
	        Sheet firstSheet = workbook.getSheetAt(0);
	        //output = new String[firstSheet.getLastRowNum()];
	        //System.out.println(output.length);
	        
	        for(int i = 0; i <=firstSheet.getLastRowNum(); i++){
	        	
	        	for(int j = 0; j< firstSheet.getRow(i).getLastCellNum(); j++){
	        		
	        		Cell cell = firstSheet.getRow(i).getCell(j);
	        		switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        output = output+ cell.getStringCellValue();
                    	System.out.print(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                    	output = output+ cell.getBooleanCellValue();
                    	System.out.print(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                    	output = output + cell.getNumericCellValue();
                        System.out.print(cell.getNumericCellValue());
                        break;
                    default:
                    	output = output + cell.getCellFormula();
                    	System.out.print(cell.getCellFormula());
                    	break;
                }
	        		System.out.print(" - ");	
	        	}
	        	System.out.println();
	        }
	        workbook.close();
	        inputStream.close();
	        return output;
	    }
	}


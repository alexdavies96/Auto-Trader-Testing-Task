package Auto_Trader_Website_Testing.AutoTrader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SpreadSheetReader {

	private Workbook workbook;

	public SpreadSheetReader(String filename){
		try {
			FileInputStream excelFile = new FileInputStream(new File(filename));
			workbook = new XSSFWorkbook(excelFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public ArrayList<String> readRow(int rowNo, String sheetName) {
		Sheet datatypeSheet = workbook.getSheet(sheetName);
		ArrayList<String> row = new ArrayList<String>();
		Row currentRow = datatypeSheet.getRow(rowNo);
		for (Cell currentCell : currentRow) {
			switch (currentCell.getCellTypeEnum()) {
			case STRING:
				row.add(currentCell.getStringCellValue());
				break;
			case NUMERIC:
				row.add(String.valueOf(currentCell.getNumericCellValue()));
				break;
			case BOOLEAN:
				row.add(String.valueOf(currentCell.getBooleanCellValue()));
				break;
			case BLANK:
				row.add(currentCell.getStringCellValue());
				break;
			case _NONE: 
				System.out.println("Error in cell");
				break;
			case ERROR:
				System.out.println("Error in cell");
				break;
			case FORMULA:
				row.add(currentCell.getCellFormula());
				break;
			}
		}
		return row;
	}
}


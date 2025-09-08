package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Utility class for reading test data from Excel files.
 */
public class ExcelUtil {

    private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/OpenCartTestData.xlsx";
    private static Workbook book;
    private static Sheet sheet;

    /**
     * Reads test data from the given sheet name and returns it as a 2D Object array.
     *
     * @param sheetName name of the Excel sheet to read data from
     * @return two-dimensional array containing the sheet data
     */
    public static Object[][] getTestData(String sheetName) throws InvalidFormatException {

		System.out.println("reading test data from sheet:" + sheetName);

		Object data[][] = null;

		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);

			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;

	}
}
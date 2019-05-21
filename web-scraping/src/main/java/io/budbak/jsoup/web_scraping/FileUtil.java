package io.budbak.jsoup.web_scraping;

import io.budbak.jsoup.model.SiteModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class FileUtil {

	public void generateExcel(List<SiteModel> scrapList) {
		File file = new File("IMDB_Top_250.xls");
		FileOutputStream fos = null;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			Sheet sheet = workbook.createSheet("Movie Data");
			int i = 0;
			Row headerRow = sheet.createRow(i);
			
			CellStyle style = workbook.createCellStyle();
		    final Font font = sheet.getWorkbook ().createFont ();
		    font.setFontName("Arial");
		    font.setBold(true);
		    font.setColor(HSSFColorPredefined.AUTOMATIC.getIndex());
			style.setFont(font);
			
			for (int count = 0; count < 4; count++) {
				Cell cell = headerRow.createCell(count);
				
				cell.setCellStyle(style);
				switch(count) { 
					case 0:
						cell.setCellValue("Movie Title");
						break;
					case 1:
						cell.setCellValue("IMDB Link");
						break;
					case 2:
						cell.setCellValue("Rating");
						break;
					case 3:
						cell.setCellValue("Survey Size");
						break;
					default:
						System.err.println("Error creating excel header");
						break;
				}
			}
			i++;
			for (SiteModel siteModel : scrapList) {
				if (i > 1) {
				Row row = sheet.createRow(i - 1);
					for (int j = 0; j < 4; j++) {
						Cell cell = row.createCell(j);
						switch(j) { 
						case 0:
							cell.setCellValue(siteModel.getTitle());
							break;
						case 1:
							cell.setCellValue(siteModel.getImdbLink());
							break;
						case 2:
							cell.setCellValue(siteModel.getRating());
							break;
						case 3:
							cell.setCellValue(siteModel.getSurveySize());
							break;
						default:
							System.err.println("Error creating excel cell");
							break;
						}
					}
				}
				i++;
			}
			

			fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.flush();
			workbook.close();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}

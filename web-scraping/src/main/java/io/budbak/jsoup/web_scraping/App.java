package io.budbak.jsoup.web_scraping;

import io.budbak.jsoup.model.SiteModel;

import java.util.List;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		Scraper scraper = new Scraper();
		List<SiteModel> scrapList = scraper
				.scrape("https://www.imdb.com/chart/top");
		// scraper.scrape("https://www.amazon.in");
		// scraper.scrape("https://www.youtube.com/");
		// scraper.scrape("https://dir.indiamart.com/impcat/wooden-study-table.html");
		if (!scrapList.isEmpty()) {
			FileUtil fileUtil = new FileUtil();
			fileUtil.generateExcel(scrapList);
		}
	}
}

package io.budbak.jsoup.web_scraping;

import io.budbak.jsoup.model.SiteModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Scraper {
	
	private static final String CONTEXT_ROOT = "https://www.imdb.com";
	
	public List<SiteModel> scrape(final String url) {
		List<SiteModel> topImdbList = new ArrayList<>(); 
		try {
			Document document = Jsoup.connect(url).userAgent("mozilla/17.0").get();
			// IMDB Top 250
			int i = 0;
			for(Element row : document.select("table.chart.full-width tr")) {
				SiteModel model = new SiteModel();
				model.setTitle(row.select(".titleColumn a").text());
				model.setImdbLink(CONTEXT_ROOT + row.select("a").attr("href"));
				model.setRating(row.select(".imdbRating").text());
				if (i > 0) { // skip the header
					String surveySize = row.getElementsByTag("strong").attr("title");
					model.setSurveySize(surveySize.substring(surveySize.indexOf("based"), surveySize.length()));
				}
				topImdbList.add(model);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return topImdbList;
	}

}

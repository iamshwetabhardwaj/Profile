package io.budbak.jsoup.model;

public class SiteModel {
	
	private String title;
	private String imdbLink;
	private String rating;
	private String surveySize;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImdbLink() {
		return imdbLink;
	}
	public void setImdbLink(String imdbLink) {
		this.imdbLink = imdbLink;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "SiteModel { " + this.getTitle() + " :: " 
				+ this.getImdbLink() + " :: "
				+ this.getRating() + " :: "
				+ this.getSurveySize()
				+ " }";
	}
	public String getSurveySize() {
		return surveySize;
	}
	public void setSurveySize(String surveySize) {
		this.surveySize = surveySize;
	}

}

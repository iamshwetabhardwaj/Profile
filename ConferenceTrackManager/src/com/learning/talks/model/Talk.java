package com.learning.talks.model;


public class Talk {

	public Talk(String title, String startTime,
			Integer duration) {
		this.title = title;
		this.startTime = startTime;
		this.duration = duration;
	}

	/**
	 * The meeting title.
	 */
	private String title;

	/**
	 * The meeting duration.
	 */
	private Integer duration;

	/**
	 * The meeting start time.
	 */
	private String startTime;


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getTitle() + " :: " + this.getDuration() + " :: " + this.getStartTime();
	}

	public String getTitle() {
		return title;
	}

	public Integer getDuration() {
		return duration;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

}

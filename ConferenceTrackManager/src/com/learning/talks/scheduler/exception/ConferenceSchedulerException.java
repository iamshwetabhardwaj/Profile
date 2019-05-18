package com.learning.talks.scheduler.exception;

public class ConferenceSchedulerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConferenceSchedulerException() {
		super();
	}

	public ConferenceSchedulerException(final String message) {
		System.err.println("Exception in ConferenceTrackManager :: " + message);
	}

}

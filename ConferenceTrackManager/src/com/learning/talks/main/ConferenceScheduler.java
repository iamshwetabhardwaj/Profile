package com.learning.talks.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.learning.talks.constants.ConferenceTrackManagerConstants;
import com.learning.talks.model.Talk;
import com.learning.talks.scheduler.exception.ConferenceSchedulerException;
import com.learning.talks.scheduler.util.ConferenceSchedulerUtil;

public class ConferenceScheduler {

	/**
	 * The method to schedule conferences
	 * 
	 * @param meetingInfoMap
	 *            - the track titles to schedule
	 * @param isScheduledFromMorning
	 *            - whether to schedule track titles from morning (true) or
	 *            evening (false)
	 * @return
	 */
	public Map<String, Talk> scheduleConferences(
			Map<String, Talk> meetingInfoMap,
			boolean isScheduledFromMorning) throws ConferenceSchedulerException {

		Map<String, Talk> scheduledConferences = new LinkedHashMap<>();

		SimpleDateFormat sdf = new SimpleDateFormat(
				ConferenceTrackManagerConstants.DATE_FORMAT);
		Date date = null;
		try {
			date = sdf.parse(ConferenceTrackManagerConstants.AM_9);
		} catch (ParseException exc) {
			throw new ConferenceSchedulerException(exc.getMessage());
		}

		Calendar morningCalendar = Calendar.getInstance();
		morningCalendar.setTime(date);
		morningCalendar.set(Calendar.AM_PM, Calendar.AM);

		try {
			date = sdf.parse(ConferenceTrackManagerConstants.PM_1);
		} catch (ParseException exc) {
			throw new ConferenceSchedulerException(exc.getMessage());
		}

		Calendar eveningCalendar = Calendar.getInstance();
		eveningCalendar.setTime(date);
		eveningCalendar.set(Calendar.AM_PM, Calendar.PM);

		if (isScheduledFromMorning) {
			scheduledConferences = scheduleConferencesFromMorning(
					meetingInfoMap, morningCalendar, eveningCalendar);
		} else {
			scheduledConferences = scheduleConferencesFromEvening(
					meetingInfoMap, morningCalendar, eveningCalendar);
		}

		return scheduledConferences;
	}

	/**
	 * The method to schedule conferences from Morning.
	 * 
	 * @param meetingInfoMap
	 * @param morningCalendar
	 * @param eveningCalendar
	 * @return
	 * @throws ConferenceSchedulerException
	 */
	private Map<String, Talk> scheduleConferencesFromMorning(
			final Map<String, Talk> meetingInfoMap,
			final Calendar morningCalendar, final Calendar eveningCalendar)
			throws ConferenceSchedulerException {
		Map<String, Talk> scheduledConferences = new LinkedHashMap<String, Talk>();
		// Morning sessions begin at 9am and must finish by 12 noon, for lunch
		// - so lunch interval after 3 hours from start time i.e. 180 minutes.
		int lunchInterval = 180;
		// Afternoon sessions start at 1pm and must finish in time for the
		// networking event.
		// The networking event can start no earlier than 4:00 and no later than
		// 5:00.
		int endTime = 240; // - so 4 hours from 1pm i.e. 240 minutes.
		int morningStartTime = 0;
		int eveningStartTime = 0;
		boolean isLunchOver = false;

		List<Talk> morningMeetings = new ArrayList<>();
		List<Talk> eveningMeetings = new ArrayList<>();

		for (Entry<String, Talk> entry : meetingInfoMap.entrySet()) {
			Talk talk = entry.getValue();

			if (!isLunchOver
					&& lunchInterval - morningStartTime < talk
							.getDuration()) {
				isLunchOver = true;
				eveningCalendar.set(Calendar.HOUR, 1); // lunch hour over, set
														// calendar ahead by 1
														// hour.
				eveningCalendar.set(Calendar.AM_PM, Calendar.PM);
			}

			if (isSlotAvailable(lunchInterval, morningStartTime, talk)) {
				morningStartTime += talk.getDuration();
				String hourOfDay = Integer
						.toString((morningCalendar.get(Calendar.HOUR) == 0)
								&& morningCalendar.get(Calendar.AM_PM) == Calendar.PM ? morningCalendar
								.get(Calendar.HOUR_OF_DAY) : morningCalendar
								.get(Calendar.HOUR));
				talk.setStartTime(hourOfDay
						+ ConferenceTrackManagerConstants.COLON
						+ morningCalendar.get(Calendar.MINUTE)
						+ ConferenceSchedulerUtil.getAmPm(morningCalendar));
		/*		meetingInfo.setMeetingStartTime(hourOfDay
						+ ConferenceTrackManagerConstants.COLON
						+ morningCalendar.get(Calendar.MINUTE)
						+ ConferenceSchedulerUtil.getAmPm(morningCalendar));*/
				// TODO: set meeting start time
				morningMeetings.add(talk);
				morningCalendar.add(Calendar.MINUTE,
						talk.getDuration());
			} else if (isLunchOver
					&& morningStartTime + talk.getDuration() >= lunchInterval
					&& isSlotAvailable(endTime, eveningStartTime, talk)) {
				eveningStartTime += talk.getDuration();
				String hourOfDay = Integer
						.toString((eveningCalendar.get(Calendar.HOUR) == 0)
								&& eveningCalendar.get(Calendar.AM_PM) == Calendar.PM ? eveningCalendar
								.get(Calendar.HOUR_OF_DAY) : eveningCalendar
								.get(Calendar.HOUR));
				/*meetingInfo.setMeetingStartTime(hourOfDay
						+ ConferenceTrackManagerConstants.COLON
						+ eveningCalendar.get(Calendar.MINUTE)
						+ ConferenceSchedulerUtil.getAmPm(eveningCalendar));*/
				// TODO: set meeting start time
				talk.setStartTime(hourOfDay
						+ ConferenceTrackManagerConstants.COLON
						+ eveningCalendar.get(Calendar.MINUTE)
						+ ConferenceSchedulerUtil.getAmPm(eveningCalendar));
				eveningMeetings.add(talk);
				eveningCalendar.add(Calendar.MINUTE,
						talk.getDuration());
			} else {
				// System.out.println("Skipped from Scheduling :: " +
				// meetingInfo);
			}
		}
		
		populateLunchAndNetworkingEvent(morningMeetings, eveningCalendar,
				eveningMeetings);

		populateScheduledConferences(scheduledConferences, morningMeetings,
				eveningMeetings);

		return scheduledConferences;
	}

	/**
	 * Check if a slot is still available to accomodate talk with duration
	 * @param lunchInterval
	 * @param startTime
	 * @param talk
	 * @return
	 */
	private boolean isSlotAvailable(int lunchInterval,
			int startTime, Talk talk) {
		return startTime + talk.getDuration() <= lunchInterval;
	}

	/**
	 * The method to schedule conferences from Evening.
	 * 
	 * @param meetingInfoMap
	 * @param morningCalendar
	 * @param eveningCalendar
	 * @return
	 * @throws ConferenceSchedulerException
	 */
	private Map<String, Talk> scheduleConferencesFromEvening(
			final Map<String, Talk> meetingInfoMap,
			final Calendar morningCalendar, final Calendar eveningCalendar)
			throws ConferenceSchedulerException {
		Map<String, Talk> scheduledConferences = new LinkedHashMap<String, Talk>();
		// Morning sessions begin at 9am and must finish by 12 noon, for lunch
		// - so lunch interval after 3 hours from start time i.e. 180 minutes.
		int lunchInterval = 180;
		// Afternoon sessions start at 1pm and must finish in time for the
		// networking event.
		// The networking event can start no earlier than 4:00 and no later than
		// 5:00.
		int endTime = 240; // - so 4 hours from 1pm i.e. 240 minutes.
		int morningStartTime = 0;
		int eveningStartTime = 0;
		boolean isLunchOver = false;

		List<Talk> morningMeetings = new ArrayList<>();
		List<Talk> eveningMeetings = new ArrayList<>();

		for (Entry<String, Talk> entry : meetingInfoMap.entrySet()) {
			Talk talk = entry.getValue();

			if (isSlotAvailable(endTime, eveningStartTime, talk)) {
				eveningStartTime += talk.getDuration();
				String hourOfDay = Integer
						.toString((eveningCalendar.get(Calendar.HOUR) == 0)
								&& eveningCalendar.get(Calendar.AM_PM) == Calendar.PM ? eveningCalendar
								.get(Calendar.HOUR_OF_DAY) : eveningCalendar
								.get(Calendar.HOUR));
				/*meetingInfo.setMeetingStartTime(hourOfDay
						+ ConferenceTrackManagerConstants.COLON
						+ eveningCalendar.get(Calendar.MINUTE)
						+ ConferenceSchedulerUtil.getAmPm(eveningCalendar));*/
				// TODO: set meeting start time
				talk.setStartTime(hourOfDay
						+ ConferenceTrackManagerConstants.COLON
						+ eveningCalendar.get(Calendar.MINUTE)
						+ ConferenceSchedulerUtil.getAmPm(eveningCalendar));
				eveningMeetings.add(talk);
				eveningCalendar.add(Calendar.MINUTE,
						talk.getDuration());
			} else if (endTime - eveningStartTime < talk
					.getDuration()) {
				if (!isLunchOver
						&& (eveningStartTime - lunchInterval) <= talk
								.getDuration()) {
					isLunchOver = true;
				}
				if (isLunchOver
						&& isSlotAvailable(lunchInterval, morningStartTime,
								talk)) {
					morningStartTime += talk.getDuration();
					String hourOfDay = Integer
							.toString((morningCalendar.get(Calendar.HOUR) == 0)
									&& morningCalendar.get(Calendar.AM_PM) == Calendar.PM ? morningCalendar
									.get(Calendar.HOUR_OF_DAY)
									: morningCalendar.get(Calendar.HOUR));
					/*meetingInfo.setMeetingStartTime(hourOfDay
							+ ConferenceTrackManagerConstants.COLON
							+ morningCalendar.get(Calendar.MINUTE)
							+ ConferenceSchedulerUtil.getAmPm(morningCalendar));*/
					// TODO: set meeting start time
					talk.setStartTime(hourOfDay
							+ ConferenceTrackManagerConstants.COLON
							+ morningCalendar.get(Calendar.MINUTE)
							+ ConferenceSchedulerUtil.getAmPm(morningCalendar));
					morningMeetings.add(talk);
					morningCalendar.add(Calendar.MINUTE,
							talk.getDuration());
				} else {
					/*
					 * System.out.println("Skipped from Scheduling :: " +
					 * meetingInfo);
					 */
				}
			} else {
				/*
				 * System.out.println("Skipped from Scheduling :: " +
				 * meetingInfo);
				 */
			}
		}
		
		populateLunchAndNetworkingEvent(morningMeetings, eveningCalendar,
				eveningMeetings);
		
		populateScheduledConferences(scheduledConferences, morningMeetings,
				eveningMeetings);

		return scheduledConferences;
	}

	/**
	 * The method to set lunch and networking events after track scheduling.
	 * 
	 * @param morningMeetings
	 * @param eveningCalendar
	 * @param eveningMeetings
	 */
	private void populateLunchAndNetworkingEvent(
			final List<Talk> morningMeetings,
			final Calendar eveningCalendar,
			final List<Talk> eveningMeetings) {
		// set lunch
		morningMeetings.add(new Talk(
				ConferenceTrackManagerConstants.LUNCH,
				ConferenceTrackManagerConstants.LUNCH_TIME, 60));

		// set networking event
		if (eveningCalendar.get(Calendar.HOUR) < 4) {
			// Networking event should not start earlier than 4
			eveningCalendar.set(Calendar.HOUR, 4); 
		}
		String hourOfDay = Integer
				.toString((eveningCalendar.get(Calendar.HOUR) == 0)
						&& eveningCalendar.get(Calendar.AM_PM) == Calendar.PM ? eveningCalendar
						.get(Calendar.HOUR_OF_DAY) : eveningCalendar
						.get(Calendar.HOUR));
		String meetingStartTime = hourOfDay
				+ ConferenceTrackManagerConstants.COLON
				+ eveningCalendar.get(Calendar.MINUTE)
				+ ConferenceSchedulerUtil.getAmPm(eveningCalendar);
		eveningMeetings.add(new Talk(
				ConferenceTrackManagerConstants.NETWORKING_EVENT,
				meetingStartTime, 60));

	}

	/**
	 * Populate scheduled meetings in order of scheduling.
	 * 
	 * @param scheduledConferences
	 * @param morningMeetings
	 * @param eveningMeetings
	 */
	private void populateScheduledConferences(
			Map<String, Talk> scheduledConferences,
			List<Talk> morningMeetings, List<Talk> eveningMeetings) {

		// add morning meetings to linked hash map
		for (Talk meetingInfo : morningMeetings) {
			scheduledConferences
					.put(meetingInfo.getTitle(), meetingInfo);
		}

		// add evening meetings to linked hash map
		for (Talk meetingInfo : eveningMeetings) {
			scheduledConferences
					.put(meetingInfo.getTitle(), meetingInfo);
		}
	}

}

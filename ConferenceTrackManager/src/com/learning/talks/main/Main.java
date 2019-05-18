package com.learning.talks.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.learning.talks.model.Talk;
import com.learning.talks.scheduler.exception.ConferenceSchedulerException;
import com.learning.talks.scheduler.util.ConferenceSchedulerUtil;

public class Main {

	public static void main(String[] args) {
		try {
			if (null == args || args.length == 0) {
				throw new ConferenceSchedulerException("Please provide file path argument.");
			} else {
				// Prepare the meeting details.
				Map<String, Talk> meetingInfoMap = ConferenceSchedulerUtil
						.readInputAndGenerateMeetingInfo(args[0]);

				// Schedule the tracks.
				ConferenceScheduler scheduler = new ConferenceScheduler();
				Map<String, Talk> tracksFromMorningMap = scheduler
						.scheduleConferences(meetingInfoMap, true);
				// Print the tracks.
				System.out.println("Track 1:");
				ConferenceSchedulerUtil
						.printScheduledTracks(tracksFromMorningMap);
				System.out.println("\n");

				Map<String, Talk> tracksFromEveningMap = scheduler
						.scheduleConferences(meetingInfoMap, false);
				// Print the tracks.
				System.out.println("Track 2:");
				ConferenceSchedulerUtil
						.printScheduledTracks(tracksFromEveningMap);
			}
		} catch (ConferenceSchedulerException exc) {
			System.err
					.println("ConferenceSchedulerException in ConferenceTrackManager :: "
							+ exc.getMessage());
		} catch (Exception exc) {
			System.err.println("Exception in ConferenceTrackManager :: "
					+ exc.getMessage());
		}
	}

}

class Tester {
	public static void main(String[] args) {
		Calendar cs = Calendar.getInstance();
		cs.set(Calendar.HOUR, 23);
		cs.set(Calendar.MINUTE, 05);
		cs.set(Calendar.SECOND, 38);
		cs.set(Calendar.MILLISECOND, 0);
		cs.set(Calendar.AM_PM, 23 <= 12 ? Calendar.AM : Calendar.PM);
		
		Calendar cTemp = Calendar.getInstance();
		cTemp.set(Calendar.HOUR, 11);
		cTemp.set(Calendar.MINUTE, 05);
		cTemp.set(Calendar.SECOND, 38);
		cTemp.set(Calendar.MILLISECOND, 0);
		cTemp.set(Calendar.AM_PM, 11 <= 12 ? Calendar.AM : Calendar.PM);
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
		System.out.println("cs -> " + sdf.format(cs.getTime()));
		System.out.println("cTemp -> " + sdf.format(cTemp.getTime()));
		System.out.println("diff -> " + new Date(cs.getTimeInMillis() - cTemp.getTimeInMillis()));
		/*int hourDiff = Math.abs(cs.get(Calendar.HOUR) - cTemp.get(Calendar.HOUR));
		int minuteDiff = Math.abs(cs.get(Calendar.MINUTE) - cTemp.get(Calendar.MINUTE));
		int secondDiff = Math.abs(cs.get(Calendar.SECOND) - cTemp.get(Calendar.SECOND));*/
		
		int hourDiff = Math.abs(cs.get(Calendar.HOUR)
				- cTemp.get(Calendar.HOUR));
		int minuteDiff = Math.abs(cs.get(Calendar.MINUTE)
				- cTemp.get(Calendar.MINUTE));
		int secondDiff = (cTemp.get(Calendar.SECOND) != 0 ? 60 - cTemp
				.get(Calendar.SECOND) : cTemp.get(Calendar.SECOND))
				+ cs.get(Calendar.SECOND);
		System.out.println("hourDiff = " + hourDiff + ", minuteDiff = "
				+ minuteDiff + ", secondDiff = " + secondDiff);
		if (hourDiff == 0 && minuteDiff == 0 && secondDiff == 60) {
			System.out.println("now");
		} else if (hourDiff > 1) {
			System.out.println(hourDiff + hourDiff == 1 ? " hour ago" : " hours ago");
		} else if (minuteDiff >= 1) {
			if (hourDiff == 0 && minuteDiff < 60) {
				System.out.println(minuteDiff + (minuteDiff == 1 ? " minute ago" : " minutes ago"));	
			}
		} else if (minuteDiff == 1 && secondDiff < 60) {
			System.out.println(secondDiff + (secondDiff == 1 ? " second ago" : " seconds ago"));
		}
	}
}

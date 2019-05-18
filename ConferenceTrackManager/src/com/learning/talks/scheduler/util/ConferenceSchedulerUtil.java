package com.learning.talks.scheduler.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.learning.talks.constants.ConferenceTrackManagerConstants;
import com.learning.talks.model.Talk;
import com.learning.talks.scheduler.exception.ConferenceSchedulerException;

public class ConferenceSchedulerUtil {

	public static Map<String, Talk> readInputAndGenerateMeetingInfo(
			final String filePath) throws ConferenceSchedulerException {

		BufferedReader br = null;
		Map<String, Talk> talkMap = new HashMap<String, Talk>();

		try {
			File file = new File(filePath);
			if (!file.isFile()) {
				throw new ConferenceSchedulerException(
						"Input File does not exist. Please provide valid input file.");
			}
			br = new BufferedReader(new FileReader(file));
			if (null != br) {
				String str = null;
				Pattern pattern = Pattern.compile("\\d+"); // No talk title has
															// numbers in it.
				while ((str = br.readLine()) != null) {
					int duration = 0;
					String title = null;
					String startTime = null;
					Matcher matcher = pattern.matcher(str);
					if (matcher.find()) {
						duration = Integer.parseInt(matcher
								.group());
					} else {
						if (str.contains(ConferenceTrackManagerConstants.LIGHTNING)) {
							duration = 5; // All talk
																// lengths are
																// either in
																// minutes (not
																// hours) or
																// lightning (5
																// minutes).
						}
					}

					title = str.substring(
									0,
									str.contains(ConferenceTrackManagerConstants.LIGHTNING) ? str
											.indexOf(ConferenceTrackManagerConstants.LIGHTNING)
											: str.indexOf(String.valueOf(duration)));
					Talk talk = new Talk(title, startTime, duration);
					talkMap.put(talk.getTitle(),
							talk);
				}

			}
		} catch (FileNotFoundException exc) {
			exc.printStackTrace();
		} catch (IOException exc) {
			exc.printStackTrace();
		} finally {
			try {
				if (null != br) {
					br.close();
				}
			} catch (IOException exc) {
				exc.printStackTrace();
			}
		}
		return talkMap;

	}
	

	/**
	 * Prints the scheduled tracks.
	 * 
	 * @param tracksMap
	 */
	public static void printScheduledTracks(
			final Map<String, Talk> tracksMap) {
		for (Entry<String, Talk> entry : tracksMap.entrySet()) {
			Talk talk = entry.getValue();
			System.out.println(talk.getStartTime()
					+ ConferenceTrackManagerConstants.SPACE
					+ talk.getTitle());
		}
	}
	

	/**
	 * Get AM or PM depending on calendar.
	 * 
	 * @param calendar
	 * @return
	 */
	public static String getAmPm(Calendar calendar) {
		return calendar.get(Calendar.AM_PM) == 0 ? ConferenceTrackManagerConstants.AM
				: ConferenceTrackManagerConstants.PM;
	}

}

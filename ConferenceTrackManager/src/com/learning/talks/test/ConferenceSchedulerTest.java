/**
 * 
 */
package com.learning.talks.test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.learning.talks.main.ConferenceScheduler;
import com.learning.talks.model.Talk;
import com.learning.talks.scheduler.exception.ConferenceSchedulerException;

/**
 * @author Shweta
 *
 */
public class ConferenceSchedulerTest {
	
	ConferenceScheduler conferenceScheduler;
	Map<String, Talk> meetingInfoMap ;
	
	@Before
	public void setUp() {
		conferenceScheduler = new ConferenceScheduler();
		meetingInfoMap = new HashMap<>();
	}

	@Test
	public void testInvalidInput() {
		try {
			conferenceScheduler.scheduleConferences(null, true);
		} catch (ConferenceSchedulerException exc) {
			Assert.assertTrue(null != exc.getMessage());
		} catch (Exception exc) {
			Assert.assertTrue(null != exc.getMessage());
		}
	}
	
	/**
	 * Test method for {@link com.thoughtworks.talks.main.ConferenceScheduler#scheduleConferences(java.util.Map, boolean)}.
	 */
	@Test
	public void testScheduleConferences() {
		Map<String, Talk> morningTrack = new HashMap<>() ;
		try {
			morningTrack = conferenceScheduler.scheduleConferences(meetingInfoMap, true);
			System.out.println(morningTrack);
			Assert.assertTrue(morningTrack.size() > 2);
		} catch (ConferenceSchedulerException exc) {
			Assert.assertTrue(morningTrack.size() == 2);
			Assert.assertTrue(null != exc.getMessage());
		}
	}

}

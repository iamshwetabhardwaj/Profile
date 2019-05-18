package com.learning.talks.slots.impl;

import com.learning.talks.slots.Slot;

public class MorningSlot implements Slot {

	@Override
	public int getDuration() {
		// read from configuration
		
		return 180;
	}

}

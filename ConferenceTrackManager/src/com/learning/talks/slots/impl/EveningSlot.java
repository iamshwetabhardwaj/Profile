package com.learning.talks.slots.impl;

import com.learning.talks.slots.Slot;

public class EveningSlot implements Slot {

	@Override
	public int getDuration() {
		// read from configurations
		return 240;
	}

}

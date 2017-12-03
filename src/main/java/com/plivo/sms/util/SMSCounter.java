package com.plivo.sms.util;

import java.io.Serializable;
import java.sql.Timestamp;

public class SMSCounter implements Serializable {

	private Timestamp startTime;
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	private int currentCount;
	
	public SMSCounter(Timestamp t, int counter){
		this.startTime = t;
		this.currentCount = counter;
	}
}

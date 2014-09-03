package org.cog.model;

public class Message {
	private ShowTime showTime;
	private int  noOfSeat;
	public Message(){
		
	}
	public Message(ShowTime showTime, int noOfSeat) {
		super();
		this.showTime = showTime;
		this.noOfSeat = noOfSeat;
	}
	
	public ShowTime getShowTime() {
		return showTime;
	}
	public int getNoOfSeat() {
		return noOfSeat;
	}
}

package org.cog.model;

import java.util.List;

public class BookingResponse {
	private int statusCode;
	private List<String> allocatedSeats;
	private int noOfAvaibleSeat;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public List<String> getAllocatedSeats() {
		return allocatedSeats;
	}

	public void setAllocatedSeats(List<String> allocatedSeats) {
		this.allocatedSeats = allocatedSeats;
	}

	public int getNoOfAvaibleSeat() {
		return noOfAvaibleSeat;
	}

	public void setNoOfAvaibleSeat(int noOfAvaibleSeat) {
		this.noOfAvaibleSeat = noOfAvaibleSeat;
	}

	public BookingResponse(int statusCode) {
		super();
		this.statusCode = statusCode;
	}
}

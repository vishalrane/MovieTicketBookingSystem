package org.cog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cog.model.BookingResponse;
import org.cog.model.Seat;
import org.cog.model.ShowTime;
import org.cog.service.MovieTicketBookingManager;

public class MovieTicketBookingManagerImpl implements MovieTicketBookingManager {
	private static Map<ShowTime, Seat[]> seatAllocationMap = new HashMap<ShowTime, Seat[]>();
	static{
		 Seat[] seats = new Seat[50];
		 for(int i = 0; i < 50; i++){
			 seats[i] = new Seat(String.valueOf(i));
		 }
		 seatAllocationMap.put(ShowTime._11AM, seats.clone());
		 seatAllocationMap.put(ShowTime._2PM, seats.clone());
		 seatAllocationMap.put(ShowTime._6PM, seats.clone());
	}
	
	public BookingResponse bookTicket(ShowTime showTime, int noOfSeats) {
		int noOfAvaibleSeat = checkTicketAvability(showTime);
		if(noOfAvaibleSeat >= noOfSeats) {
			List<String> allocatedSeats = allocateSeats(showTime, noOfSeats);
			BookingResponse response = new BookingResponse(200);
			response.setAllocatedSeats(allocatedSeats);
			return response;
		}
		
		BookingResponse response = new BookingResponse(500);
		response.setNoOfAvaibleSeat(noOfAvaibleSeat);
		return response;
	}

	public int checkTicketAvability(ShowTime showTime){
		Seat[] seats = seatAllocationMap.get(showTime);
		int noOfAvaibleSeat = 0;
		for (int i = 0; i < seats.length; i++) {
			if(seats[i].getVisible())noOfAvaibleSeat++;
		}
		return noOfAvaibleSeat;
	}

	public static Map<ShowTime, Seat[]> getSeatAllocationMap() {
		return seatAllocationMap;
	}
	
	private List<String> allocateSeats(ShowTime showTime, int noOfSeats){
		Seat[] seats = seatAllocationMap.get(showTime);
		List<String> allocatedSeats = new ArrayList<String>();
		for (int i = 0; i < seats.length && noOfSeats > 0; i++) {
			if(seats[i].getVisible()){
				seats[i].setVisible(false);
				allocatedSeats.add(seats[i].getName());
				noOfSeats--;
			}
		}
		return allocatedSeats;
	}
}

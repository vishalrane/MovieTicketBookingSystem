package org.cog.service.impl.test;


import java.util.Map;

import org.cog.model.Message;
import org.cog.model.BookingResponse;
import org.cog.model.Seat;
import org.cog.model.ShowTime;
import org.cog.service.MovieTicketBookingManager;
import org.cog.service.impl.MovieTicketBookingManagerImpl;
import org.junit.Assert;
import org.junit.Test;

public class MovieTicketBookingManagerImplTest {

	
	
	@Test
	public void testCheckTicketAvability() {
		MovieTicketBookingManager bookingManager = new MovieTicketBookingManagerImpl();
		Map<ShowTime, Seat[]> seatAllocationMap = MovieTicketBookingManagerImpl.getSeatAllocationMap();
		Seat[] seats = seatAllocationMap.get(ShowTime._11AM);
		for (int i = 0; i < seats.length; i++) {
			if(i == 45) break;
			seats[i].setVisible(false);
		}
		
		int noOfSeatAvailable = bookingManager.checkTicketAvability(ShowTime._11AM);
		
		Assert.assertEquals(noOfSeatAvailable, 5);
	}
	
	@Test
	public void testBookTicket() {
		Message message = new Message(ShowTime._11AM, 5);
		MovieTicketBookingManager bookingManager = new MovieTicketBookingManagerImpl();
		Map<ShowTime, Seat[]> seatAllocationMap = MovieTicketBookingManagerImpl.getSeatAllocationMap();
		Seat[] seats = seatAllocationMap.get(message.getShowTime());
		for (int i = 0; i < seats.length; i++) {
			if(i == 45) break;
			seats[i].setVisible(false);
		}
		
		BookingResponse response = bookingManager.bookTicket(message.getShowTime(), message.getNoOfSeat());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertArrayEquals(new String[]{"45","46","47","48","49"}, response.getAllocatedSeats().toArray(new String[response.getAllocatedSeats().size()]));
	}

}

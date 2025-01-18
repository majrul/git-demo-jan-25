package com.training.resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.training.model.PassengerDetails;
import com.training.model.PassengerDetails.Gender;
import com.training.model.PassengerDetails.Status;
import com.training.model.PnrDetails;

@Path("/booking-status")
public class BookingStatusResource {

	//http://localhost:8080/my-rest-app/api/booking-status?pnrNo=1234567890
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PnrDetails getStatus(@QueryParam("pnrNo") int pnrNo) {
		//for the time being, we will return hard coded data
		PnrDetails pnrDetails = new PnrDetails();
		pnrDetails.setPnrNo(pnrNo);
		pnrDetails.setTrainNo(12121);
		pnrDetails.setTravelDate(LocalDate.of(2025, 1, 31));
		
		List<PassengerDetails> passengerDetails = new ArrayList<PassengerDetails>();
		passengerDetails.add(new PassengerDetails("Raj", Gender.MALE, Status.RAC));		
		passengerDetails.add(new PassengerDetails("Ravi", Gender.MALE, Status.CONFIRMED));
		pnrDetails.setPassengers(passengerDetails);
		
		return pnrDetails;
	}
}

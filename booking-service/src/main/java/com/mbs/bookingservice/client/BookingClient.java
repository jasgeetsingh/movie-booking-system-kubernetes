package com.mbs.bookingservice.client;

import com.mbs.bookingservice.model.BookingDao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "admin")
public interface BookingClient {

	@PostMapping("/admin/booking/add")
	BookingDao newBookingRequest(@RequestBody BookingDao bookingDao) ;
	
}

package com.mahendra.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/interest")
public class InterestService {

	// The url should look like
	// /interest?p=12000&r=4.3&d=36
	@GET
	@Produces("text/plain")
	public double calc(@QueryParam("p") double principal,
				@QueryParam("r") double rate,
				@QueryParam("d") int duration) {
		System.out.println("Service is invoked");
		return principal * (rate / 100 / 12) * duration;
	}
}

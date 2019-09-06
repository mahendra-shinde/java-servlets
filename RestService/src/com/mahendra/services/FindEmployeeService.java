package com.mahendra.services;

import javax.ws.rs.*;

import com.mahendra.entities.Employee;

@Path("/employee")
public class FindEmployeeService {

	@GET
	@Produces("application/xml")
	public Employee find() {
		return new Employee("Donald","Trump",76);
	}
}

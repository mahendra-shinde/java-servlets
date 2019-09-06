package com.mahendra.services;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class InterestService {

	@WebMethod
	public double calc(double principal,double rate, int duration) {
		return principal*(rate/100/12)*duration;
	}
}

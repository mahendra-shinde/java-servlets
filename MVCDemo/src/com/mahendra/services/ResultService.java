package com.mahendra.services;

import java.util.*;

import com.mahendra.entities.Result;


public class ResultService {
	private static ResultService service = new ResultService();
	public static ResultService getResultService() {
		return service;
	}
	
	private List<Result> results= new LinkedList<>();
	
	private ResultService() {
		results.add(new Result(101,"Durgesh","Failed")); 
		results.add(new Result(102,"Rahul","Failed"));
		results.add(new Result(103,"Rohan","Failed"));
		results.add(new Result(104,"Shahrukh","Pass"));
		results.add(new Result(105,"Shahista","Absent"));
	}
	
	/**
	 * Find result by roll number
	 * @param rollNo
	 * @return result object
	 */
	public Result find(int rollNo) {
		for(Result rs : results) {
			if(rs.getRollNo()==rollNo) {
				return rs;
			}
		}
		return null;
	}
}

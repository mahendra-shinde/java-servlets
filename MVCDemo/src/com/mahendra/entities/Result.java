package com.mahendra.entities;

public class Result {
	private int rollNo;
	private String name;
	private String result;
	
	
	public Result(int rollNo, String name, String result) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.result = result;
	}

	public Result() {
		super();
	}

	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
}

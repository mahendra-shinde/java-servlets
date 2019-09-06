package com.mahendra.app;

import java.rmi.RemoteException;

import com.mahendra.services.InterestServiceProxy;

public class Main {

	public static void main(String[] args) {
		InterestServiceProxy proxy = new InterestServiceProxy();
		try {
			double amt = proxy.calc(123000D, 4.5, 36);
			System.out.println("Interest "+amt);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

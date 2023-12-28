package com.ilp.entity;

import java.util.ArrayList;

public class SavingsMaxAccount extends Product {

	private double minimumBalance;

	public SavingsMaxAccount(String productCode, String productName, ArrayList<Service> serviceList,
			double minimumBalance) {
		super(productCode, productName, serviceList);
		this.minimumBalance = minimumBalance;
	}

	public double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	@Override
	public String toString() {
		return super.toString()+",minimumBalance=" + minimumBalance + "]";
	}
	
	
	
	
}

package com.ilp.entity;

import java.util.ArrayList;

public class LoanAccount extends Product {
	private double chequeDeposit;

	public LoanAccount(String productCode, String productName, ArrayList<Service> serviceList, double chequeDeposit) {
		super(productCode, productName, serviceList);
		this.chequeDeposit = chequeDeposit;
	}

	public double getChequeDeposit() {
		return chequeDeposit;
	}

	public void setChequeDeposit(double chequeDeposit) {
		this.chequeDeposit = chequeDeposit;
	}
	
}

package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class ProductService {

	public static ArrayList<Product> createProduct(ArrayList<Service> serviceList) {
		
		

		Scanner scanner = new Scanner(System.in);

		ArrayList<Product> tempProductList = new ArrayList<Product>();
		ArrayList<Service> tempServiceList = new ArrayList<Service>();
		
		if(serviceList.size()==0) {
			System.out.print("No services Exist. Sorry!");
			return tempProductList;
		}
		
		char continueChoice = 'y';

		do {

			System.out.print("\nEnter product code : ");
			String productCode = scanner.next();

			System.out.print("\nEnter product name : ");
			String productName = scanner.next();

			for (Service service : serviceList) {

				System.out.println("Do you want to add " + service.getServiceName() + " to product (y/n)");
				char addServiceChoice = scanner.next().charAt(0);
				if (addServiceChoice == 'y') {
					tempServiceList.add(service);
				}

			}

			switch (productName.toLowerCase()) {

			case "savingsmaxaccount":
				tempProductList.add(new SavingsMaxAccount(productCode, productName, tempServiceList, 1000));
				break;
			case "currentaccount":
				tempProductList.add(new CurrentAccount(productCode, productName, tempServiceList));
				break;
			case "loanaccount":
				tempProductList.add(new LoanAccount(productCode, productName, tempServiceList, 0.3));
				break;
			default:
				System.out.print("\nInvalid choice");
				break;
			}

			System.out.print("\nDo you want to create more (y/n) : ");

			continueChoice = scanner.next().charAt(0);

		} while (continueChoice == 'y');
		
		return tempProductList;

	}



}

package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;
import com.ilp.service.CustomerAccountService;
import com.ilp.service.ProductService;
import com.ilp.service.ServiceCreator;

public class BankingUtility {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		ArrayList<Service> serviceList = new ArrayList<Service>();
		ArrayList<Product> productList = new ArrayList<Product>();

//		serviceList.add(new Service("S01", "CashDeposit", 0));
//		serviceList.add(new Service("S02", "ATMWithdrawl", 0));
//		serviceList.add(new Service("S03", "OnlineBanking", 0));
//		serviceList.add(new Service("S04", "MobileBanking", 0));
//		serviceList.add(new Service("S05", "ChequeDeposit", 0));
//
//		productList.add(new SavingsMaxAccount("P01", "SavingsMaxAccount", serviceList, 1000));
//		productList.add(new CurrentAccount("P02", "CurrentAccount", serviceList));
//		productList.add(new LoanAccount("P03", "LoanAccount", serviceList, 0.3));

		Customer customer = null;

		int mainMenuChoice;

		do {

			System.out.print("\n************Welcome To Bank************\n");

			System.out.print("1.Create Services\n" + "2.Create Product\n" + "3.Create Customer\n");
			System.out.print("4.Manage Accounts\n" + "5.Display Customer\n" + "6.Exit\n");

			System.out.print("\nEnter your choice : ");
		    mainMenuChoice = scanner.nextInt();

			switch (mainMenuChoice) {
			case 1:
				serviceList.addAll(ServiceCreator.createService());
				break;
			case 2:
				productList.addAll(ProductService.createProduct(serviceList));
				break;
			case 3:
				if (customer == null) {
					Account account = CustomerAccountService.createAccount(productList);
					customer = CustomerAccountService.createCustomer(account);
				} 
				else {
					Account account = CustomerAccountService.createAccount(productList);
					ArrayList<Account> accountList = customer.getAccountList();
					accountList.add(account);
					customer.setAccountList(accountList);
					System.out.print(customer);
				}
				break;
			case 4:
				CustomerAccountService.manageAccount(customer);
				break;
			case 5:
				CustomerAccountService.displayCustomer(customer);
				break;
			case 6:
				System.out.println("Exiting from application");
				break;
			default:
				System.out.println("Please select another option");
				break;
			}


		} while ( mainMenuChoice <6 );

	}

}

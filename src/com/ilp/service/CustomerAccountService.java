package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class CustomerAccountService {

	public static Customer createCustomer(Account account) {
		
		if(account==null) {
			return null;
		}

		Scanner scanner = new Scanner(System.in);

		System.out.print("\nEnter Customer Code : ");
		String customerCode = scanner.next();

		System.out.print("\nEnter customer Name : ");
		String customerName = scanner.next();

		ArrayList<Account> accountList = new ArrayList<Account>();
		accountList.add(account);

		return new Customer(customerCode, customerName, accountList);
	}

	public static Account createAccount(ArrayList<Product> productList) {
		
		if(productList.size()==0) {
			System.out.println("No products exists. Sorry!");
			return null;
		}
		Scanner scanner = new Scanner(System.in);
		int i = 1;
		System.out.print("\n*********Accounts Available*********");
		for (Product product : productList) {
			System.out.print("\n" + i + ". " + product.getProductName());
			i++;
		}

		System.out.print("\nEnter your choice : ");
		int accountChoice = scanner.nextInt();

		System.out.print("\nEnter account No : ");
		String accountNo = scanner.next();

		System.out.print("\nEnter account Balance : ");
		double accountBalance = scanner.nextDouble();

		Product product = productList.get(accountChoice - 1);

		// If savingsMax, ensure minimumBalance is 1000, even when creating.
		if (product instanceof SavingsMaxAccount) {
			double minimumBal = ((SavingsMaxAccount) product).getMinimumBalance();

			while (accountBalance < minimumBal) {
				System.out.print("\nBalance must be above " + minimumBal);
				System.out.print("\nEnter account Balance : ");
				accountBalance = scanner.nextDouble();
			}
		}

		return new Account(accountNo, productList.get(accountChoice - 1).getProductName(), accountBalance,
				productList.get(accountChoice - 1));

	}

	public static void manageAccount(Customer customer) {
		if(customer==null) {
			System.out.println("No customer exist. Sorry");
			return;
		}
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nEnter Customer Code : ");
		String customerCode = scanner.next();

		if (customerCode.equalsIgnoreCase(customer.getCustomerCode())) {

			System.out.print("\n" + customer.getCustomerName() + " has Following Accounts");

			// List all accounts of Customer
			for (Account account : customer.getAccountList()) {
				System.out.print("\n" + account.getAccountType());
			}

			System.out.print("\nEnter your choice : ");
			String accountChoice = scanner.next();

			// Find the matching Account user selected
			Account selectedAccount = null;
			for (Account account : customer.getAccountList()) {
				if (account.getAccountType().equalsIgnoreCase(accountChoice)) {
					selectedAccount = account;
				}
			}

			char transactionContinueChoice = 'y';
			do {

				System.out.print("\n\n1.Deposit 2.Withdraw 3.DisplayBalance");
				System.out.print("\nEnter your choice : ");
				int transactionChoice = scanner.nextInt();
				double amount = 0, minimumBalance = 0,reductionAmount=0;

				if (selectedAccount.getProduct() instanceof SavingsMaxAccount) {
					minimumBalance = ((SavingsMaxAccount) selectedAccount.getProduct()).getMinimumBalance();
				}
				else if (selectedAccount.getProduct() instanceof LoanAccount) {
					
					System.out.print("\n1.Cash deposit 2.ChequeDeposit");
					int depositChoice=scanner.nextInt();
					if(depositChoice==1) {
						continue;
					}else if(depositChoice==2) {
						reductionAmount = ((LoanAccount) selectedAccount.getProduct()).getChequeDeposit();
					}
					else {
						System.out.print("\n1Wrong Option");
					}
				}
				

				switch (transactionChoice) {
				case 1:
					// Deposit means Balance = Balance + deposited Money
					System.out.print("\nCurrent Balance is : " + selectedAccount.getBalance());
					System.out.print("\nEnter the amount to be deposited:");
					amount = scanner.nextDouble();
					selectedAccount.setBalance(selectedAccount.getBalance() + amount - amount*reductionAmount);
					System.out.print("New Balance is : " + selectedAccount.getBalance());
					break;
				case 2:
					// Withdraw means Balance = Balance - Withdrawn Money
					System.out.print("\nCurrent Balance is : " + selectedAccount.getBalance());
					System.out.print("\nEnter the amount to be withdrawn:");
					amount = scanner.nextDouble();
					if (amount + minimumBalance > selectedAccount.getBalance()) {
						System.out.print("\nThis account needs a minimum of " + minimumBalance + " Rs");
						break;
					}
					selectedAccount.setBalance(selectedAccount.getBalance() - amount);
					System.out.print("New Balance is : " + selectedAccount.getBalance());
					break;
				case 3:
					displayCustomer(customer,"alldetails");
					break;
				default:
					break;
				}

				System.out.print("\n\nDo you want to transact more (y/n) : ");
				transactionContinueChoice = scanner.next().charAt(0);

			} while (transactionContinueChoice == 'y');

		} else {
			System.out.print("\nThere is no such Customer");
		}
	}
	
	public static void displayCustomer(Customer customer) {
		
		if(customer==null) {
			System.out.println("No customer exist. Sorry");
			return;
		}

		// Show all details of customer
		System.out.print("\n********************Customer-Account Details********************");
		System.out.print("\nCustomerId \tCustomerName \tAccountType");
		for (Account account : customer.getAccountList()) {
			System.out.print("\n" + customer.getCustomerCode());
			System.out.print("\t\t" + customer.getCustomerName());
			System.out.print("\t" + account.getAccountType());
		}
	}

	public static void displayCustomer(Customer customer,String allDetails) {
		
		if(customer==null) {
			System.out.println("No customer exist. Sorry");
			return;
		}

		// Show all details of customer
		System.out.print("\n********************Customer Details********************");
		System.out.print("\nCustomerId \tCustomerName \tAccountType \tBalance");
		for (Account account : customer.getAccountList()) {
			System.out.print("\n" + customer.getCustomerCode());
			System.out.print("\t\t" + customer.getCustomerName());
			System.out.print("\t" + account.getAccountType());
			System.out.print("\t" + account.getBalance());
			System.out.print("\nServices Provided\n");
			for (Service service : account.getProduct().getServiceList()) {
				System.out.print(service.getServiceName() + "\t");
			}
		}
	}
}

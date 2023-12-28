package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Service;

public class ServiceCreator {

	public static ArrayList<Service> createService() {
		Scanner scanner = new Scanner(System.in);

		ArrayList<Service> tempServiceList = new ArrayList<Service>();
		char continueChoice = 'y';

		do {

			System.out.print("\nEnter service code : ");
			String serviceCode = scanner.nextLine();

			System.out.print("\nEnter service name : ");
			String serviceName = scanner.nextLine();

			System.out.print("\nEnter rate : ");
			double rate = scanner.nextDouble();
			scanner.nextLine();
			tempServiceList.add(new Service(serviceCode, serviceName, rate));

			System.out.print("\nDo you want to create more (y/n) : ");

			continueChoice = scanner.next().charAt(0);
			scanner.nextLine();

		} while (continueChoice == 'y');
		
		return tempServiceList;

	}

}

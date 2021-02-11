package com.techelevator;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.view.Menu;

import materials.Candy;
import materials.Chip;
import materials.Drink;
import materials.Funds;
import materials.Gum;
import materials.Vendable;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT };
	// building menu options as array of strings
	private static final String[] PURCHASE_MENU = { "Feed Money", "Select Product", "Finish Transaction", "Back" };
	private static final String[] FEED_MONEY_MENU = { "1", "5", "10", "Back" };

	// also not sure about this list
	private static final List<Vendable> VENDING_MACHINE_ITEMS = new ArrayList<Vendable>();

	Log report = new Log("Log.txt");

	// don't know about this one
	// private static final String[] SELECT_PRODUCT_MENU = ;

	public void inventory() {
		File inventorySourceList = new File("vendingmachine.csv");

		try {
			Scanner stockScanner = new Scanner(inventorySourceList);

			while (stockScanner.hasNextLine()) {
				String lineOfData = stockScanner.nextLine();
				String[] lineOfDataArr = lineOfData.split("\\|");
				String productCode = lineOfDataArr[0];
				String produceName = lineOfDataArr[1];
				BigDecimal price = new BigDecimal(lineOfDataArr[2]);
				if (lineOfDataArr[3].equals("Chip")) {
					Vendable chip = new Chip(productCode, produceName, price);
					VENDING_MACHINE_ITEMS.add(chip);
				}
				if (lineOfDataArr[3].equals("Candy")) {
					Vendable chip = new Candy(productCode, produceName, price);
					VENDING_MACHINE_ITEMS.add(chip);
				}
				if (lineOfDataArr[3].equals("Drink")) {
					Vendable chip = new Drink(productCode, produceName, price);
					VENDING_MACHINE_ITEMS.add(chip);
				}
				if (lineOfDataArr[3].equals("Gum")) {
					Vendable chip = new Gum(productCode, produceName, price);
					VENDING_MACHINE_ITEMS.add(chip);
				}
			}

		} catch (Exception e) {
			System.exit(1);
		}
	}

	private Menu menu;

	private Funds funds = new Funds();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		report.writeFile("User Interaction");
		inventory();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			// added here
			System.out.println("You picked: " + choice);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				for (Vendable item : VENDING_MACHINE_ITEMS) {
					System.out.println(item.getProductCode() + " | " + item.getProductName() + " | $" + item.getPrice()
							+ " | " + item.getQuantity() + " Left");
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				String selection = "";

				while (!selection.equals("Back")) {
					selection = (String) menu.getChoiceFromOptions(PURCHASE_MENU);

					if (selection.equals("Feed Money")) {
						processMoney();
					} else if (selection.equals("Select Product")) {
						for (Vendable item : VENDING_MACHINE_ITEMS) {
							System.out.println(item.getProductCode() + " | " + item.getProductName() + " | $"
									+ item.getPrice() + " | " + item.getQuantity() + " Left");
						}
						Scanner productChoice = new Scanner(System.in);
						System.out.println("");
						System.out.println("Enter Product Code");
						String productString = productChoice.nextLine().toUpperCase();
						if (available(productString)) {
							for (Vendable item : VENDING_MACHINE_ITEMS) {

								if (productString.equals(item.getProductCode())) {
									if (funds.getBalance().compareTo(item.getPrice()) < 0) {
										System.out.println("Please Add More Money :)");
										System.out.println("$" + funds.getBalance() + " Left");
										break;
									}
									if (item.getQuantity() > 0) {
										item.reduceInventory();
										funds.removeMoney(item.getPrice());
										System.out.println("$" + funds.getBalance() + " Left");
										item.makeNoise();
										report.writeFile("User Made Purchase | " + item.getProductName() + " | "
												+ item.getProductCode() + " | $"
												+ (funds.getBalance().add(item.getPrice())
														+ " User Funds Before Purchase | $" + funds.getBalance()
														+ " User Funds After Purchase"));

									} else {
										System.out.println("Selection Is Sold Out, Sorry!");
									}

								}
							}
						} else {
							System.out.println("Choice Not Valid");
						}
					}

					else if (selection.contentEquals("Finish Transaction")) {
						report.writeFile("User Given Change $" + funds.getBalance() + " | Current Balance $0.00");
						getChange();
						funds.removeMoney(funds.getBalance());
						System.out.println("Thank You For Your Purchase :)");
						break;
					}

//					System.out.println("You selected from the 2nd level: " + selection);
				}
			} else if (choice.contentEquals(MAIN_MENU_OPTION_EXIT)) {
				report.writeFile("User Session Ended");
				System.exit(1);
			}

		}

	}

	public void processMoney() {

		String selection = "";
		while (!selection.equals("Back")) {

			selection = (String) menu.getChoiceFromOptions(FEED_MONEY_MENU);

			if (selection.equals("1")) {
				funds.setBalance(new BigDecimal(1));
			}
			if (selection.equals("5")) {
				funds.setBalance(new BigDecimal(5));
			}
			if (selection.equals("10")) {
				funds.setBalance(new BigDecimal(10));
			}
			if (!selection.equals("Back")) {
				report.writeFile(
						"User Fed Money Amount: $" + selection + " | Total User Balance $" + funds.getBalance());
			}
			System.out.println("You have $" + funds.getBalance());

		}

	}

	public boolean available(String inputString) {
		boolean exists = false;
		for (Vendable item : this.VENDING_MACHINE_ITEMS) {
			if (inputString.equals(item.getProductCode())) {
				exists = true;
			}
		}
		return exists;

	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	public void getChange() {
		BigDecimal oneHundred = new BigDecimal("100");
		BigDecimal customerChange = funds.getBalance().multiply(oneHundred);
		int customerChangeInt = customerChange.intValue();
		int quarters = customerChangeInt / 25;
		customerChangeInt = customerChangeInt % 25;
		int dimes = customerChangeInt / 10;
		customerChangeInt = customerChangeInt % 10;
		int nickels = customerChangeInt / 5;
		System.out.println("Your Change Is");
		System.out.println(quarters + " Quarters");
		System.out.println(dimes + " Dimes");
		System.out.println(nickels + " Nickels");

	}

}

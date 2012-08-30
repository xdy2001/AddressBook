package com.mark.addressbook;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Console implements Runnable {
    public static String ab = "ab>";
    private AddressContainer container = new AddressContainer();
    private Scanner scan = new Scanner(System.in);

    @Override
    public void run() {
        while (true) {
            System.out.print(ab);
            String command = null;
            command = scan.nextLine();

            if ("!quit".equals(command)) {
                try {
                    container.export();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            } else if ("!help".equals(command)) {
                printMsg();
            } else if ("add".equals(command)) {
                System.out.print("Name: ");
                String name = scan.nextLine();
                String mobile = null;
                while (true) {
                    System.out.print("Mobile: ");
                    mobile = scan.nextLine();
                    if (mobile.matches("^\\d{11}$")) {
                        break;
                    } else {
                        System.out.println("Mobile should be 11 digit.");
                    }
                }
                System.out.print("Address: ");
                String address = scan.nextLine();
                container.add(name, mobile, address);
            } else if ("search".equals(command)) {
                while (true) {
                    System.out.print("by (name|mobile|address): ");
                    String searchType = scan.nextLine();
                    if ("name".equals(searchType)) {
                        System.out.print("Name: ");
                        String pattern = scan.nextLine();
                        container.searchByName(pattern);
                        break;
                    } else if ("mobile".equals(searchType)) {
                        System.out.print("Mobile: ");
                        String pattern = scan.nextLine();
                        container.searchByMobile(pattern);
                        break;
                    } else if ("address".equals(searchType)) {
                        System.out.print("Address: ");
                        String pattern = scan.nextLine();
                        container.searchByAddress(pattern);
                        break;
                    } else {
                        System.out.println("Error command.");
                    }
                }
            } else if ("delete".equals(command)) {
                while (true) {
                    System.out.print("by (name|mobile|address): ");
                    String searchType = scan.nextLine();
                    if ("name".equals(searchType)) {
                        System.out.print("Name: ");
                        String pattern = scan.nextLine();
                        int count = container.deleteByName(pattern);
                        System.out.println(count + " address entries deleted");
                        break;
                    } else if ("mobile".equals(searchType)) {
                        System.out.print("Mobile: ");
                        String pattern = scan.nextLine();
                        int count = container.deleteByMobile(pattern);
                        System.out.println(count + " address entries deleted");
                        break;
                    } else if ("address".equals(searchType)) {
                        System.out.print("Address: ");
                        String pattern = scan.nextLine();
                        int count = container.deleteByAddress(pattern);
                        System.out.println(count + " address entries deleted");
                        break;
                    } else {
                        System.out.println("Error Command.");
                    }
                }
            } else {
                System.out
                        .println("Error Command, Please use !help to show help.");
            }
        }
    }

    private void printMsg() {
        System.out.println("Commands:");
        System.out.println("add|search|delete|!help|!quit");
        System.out.println("add:	add address entry into Address.xml");
        System.out
                .println("search:	get one or more address entries by conditions");
        System.out.println("delete:	delete address entries by conditions");
        System.out.println("!help:	show help");
        System.out.println("!quit:	exit");
    }
}

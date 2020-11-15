package com.tataru;


import java.util.Scanner;



public class Main {

    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        Bank bank = new Bank("Transilvania");

        boolean quit = false;
        System.out.println("Welcome to Transilvania bank!");
        while (!quit) {
            printMenu();
            System.out.print("Enter your option: ");
            int action = scanner.nextInt();
            scanner.nextLine();
            System.out.println("");
            switch (action) {
                case 1:
                    //login

                    System.out.println("You chose to log in!");
                    System.out.print("Please enter your account ID: ");
                    String accountId = scanner.nextLine();
                    System.out.print("Please enter your pin number: ");
                    String pinInserted = scanner.nextLine();
                    User newUser = bank.userLogin(accountId, pinInserted);
                    if(newUser != null){
                        System.out.println("Log in successful!");
                        System.out.println();
                        boolean quit1 = false;
                        while(!quit1){
                            printSecondMenu();
                            System.out.print("Enter your option: ");
                            int action1 = scanner.nextInt();
                            scanner.nextLine();
                            switch (action1){
                                case 1:
                                    // print user accounts
                                    bank.printAccounts(newUser);
                                    System.out.println();
                                    break;
                                case 2:
                                    // deposite money
                                    System.out.print("Enter the amount you want to deposit: ");
                                    double amount = scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.print("Select the account where you want to deposit. ");
                                    bank.printAccounts(newUser);
                                    System.out.print("Enter account number: ");
                                    int position = scanner.nextInt();
                                    scanner.nextLine();
                                    if(bank.depositTransaction(newUser, position, amount)){
                                        System.out.println("Deposit successful!\n");
                                    } else {
                                        System.out.println("Operation failed!\n");
                                    }

                                    break;
                                case 3:
                                    // withdraw money
                                    System.out.print("Enter the amount you want to withdraw: ");
                                    double withdrawAmount = scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.print("Select the account from where you want to withdraw. ");
                                    bank.printAccounts(newUser);
                                    System.out.print("Enter account number: ");
                                    position = scanner.nextInt();
                                    scanner.nextLine();
                                    if(bank.withdrawTransaction(newUser, position, withdrawAmount)){
                                        System.out.println("Withdraw successful!");
                                        System.out.println("Don't forget to pick up your money!");
                                    } else {
                                        System.out.println("Operation failed!");
                                    }


                                    break;
                                case 4:
                                    // show a list of transasctions
                                    System.out.println("Transactions history:");
                                    bank.showTransactionsHistory(newUser);

                                    // to-do: show transactions for all accounts -> DONE

                                    break;
                                case 5:
                                    // create a new account
                                    System.out.print("Enter the name of the new account: ");
                                    String newAccountName = scanner.nextLine();
                                    bank.addNewAccount(newAccountName, newUser);
                                    break;
                                case 6:
                                    System.out.println("Going back to main menu.");
                                    quit1 = true;
                                    break;

                            }


                        }

                    } else {
                        System.out.println("Data not correct!\n");
                    }

                    break;
                case 2:
                    //create new account
                    System.out.println("You chose to create a new account!");
                    System.out.print("Please enter your first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Please enter your last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Please enter your pin code: ");
                    String pin =  scanner.nextLine();
                    User acc = bank.addUser(firstName, lastName, pin);
                    System.out.println("Account created successfully!");
                    System.out.println("Your account ID is: " + acc.getUserID());
                    break;
                case 3:
                    //exit
                    quit = true;
                    System.out.println("Application is closing...");
                    System.out.println("Good bye!");
                    break;
            }

        }

    }

    private static void printMenu(){
        System.out.println("-----------------------------------");
        System.out.println("Options: ");
        System.out.println("1 -> Log in\n" +
                "2 -> Register\n" +
                "3 -> Quit");
        System.out.println("-----------------------------------");
    }

    private static void printSecondMenu(){
        System.out.println("-----------------------------------");
        System.out.println("Options: ");
        System.out.println("1 -> Available accounts\n" +
                            "2 -> Deposit money\n" +
                            "3 -> Withdraw money\n" +
                            "4 -> Show transaction history\n" +
                            "5 -> Create a new account\n" +
                            "6 -> Main menu");
        System.out.println("-----------------------------------");
    }

}

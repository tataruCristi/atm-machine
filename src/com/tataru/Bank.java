package com.tataru;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private ArrayList<User> user;
    private ArrayList<Account> accounts;

    public Bank(String name) {
        this.name = name;
        this.user = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public User addUser(String firstName, String lastName, String pin){
        String userId = getNewUserID();

        User newUser = new User(firstName, lastName, pin, userId);
        this.user.add(newUser);

        //saving account will be available by default
        Account newAccount = new Account("Savings", newUser);
        newUser.AddAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;
    }

    public void addNewAccount(String name, User holder){
        Account account = new Account(name, holder);
        this.accounts.add(account);
        holder.AddAccount(account);
    }

    public String getUserId(User user){
        return user.getUserID();
    }

    public User userLogin(String userId, String pin){
        for(User u : this.user){
            if(u.getUserID().equals(userId) && u.validatePin(pin)){
                return u;
            }
        }
        return null;
    }

    public void printAccounts(User user){
        System.out.println("Available accounts: ");
        for(int i =0; i<user.getAccounts().size(); i++){
            System.out.println((i+1) + ". " + user.getAccounts().get(i).getName());
            System.out.println("Balance: " + user.getAccounts().get(i).getBalance());
        }

    }

    private String getNewUserID(){
        String id;
        Random rand = new Random();
        int len = 6;
        boolean unique = true;

        do{
            id = "";
            // generate the 6 numbers for id
            for(int i=0; i<len;i++){
                id += ((Integer)rand.nextInt(10)).toString();
            }

            // check to make sure id is unique
            for(User u : this.user) {
                if(id.equals(u.getUserID())){
                    unique = false;
                    break;
                }
            }
        } while (!unique);
        return id;
    }


    public boolean depositTransaction(User user, int accountNum,double amount){
        Account account = user.getAccounts().get(accountNum-1);
        if(accountNum <= user.getAccounts().size()){
            account.depositMoney(account, amount);
            return true;
        }
        return false;
    }

    public boolean withdrawTransaction(User user, int accountNum, double amount) {
        Account account = user.getAccounts().get(accountNum-1);
        if(accountNum <= user.getAccounts().size()){
            account.withdrawMoney(account, amount);
            return true;
        }
        return false;
    }


    public void showTransactionsHistory(User user){

        for (Account acc : user.getAccounts()) {
            System.out.println();
            System.out.println(acc.getName() + ": ");
            ArrayList<Transaction> transaction = acc.getTransactions();
            for(int i=0; i<transaction.size();i++){
                System.out.println("\tTransaction amount: " + transaction.get(i).getAmount());
                System.out.println("\tTransaction type: " + transaction.get(i).getType());
                System.out.println("\tDate: " + transaction.get(i).getTimestamp());
            }
        }
    }



}



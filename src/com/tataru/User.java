package com.tataru;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String userID;
    private byte pinHash[];

    ArrayList<Account> accounts;


    public void AddAccount(Account account) {
        accounts.add(account);
    }

    public String getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public User(String firstName, String lastName, String pinNumber, String userID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = new ArrayList<>();
        this.userID = userID;

        // using MD5 hash to store the password
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pinNumber.getBytes());
            }
        catch (NoSuchAlgorithmException e){
            System.err.println("err, cought NoSuchExceptionAlgorithm");
            e.printStackTrace();
            System.exit(1);
        }

    }

// method will digest the inserted pin and compare it to the hash value
    public boolean validatePin(String aPin){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e){
            System.err.println("err, cought NoSuchExceptionAlgorithm");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

}

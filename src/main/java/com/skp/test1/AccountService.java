package com.skp.test1;

public interface AccountService {
    void deposit(int amount) throws Exception;
    void withdraw(int amount);
    void printStatement();
}

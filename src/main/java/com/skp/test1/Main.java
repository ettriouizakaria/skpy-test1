package com.skp.test1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Account account = new Account(){
            private final Queue<String> dates = new LinkedList<>(
                    Arrays.asList("10-01-2012", "13-01-2012", "14-01-2012")
            );

            @Override
            protected String getDate(){
                return dates.poll();
            }
        };

        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);
        account.printStatement();
    }
}
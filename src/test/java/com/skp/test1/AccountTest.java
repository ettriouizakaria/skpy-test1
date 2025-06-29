package com.skp.test1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class AccountTest {
    private Account account;

    @Before
    public void setUp(){
        account = new Account(){
            private final Queue<String> dates = new LinkedList<>(
                    Arrays.asList("10-01-2012", "13-01-2012", "14-01-2012")
            );

            @Override
            protected String getDate(){
                return dates.poll();
            }
        };
    }

    @Test
    public void depositTest(){
        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        account.printStatement();

        String expected = "DATE       | AMOUNT | BALANCE\n" +
                        "14-01-2012 | -500 | 2500\n" +
                        "13-01-2012 | 2000 | 3000\n" +
                        "10-01-2012 | 1000 | 1000\n";

        Assert.assertTrue(outContent.toString().contains("14-01-2012 | -500 | 2500"));
        Assert.assertTrue(outContent.toString().contains("13-01-2012 | 2000 | 3000"));
        Assert.assertTrue(outContent.toString().contains("10-01-2012 | 1000 | 1000"));
    }

    @Test
    public void depositTestWithNegativeAmount(){
        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-300);
        });
        Assert.assertEquals("Invalid amount, it must be positive!", exception.getMessage());
    }

    @Test
    public void withdrawTestWithNegativeAmountAndThrowsException(){
        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-500);
        });
        Assert.assertEquals("Invalid amount, it must be positive!", exception.getMessage());
    }

    @Test
    public void withdrawTestWithInsufficientBalanceAndThrowsException(){
        account.deposit(500);

        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () ->{
           account.withdraw(1050);
        });
        Assert.assertEquals("Cannot withdraw this amount, insufficient balance!", exception.getMessage());
    }

    @Test
    public void printStatementTestHeader(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStream));

        account.printStatement();

        Assert.assertTrue(outputStream.toString().trim().replaceAll("\\s+", "").contains("DATE|AMOUNT|BALANCE"));
    }

}
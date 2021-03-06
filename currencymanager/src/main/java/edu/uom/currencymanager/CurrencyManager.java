package edu.uom.currencymanager;

import edu.uom.currencymanager.currencies.*;
import edu.uom.currencymanager.currencyserver.CurrencyServer;
import edu.uom.currencymanager.time.IUtilTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrencyManager {

    private ICurrencyDatabase currencyDatabase;
    private CurrencyOperations currencyOperations;

    public CurrencyManager(ICurrencyDatabase currencyDatabase) throws Exception {
        this.currencyDatabase = currencyDatabase;
        this.currencyOperations = new CurrencyOperations(currencyDatabase);
    }

    public CurrencyManager(ICurrencyDatabase currencyDatabase, CurrencyServer currencyServer, IUtilTime utilTime) throws Exception {
        this.currencyDatabase = currencyDatabase;
        this.currencyOperations = new CurrencyOperations(currencyDatabase, currencyServer, utilTime);
    }

    public static void main(String[] args) throws Exception {

        ICurrencyDatabase _currencyDatabase = DatabaseFactory.GetCurrencyDatabase();
        CurrencyManager manager = new CurrencyManager(_currencyDatabase);

        Scanner sc = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("\nMain Menu\n---------\n");

            System.out.println("1. List currencies");
            System.out.println("2. List exchange rates between major currencies");
            System.out.println("3. Check exchange rate");
            System.out.println("4. Add currency");
            System.out.println("5. Delete currency");
            System.out.println("0. Quit");

            System.out.print("\nEnter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    List<Currency> currencies = manager.currencyDatabase.getCurrencies();
                    System.out.println("\nAvailable Currencies\n--------------------");
                    for (Currency currency : currencies) {
                        System.out.println(currency.toString());
                    }
                    break;
                case 2:
                    List<ExchangeRate> exchangeRates = manager.getMajorCurrencyRates();
                    System.out.println("\nMajor Currency Exchange Rates\n-----------------------------");
                    for (ExchangeRate rate : exchangeRates) {
                        System.out.println(rate.toString());
                    }
                    break;
                case 3:
                    System.out.print("\nEnter source currency code (e.g. EUR): ");
                    String src = sc.next().toUpperCase();
                    System.out.print("\nEnter destination currency code (e.g. GBP): ");
                    String dst = sc.next().toUpperCase();
                    try {
                        ExchangeRate rate = manager.getExchangeRate(src, dst);
                        System.out.println(rate.toString());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                case 4:
                    System.out.print("\nEnter the currency code: ");
                    String code = sc.next().toUpperCase();
                    System.out.print("Enter currency name: ");
                    String name = sc.next();
                    name += sc.nextLine();

                    String major = "\n";
                    while (!(major.equalsIgnoreCase("y") || major.equalsIgnoreCase("n"))) {
                        System.out.println("Is this a major currency? [y/n]");
                        major = sc.next();
                    }

                    try {
                        manager.addCurrency(code, name, major.equalsIgnoreCase("y"));
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("\nEnter the currency code: ");
                    code = sc.next().toUpperCase();
                    try {
                        manager.deleteCurrencyWithCode(code);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;

            }

            Thread.sleep(1000);
        }
    }

    public List<ExchangeRate> getMajorCurrencyRates() throws Exception {

        List<ExchangeRate> exchangeRates = new ArrayList<ExchangeRate>();

        List<Currency> currencies = currencyOperations.getMajorCurrencies();

        for (Currency src : currencies) {
            for (Currency dst : currencies) {
                if (src != dst) {
                    exchangeRates.add(currencyOperations.getExchangeRate(src.code, dst.code));
                }
            }
        }
        return exchangeRates;
    }

    public ExchangeRate getExchangeRate(String sourceCurrency, String destinationCurrency) throws Exception {
        return currencyOperations.getExchangeRate(sourceCurrency, destinationCurrency);
    }

    public void addCurrency(String code, String name, boolean major) throws Exception {

        //Check format of code
        if (code.length() != 3) {
            throw new Exception("A currency code should have 3 characters.");
        }

        //Check minimum length of name
        if (name.length() < 4) {
            throw new Exception("A currency's name should be at least 4 characters long.");
        }

        //Check if currency already exists
        if (CurrencyOperations.currencyExists(currencyDatabase.getCurrencies(),code)) {
            throw new Exception("The currency " + code + " already exists.");
        }

        //Add currency to database
        currencyDatabase.addCurrency(new Currency(code,name,major));

    }

    public void deleteCurrencyWithCode(String code) throws Exception {

        if (!CurrencyOperations.currencyExists(currencyDatabase.getCurrencies(),code)) {
            throw new Exception("Currency does not exist: " + code);
        }

        currencyDatabase.deleteCurrency(code);

    }

}

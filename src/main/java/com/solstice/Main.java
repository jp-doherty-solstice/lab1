package com.solstice;

import com.solstice.tables.QuotesManager;
import javax.json.JsonArray;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws IOException, ParseException, SQLException {

//        URL url = new URL("https://bootcamp-training-files.cfapps.io/week1/week1-stocks.json");
//        JsonArray jsonArray = JsonManager.getJsonArrayFromUrl( url );
//        DBUtil.populateDBFromJsonArray( jsonArray );

        while (true) {
            String symbol = getSymbolFromUser();
            String timeString = getTimeStringFromUser();
            QuotesManager.queryDailyStockData(symbol, timeString);
        }

    }


    private static String getSymbolFromUser() {
        System.out.println("Enter the stock symbol: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    private static String getTimeStringFromUser() {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter year in the form yyyy: ");

        stringBuilder.append(scanner.nextLine());
        stringBuilder.append("-");

        System.out.println("Enter month in the form mm: ");

        stringBuilder.append(scanner.nextLine());
        stringBuilder.append("-");

        System.out.println("Enter day in the form dd: ");

        stringBuilder.append(scanner.nextLine());
        stringBuilder.append("T00:00:00");

        return stringBuilder.toString();
    }


}

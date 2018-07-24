package com.solstice.tables;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.solstice.DBType;
import com.solstice.DBUtil;
import com.solstice.TimeUtil;
import com.solstice.beans.Quote;


public class QuotesManager {

    public static boolean insert(Quote bean) throws SQLException, ParseException {
        String sql = "INSERT into quotes (symbol, price, volume, timeTraded) values (?, ?, ?, ?)";
        ResultSet keys = null;
        try (
                Connection connection = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ) {
            statement.setString(1, bean.getSymbol());
            statement.setDouble(2, bean.getPrice());
            statement.setInt(3, bean.getVolume());
            statement.setTimestamp(4, bean.getTimestamp());
            int affected = statement.executeUpdate();
            if (affected == 1) {
                keys = statement.getGeneratedKeys();
                keys.next();
            } else {
                System.err.println("No rows affected");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            if (keys != null) {
                keys.close();
            }
        }
        return true;
    }


    public static void queryDailyStockData(String symbol, String dateString) throws SQLException, ParseException {
        String SQL = "SELECT max(price) as maxPrice, min(price) as minPrice, sum(volume) as totalVolume " +
                "FROM quotes WHERE symbol = ? and date(timeTraded) = date(?)";
        ResultSet resultSet;
        try (
                Connection connection = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement statement = connection.prepareStatement(
                        SQL,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                ) {
            statement.setString(1, symbol);
            Timestamp timestamp = TimeUtil.getTimestampFromDateString(dateString);
            statement.setTimestamp(2, timestamp);
            resultSet = statement.executeQuery();
            displayStockData(symbol, dateString, resultSet);
        }
    }


    private static void displayStockData(String symbol, String dateString, ResultSet resultSet) throws SQLException {
        resultSet.beforeFirst();
        while (resultSet.next()) {
            Double maxPrice = resultSet.getDouble("maxPrice");
            Double minPrice = resultSet.getDouble("minPrice");
            int totalVolume = resultSet.getInt("totalVolume");
            System.out.println();
            System.out.println("DAILY STOCK DATA FOR " + symbol + " ON " + dateString.substring(0, 10));
            System.out.println("Max price: " + maxPrice);
            System.out.println("Min price: " + minPrice);
            System.out.println("Total volume: " + totalVolume);
            System.out.println();
            System.out.println();
        }
    }


}

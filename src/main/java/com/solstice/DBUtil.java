package com.solstice;

import com.solstice.DBType;
import com.solstice.beans.Quote;
import com.solstice.tables.QuotesManager;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBUtil {

    private static final String USERNAME = "dbuser";
    private static final String PASSWORD = "dbpassword";
    private static final String MYSQL_CONNECTION_STRING = "jdbc:mysql://localhost/quotes";

    public static Connection getConnection(DBType dbType) throws SQLException {
        switch (dbType) {
            case MYSQL:
                return DriverManager.getConnection(MYSQL_CONNECTION_STRING, USERNAME, PASSWORD);
            default:
                return null;
        }
    }

    public static void populateDBFromJsonArray(JsonArray jsonArray) throws ParseException, SQLException {

        for (int i = 0; i < jsonArray.size(); i++) {

            JsonObject obj = jsonArray.getJsonObject(i);

            Quote bean = new Quote();

            bean.setSymbol(obj.getString("symbol"));
            bean.setPrice(obj.getJsonNumber("price").doubleValue());
            bean.setVolume(obj.getInt("volume"));

            String dateString = obj.getString("date");
            Timestamp timestamp = TimeUtil.getTimestampFromDateString(dateString);
            bean.setTimestamp(timestamp);

            boolean result = QuotesManager.insert(bean);

            if (result) {
                System.out.println("New row with primary key");
            }

        }

    }



}

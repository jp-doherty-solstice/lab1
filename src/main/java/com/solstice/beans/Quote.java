package com.solstice.beans;

import java.sql.Timestamp;
import java.util.Date;

public class Quote {

    private String symbol;
    private Double price;
    private int volume;
    private Timestamp timestamp;

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Timestamp date) {
        this.timestamp = date;
    }

}

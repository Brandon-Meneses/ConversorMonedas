package org.example;

import java.time.LocalDateTime;

class ConversionRecord {
    private double amount;
    private String fromCurrency;
    private String toCurrency;
    private double convertedAmount;
    private java.time.LocalDateTime timestamp;

    public ConversionRecord(double amount, String fromCurrency, String toCurrency, double convertedAmount) {
        this.amount = amount;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.convertedAmount = convertedAmount;
        this.timestamp = java.time.LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("%.2f %s = %.2f %s (Realizado el %s)", amount, fromCurrency, convertedAmount, toCurrency, timestamp);
    }
}

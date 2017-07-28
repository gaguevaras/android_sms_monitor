package com.platanolabs.expensetracker;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gustavoguevara on 7/27/17.
 */

class Expense {
    private String type;
    private String amount;
    private String vendor;
    private String reportedTime;
    private Date receivedTime;
    private String meanOfPayment;

    public Expense(String message) {
        Pattern p = Pattern.compile("^Bancolombia le informa (\\w+) por \\$(.*) en (.*) (\\d+:\\d+). (\\d+\\/\\d+\\/\\d+) (.*) Inquietudes al");
        Matcher matcher = p.matcher(message);
        if (matcher.find()) {
            this.type = matcher.group(1);
            this.amount = matcher.group(2);
            this.vendor = matcher.group(3);
            this.reportedTime = matcher.group(4) + " " + matcher.group(5);
            this.receivedTime = new Date();
            this.meanOfPayment = matcher.group(6);
        }
    }

    public Expense() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getReportedTime() {
        return reportedTime;
    }

    public void setReportedTime(String reportedTime) {
        this.reportedTime = reportedTime;
    }

    public Date getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getMeanOfPayment() {
        return meanOfPayment;
    }

    public void setMeanOfPayment(String meanOfPayment) {
        this.meanOfPayment = meanOfPayment;
    }
}

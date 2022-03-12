package com.congla.codehqtest.testproject.data.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {
    public static String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public static Date fromStringToDate(String dateString) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
}

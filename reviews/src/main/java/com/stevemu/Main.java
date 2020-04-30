package com.stevemu;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            CSVPrinter printer = new CSVPrinter(System.out, CSVFormat.EXCEL);
            printer.printRecord("Craig", "Dennis", 5, "Loved it!");
            printer.printRecord("Chris", "Rammaciotti", 4, "Pretty good, hi!!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

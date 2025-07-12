package com.horstmann.places;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CSVDemo {
    public static void main(String[] args) throws IOException {
        Reader in = new FileReader("countries.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.builder().setDelimiter(';').setHeader().build().parse(in);
        for (CSVRecord record : records) {
            String name = record.get("Name");
            double area = Double.parseDouble(record.get("Area"));
            System.out.println(name + " has area " + area);
        }
    }
}

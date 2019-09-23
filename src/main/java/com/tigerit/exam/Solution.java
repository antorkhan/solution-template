package com.tigerit.exam;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static com.tigerit.exam.IO.*;

/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
public class Solution implements Runnable {
    static private final String INPUT = "C:\\sites\\solution-template\\src\\main\\java\\com\\tigerit\\exam\\input.txt";
    static private final String OUTPUT = "C:\\sites\\solution-template\\src\\main\\java\\com\\tigerit\\exam\\output.txt";
    @Override
    public void run() {
        FileInputStream instream = null;
        PrintStream outstream = null;

        try {
            instream = new FileInputStream(INPUT);
            outstream = new PrintStream(new FileOutputStream(OUTPUT));
            System.setIn(instream);
            System.setOut(outstream);
        } catch (Exception e) {
            System.err.println("Error Occurred.");
        }

        // your application entry point

        // sample input process
        int T = readLineAsInteger();
        for (int i = 0; i < T; i++) {
            int nT = readLineAsInteger();
            for (int j = 0; j < nT; j++) {
                String table_name = readLine();
                String row_and_col_raw = readLine();
                String row_and_col[] = row_and_col_raw.split(" ");

                int num_of_row = Integer.parseInt(row_and_col[0]);
                int num_col_col = Integer.parseInt(row_and_col[1]);
                String table_names = readLine();
//                  Column name input
                for (int k = 0; k < num_of_row; k++) {
                    String row = readLine();
//                    A string containing integers which needs to be parsed.
                }

            }
            int number_of_query = readLineAsInteger();
            for (int noq = 0; noq < number_of_query; noq++){
                String query = readLine();
                System.out.println("Test: 1");
            }
        }
//
//         sample output process
//        printLine(string);
//        printLine(integer);
    }
}

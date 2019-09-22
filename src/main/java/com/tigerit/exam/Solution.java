package com.tigerit.exam;


import static com.tigerit.exam.IO.*;

/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
public class Solution implements Runnable {
    @Override
    public void run() {
        // your application entry point

        // sample input process
        int T = readLineAsInteger();

        for (int i = 0; i < T; i++) {
            int nT = readLineAsInteger();
            for (int j = 0; j < nT; j++) {
                String table_name = readLine();
                int num_of_row = readLineAsInteger();
                int num_col_col = readLineAsInteger();
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

        // sample output process
//        printLine(string);
//        printLine(integer);
    }
}

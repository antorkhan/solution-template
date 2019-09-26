package com.tigerit.exam;

import java.util.*;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tigerit.exam.IO.*;

/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application"s execution points start from inside run method.
 */
public class Solution implements Runnable {
    static private final String INPUT = "C:\\sites\\solution-template\\src\\main\\java\\com\\tigerit\\exam\\input.txt";
    static private final String OUTPUT = "C:\\sites\\solution-template\\src\\main\\java\\com\\tigerit\\exam\\output.txt";
    private HashMap<String, HashMap<String, ArrayList<Integer>>> db = new HashMap<>();


    private String[] field_selector(String selectInput) {
        selectInput = selectInput.trim();
        String fields[] = {"table_a.id_a", "table_b.id_b"};
        if (selectInput.charAt(0) == '*') {

        } else {
//            fields = selectInput.split(",");
        }
        return fields;
    }

    private void join_tables(String table_1, String column_1, String table_2, String column_2, String SelectedColumns[], int query_no) {
        if (query_no != 1)
            System.out.println("");


        for (String col : SelectedColumns) {
            col = col.split("\\.")[1];
            System.out.print(col + " ");
        }

        for (int i = 0; i < db.get(table_1).get(column_1).size(); i++) {
            for (int j = 0; j < db.get(table_2).get(column_2).size(); j++) {
                if (db.get(table_1).get(column_1).get(i).equals(db.get(table_2).get(column_2).get(j))) {
                    String output = "\n";
                    for (String col : SelectedColumns) {

//                        System.out.println("str -> "+ col.split("\\.")[0]);
                        output += db.get(col.split("\\.")[0]).get(col.split("\\.")[1]).get(i) + " ";
                    }

                    System.out.print(output);
                }
            }
        }
        System.out.println(" ");
    }

    @Override
    public void run() {
//        String query_string = "SELECT MY BIG HEAD FROM `shipping_addresses`";
        Pattern selectPattern = Pattern.compile("^SELECT (.*?) FROM");
        Pattern firstTablePattern = Pattern.compile("FROM (.*?) JOIN");
        Pattern secondTablePattern = Pattern.compile("JOIN (.*?) ON");
        Pattern firstKeyPattern = Pattern.compile(" ON .*\\.(.*?) ");
        Pattern secondKeyPattern = Pattern.compile(" = .*\\.(.*?)$");
        String firstTableName = null;
        String secondTableName = null;
        String firstKey = null;
        String secondKey = null;

//        if (matcher.find())
//        {
//            System.out.println(matcher.group(1));
//        }
//        System.out.println(test);

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
            db.clear();
            if (i != 0)
                System.out.println("");
            System.out.println("Test: " + (i + 1));


            HashMap<Integer, String> tableNameMaps = new HashMap<>();


//          System.out.println(db.get("table1").get("id_a").get(0));
//
            int nT = readLineAsInteger();
            for (int j = 0; j < nT; j++) {
                String table_name = readLine();
                String row_and_col_raw = readLine();
                String row_and_col[] = row_and_col_raw.split(" ");

                int num_of_row = Integer.parseInt(row_and_col[0]);
                int num_of_col = Integer.parseInt(row_and_col[1]);

//              Take Col Name Inputs and iterate over them to create structure Ex: id_a a1 a2
                String col_names_raw = readLine();
                String col_names[] = col_names_raw.split(" ");


                int key = 0;
                HashMap<String, ArrayList<Integer>> table_col = new HashMap<>();
                for (String col_name : col_names) {

//                    System.out.println(col_name);
                    table_col.put(col_name, new ArrayList<>()); //Empty Column
                    db.put(table_name, table_col);
                    tableNameMaps.put(key, col_name);
                    key++;
//                    System.out.println(db);
                }


//               Row Input Ex: 1 2 3
                for (int k = 0; k < num_of_row; k++) {
                    String row_input_raw = readLine();
                    String row_input[] = row_input_raw.split(" ");
                    for (int l = 0; l < num_of_col; l++) {
                        db.get(table_name).get(tableNameMaps.get(l)).add(Integer.parseInt(row_input[l]));
                    }
                }

            }
//            System.out.println(db.get("table_a").get("id_a"));
            int number_of_query = readLineAsInteger();
//            System.out.println(number_of_query);
            for (int noq = 0; noq < number_of_query; noq++) {
                String query = readLine();
                Matcher selectMatcher = selectPattern.matcher(query);
                Matcher firstTableMatcher = firstTablePattern.matcher(query);
                Matcher secondTableMatcher = secondTablePattern.matcher(query);
                Matcher firstKeyMatcher = firstKeyPattern.matcher(query);
                Matcher secondKeyMatcher = secondKeyPattern.matcher(query);
                if (selectMatcher.find() && firstTableMatcher.find() && secondTableMatcher.find() && firstKeyMatcher.find() && secondKeyMatcher.find()) {
                    firstTableName = firstTableMatcher.group(1);
                    secondTableName = secondTableMatcher.group(1);
                    firstKey = firstKeyMatcher.group(1);
                    secondKey = secondKeyMatcher.group(1);
//                    System.out.println("First -> "+firstTableName);


//                    System.out.println("Selected Field -> "+selectMatcher.group(1));  // Column Selector
                    String[] selected_columns_table_one = {"id_a", "a1", "a2"};
                    String[] selected_columns_table_two = {"id_b", "b1", "b2"};
                    String[] selectedColumns = field_selector(selectMatcher.group(1));
//                    System.out.println(query);
                    join_tables("table_a",
                            firstKey,
                            "table_b",
                            secondKey,
                            selectedColumns,
                            noq + 1);
//

                }


            }

        }
    }
}

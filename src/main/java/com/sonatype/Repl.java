package com.sonatype;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Created by Arash on 2019-03-26.
 */
public class Repl {
    public static void main(String[] args) {
        NumberSpeller numberSpeller = new NumberSpeller();


        PrintStream out = System.out;
        out.println("enter a number in digits. Ctrl+C to exit");
        out.print("numberspeller>");
        try( BufferedReader inputReader = new BufferedReader(
                new InputStreamReader(System.in)
        )) {
        while(true) {

                String input = inputReader.readLine();
                try{
                    long number = Long.valueOf(input);
                    out.println(numberSpeller.spell(number));
                } catch (NumberFormatException e){
                    out.println("Input is not a number");
                }

                out.print("numberspeller>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.sonatype;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * Simple class to spell a long number expressed in digits. It adds an end before the tends.
 * For example 111 is spelled One hundred and eleven.
 * Class is thread safe, carries no state.
 * Created by Arash on 2019-03-22.
 */

public class NumberSpeller {

    public String spell(long number) {

        if(number == 0) {
            return "Zero";
        }

        List<Integer> sections = calcSections(number);
        StringBuilder accumulator = new StringBuilder();
        for (int i = sections.size() -1 ; i >= 0 ; i--) {
            spellParts(sections.get(i), i, accumulator);
        }
        return capitalizeFirstLetter(accumulator.toString());
    }

    private void spellParts(int number, int section, StringBuilder accumulator) {
        if (number == 0 ) return;
        readHundreds(number, section == 0, accumulator);
        if(section == 0 ) return;;
        accumulator.append(' ').append(SpellingConstants.sections[section])
                .append( " ");

    }


    // access relaxed for testing
    private void readHundreds(int threeDigitNumber, boolean addAnd, StringBuilder accumulator){
        int hundreds= threeDigitNumber/100;
        if(hundreds > 0 ){
            spellDouble(hundreds, accumulator);
            accumulator.append( " hundred ");
        }
        if( addAnd && !accumulator.toString().isEmpty()) {
            accumulator.append("and ");
        }
        spellDouble(threeDigitNumber % 100, accumulator);

    }

    private void spellSingles(int singleDigit, StringBuilder accumulator){
        accumulator.append(SpellingConstants.underTwenty[singleDigit]);
    }

    private void spellDouble(int doubleDigit, StringBuilder accumulator){

        if(doubleDigit < 20){
            accumulator.append(SpellingConstants.underTwenty[doubleDigit]);
        } else {
            accumulator.append(SpellingConstants.tens[doubleDigit /10])
                    .append(' ');
            spellSingles(doubleDigit%10, accumulator);

        }
    }

    private String capitalizeFirstLetter(String result) {
        return Character.toUpperCase(result.charAt(0)) + result.substring(1);
    }

    LinkedList<Integer> calcSections(long number) {
        LinkedList<Integer> sections = new LinkedList<>();
        while( number > 999) {
            long section = number % 1000;
            number /=  1000;
            sections.addLast((int) section);
        }
        sections.addLast((int) number);
        return sections;
    }

    int digitCount(long number){
        long remainder = number;
        int digits = 0;
        while ( remainder > 0) {
            digits++;
            remainder = remainder /10;
        }
        return digits;
    }
}

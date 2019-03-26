package com.sonatype;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * Simple class to spell a long number expressed in digits. It adds an end before the tends.
 * For example 111 is spelled One hundred and eleven.
 * Class is thread safe, carries no state.
 *
 * @ImpleNote We use a {@link java.lang.StringBuilder} to put together the result. This if for efficiency reasons to save us from creating many strings,
 *            but it come with a complexity in code. Depending on the performance requirement, it might be better to use simple strings,
 *            in a future refactoring. This will make dealing with whitespaces and 'and' simpler
 *
 * Created by Arash on 2019-03-22.
 */

public class NumberSpeller {

    public String spell(long number) {

        if(number == 0) {
            return "Zero";
        }
        //cannot handle MIN_VALUE directly as we cannot change it to a positive number(MIN_VALUE * -1 == MIN_VALUE)
        if(number == Long.MIN_VALUE) {
            return "Negative nine quintillion two hundred twenty three quadrillion three hundred seventy two trillion thirty six billion eight hundred fifty four million seven hundred seventy five thousand eight hundred and eight";
        }
        StringBuilder accumulator = new StringBuilder();
        boolean isMinus = false;
        if( number < 0  ){
            isMinus = true;
            number = -1 * number;
        }

        List<Integer> sections = calcSections(number);

        for (int i = sections.size() -1 ; i >= 0 ; i--) {
            spellParts(sections.get(i), i, accumulator);
        }
        if (isMinus) {
            accumulator.insert(0, "negative ");
        }
        return capitalizeFirstLetter(accumulator.toString().trim());
    }

    private void spellParts(int number, int section, StringBuilder accumulator) {
        if (number == 0 ) return;
        readHundreds(number, section == 0, accumulator);
        if(section == 0 ) return;
        accumulator.append(' ').append(SpellingConstants.sections[section])
                .append( ' ');

    }


    // access relaxed for testing
    private void readHundreds(int threeDigitNumber, boolean addAnd, StringBuilder accumulator){
        int hundreds= threeDigitNumber/100;
        if(hundreds > 0 ){
            spellDouble(hundreds, accumulator);
            accumulator.append( " hundred ");
        }
        int rest = threeDigitNumber % 100;

        //we don't want to append space or "and"
        if( rest == 0 ){
            return;
        }
        if( addAnd && !accumulator.toString().isEmpty()) {
            accumulator.append("and ");
        }
        spellDouble(rest, accumulator);

    }

    private void spellSingles(int singleDigit, StringBuilder accumulator){
        accumulator.append(SpellingConstants.underTwenty[singleDigit]);
    }

    private void spellDouble(int doubleDigit, StringBuilder accumulator){

        if(doubleDigit < 20){
            accumulator.append(SpellingConstants.underTwenty[doubleDigit]);
        } else {
            accumulator.append(SpellingConstants.tens[doubleDigit /10]);
            int rest = doubleDigit%10;
            if(rest != 0) {
                accumulator.append(' ');
                spellSingles(rest, accumulator);
            }

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

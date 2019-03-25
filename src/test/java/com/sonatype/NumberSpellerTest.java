package com.sonatype;


import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class NumberSpellerTest {

    private final NumberSpeller numberSpeller;

    public NumberSpellerTest() {
        this.numberSpeller = new NumberSpeller();
    }

    @Test
    public void countDigitsSingle() {
        assertThat(numberSpeller.digitCount(5), equalTo(1));

    }
    @Test
    public void countDigits() {
        assertThat(numberSpeller.digitCount(10), equalTo(2));

    }
    @Test
    public void countDigitsBig() {
        assertThat(numberSpeller.digitCount(123456789), equalTo(9));

    }

    @Test
    public void sections10(){
        LinkedList<Integer> longs = numberSpeller.calcSections(10);
        assertThat(longs.size(), equalTo(1) );
        assertThat(longs.peekLast(), equalTo(10) );
    }
    @Test
    public void sections123(){
        LinkedList<Integer> longs = numberSpeller.calcSections(123);
        assertThat(longs.size(), equalTo(1) );
        assertThat(longs.peekLast(), equalTo(123) );
    }
    @Test
    public void sections1000(){
        LinkedList<Integer> longs = numberSpeller.calcSections(1000);
        assertThat(longs.size(), equalTo(2) );
        assertThat(longs.get(0), equalTo(0) );
        assertThat(longs.get(1), equalTo(1) );
    }
    @Test
    public void sections101010(){
        LinkedList<Integer> longs = numberSpeller.calcSections(101010);
        assertThat(longs.size(), equalTo(2) );
        assertThat(longs.get(1), equalTo(101) );
        assertThat(longs.get(0), equalTo(10) );
    }
    @Test
    public void testOne(){
        String str = numberSpeller.spell(1);
        assertThat(str, equalTo("One"));
    }

    @Test
    public void testFive(){
        String str = numberSpeller.spell(5);
        assertThat(str, equalTo("Five"));
    }
    @Test
    public void testZero(){
        String str = numberSpeller.spell(0);
        assertThat(str, equalTo("Zero"));
    }
    @Test
    public void test13(){
        String str = new NumberSpeller().spell(13);
        assertThat(str, equalTo("Thirteen"));
    }

    @Test
    public void test111(){
        String str = numberSpeller.spell(111L);
        assertThat(str, equalTo("One hundred and eleven"));
    }

    @Test
    public void test85(){
        assertThat(numberSpeller.spell(85L), equalTo("Eighty five"));
    }

    @Test
    public void test5237(){
        assertThat(numberSpeller.spell(5237L), equalTo("Five thousand two hundred and thirty seven"));
    }

    @Test
    public void test7654321(){
        assertThat(numberSpeller.spell(7_654_321L),
                equalTo("Seven million six hundred fifty four thousand three hundred and twenty one"));
    }
    @Test
    public void testVeryLarge(){
        assertThat(numberSpeller.spell(Long.MAX_VALUE), equalTo("Nine quintillion two hundred twenty three quadrillion three hundred seventy two trillion thirty six billion eight hundred fifty four million seven hundred seventy five thousand eight hundred and seven"));
    }

}

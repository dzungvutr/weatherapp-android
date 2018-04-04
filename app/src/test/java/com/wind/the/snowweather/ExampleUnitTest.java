package com.wind.the.snowweather;

import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
   // @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        System.out.println("convertInt - " + convertInt("230:adsgdg00"));
        System.out.println("subHours - " + subHours("2018-02-28 15:00:00"));
        System.out.println("convertMetreToKilometer - "+convertMetreToKilometer(19805.012f));
    }

    public int convertInt(String time) {
        time = time.substring(0, time.indexOf(":"));
        return Integer.parseInt(time);
    }

    public static String subHours(String hours) {
        return hours = hours.substring(hours.indexOf(" ") + 1, hours.indexOf(":") + 3);
    }

    public static String convertMetreToKilometer(Float visibility) {
        return new DecimalFormat("#.##").format(visibility / 1000f);
    }

    final int MAX_SPEED = 90; // biến final
    String MAX ="100";
    private void run(final String mString) {
        MAX = "400";
        System.out.println(mString);
    }
    @Test
    public void main() {
        //ExampleUnitTest obj = new ExampleUnitTest();
        //obj.run();
    run("TEST");
    }

}
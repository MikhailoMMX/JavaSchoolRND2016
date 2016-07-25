package ru.sbertech.lesson5;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

class MyException extends Exception {
    //это исключение - проверяемое
}

class MyRuntimeException extends RuntimeException {
    //это исключение - непроверяемое

}

public class MyExceptionTest {
    public static void main(String[] args) {
        //throw new MyRuntimeException();    // норм
        //throw new MyException();    // java ругается на необработанное исключение

        try {
            //Integer.parseInt("Fourty two");

            //new URL("slashdotdotorg");

            int a = 1/0;
        }
        catch ( NumberFormatException e){
            System.out.println(e.toString());
        }
        /*catch (MalformedURLException e){
            System.out.println(e.toString());
        }*/
        catch (/*IOException|*/ ArithmeticException e){
            System.out.println(e.toString());
        }
        catch (Exception e){
            System.out.println(e.toString());
        }   // а в обратном порядке нельзя
    }
}

package com.sT31_N.Krypt;

public class sT31_NUtilities {
    public final static void clearConsole() // Clear the console.
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }

    public final static void createGap(){ // Print two blank lines in console.
        System.out.println("");
        System.out.println("");
    }
}
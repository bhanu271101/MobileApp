package com.example.memo.Utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {
     private static final String Email_Regex=
     "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

     private static final Pattern pattern=Pattern.compile(Email_Regex);

     public static boolean isValidEmail(String Email)
     {
        Matcher matcher=pattern.matcher(Email);
        return matcher.matches();
     }

}

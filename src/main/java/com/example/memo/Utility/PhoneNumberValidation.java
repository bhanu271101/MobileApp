package com.example.memo.Utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidation {

    private static String phoneNumber_Regex="^\\d{10}$";

    private static Pattern pattern=Pattern.compile(phoneNumber_Regex);

    public static boolean isValidPhoneNumber(String phoneNumber)
    {
        Matcher matcher=pattern.matcher(phoneNumber);
        return matcher.matches();
    }

}

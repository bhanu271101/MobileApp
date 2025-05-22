package com.example.memo.Utility;

public class PasswordValidation {

    private static final int Min_length=8;

    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+";

    public static boolean passwordValidation(String password)
    {
        if(password.length()<Min_length)
        {
            return false;
        }
        if(!password.matches(".*[A-Z].*"))
        {
            return false;
        }
        if(!password.matches(".*[a-z].*"))
        {
            return false;
        }

        if(!password.matches(".*\\d.*"))
        {
            return false;
        }
        if(!containsSpecalCharacters(password))
        {
            return false;
        }

        return true;


    }
        private static boolean containsSpecalCharacters(String password)
        {
            for(int i=0;i<password.length();i++)
            {
                char c=password.charAt(i);
                if(SPECIAL_CHARACTERS.contains(String.valueOf(c)))
                {
                    return true;
                }
            }
            return false;
        }
        


    

}

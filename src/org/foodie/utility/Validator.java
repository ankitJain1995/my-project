package org.foodie.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author AFROZ
 */
public class Validator {

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    public static boolean isValidMobileNo(String str) {
        //(0/91): number starts with (0/91)  
        //[7-9]: starting of the number may contain a digit between 0 to 9  
        //[0-9]: then contains digits 0 to 9  
        Pattern ptrn = Pattern.compile("(0/91)?[6-9][0-9]{9}");
        //the matcher() method creates a matcher that will match the given input against this pattern  
        Matcher match = ptrn.matcher(str);
        //returns a boolean value  
        return (match.find() && match.group().equals(str));
    }

}

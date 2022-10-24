package ra.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopValidation {
    public static boolean checkEmail(String str) {
        String regex = "^(.+)@(.+)$";
        Pattern path = Pattern.compile(regex);
        Matcher matcher = path.matcher(str);
        return matcher.matches();
    }

    public static boolean checkEmptyString(String str) {
        if (str.trim().length() != 0) {
            return true;
        }
        return false;
    }

    public static boolean checkUserNameLength(String str) {
        if (str.trim().length() >= 6) {
            return true;
        }
        return false;
    }

    public static boolean checkValidateUserName(String str) {
        String regex = "^[a-zA-Z0-9._-]{3,}$";
        Pattern path = Pattern.compile(regex);
        Matcher matcher = path.matcher(str);
        return matcher.matches();
    }

    public static boolean checkPassword(String str) {
        if (str.trim().length() >= 6) {
            return true;
        }
        return false;
    }

    public static boolean checkPhoneNumber(String str) {
        if (str.charAt(0) == '8' && str.charAt(1) == '4' && str.length() == 11) {
            return true;
        }
        return false;
    }

    public static boolean checkInputNumber(String str) {
        try {
            int number = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkValidateName(String str) {
        if (str.trim().length() >= 6 && str.trim().length() <= 30) {
            return true;
        }
        return false;
    }

    public static String checkInputStatus(String str) {
        if (str.equals("1")) {
            return "1";
        } else if (str.equals("2")) {
            return "2";
        } else {
            return "3";
        }
    }

    public static boolean checkProductId_length(String str) {
        if (str.trim().length() == 5) {
            return true;
        }
        return false;
    }

    public static boolean checkProductId(String str) {
        if (str.charAt(0) == 'P') {
            return true;
        }
        return false;
    }

    public static boolean checkId5(String str) {
        if (str.trim().length() == 5) {
            return true;
        }
        return false;
    }

    public static boolean checkCatalogId(String str) {
        if (str.charAt(0) == 'T') {
            return true;
        }
        return false;
    }

    public static boolean checkFlowerId(String str) {
        if (str.charAt(0) == 'H') {
            return true;
        }
        return false;
    }

    public static boolean checkInputPrice(String str) {
        boolean checkExit = true;
        try {
            float check = Float.parseFloat(str);
            if (check > 0) {
                checkExit = true;
            }
        } catch (NumberFormatException e) {
            checkExit = false;
        }
        return checkExit;
    }

    public static boolean checkProductName(String str) {
        if (str.trim().length() >= 6 && str.trim().length() <= 50) {
            return true;
        }
        return false;
    }

}

//    public static boolean checLengthString(String str, int min, int max){
//        if (str.length()>=min && str.length()<=max){
//            return true;
//        }else {
//            return false;
//        }
//    }
//    public static boolean checkInterger(String str){
//        try {
//            int number= Integer.parseInt(str);
//                return true;
//
//
//        }catch (NumberFormatException ex){
//            return false;
//
//        }
//    }
//    public static boolean checkFloat(String str){
//        try {
//            float number= Float.parseFloat(str);
//            return true;
//        }catch (NumberFormatException ex){
//            return false;
//        }
//    }



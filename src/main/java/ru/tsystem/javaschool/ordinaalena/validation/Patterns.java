package ru.tsystem.javaschool.ordinaalena.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patterns {
        public static boolean emailPattern(String email){
            Pattern pattern = Pattern.compile("^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$", Pattern.CASE_INSENSITIVE);
            return match(pattern, email);
        }

        public static boolean namePattern(String name){
            Pattern pattern = Pattern.compile("([a-zа-яёА-ЯЁ0-9\\s\\.-]{1,32})", Pattern.CASE_INSENSITIVE);
            return match(pattern,name);
        }

        public static boolean localityPattern(String locality){
            Pattern pattern = Pattern.compile("([a-zа-яёА-ЯЁ\\s\\.-]{1,32})", Pattern.CASE_INSENSITIVE);
            return match(pattern,locality);
        }

        public static boolean homePattern(String home){
            Pattern pattern = Pattern.compile("([a-zа-яёА-ЯЁ0-9\\s\\.-]{1,32})", Pattern.CASE_INSENSITIVE);
            return match(pattern,home);
        }

        public static boolean parolePattern(String parole){
            Pattern pattern = Pattern.compile("([a-z0-9_-]{1,32})", Pattern.CASE_INSENSITIVE);
            return match(pattern, parole);
        }

        public static boolean numbersPattern(String num){
            Pattern pattern = Pattern.compile("([0-9]{1,9})");
            return match(pattern, num);
        }

        public static boolean addressIndexPattern(String index){
            Pattern pattern = Pattern.compile("([0-9]{1,6})");
            return match(pattern, index);
        }
        public static boolean phonenumberPattern(String phone){
            Pattern pattern = Pattern.compile("([0-9]{1,11})");
            return match(pattern, phone);
        }

        private static boolean match(Pattern pattern, String value){
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }
    }


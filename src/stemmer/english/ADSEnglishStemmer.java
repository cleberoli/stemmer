/*
 * Copyright 2017 - Cleber Oliveira and Wladmir Brandao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * The definition of the Porter 2 stemmer for English can be found at
 *
 * http://snowball.tartarus.org/algorithms/english/stemmer.html
 *
 */

package stemmer.english;

public class ADSEnglishStemmer extends EnglishStemmer {

    public ADSEnglishStemmer() {
        super();
    }

    /*
     * Search for the longest among the following suffixes, and, if found and in R1, perform the action indicated.
     * tional:   replace by tion
     * enci:   replace by ence
     * anci:   replace by ance
     * abli:   replace by able
     * entli:   replace by ent
     * izer   ization:   replace by ize
     * ational   ation   ator:   replace by ate
     * alism   aliti   alli:   replace by al
     * fulness:   replace by ful
     * ousli   ousness:   replace by ous
     * iveness   iviti:   replace by ive
     * biliti   bli:   replace by ble
     * ogi:   replace by og if preceded by l
     * fulli:   replace by ful
     * lessli:   replace by less
     * li:   delete if preceded by a valid li-ending
     */
    @Override
    protected String step2() {
        String str = current.toString();
        String newString = "";
        String suffix = "";
        String r1 = r1();
        char ch;
        int suffixSize = 0;
        int cursor = str.length() - 1;
        int state = 0;
        boolean done = false;

        while (cursor >= 0) {
            ch = str.charAt(cursor);

            switch (state) {
                case 0 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 1;
                            break;
                        case 'l' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 20;
                            break;
                        case 'm' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 26;
                            break;
                        case 'n' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 30;
                            break;
                        case 'r' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 36;
                            break;
                        case 's' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 41;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 1 :
                    switch (ch) {
                        case 'c' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 2;
                            break;
                        case 'l' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 4;
                            break;
                        case 't' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 14;
                            break;
                        case 'g' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 19;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 2 :
                    switch (ch) {
                        case 'n' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 3;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 3 :
                    switch (ch) {
                        case 'a' :
                        case 'e' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 4 :
                    switch (ch) {
                        case 'b' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 5;
                            break;
                        case 'l' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 6;
                            break;
                        case 's' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 8;
                            break;
                        case 't' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 12;
                            break;
                        default :
                            cursor--;
                            state = 200;
                            break;
                    }
                    break;
                case 5 :
                    switch (ch) {
                        case 'a' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            cursor--;
                            state = 200;
                            break;
                    }
                    break;
                case 6 :
                    switch (ch) {
                        case 'a' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        case 'u' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 7;
                            break;
                        default :
                            suffix = "li";
                            suffixSize = 2;
                            cursor = str.length() - 3;
                            state = 200;
                            break;
                    }
                    break;
                case 7 :
                    switch (ch) {
                        case 'f' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            suffix = "li";
                            suffixSize = 2;
                            cursor = str.length() - 3;
                            state = 200;
                            break;
                    }
                    break;
                case 8 :
                    switch (ch) {
                        case 's' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 9;
                            break;
                        case 'u' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 11;
                            break;
                        default :
                            suffix = "li";
                            suffixSize = 2;
                            cursor = str.length() - 3;
                            state = 200;
                            break;
                    }
                    break;
                case 9 :
                    switch (ch) {
                        case 'e' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 10;
                            break;
                        default :
                            suffix = "li";
                            suffixSize = 2;
                            cursor = str.length() - 3;
                            state = 200;
                            break;
                    }
                    break;
                case 10 :
                    switch (ch) {
                        case 'l' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            suffix = "li";
                            suffixSize = 2;
                            cursor = str.length() - 3;
                            state = 200;
                            break;
                    }
                    break;
                case 11 :
                    switch (ch) {
                        case 'o' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            suffix = "li";
                            suffixSize = 2;
                            cursor = str.length() - 3;
                            state = 200;
                            break;
                    }
                    break;
                case 12 :
                    switch (ch) {
                        case 'n' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 13;
                            break;
                        default :
                            suffix = "li";
                            suffixSize = 2;
                            cursor = str.length() - 3;
                            state = 200;
                            break;
                    }
                    break;
                case 13 :
                    switch (ch) {
                        case 'e' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            suffix = "li";
                            suffixSize = 2;
                            cursor = str.length() - 3;
                            state = 200;
                            break;
                    }
                    break;
                case 14 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 15;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 15 :
                    switch (ch) {
                        case 'l' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 16;
                            break;
                        case 'v' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 18;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 16 :
                    switch (ch) {
                        case 'a' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 17;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 17 :
                    switch (ch) {
                        case 'b' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 18 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 19 :
                    switch (ch) {
                        case 'o' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 20 :
                    switch (ch) {
                        case 'a' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 21;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 21 :
                    switch (ch) {
                        case 'n' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 22;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 22 :
                    switch (ch) {
                        case 'o' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 23;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 23 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 24;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 24 :
                    switch (ch) {
                        case 't' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 25;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 25 :
                    switch (ch) {
                        case 'a' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            cursor--;
                            state = 200;
                            break;
                    }
                    break;
                case 26 :
                    switch (ch) {
                        case 's' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 27;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 27 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 28;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 28 :
                    switch (ch) {
                        case 'l' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 29;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 29 :
                    switch (ch) {
                        case 'a' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 30 :
                    switch (ch) {
                        case 'o' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 31;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 31 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 32;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 32 :
                    switch (ch) {
                        case 't' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 33;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 33 :
                    switch (ch) {
                        case 'a' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 34;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 34 :
                    switch (ch) {
                        case 'z' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 35;
                            break;
                        default :
                            cursor--;
                            state = 200;
                            break;
                    }
                    break;
                case 35 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 36 :
                    switch (ch) {
                        case 'e' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 37;
                            break;
                        case 'o' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 39;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 37 :
                    switch (ch) {
                        case 'z' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 38;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 38 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 39 :
                    switch (ch) {
                        case 't' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 40;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 40 :
                    switch (ch) {
                        case 'a' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 41 :
                    switch (ch) {
                        case 's' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 42;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 42 :
                    switch (ch) {
                        case 'e' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 43;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 43 :
                    switch (ch) {
                        case 'n' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 44;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 44 :
                    switch (ch) {
                        case 'e' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 45;
                            break;
                        case 'l' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 47;
                            break;
                        case 's' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 49;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 45 :
                    switch (ch) {
                        case 'v' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 46;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 46 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 47 :
                    switch (ch) {
                        case 'u' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 48;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 48 :
                    switch (ch) {
                        case 'f' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 49 :
                    switch (ch) {
                        case 'u' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 50;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 50 :
                    switch (ch) {
                        case 'o' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 200 :
                    if (r1.contains(suffix)) {
                        done = true;

                        switch (suffix) {
                            case "tional" :
                                newString = str.substring(0, str.length() - suffixSize) + "tion";
                                break;
                            case "enci" :
                                newString = str.substring(0, str.length() - suffixSize) + "ence";
                                break;
                            case "anci" :
                                newString = str.substring(0, str.length() - suffixSize) + "ance";
                                break;
                            case "abli" :
                                newString = str.substring(0, str.length() - suffixSize) + "able";
                                break;
                            case "entli" :
                                newString = str.substring(0, str.length() - suffixSize) + "ent";
                                break;
                            case "izer" :
                            case  "ization" :
                                newString = str.substring(0, str.length() - suffixSize) + "ize";
                                break;
                            case "ational" :
                            case "ation" :
                            case "ator" :
                                newString = str.substring(0, str.length() - suffixSize) + "ate";
                                break;
                            case "alism" :
                            case "aliti" :
                            case "alli" :
                                newString = str.substring(0, str.length() - suffixSize) + "al";
                                break;
                            case "fulness" :
                                newString = str.substring(0, str.length() - suffixSize) + "ful";
                                break;
                            case "ousli" :
                            case "ousness" :
                                newString = str.substring(0, str.length() - suffixSize) + "ous";
                                break;
                            case "iveness" :
                            case "iviti" :
                                newString = str.substring(0, str.length() - suffixSize) + "ive";
                                break;
                            case "biliti" :
                            case "bli" :
                                newString = str.substring(0, str.length() - suffixSize) + "ble";
                                break;
                            case "ogi" :
                                newString = (str.charAt(cursor) == 'l') ? str.substring(0, str.length() - suffixSize) + "og" : str;
                                break;
                            case "fulli" :
                                newString = str.substring(0, str.length() - suffixSize) + "ful";
                                break;
                            case "lessli" :
                                newString = str.substring(0, str.length() - suffixSize) + "less";
                                break;
                            case "li" :
                                newString = (inLI(str.length() - 3)) ? str.substring(0, str.length() - suffixSize) : str;
                                break;
                        }
                    }

                    cursor = -1;
                    break;
            }
        }

        return done ? newString : str;
    }

    /*
     * Search for the longest among the following suffixes, and, if found and in R1, perform the action indicated.
     * tional:   replace by tion
     * ational:   replace by ate
     * alize:   replace by al
     * icate   iciti   ical:   replace by ic
     * ful   ness:   delete
     * ative:   delete if in R2
     */
    @Override
    protected String step3() {
        String str = current.toString();
        String newString = "";
        String suffix = "";
        String r1 = r1();
        String r2 = r2();
        char ch;
        int suffixSize = 0;
        int cursor = str.length() - 1;
        int state = 0;
        boolean done = false;

        while (cursor >= 0) {
            ch = str.charAt(cursor);

            switch (state) {
                case 0 :
                    switch (ch) {
                        case 'e' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 1;
                            break;
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 11;
                            break;
                        case 'l' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 15;
                            break;
                        case 's' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 23;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 1 :
                    switch (ch) {
                        case 't' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 2;
                            break;
                        case 'v' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 5;
                            break;
                        case 'z' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 8;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 2 :
                    switch (ch) {
                        case 'a' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 3;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 3 :
                    switch (ch) {
                        case 'c' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 4;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 4 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 5 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 6;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 6 :
                    switch (ch) {
                        case 't' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 7;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 7 :
                    switch (ch) {
                        case 'a' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 8 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 9;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 9 :
                    switch (ch) {
                        case 'l' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 10;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 10 :
                    switch (ch) {
                        case 'a' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 11 :
                    switch (ch) {
                        case 't' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 12;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 12 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 13;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 13 :
                    switch (ch) {
                        case 'c' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 14;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 14 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 15 :
                    switch (ch) {
                        case 'a' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 16;
                            break;
                        case 'u' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 22;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 16 :
                    switch (ch) {
                        case 'c' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 17;
                            break;
                        case 'n' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 18;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 17 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 18 :
                    switch (ch) {
                        case 'o' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 19;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 19 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 20;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 20 :
                    switch (ch) {
                        case 't' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 21;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 21 :
                    switch (ch) {
                        case 'a' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            cursor--;
                            state = 200;
                            break;
                    }
                    break;
                case 22 :
                    switch (ch) {
                        case 'f' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 23 :
                    switch (ch) {
                        case 's' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 24;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 24 :
                    switch (ch) {
                        case 'e' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 25;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 25 :
                    switch (ch) {
                        case 'n' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 200 :
                    if (r1.contains(suffix)) {
                        done = true;

                        switch (suffix) {
                            case "tional" :
                                newString = str.substring(0, str.length() - suffixSize) + "tion";
                                break;
                            case "ational" :
                                newString = str.substring(0, str.length() - suffixSize) + "ate";
                                break;
                            case "alize" :
                                newString = str.substring(0, str.length() - suffixSize) + "al";
                                break;
                            case "icate" :
                            case "iciti" :
                            case "ical" :
                                newString = str.substring(0, str.length() - suffixSize) + "ic";
                                break;
                            case "ful" :
                            case "ness" :
                                newString = str.substring(0, str.length() - suffixSize);
                                break;
                            case "ative" :
                                newString = (r2.contains(suffix)) ? str.substring(0, str.length() - suffixSize) : str;
                                break;
                        }
                    }

                    cursor = -1;
                    break;
            }
        }

        return done ? newString : str;
    }

    /*
     * Search for the longest among the following suffixes, and, if found and in R2, perform the action indicated.
     * al   ance   ence   er   ic   able   ible   ant   ement   ment   ent   ism   ate   iti   ous   ive   ize
     * ->   delete
     * ion
     * ->   delete if preceded by s or t
     */
    @Override
    protected String step4() {
        String str = current.toString();
        String newString = "";
        String suffix = "";
        String r2 = r2();
        char ch;
        int suffixSize = 0;
        int cursor = str.length() - 1;
        int state = 0;
        boolean done = false;

        while (cursor >= 0) {
            ch = str.charAt(cursor);

            switch (state) {
                case 0 :
                    switch (ch) {
                        case 'c' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 1;
                            break;
                        case 'e' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 2;
                            break;
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 9;
                            break;
                        case 'l' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 11;
                            break;
                        case 'm' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 12;
                            break;
                        case 'n' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 14;
                            break;
                        case 'r' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 17;
                            break;
                        case 's' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 18;
                            break;
                        case 't' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 20;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 1 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 2 :
                    switch (ch) {
                        case 'c' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 3;
                            break;
                        case 'l' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 5;
                            break;
                        case 't' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 7;
                            break;
                        case 'v' :
                        case 'z' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 8;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 3 :
                    switch (ch) {
                        case 'n' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 4;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 4:
                    switch (ch) {
                        case 'a' :
                        case 'e' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 5 :
                    switch (ch) {
                        case 'b' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 6;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 6 :
                    switch (ch) {
                        case 'a' :
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 7 :
                    switch (ch) {
                        case 'a' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 8 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 9 :
                    switch (ch) {
                        case 't' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 10;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 10 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 11 :
                    switch (ch) {
                        case 'a' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 12 :
                    switch (ch) {
                        case 's' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 13;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 13 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 14:
                    switch (ch) {
                        case 'o' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 15;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 15 :
                    switch (ch) {
                        case 'i' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 16;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 16 :
                    switch (ch) {
                        case 's' :
                        case 't' :
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 17 :
                    switch (ch) {
                        case 'e' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 18 :
                    switch (ch) {
                        case 'u' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 19;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 19 :
                    switch (ch) {
                        case 'o' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 20 :
                    switch (ch) {
                        case 'n' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 21;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 21 :
                    switch (ch) {
                        case 'a' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        case 'e' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 22;
                            break;
                        default :
                            newString = str;
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 22 :
                    switch (ch) {
                        case 'm' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 23;
                            break;
                        default :
                            cursor--;
                            state = 200;
                            break;
                    }
                    break;
                case 23 :
                    switch (ch) {
                        case 'e' :
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 200;
                            break;
                        default :
                            cursor--;
                            state = 200;
                            break;
                    }
                    break;
                case 200 :
                    done = true;
                    newString = (r2.contains(suffix)) ? str.substring(0, str.length() - suffixSize) : str;
                    cursor = -1;
                    break;

            }

        }

        return done ? newString : str;
    }

}

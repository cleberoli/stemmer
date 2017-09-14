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

package stemmer.english.EnADS;

import stemmer.Stemmer;

public class Porter2ADS extends Stemmer {

    protected StringBuffer current;
    private boolean yFound;
    private int iP2;
    private int iP1;

    public Porter2ADS() {
        current = new StringBuffer();
        setCurrent("");
    }

    /*
     * Define a vowel as one of
     * a   e   i   o   u   y
     */
    private boolean isVowel(char c) {
        return ((c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u') || (c == 'y'));
    }

    /*
     * Define a double as one of
     * bb   dd   ff   gg   mm   nn   pp   rr   tt
     */
    private boolean endsWithDouble(String s) {
        return (s.endsWith("bb") || s.endsWith("dd") || s.endsWith("ff") || s.endsWith("gg") || s.endsWith("mm") ||
                s.endsWith("nn") || s.endsWith("pp") || s.endsWith("rr") || s.endsWith("tt"));
    }

    /*
     * Define a valid li-ending as one of
     * c   d   e   g   h   k   m   n   r   t
     */
    private boolean inLI(int index) {
        if (index < 0 || index >= current.length())
            return false;
        else {
            char c = current.toString().charAt(index);

            return c == 'c' || c == 'd' || c == 'e' || c == 'g' || c == 'h' || c == 'k' || c == 'm' || c == 'n' || c == 'r' || c == 't';
        }
    }

    /*
     * R1 is the region after the first non-vowel following a vowel, or the end of the word if there is no such non-vowel.
     */
    private String r1() {
        String str = current.toString();
        return (iP1 >= str.length()) ? "" : str.substring(iP1, str.length());
    }

    /*
     * R2 is the region after the first non-vowel following a vowel in R1, or the end of the word if there is no such non-vowel.
     */
    private String r2() {
        String str = current.toString();
        return (iP2 >= str.length()) ? "" : str.substring(iP2, str.length());
    }

    /*
     * Define a short syllable in a word as either (a) a vowel followed by a non-vowel other than w, x or Y and
     * preceded by a non-vowel, or * (b) a vowel at the beginning of the word followed by a non-vowel.
     */
    private boolean shortV(int index) {
        if ((outVWXY(index - 1)) && (inV(index - 2)) && (outV(index - 3)))
            return true;

        if((outV(index - 1)) && (inV(index - 2)) && (index <= 2))
            return true;

        return false;
    }

    /*
    * Remove initial ', if present. Then, set initial y, or y after a vowel, to Y.
    */
    private String prelude() {
        String str = current.toString();
        yFound = false;

        if (str.startsWith("'"))
            str = str.substring(1, str.length());

        if (str.startsWith("y")) {
            str = "Y" + str.substring(1, str.length());
            yFound = true;
        }

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == 'y' && isVowel(str.charAt(i - 1))) {
                str = str.substring(0, i) + "Y" + str.substring(i + 1, str.length());
                yFound = true;
            }
        }

        return str;
    }

    /*
     * Establish the regions R1 and R2
     */
    private void markRegions() {
        String str = current.toString();
        iP1 = 0;
        iP2 = 0;

        if (str.startsWith("gener") || str.startsWith("arsen"))
            iP1 = 5;
        else if (str.startsWith("commun"))
            iP1 = 6;
        else {
            for (int i = 0; i < str.length() - 1; i++) {
                if (isVowel(str.charAt(i)) && !isVowel(str.charAt(i + 1))) {
                    iP1 = i + 2;
                    break;
                }
            }
        }

        if (iP1 == 0 || iP1 > str.length())
            iP1 = str.length();

        if (iP1 >= str.length() - 1)
            iP2 = str.length();
        else {
            for (int i = iP1; i < str.length() - 1; i++) {
                if (isVowel(str.charAt(i)) && !isVowel(str.charAt(i + 1))) {
                    iP2 = i + 2;
                    break;
                }
            }
        }

        if (iP2 == 0 || iP2 > str.length())
            iP2 = str.length();
    }

    /*
     * Search for the longest among the suffixes,
     * '
     * 's
     * 's'
     * and remove if found.
     */
    private String step0() {
        String str = current.toString();

        if (str.endsWith("'s'"))
            return str.substring(0, str.length() - 3);
        else if (str.endsWith("'s"))
            return str.substring(0, str.length() - 2);
        else if (str.endsWith("'"))
            return str.substring(0, str.length() - 1);
        else
            return str;
    }

    /*
     * Search for the longest among the following suffixes, and perform the action indicated.
     * sses     ->      replace by ss
     * ied ies  ->      replace by i if preceded by more than one letter, otherwise by ie
     * s        ->      delete if the preceding word part contains a vowel not immediately before the s
     * us ss    ->      do nothing
     */
    private String step1a() {
        String str = current.toString();

        if (str.endsWith("sses"))
            return str.substring(0, str.length() - 2);
        else if (str.endsWith("ied") || str.endsWith("ies")) {
            if (str.length() > 4)
                return str.substring(0, str.length() - 2);
            else
                return str.substring(0, str.length() - 1);
        } else if (str.endsWith("us") || str.endsWith("ss"))
            return str;
        else if (str.endsWith("s") && str.length() >= 3 && hasVowel(str.substring(0, str.length() - 2)))
            return str.substring(0, str.length() - 1);
        else
            return str;
    }

    /*
     * Search for the longest among the following suffixes, and perform the action indicated.
     * eed eedly            ->      replace by ee if in R1
     * ed edly ing ingly    ->      delete if the preceding word part contains a vowel, and after the deletion:
     *                              if the word ends at, bl or iz add e, or
     *                              if the word ends with a double remove the last letter, or
     *                              if the word is short, add e
     */
    private String step1b() {
        String str = current.toString();
        String r1 = r1();

        if (str.endsWith("eed") && r1.contains("eed"))
            return str.substring(0, str.length() - 1);
        else if (str.endsWith("eedly") && r1.contains("eedly"))
            return str.substring(0, str.length() - 3);
        else if ((str.endsWith("ed") && !str.endsWith("eed") && hasVowel(str.substring(0, str.length() - 2))) ||
                (str.endsWith("edly") && !str.endsWith("eedly") && hasVowel(str.substring(0, str.length() - 4))) ||
                (str.endsWith("ing") && hasVowel(str.substring(0, str.length() - 3))) ||
                (str.endsWith("ingly") && hasVowel(str.substring(0, str.length() - 5)))) {
            if (str.endsWith("ed"))
                setCurrent(str.substring(0, str.length() - 2));
            else if (str.endsWith("edly"))
                setCurrent(str.substring(0, str.length() - 4));
            else if (str.endsWith("ing"))
                setCurrent(str.substring(0, str.length() - 3));
            else
                setCurrent(str.substring(0, str.length() - 5));

            str = current.toString();
            r1 = r1();

            if (str.endsWith("at") || str.endsWith("bl") || str.endsWith("iz"))
                return str + "e";
            else if (endsWithDouble(str))
                return str.substring(0, str.length() - 1);
            else if (r1.length() == 0 && shortV(str.length()))
                return str + "e";
        }

        return str;
    }

    /*
     * Replace suffix y or Y by i if preceded by a non-vowel which is not the first letter of the word
     */
    private String step1c() {
        String str = current.toString();

        if (str.length() > 2) {
            if ((str.endsWith("y") || str.endsWith("Y")) && !isVowel(str.charAt(str.length() - 2)))
                return str.substring(0, str.length() - 1) + "i";
            else
                return str;
        } else
            return str;
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
    private String step2() {
        String str = current.toString();
        String r1 = r1();

        if (str.endsWith("tional") && !str.endsWith("ational") && r1.contains("tional"))
            return str.substring(0, str.length() - 6) + "tion";
        else if (str.endsWith("enci") && r1.contains("enci"))
            return str.substring(0, str.length() - 4) + "ence";
        else if (str.endsWith("anci") && r1.contains("anci"))
            return str.substring(0, str.length() - 4) + "ance";
        else if (str.endsWith("abli") && r1.contains("abli"))
            return str.substring(0, str.length() - 4) + "able";
        else if (str.endsWith("entli") && r1.contains("entli"))
            return str.substring(0, str.length() - 5) + "ent";
        else if (str.endsWith("izer") && r1.contains("izer"))
            return str.substring(0, str.length() - 4) + "ize";
        else if (str.endsWith("ization") && r1.contains("ization"))
            return str.substring(0, str.length() - 7) + "ize";
        else if (str.endsWith("ational") && r1.contains("ational"))
            return str.substring(0, str.length() - 7) + "ate";
        else if (str.endsWith("ation") && r1.contains("ation"))
            return str.substring(0, str.length() - 5) + "ate";
        else if (str.endsWith("ator") && r1.contains("ator"))
            return str.substring(0, str.length() - 4) + "ate";
        else if (str.endsWith("alism") && r1.contains("alism"))
            return str.substring(0, str.length() - 5) + "al";
        else if (str.endsWith("aliti") && r1.contains("aliti"))
            return str.substring(0, str.length() - 5) + "al";
        else if (str.endsWith("alli") && r1.contains("alli"))
            return str.substring(0, str.length() - 4) + "al";
        else if (str.endsWith("fulness") && r1.contains("fulness"))
            return str.substring(0, str.length() - 7) + "ful";
        else if (str.endsWith("ousli") && r1.contains("ousli"))
            return str.substring(0, str.length() - 5) + "ous";
        else if (str.endsWith("ousness") && r1.contains("ousness"))
            return str.substring(0, str.length() - 7) + "ous";
        else if (str.endsWith("iveness") && r1.contains("iveness"))
            return str.substring(0, str.length() - 7) + "ive";
        else if (str.endsWith("iviti") && r1.contains("iviti"))
            return str.substring(0, str.length() - 5) + "ive";
        else if (str.endsWith("biliti") && r1.contains("biliti"))
            return str.substring(0, str.length() - 6) + "ble";
        else if (str.endsWith("bli") && r1.contains("bli") && !str.endsWith("abli"))
            return str.substring(0, str.length() - 3) + "ble";
        else if (str.endsWith("logi") && r1.contains("ogi"))
            return str.substring(0, str.length() - 3) + "og";
        else if (str.endsWith("fulli") && r1.contains("fulli"))
            return str.substring(0, str.length() - 5) + "ful";
        else if (str.endsWith("lessli") && r1.contains("lessli"))
            return str.substring(0, str.length() - 6) + "less";
        else if (str.endsWith("li") && r1.contains("li") && inLI(str.length() - 3) && !str.endsWith("abli") &&
                !str.endsWith("entli") && !str.endsWith("alli") && !str.endsWith("ousli") && !str.endsWith("bli") &&
                !str.endsWith("fulli") && !str.endsWith("lessli") )
            return str.substring(0, str.length() - 2) + "";
        else
            return str;
    }

    private String step2T() {
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
    private String step3() {
        String str = current.toString();
        String r1 = r1();
        String r2 = r2();

        if (str.endsWith("ational") && r1.contains("ational"))
            return str.substring(0, str.length() - 7) + "ate";
        else if (str.endsWith("tional") && !str.endsWith("ational") && r1.contains("tional"))
            return str.substring(0, str.length() - 6) + "tion";
        else if (str.endsWith("alize") && r1.contains("alize"))
            return str.substring(0, str.length() - 5) + "al";
        else if (str.endsWith("icate") && r1.contains("icate"))
            return str.substring(0, str.length() - 5) + "ic";
        else if (str.endsWith("iciti") && r1.contains("iciti"))
            return str.substring(0, str.length() - 5) + "ic";
        else if (str.endsWith("ical") && r1.contains("ical"))
            return str.substring(0, str.length() - 4) + "ic";
        else if (str.endsWith("ful") && r1.contains("ful"))
            return str.substring(0, str.length() - 3);
        else if (str.endsWith("ness") && r1.contains("ness"))
            return str.substring(0, str.length() - 4);
        else if (str.endsWith("ative") && r2.contains("ative"))
            return str.substring(0, str.length() - 5);
        else
            return str;
    }

    private String step3T() {
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
    private String step4() {
        String str = current.toString();
        String r2 = r2();

        if (str.endsWith("ion") && r2.contains("ion") && str.length() >= 4 && (str.charAt(str.length() - 4) == 's' || str.charAt(str.length() - 4) == 't'))
            return str.substring(0, str.length() - 3);
        else if (str.endsWith("al") && r2.contains("al"))
            return str.substring(0, str.length() - 2);
        else if (str.endsWith("ance") && r2.contains("ance"))
            return str.substring(0, str.length() - 4);
        else if (str.endsWith("ence") && r2.contains("ence"))
            return str.substring(0, str.length() - 4);
        else if (str.endsWith("er") && r2.contains("er"))
            return str.substring(0, str.length() - 2);
        else if (str.endsWith("ic") && r2.contains("ic"))
            return str.substring(0, str.length() - 2);
        else if (str.endsWith("able") && r2.contains("able"))
            return str.substring(0, str.length() - 4);
        else if (str.endsWith("ible") && r2.contains("ible"))
            return str.substring(0, str.length() - 4);
        else if (str.endsWith("ant") && r2.contains("ant"))
            return str.substring(0, str.length() - 3);
        else if (str.endsWith("ement") && r2.contains("ement"))
            return str.substring(0, str.length() - 5);
        else if (str.endsWith("ment") && !str.endsWith("ement") && r2.contains("ment"))
            return str.substring(0, str.length() - 4);
        else if (str.endsWith("ent") && !str.endsWith("ment") && r2.contains("ent"))
            return str.substring(0, str.length() - 3);
        else if (str.endsWith("ism") && r2.contains("ism"))
            return str.substring(0, str.length() - 3);
        else if (str.endsWith("ate") && r2.contains("ate"))
            return str.substring(0, str.length() - 3);
        else if (str.endsWith("iti") && r2.contains("iti"))
            return str.substring(0, str.length() - 3);
        else if (str.endsWith("ous") && r2.contains("ous"))
            return str.substring(0, str.length() - 3);
        else if (str.endsWith("ive") && r2.contains("ive"))
            return str.substring(0, str.length() - 3);
        else if (str.endsWith("ize") && r2.contains("ize"))
            return str.substring(0, str.length() - 3);
        else
            return str;
    }

    private String step4T() {
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

    /*
     * Search for the the following suffixes, and, if found, perform the action indicated.
     * e    ->      delete if in R2, or in R1 and not preceded by a short syllable
     * l    ->      delete if in R2 and preceded by l
     */
    private String step5() {
        String str = current.toString();

        if (str.endsWith("e")) {
            if ((r2().contains("e")) || (r1().contains("e") && !shortV(str.length() - 1)))
                return str.substring(0, str.length() - 1);
        } else if (str.endsWith("l")) {
            if (r2().contains("l") && str.endsWith("ll"))
                return str.substring(0, str.length() - 1);
        }

        return str;
    }

    /*
     * Finally, turn any remaining Y letters in the word back into lower case.
     */
    private String postlude() {
        String str = current.toString();
        String processedString = "";
        char ch;
        int cursor = 0;

        if (yFound) {
            while (cursor < str.length()) {
                ch = str.charAt(cursor);

                switch (ch) {
                    case 'Y' :
                        processedString += 'y';
                        cursor++;
                        break;
                    default :
                        processedString += ch;
                        cursor++;
                        break;
                }
            }
        }

        return yFound ? processedString : str;
    }

    /*
     * Following step 1a, leave the following invariant,
     * inning  		  outing  		  canning  		  herring
     * earring        proceed  		  exceed  		  succeed
     */
    private boolean exception2() {
        String str = current.toString();
        return (str.equals("inning") || str.equals("outing") || str.equals("canning") || str.equals("herring") ||
                str.equals("earring") || str.equals("proceed") || str.equals("exceed") || str.equals("succeed"));
    }

    /*
     * Stem certain special words as follows,
     * skis		  ->  		ski
     * skies	  ->  		sky
     * dying      ->        die
     * lying      ->        lie
     * tying	  ->		tie
     * idly       ->        idl
     * gently     ->        gentl
     * ugly       ->        ugli
     * early      ->        earli
     * only       ->        onli
     * singly	  ->		singl
     *
     * If one of the following is found, leave it invariant,
     * sky
     * news
     * howe
     * atlas
     * cosmos
     * bias
     * andes
     */
    private boolean exception1() {
        String str = current.toString();

        switch (str) {
            case "skis":
                setCurrent("ski");
                return true;
            case "skies":
                setCurrent("sky");
                return true;
            case "dying":
                setCurrent("die");
                return true;
            case "lying":
                setCurrent("lie");
                return true;
            case "tying":
                setCurrent("tie");
                return true;
            case "idly":
                setCurrent("idl");
                return true;
            case "gently":
                setCurrent("gentl");
                return true;
            case "ugly":
                setCurrent("ugli");
                return true;
            case "early":
                setCurrent("earli");
                return true;
            case "only":
                setCurrent("onli");
                return true;
            case "singly":
                setCurrent("singl");
                return true;
            case "sky":
                return true;
            case "news":
                return true;
            case "howe":
                return true;
            case "atlas":
                return true;
            case "cosmos":
                return true;
            case "bias":
                return true;
            case "andes":
                return true;
            default:
                return false;
        }
    }

    private boolean hasVowel(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i)))
                return true;
        }

        return false;
    }

    private boolean outVWXY(int index) {
        if (index < 0 || index >= current.length())
            return false;
        else {
            char c = current.toString().charAt(index);

            return 'a' <= c && c <= 'z' && c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' && c != 'w' && c != 'x' && c != 'y' && c != 'Y';
        }
    }

    private boolean outV(int index) {
        if (index < 0 || index >= current.length())
            return false;
        else {
            char c = current.toString().charAt(index);

            return ('a' <= c && c <= 'z' && c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') || (c == 'Y');
        }
    }

    private boolean inV(int index) {
        if (index < 0 || index >= current.length())
            return false;
        else {
            char c = current.toString().charAt(index);

            return isVowel(c);
        }
    }

    public boolean stem() {
        if (!exception1()) {
            if (current.toString().length() >= 3) {
                setCurrent(prelude());
                markRegions();
                setCurrent(step0());
                setCurrent(step1a());

                if (!exception2()) {
                    setCurrent(step1b());
                    setCurrent(step1c());
                    setCurrent(step2T());
                    setCurrent(step3T());
                    setCurrent(step4T());
                    setCurrent(step5());
                }
                setCurrent(postlude());
            }
        }

        return true;
    }

    public void setCurrent(String value) {
        current.replace(0, current.length(), value);
    }

    public String getCurrent() {
        String result = current.toString();
        current = new StringBuffer();
        return result;
    }
}

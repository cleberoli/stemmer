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

import stemmer.Stemmer;

public class EnglishStemmer extends Stemmer {

    protected StringBuffer current;
    private boolean B_Y_found;
    private int I_p2;
    private int I_p1;

    public EnglishStemmer() {
        current = new StringBuffer();
        setCurrent("");
    }

    @Override
    public boolean stem() {
        if (!exception1()) {
            // If the word has two letters or less, leave it as it is.
            if (current.toString().length() >= 3) {
                setCurrent(prelude());
                markRegions();
                setCurrent(step0());
                setCurrent(step1a());

                if (!exception2()) {
                    setCurrent(step1b());
                    setCurrent(step1c());
                    setCurrent(step2());
                    setCurrent(step3());
                    setCurrent(step4());
                    setCurrent(step5());
                }
                setCurrent(postlude());
            }
        }

        return true;
    }

    @Override
    public void setCurrent(String value) {
        current.replace(0, current.length(), value);
    }

    @Override
    public String getCurrent() {
        String result = current.toString();
        current = new StringBuffer();
        return result;
    }

    /*
     * Define a vowel as one of
     * a   e   i   o   u   y
     */
    @Override
    protected boolean isVowel(char c) {
        return ((c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u') || (c == 'y'));
    }

    /*
     * Define a double as one of
     * bb   dd   ff   gg   mm   nn   pp   rr   tt
     */
    protected boolean endsWithDouble(String s) {
        return (s.endsWith("bb") || s.endsWith("dd") || s.endsWith("ff") || s.endsWith("gg") || s.endsWith("mm") ||
                s.endsWith("nn") || s.endsWith("pp") || s.endsWith("rr") || s.endsWith("tt"));
    }

    /*
     * Define a valid li-ending as one of
     * c   d   e   g   h   k   m   n   r   t
     */
    protected boolean inLI(int index) {
        if (index < 0 || index >= current.length())
            return false;
        else {
            char c = current.toString().charAt(index);

            return c == 'c' || c == 'd' || c == 'e' || c == 'g' || c == 'h' || c == 'k' || c == 'm' || c == 'n' || c == 'r' || c == 't';
        }
    }

    protected boolean outVWXY(int index) {
        if (index < 0 || index >= current.length())
            return false;
        else {
            char c = current.toString().charAt(index);

            return 'a' <= c && c <= 'z' && c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' && c != 'w' && c != 'x' && c != 'y' && c != 'Y';
        }
    }

    protected boolean outV(int index) {
        if (index < 0 || index >= current.length())
            return false;
        else {
            char c = current.toString().charAt(index);

            return ('a' <= c && c <= 'z' && c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') || (c == 'Y');
        }
    }

    protected boolean inV(int index) {
        if (index < 0 || index >= current.length())
            return false;
        else {
            char c = current.toString().charAt(index);

            return isVowel(c);
        }
    }

    protected boolean hasVowel(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i)))
                return true;
        }

        return false;
    }

    /*
     * R1 is the region after the first non-vowel following a vowel, or the end of the word if there is no such non-vowel.
     */
    protected String r1() {
        String str = current.toString();
        return (I_p1 >= str.length()) ? "" : str.substring(I_p1, str.length());
    }

    /*
     * R2 is the region after the first non-vowel following a vowel in R1, or the end of the word if there is no such non-vowel.
     */
    protected String r2() {
        String str = current.toString();
        return (I_p2 >= str.length()) ? "" : str.substring(I_p2, str.length());
    }

    /*
     * Define a short syllable in a word as either (a) a vowel followed by a non-vowel other than w, x or Y and
     * preceded by a non-vowel, or * (b) a vowel at the beginning of the word followed by a non-vowel.
     */
    protected boolean shortV(int index) {
        if ((outVWXY(index - 1)) && (inV(index - 2)) && (outV(index - 3)))
            return true;

        if((outV(index - 1)) && (inV(index - 2)) && (index <= 2))
            return true;

        return false;
    }

    /*
     * Remove initial ', if present. Then, set initial y, or y after a vowel, to Y.
     */
    @Override
    protected String prelude() {
        String str = current.toString();
        B_Y_found = false;

        if (str.startsWith("'"))
            str = str.substring(1, str.length());

        if (str.startsWith("y")) {
            str = "Y" + str.substring(1, str.length());
            B_Y_found = true;
        }

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == 'y' && isVowel(str.charAt(i - 1))) {
                str = str.substring(0, i) + "Y" + str.substring(i + 1, str.length());
                B_Y_found = true;
            }
        }

        return str;
    }

    /*
     * Establish the regions R1 and R2
     */
    @Override
    protected void markRegions() {
        String str = current.toString();
        I_p1 = 0;
        I_p2 = 0;

        // If the words begins gener, commun or arsen, set R1 to be the remainder of the word.
        if (str.startsWith("gener") || str.startsWith("arsen"))
            I_p1 = 5;
        else if (str.startsWith("commun"))
            I_p1 = 6;
        else {
            for (int i = 0; i < str.length() - 1; i++) {
                if (isVowel(str.charAt(i)) && !isVowel(str.charAt(i + 1))) {
                    I_p1 = i + 2;
                    break;
                }
            }
        }

        if (I_p1 == 0 || I_p1 > str.length())
            I_p1 = str.length();

        if (I_p1 >= str.length() - 1)
            I_p2 = str.length();
        else {
            for (int i = I_p1; i < str.length() - 1; i++) {
                if (isVowel(str.charAt(i)) && !isVowel(str.charAt(i + 1))) {
                    I_p2 = i + 2;
                    break;
                }
            }
        }

        if (I_p2 == 0 || I_p2 > str.length())
            I_p2 = str.length();
    }

    /*
     * Finally, turn any remaining Y letters in the word back into lower case.
     */
    @Override
    protected String postlude() {
        String str = current.toString();
        return B_Y_found ? str.replace('Y', 'y') : str;
    }

    /*
     * Following step 1a, leave the following invariant,
     * inning  		  outing  		  canning  		  herring
     * earring        proceed  		  exceed  		  succeed
     */
    protected boolean exception2() {
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
    protected boolean exception1() {
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

    /*
     * Search for the longest among the suffixes,
     * '
     * 's
     * 's'
     * and remove if found.
     */
    protected String step0() {
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
    protected String step1a() {
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
    protected String step1b() {
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
    protected String step1c() {
        String str = current.toString();

        if (str.length() > 2) {
            if ((str.endsWith("y") || str.endsWith("Y")) && !isVowel(str.charAt(str.length() - 2)))
                return str.substring(0, str.length() - 1) + "i";
            else
                return str;
        } else
            return str;
    }

    protected String step2() {
        return current.toString();
    }

    protected String step3() {
        return current.toString();
    }

    protected String step4() {
        return current.toString();
    }

    /*
     * Search for the the following suffixes, and, if found, perform the action indicated.
     * e    ->      delete if in R2, or in R1 and not preceded by a short syllable
     * l    ->      delete if in R2 and preceded by l
     */
    protected String step5() {
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
}

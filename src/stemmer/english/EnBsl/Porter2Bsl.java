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

package stemmer.english.EnBsl;

import stemmer.Stemmer;

public class Porter2Bsl extends Stemmer {

    protected StringBuffer current;
    private boolean B_Y_found;
    private int I_p2;
    private int I_p1;

    public Porter2Bsl() {
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
        return (I_p1 >= str.length()) ? "" : str.substring(I_p1, str.length());
    }

    /*
     * R2 is the region after the first non-vowel following a vowel in R1, or the end of the word if there is no such non-vowel.
     */
    private String r2() {
        String str = current.toString();
        return (I_p2 >= str.length()) ? "" : str.substring(I_p2, str.length());
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
    private void markRegions() {
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
        return B_Y_found ? str.replace('Y', 'y') : str;
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

    private boolean hasVowel(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i)))
                return true;
        }

        return false;
    }

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

    public void setCurrent(String value) {
        current.replace(0, current.length(), value);
    }

    public String getCurrent() {
        String result = current.toString();
        current = new StringBuffer();
        return result;
    }
}

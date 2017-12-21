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

public class BSLEnglishStemmer extends EnglishStemmer {

    public BSLEnglishStemmer() {
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
    @Override
    protected String step3() {
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
    @Override
    protected String step4() {
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

}

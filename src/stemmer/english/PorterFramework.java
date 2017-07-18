package stemmer.english;

public class PorterFramework {

    private static final int STOP = -1;

    public static String toLowerCase(String str) {
        String lowerCaseString = "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
                lowerCaseString += (char)(str.charAt(i) + 32);
            else
                lowerCaseString += str.charAt(i);
        }

        return lowerCaseString;
    }

    /**
     * Letter A, E, I, O or U
     */
    public static boolean isVowel (char c) {
        return ((c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u'));
    }

    /**
     * A \consonant\ in a word is a letter other than A, E, I, O or U, and other
     * than Y preceded by a consonant. (The fact that the term `consonant' is
     * defined to some extent in terms of itself does not make it ambiguous.) So in
     * TOY the consonants are T and Y, and in SYZYGY they are S, Z and G. If a
     * letter is not a consonant it is a \vowel\.
     */
    public static boolean hasVowel(String str) {
/*        char[] strchars = str.toCharArray();

        for (int i = 0; i < strchars.length; i++) {
            if (isVowel(strchars[i]))
                return true;
        }

        return str.indexOf('y') > -1;*/
        return hasVowel(str, str.length() - 1);
    }

    /**
     * A \consonant\ in a word is a letter other than A, E, I, O or U, and other
     * than Y preceded by a consonant. (The fact that the term `consonant' is
     * defined to some extent in terms of itself does not make it ambiguous.) So in
     * TOY the consonants are T and Y, and in SYZYGY they are S, Z and G. If a
     * letter is not a consonant it is a \vowel\.
     */
    public static boolean hasVowel(String str, int position){

        int state = 1;
        boolean vowel = false;
        char letter;

        while (position >= 0) {
            switch (state) {
                case 1 :
                    letter = str.charAt(position);

                    if (isVowel(letter)) {
                        vowel = true;
                        position = STOP;
                    } else if (letter == 'y')
                        state = 2;

                    position--;
                    break;
                case 2 :
                    letter = str.charAt(position);

                    if (letter != 'y') {
                        vowel = true;
                        position = STOP;
                    }

                    position--;
                    break;
            }
        }

        return vowel;
    }

    /**
     * A consonant will be denoted by c, a vowel by v. A list ccc... of length
     * greater than 0 will be denoted by C, and a list vvv... of length greater
     * than 0 will be denoted by V. Any word, or part of a word, therefore has one
     * of the four forms:
     *
     *  CVCV ... C
     *  CVCV ... V
     *  VCVC ... C
     *  VCVC ... V
     *
     * These may all be represented by the single form
     *
     *  [C]VCVC ... [V]
     *
     * where the square brackets denote arbitrary presence of their contents.
     * Using (VC){m} to denote VC repeated m times, this may again be written as
     *
     *  [C](VC){m}[V].
     *
     * m will be called the \measure\ of any word or word part when represented in
     * this form.
     */
    public static int stringMeasure (String str) {
        int count = 0;
        boolean vowelSeen = false;
        char[] strchars = str.toCharArray();

        for (int i = 0; i < strchars.length; i++) {
            if (isVowel(strchars[i])) {
                vowelSeen = true;
            } else if (vowelSeen) {
                count++;
                vowelSeen = false;
            }
        }
        return count;
    }

    public static boolean endsWithCVC (String str) {
        char c, v, c2;

        if (str.length() >= 3) {
            c = str.charAt(str.length() - 1);
            v = str.charAt(str.length() - 2);
            c2 = str.charAt(str.length() - 3);
        } else
            return false;


        if ((c == 'w') || (c == 'x') || (c == 'y'))
            return false;
        else if (isVowel(c))
            return false;
        else if (!isVowel(v))
            return false;
        else if (isVowel(c2))
            return false;
        else
            return true;
    }

    public static boolean endsWithDoubleConsonant(String str) {
        if (str.charAt(str.length() - 1) == str.charAt(str.length() - 2) &&
                !hasVowel(str.substring(str.length() - 2)))
            return true;
        else
            return false;
    }

}

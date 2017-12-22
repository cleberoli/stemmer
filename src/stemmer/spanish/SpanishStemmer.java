package stemmer.spanish;

import stemmer.Stemmer;

public class SpanishStemmer extends Stemmer {

    protected StringBuffer current;
    private int I_p2;
    private int I_p1;
    private int I_pV;

    public SpanishStemmer() {
        current = new StringBuffer();
        setCurrent("");
    }

    @Override
    public boolean stem() {
        String s;
        markRegions();
        setCurrent(attachedPronoun());

        s = current.toString();
        setCurrent(standardSuffix());

        if (s.equals(current.toString())) {
            s = current.toString();
            setCurrent(yVerbSuffix());

            if (s.equals(current.toString()))
                setCurrent(verbSuffix());
        }

        setCurrent(residualSuffix());
        setCurrent(postlude());

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

    @Override
    protected boolean isVowel(char c) {
        return ((c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u') || (c == 'á') || (c == 'é') || (c == 'í') || (c == 'ó') || (c == 'ú') || (c == 'ü'));
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

    protected String rV() {
        String str = current.toString();
        return (I_pV >= str.length()) ? "" : str.substring(I_pV, str.length());
    }

    @Override
    protected String prelude() {
        return current.toString();
    }

    @Override
    protected void markRegions() {
        I_pV = current.length();
        I_p1 = current.length();
        I_p2 = current.length();

        String str = current.toString();

        if (str.length() >= 2) {
            if (!isVowel(str.charAt(1))) {
                for (int i = 2; i < str.length(); i++)
                    if (isVowel(str.charAt(i))) {
                        I_pV = ((i + 1) > str.length()) ? str.length() : i + 1;
                        break;
                    }
            } else if (isVowel(str.charAt(0)) && isVowel(str.charAt(1)))
                for (int i = 2; i < str.length(); i++) {
                    if (!isVowel(str.charAt(i))) {
                        I_pV = ((i + 1) > str.length()) ? str.length() : i + 1;
                        break;
                    }
                }
            else
                I_pV = (3 > str.length()) ? str.length() : 3;

        }


        for (int i = 0; i < str.length() - 1; i++) {
            if (isVowel(str.charAt(i)) && !isVowel(str.charAt(i + 1))) {
                I_p1 = ((i + 2) > str.length()) ? str.length() : i + 2;
                break;
            }
        }

        if (I_p1 >= str.length() - 1)
            I_p2 = str.length();
        else {
            for (int i = I_p1; i < str.length() - 1; i++) {
                if (isVowel(str.charAt(i)) && !isVowel(str.charAt(i + 1))) {
                    I_p2 = ((i + 2) > str.length()) ? str.length() : i + 2;
                    break;
                }
            }
        }
    }

    @Override
    protected String postlude() {
        String str = current.toString();
        StringBuilder sb = new StringBuilder(str.length());

        for (char c : str.toCharArray()) {
            if (c == 'á')
                sb.append('a');
            else if (c == 'é')
                sb.append('e');
            else if (c == 'í')
                sb.append('i');
            else if (c == 'ó')
                sb.append('o');
            else if (c == 'ú')
                sb.append('u');
            else
                sb.append(c);
        }

        return sb.toString();
    }

    protected String attachedPronoun() {
        String str = current.toString();
        String rv = rV();
        int suffixSize = 0;

        if (str.endsWith("selas")) {
            suffixSize = 5;
        } else if (str.endsWith("selos")) {
            suffixSize = 5;
        } else if (str.endsWith("sela")) {
            suffixSize = 4;
        } else if (str.endsWith("selo")) {
            suffixSize = 4;
        } else if (str.endsWith("las") && !str.endsWith("selas")) {
            suffixSize = 3;
        } else if (str.endsWith("los") && !str.endsWith("selos")) {
            suffixSize = 3;
        } else if (str.endsWith("les")) {
            suffixSize = 3;
        } else if (str.endsWith("nos")) {
            suffixSize = 3;
        } else if (str.endsWith("la") && !str.endsWith("sela")) {
            suffixSize = 2;
        } else if (str.endsWith("lo") && !str.endsWith("selo")) {
            suffixSize = 2;
        } else if (str.endsWith("le")) {
            suffixSize = 2;
        } else if (str.endsWith("me")) {
            suffixSize = 2;
        } else if (str.endsWith("se")) {
            suffixSize = 2;
        }

        rv = (suffixSize >= rv.length()) ? "" : rv.substring(0, rv.length() - suffixSize);

        if (suffixSize >= 2) {
            if (rv.endsWith("iéndo")) {
                return str.substring(0, str.length() - suffixSize - 5) + "iendo";
            } else if (rv.endsWith("ándo")) {
                return str.substring(0, str.length() - suffixSize - 4) + "ando";
            } else if (rv.endsWith("ár")) {
                return str.substring(0, str.length() - suffixSize - 2) + "ar";
            } else if (rv.endsWith("ér")) {
                return str.substring(0, str.length() - suffixSize - 2) + "er";
            } else if (rv.endsWith("ír")) {
                return str.substring(0, str.length() - suffixSize - 2) + "ir";
            } else if (rv.endsWith("iendo") || rv.endsWith("ando") || rv.endsWith("ar") || rv.endsWith("er") || rv.endsWith("ir")) {
                return str.substring(0, str.length() - suffixSize);
            } else if (rv.endsWith("yendo") && str.contains("uyendo")) {
                return str.substring(0, str.length() - suffixSize);
            }
        }

        return str;
    }

    protected String standardSuffix() {
        return current.toString();
    }

    protected String yVerbSuffix() {
        String str = current.toString();
        String rv = rV();

        if ((rv.endsWith("yeron") && str.endsWith("uyeron")) ||
                (rv.endsWith("yendo") && str.endsWith("uyendo")) ||
                (rv.endsWith("yamos") && str.endsWith("uyamos"))) {
            return str.substring(0, str.length() - 5);
        } else if ((rv.endsWith("yais") && str.endsWith("uyais"))) {
            return str.substring(0, str.length() - 4);
        } else if ((rv.endsWith("yan") && str.endsWith("uyan")) ||
                (rv.endsWith("yen") && str.endsWith("uyen")) ||
                (rv.endsWith("yas") && str.endsWith("uyas")) ||
                (rv.endsWith("yes") && str.endsWith("uyes"))) {
            return str.substring(0, str.length() - 3);
        } else if ((rv.endsWith("ya") && str.endsWith("uya")) ||
                (rv.endsWith("ye") && str.endsWith("uye")) ||
                (rv.endsWith("yo") && str.endsWith("uyo")) ||
                (rv.endsWith("yó") && str.endsWith("uyó"))) {
            return str.substring(0, str.length() - 2);
        }

        return str;
    }

    protected String verbSuffix() {
        return current.toString();
    }

    protected String residualSuffix() {
        String str = current.toString();
        String rv = rV();

        if (rv.endsWith("os")) {
            return str.substring(0, str.length() - 2);
        } else if (rv.endsWith("a") || rv.endsWith("o") || rv.endsWith("á") || rv.endsWith("í") || rv.endsWith("ó")) {
            return str.substring(0, str.length() - 1);
        } else if (str.endsWith("gue") || str.endsWith("gué")) {
            if (rv.endsWith("ue") || rv.endsWith("ué")) {
                return str.substring(0, str.length() - 2);
            }
        } else if (rv.endsWith("e") || rv.endsWith("é")) {
            return str.substring(0, str.length() - 1);
        }

        return str;
    }
}

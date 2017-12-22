package stemmer.portuguese;

import stemmer.Stemmer;

public class PortugueseStemmer extends Stemmer {

    protected StringBuffer current;
    private int I_p2;
    private int I_p1;
    private int I_pV;

    protected boolean s1;
    protected boolean s2;

    public PortugueseStemmer() {
        current = new StringBuffer();
        setCurrent("");
    }

    @Override
    public boolean stem() {
        setCurrent(prelude());
        markRegions();
        setCurrent(standardSuffix());

        if (!s1)
            setCurrent(verbSuffix());

        if (s1 || s2)
            setCurrent(step3());
        else
            setCurrent(residualSuffix());

        setCurrent(residualForm());
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
        return ((c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u') || (c == 'á') || (c == 'é') || (c == 'í') || (c == 'ó') || (c == 'ú') || (c == 'â') || (c == 'ê') || (c == 'ô') || (c == 'ã') || (c == 'õ'));
    }

    protected String r1() {
        String str = current.toString();
        return (I_p1 >= str.length()) ? "" : str.substring(I_p1, str.length());
    }

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
        String str = current.toString();
        String newString = "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'ã')
                newString += "a~";
            else if (str.charAt(i) == 'õ')
                newString += "o~";
            else
                newString += str.charAt(i);
        }

        return newString;
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
        String newString = "";

        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == 'a' && str.charAt(i + 1) == '~') {
                newString += 'ã';
                i++;
            } else if (str.charAt(i) == 'o' && str.charAt(i + 1) == '~') {
                newString += 'õ';
                i++;
            } else
                newString += str.charAt(i);
        }

        if (str.charAt(str.length() - 1) != '~')
            newString = newString + str.charAt(str.length() - 1);

        return newString;
    }

    protected String standardSuffix() {
        return current.toString();
    }

    protected String verbSuffix() {
        return current.toString();
    }

    protected String step3() {
        String str = current.toString();
        String rv = rV();

        if (rv.endsWith("i") && str.endsWith("ci"))
            str = str.substring(0, str.length() - 1);

        return str;
    }

    protected String residualSuffix() {
        String str = current.toString();
        String rv = rV();

        if (rv.endsWith("os"))
            str = str.substring(0, str.length() - 2);
        else if (rv.endsWith("a") || rv.endsWith("i") || rv.endsWith("o") || rv.endsWith("á") || rv.endsWith("í") || rv.endsWith("ó"))
            str = str.substring(0, str.length() - 1);

        return str;
    }

    protected String residualForm() {
        String str = current.toString();
        String rv = rV();

        if (rv.endsWith("e") || rv.endsWith("é") || rv.endsWith("ê")) {
            str = str.substring(0, str.length() - 1);

            if (str.endsWith("gu") && (rv.endsWith("ue") || rv.endsWith("ué") || rv.endsWith("uê")))
                str = str.substring(0, str.length() - 1);
            else if (str.endsWith("ci") && (rv.endsWith("ie") || rv.endsWith("ié") || rv.endsWith("iê")))
                str = str.substring(0, str.length() - 1);

        } else if (str.endsWith("ç"))
            str = str.substring(0, str.length() - 1) + 'c';

        return str;
    }
}

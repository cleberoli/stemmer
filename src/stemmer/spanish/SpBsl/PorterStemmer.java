package stemmer.spanish.SpBsl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class PorterStemmer extends Stemmer {

    private static final long serialVersionUID = 1L;

    String r1_txt;
    String r2_txt;
    String rv_txt;
    int r1, r2, rv;


    public String stem(String str) {

        int len = str.length();

        this.r1 = this.r2 = this.rv = len;

        // check for zero length
        if (len > 3) {
            // all characters must be letters
            char[] c = str.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (!Character.isLetter(c[i])) {
                    return str.toLowerCase();
                }
            }
        } else {
            return str.toLowerCase();
        }

        str = str.toLowerCase();

        // R1 is the region after the first non-vowel following a vowel, or is
        // the null region at the end of the str if there is no such non-vowel.
        for (int i = 0; i < (len - 1) && r1 == len; i++) {
            if (is_vowel(str.charAt(i)) && !is_vowel(str.charAt(i + 1))) {
                this.r1 = i + 2;
            }
        }

        // R2 is the region after the first non-vowel following a vowel in R1,
        // or is the null region at the end of the str if there is no such
        // non-vowel.
        for (int i = this.r1; i < (len - 1) && this.r2 == len; i++) {
            if (is_vowel(str.charAt(i)) && !is_vowel(str.charAt(i + 1))) {
                this.r2 = i + 2;
            }
        }

        if (len > 3) {
            if (!is_vowel(str.charAt(1))) {
                // If the second letter is a consonant, RV is the region after
                // the next following vowel
                this.rv = getNextVowelPos(str, 2) + 1;
            } else if (is_vowel(str.charAt(0)) && is_vowel(str.charAt(1))) {
                // or if the first two letters are vowels, RV is the region
                // after the next consonant
                this.rv = getNextConsonantPos(str, 2) + 1;
            } else {
                // otherwise (consonant-vowel case) RV is the region after the
                // third letter. But RV is the end of the str if these
                // positions cannot be found.
                this.rv = 3;
            }
        }

        this.r1_txt = substr(str, this.r1);
        this.r2_txt = substr(str, this.r2);
        this.rv_txt = substr(str, this.rv);

        str = step0(str);

        String aux = str;
        this.r1_txt = substr(str, this.r1);
        this.r2_txt = substr(str, this.r2);
        this.rv_txt = substr(str, this.rv);

        str = step1(str);
        if (aux.equals(str)) {
            str = step2a(str);
            aux = str;
            if (aux.equals(str)) {
                str = step2b(str);
                aux = str;
                if (aux.equals(str)) {
                    this.r1_txt = substr(str, this.r1);
                    this.r2_txt = substr(str, this.r2);
                    this.rv_txt = substr(str, this.rv);
                }
            }

        }

        this.r1_txt = substr(str, this.r1);
        this.r2_txt = substr(str, this.r2);
        this.rv_txt = substr(str, this.rv);
        return step3(str);

    } // end stem

    protected String step0 (String str) {


        String[] pronoun_suf = array("me", "se", "sela", "selo", "selas","selos", "la", "le", "lo", "las", "les", "los", "nos");
        String[] pronoun_suf_pre1 = array("iéndo", "ándo", "ár", "ér", "ír");
        String[] pronoun_suf_pre2 = array("ando", "iendo", "ar", "er", "ir");
        String suf = endsinArr(str, pronoun_suf);

        if (!suf.equals("")) {
            String pre_suff = endsinArr(substr(rv_txt, 0, -suf.length()),
                    pronoun_suf_pre1);
            if (!pre_suff.equals("")) {
                {
                    String retorno = removeAccent(substr(str, 0, -suf.length()));
                    return retorno;
                }
            } else {
                pre_suff = endsinArr(substr(rv_txt, 0, -suf.length()),
                        pronoun_suf_pre2);
                if (!pre_suff.equals("")
                        || (endsin(str, "yendo") && (substr(str,
                        -suf.length() - 6, 1).equals("u")))) {
                    String retorno = substr(str, 0, -suf.length());
                    return retorno;
                }
            }
        }

        return str;
    } // end step0

    protected String step1(String str) {

        String suf = "";
        if (!(suf = endsinArr(this.r2_txt, array("anza", "anzas", "ico", "ica",
                "icos", "icas", "ismo", "ismos", "able", "ables", "ible",
                "ibles", "ista", "istas", "oso", "osa", "osos", "osas",
                "amiento", "amientos", "imiento", "imientos"))).equals("")) {
            String retorno = substr(str, 0, -suf.length());

            return retorno;
            //case 1.1
        } else if (!(suf = endsinArr(this.r2_txt, array("icadora", "icador",
                "icación", "icadoras", "icadores", "icaciones", "icante",
                "icantes", "icancia", "icancias", "adora", "ador", "ación",
                "adoras", "adores", "aciones", "ante", "antes", "ancia", "ancias"))).equals("")) {
            String retorno =  substr(str, 0, -suf.length());

            return retorno;
            //case 1.2
        } else if (!(suf = endsinArr(this.r2_txt, array("logía", "logías"))).equals("")) {
            String retorno = substr(str, 0, -suf.length()) + "log";

            return retorno;
        } else if (!(suf = endsinArr(this.r2_txt, array("ución", "uciones"))).equals("")) {
            String retorno = substr(str, 0, -suf.length()) + "u";

            return retorno;
        } else if (!(suf = endsinArr(this.r2_txt, array("encia", "encias"))).equals("")) {
            String retorno = substr(str, 0, -suf.length()) + "ente";

            return retorno;
        } else if (!(suf = endsinArr(this.r2_txt, array("ativamente", "ivamente",
                "osamente", "icamente", "adamente"))).equals("")) {
            String retorno = substr(str, 0, -suf.length());

            return retorno;
        } else if (!(suf = endsinArr(this.r1_txt, array("amente"))).equals("")) {
            String retorno = substr(str, 0, -suf.length());

            return retorno;
        } else if (!(suf = endsinArr(this.r2_txt, array("antemente", "ablemente",
                "iblemente", "mente"))).equals("")) {
            String retorno = substr(str, 0, -suf.length());

            return retorno;
        } else if (!(suf = endsinArr(this.r2_txt, array("abilidad", "abilidades",
                "icidad", "icidades", "ividad", "ividades", "idad", "idades"))).equals("")) {
            String retorno = substr(str, 0, -suf.length());

            return retorno;
        } else if (!(suf = endsinArr(this.r2_txt, array("ativa", "ativo", "ativas",
                "ativos", "iva", "ivo", "ivas", "ivos"))).equals("")) {
            String retorno = substr(str, 0, -suf.length());

            return retorno;
        }

        return str;
    } // end step1

    protected String step2a(String str) {

        String suf = "";
        // Do step 2a if no ending was removed by step 1.
        if ((!(suf = endsinArr(this.rv_txt,
                array("ya", "ye", "yan", "yen", "yeron", "yendo", "yo",
                        "yó", "yas", "yes", "yais", "yamos"))).equals(""))
                && (substr(str, -suf.length() - 1, 1).equals("u"))) {
            return substr(str, 0, -suf.length());
        }
        return str;
    } // end step2a

    protected String step2b(String str) {

        String suf = "";
        if (!(suf = endsinArr(this.rv_txt, array("arían", "arías",
                "arán", "arás", "aríais", "aría", "aréis", "aríamos",
                "aremos", "ará", "aré", "erían", "erías", "erán",
                "erás", "eríais", "ería", "eréis", "eríamos", "eremos",
                "erá", "eré", "irían", "irías", "irán", "irás",
                "iríais", "iría", "iréis", "iríamos", "iremos", "irá",
                "iré", "aba", "ada", "ida", "ía", "ara", "iera", "ad",
                "ed", "id", "ase", "iese", "aste", "iste", "an",
                "aban", "ían", "aran", "ieran", "asen", "iesen",
                "aron", "ieron", "ado", "ido", "ando", "iendo", "ió",
                "ar", "er", "ir", "as", "abas", "adas", "idas", "ías",
                "aras", "ieras", "ases", "ieses", "ís", "áis", "abais",
                "íais", "arais", "ierais", "aseis", "ieseis", "asteis",
                "isteis", "ados", "idos", "amos", "ábamos", "íamos",
                "imos", "áramos", "iéramos", "iésemos", "ásemos"))).equals("")) {
            str = substr(str, 0, -suf.length());
        } else if (!(suf = endsinArr(this.rv_txt, array("en", "es", "éis", "emos"))).equals("")) {
            str = substr(str, 0, -suf.length());
            if (endsin(str, "gu")) {
                str = substr(str, 0, -1);
            }
        }

        return str;
    } // end step2b

    protected String step3(String str) {


        String suf ="";

        if (!(suf = endsinArr(this.rv_txt, array("os", "a", "o", "á", "í", "ó")))
                .equals("")) {
            str = substr(str, 0, -suf.length());
        } else if (!(suf = endsinArr(this.rv_txt, array("e", "é"))).equals("")) {
            str = substr(str, 0, -1);
            rv_txt = substr(str, this.rv);
            if (endsin(this.rv_txt, "u") && endsin(str, "gu")) {
                str = substr(str, 0, -1);
            }
        }


        return removeAccent(str);

    } // end step1c

    /*
     * ------------------------------------------------------- The following are
     * functions to help compute steps 1 - 3
     * -------------------------------------------------------
     */


    /**
     * Retorna un array de String dados muchos Strings
     */
    private String[] array(String... strings) {
        return strings;
    }


    /**
     * Determina si el sufijo esta presente en una palabra
     */
    private boolean endsin(String word, String suffix) {
        if (word.length() < suffix.length()) {
            return false;
        }
        return (substr(word, -suffix.length()).equals(suffix));
    }



    /**
     *Retorna una subcadena de un String
     */
    private String substr(String str, int beginIndex, int length) {
        if (beginIndex == length) {
            return "";

        } else {

            if ((beginIndex >= 0)) { // incio positivo
                int endIndex;
                if ((length >= 0)) { // longitud positiva
                    endIndex = beginIndex + length;
                    if (endIndex > str.length()) {
                        str = str.substring(beginIndex, str.length());
                        return str;
                    } else {
                        str = str.substring(beginIndex, endIndex);
                        return str;
                    }
                } else { // longitud negativa
                    endIndex = str.length() + length;
                    try {
                        str = str.substring(beginIndex, endIndex);
                    } catch (StringIndexOutOfBoundsException e) {
                        str = "";
                    }
                    return str;
                }

            } else {// incio negativo
                int endIndex;
                int newBeginIndex;
                if ((length >= 0)) { // longitud positiva
                    newBeginIndex = str.length() + beginIndex;
                    endIndex = newBeginIndex + length;
                    if (endIndex > str.length()) {
                        str = str.substring(newBeginIndex, str.length());
                        return str;
                    } else {
                        str = str.substring(newBeginIndex, endIndex);
                        return str;
                    }
                } else { // longitud negativa
                    newBeginIndex = str.length() + beginIndex;
                    endIndex = str.length() + length;

                    try {
                        str = str.substring(newBeginIndex, endIndex);
                    } catch (StringIndexOutOfBoundsException e) {
                        str = "";
                    }

                    return str;
                }
            }

        }

    }


    /**
     * Retorna una subcadena de un String
     */
    private String substr(String str, int beginIndex) {
        if (Math.abs(beginIndex) > str.length()) {
            return str;
        } else if (beginIndex >= 0) {
            return str.substring(beginIndex, str.length());
        } else {
            return str.substring(str.length() + beginIndex, str.length());
        }
    }

    /**
     * Retorna una palabra sin acentos
     */
    private String removeAccent(String str) {

        return str.replaceAll("á", "a").replaceAll("é", "e")
                .replaceAll("í", "i").replaceAll("ó", "o")
                .replaceAll("ú", "u");

    }

    /**
     * Determina si el caracter es una vocal.
     */
    private boolean is_vowel(char c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'á' || c == 'é' || c == 'í' || c == 'ó' || c == 'ú');
    }

    /**
     * Retorna la posicion de la primera vocal encontrada
     */
    private int getNextVowelPos(String str, int start) {
        int len = str.length();
        for (int i = start; i < len; i++) {
            if (is_vowel(str.charAt(i))) {
                return i;
            }
        }
        return len;
    }


    /**
     * Retorna la posicion de la primera consonante encontrada
     */
    private int getNextConsonantPos(String str, int start) {
        int len = str.length();
        for (int i = start; i < len; i++) {
            if (!is_vowel(str.charAt(i))) {
                return i;
            }
        }
        return len;
    }


    /**
     * Retorna el sufijo mas largo presente en una palabra
     */
    private String endsinArr(String str, String[] suffixes) {
        String tmp = "";
        for (String suff : suffixes) {
            if (endsin(str, suff)) {
                if (suff.length() >= tmp.length()) {
                    tmp = suff;
                }
            }
        }
        return tmp;
    }



    public void test() throws IOException {

        String file = "wiki_00_es.txt";
        String encoding = "UTF-8";
        BufferedReader input;


        try {
            input = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), encoding));
            String linea;
            while (input.ready()) {
                String array[] = new String[2];
                linea = input.readLine();
                array = linea.split(" ");
                if (array.length >= 1) {
                    System.out.println(stem(array[0]));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

} // end class
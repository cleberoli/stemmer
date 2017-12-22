package stemmer.spanish;


public class BSLSpanishStemmer extends SpanishStemmer {

    public BSLSpanishStemmer() {
        super();
    }

    @Override
    protected String standardSuffix() {
        String str = current.toString();
        int suffixLength = 0;
        int suffixCase = -1;

        if (str.endsWith("imientos") || str.endsWith("amientos")) {
            suffixLength = 8;
            suffixCase = 0;
        } else if (str.endsWith("imiento") || str.endsWith("amiento")) {
            suffixLength = 7;
            suffixCase = 0;
        } else if (str.endsWith("aciones")) {
            suffixLength = 7;
            suffixCase = 1;
        } else if (str.endsWith("uciones")) {
            suffixLength = 7;
            suffixCase = 3;
        } else if (str.endsWith("ancias") || str.endsWith("adores") || str.endsWith("adoras")) {
            suffixLength = 6;
            suffixCase = 1;
        } else if (str.endsWith("logías")) {
            suffixLength = 6;
            suffixCase = 2;
        } else if (str.endsWith("encias")) {
            suffixLength = 6;
            suffixCase = 4;
        } else if (str.endsWith("amente")) {
            suffixLength = 6;
            suffixCase = 5;
        } else if (str.endsWith("idades")) {
            suffixLength = 6;
            suffixCase = 7;
        } else if (str.endsWith("istas") || str.endsWith("ibles") || str.endsWith("ables") || str.endsWith("ismos") || str.endsWith("anzas")) {
            suffixLength = 5;
            suffixCase = 0;
        } else if (str.endsWith("ancia") || str.endsWith("antes") || str.endsWith("ación") || str.endsWith("adora")) {
            suffixLength = 5;
            suffixCase = 1;
        } else if (str.endsWith("logía")) {
            suffixLength = 5;
            suffixCase = 2;
        } else if (str.endsWith("ución")) {
            suffixLength = 5;
            suffixCase = 3;
        } else if (str.endsWith("encia")) {
            suffixLength = 5;
            suffixCase = 4;
        } else if (str.endsWith("mente")) {
            suffixLength = 5;
            suffixCase = 6;
        } else if (str.endsWith("osas") || str.endsWith("osos") || str.endsWith("ista") || str.endsWith("ible") || str.endsWith("able") || str.endsWith("ismo") || str.endsWith("icas") || str.endsWith("icos") || str.endsWith("anza")) {
            suffixLength = 4;
            suffixCase = 0;
        } else if (str.endsWith("ante") || str.endsWith("ador")) {
            suffixLength = 4;
            suffixCase = 1;
        } else if (str.endsWith("idad")) {
            suffixLength = 4;
            suffixCase = 7;
        } else if (str.endsWith("ivas") || str.endsWith("ivos")) {
            suffixLength = 4;
            suffixCase = 8;
        } else if (str.endsWith("osa") || str.endsWith("oso") || str.endsWith("ica") || str.endsWith("ico")) {
            suffixLength = 3;
            suffixCase = 0;
        } else if (str.endsWith("ivo") || str.endsWith("iva")) {
            suffixLength = 3;
            suffixCase = 8;
        }

        String suffix = str.substring(str.length() - suffixLength, str.length());
        String r2 = r2();
        String r1 = r1();

        switch (suffixCase) {
            case 0:
                if (r2.endsWith(suffix))
                    str = str.substring(0, str.length() - suffixLength);
                break;
            case 1:
                if (r2.endsWith(suffix)) {
                    str = str.substring(0, str.length() - suffixLength);

                    if (str.endsWith("ic")) {
                        if (r2.endsWith("ic" + suffix))
                            str = str.substring(0, str.length() - 2);
                    }
                }
                break;
            case 2:
                if (r2.endsWith(suffix))
                    str = str.substring(0, str.length() - suffixLength) + "log";
                break;
            case 3:
                if (r2.endsWith(suffix))
                    str = str.substring(0, str.length() - suffixLength) + "u";
                break;
            case 4:
                if (r2.endsWith(suffix))
                    str = str.substring(0, str.length() - suffixLength) + "ente";
                break;
            case 5:
                if (r1.endsWith(suffix)) {
                    str = str.substring(0, str.length() - suffixLength);

                    if (str.endsWith("iv")) {
                        if (r2.endsWith("iv" + suffix)) {
                            str = str.substring(0, str.length() - 2);

                            if (str.endsWith("at")) {
                                if (r2.endsWith("ativ" + suffix))
                                    str = str.substring(0, str.length() - 2);
                            }
                        }
                    } else if (str.endsWith("os") || str.endsWith("ic") || str.endsWith("ad")) {
                        if (r2.endsWith("os" + suffix) || r2.endsWith("ic" + suffix) || r2.endsWith("ad" + suffix))
                            str = str.substring(0, str.length() - 2);
                    }

                }
                break;
            case 6:
                if (r2.endsWith(suffix)) {
                    str = str.substring(0, str.length() - suffixLength);

                    if (str.endsWith("ante") || str.endsWith("able") || str.endsWith("ible")) {
                        if (r2.endsWith("ante" + suffix) || r2.endsWith("able" + suffix) || r2.endsWith("ible" + suffix))
                            str = str.substring(0, str.length() - 4);
                    }
                }
                break;
            case 7:
                if (r2.endsWith(suffix)) {
                    str = str.substring(0, str.length() - suffixLength);

                    if (str.endsWith("abil") || str.endsWith("ic") || str.endsWith("iv")) {
                        if (r2.endsWith("abil" + suffix))
                            str = str.substring(0, str.length() - 4);
                        else if (r2.endsWith("ic" + suffix) || r2.endsWith("iv" + suffix))
                            str = str.substring(0, str.length() - 2);
                    }
                }
                break;
            case 8:
                if (r2.endsWith(suffix)) {
                    str = str.substring(0, str.length() - suffixLength);

                    if (str.endsWith("at")) {
                        if (r2.endsWith("at" + suffix))
                            str = str.substring(0, str.length() - 2);
                    }
                }
                break;
        }

        return str;
    }

    @Override
    protected String verbSuffix() {
        String str = current.toString();
        String rv = rV();
        int suffixLength = 0;
        int suffixCase = -1;


        if (rv.endsWith("iésemos") || rv.endsWith("iéramos") || rv.endsWith("iríamos") || rv.endsWith("eríamos") || rv.endsWith("aríamos")) {
            suffixCase = 1;
            suffixLength = 7;
        } else if (rv.endsWith("ásemos") || rv.endsWith("áramos") || rv.endsWith("ábamos") || rv.endsWith("isteis") || rv.endsWith("asteis") || rv.endsWith("ieseis") || rv.endsWith("ierais") || rv.endsWith("iremos") || rv.endsWith("iríais") || rv.endsWith("eremos") || rv.endsWith("eríais") || rv.endsWith("aremos") || rv.endsWith("aríais")) {
            suffixCase = 1;
            suffixLength = 6;
        } else if (rv.endsWith("íamos") || rv.endsWith("aseis") || rv.endsWith("arais") || rv.endsWith("abais") || rv.endsWith("ieses") || rv.endsWith("ieras") || rv.endsWith("iendo") || rv.endsWith("ieron") || rv.endsWith("iesen") || rv.endsWith("ieran") || rv.endsWith("iréis") || rv.endsWith("irías") || rv.endsWith("irían") || rv.endsWith("eréis") || rv.endsWith("erías") || rv.endsWith("erían") || rv.endsWith("aréis") || rv.endsWith("arías") || rv.endsWith("arían")) {
            suffixCase = 1;
            suffixLength = 5;
        } else if (rv.endsWith("emos")) {
            suffixCase = 0;
            suffixLength = 4;
        } else if (rv.endsWith("imos") || rv.endsWith("amos") || rv.endsWith("idos") || rv.endsWith("ados") || rv.endsWith("íais") || rv.endsWith("ases") || rv.endsWith("aras") || rv.endsWith("idas") || rv.endsWith("adas") || rv.endsWith("abas") || rv.endsWith("ando") || rv.endsWith("aron") || rv.endsWith("asen") || rv.endsWith("aran") || rv.endsWith("aban") || rv.endsWith("iste") || rv.endsWith("aste") || rv.endsWith("iese") || rv.endsWith("iera") || rv.endsWith("iría") || rv.endsWith("irás") || rv.endsWith("irán") || rv.endsWith("ería") || rv.endsWith("erás") || rv.endsWith("erán") || rv.endsWith("aría") || rv.endsWith("arás") || rv.endsWith("arán")) {
            suffixCase = 1;
            suffixLength = 4;
        } else if (rv.endsWith("éis")) {
            suffixCase = 0;
            suffixLength = 3;
        } else if (rv.endsWith("áis") || rv.endsWith("ías") || rv.endsWith("ido") || rv.endsWith("ado") || rv.endsWith("ían") || rv.endsWith("ase") || rv.endsWith("ara") || rv.endsWith("ida") || rv.endsWith("ada") || rv.endsWith("aba") || rv.endsWith("iré") || rv.endsWith("irá") || rv.endsWith("eré") || rv.endsWith("erá") || rv.endsWith("aré") || rv.endsWith("ará")) {
            suffixCase = 1;
            suffixLength = 3;
        } else if (rv.endsWith("en") || rv.endsWith("es")) {
            suffixCase = 0;
            suffixLength = 2;
        } else if (rv.endsWith("ís") || rv.endsWith("as") || rv.endsWith("ir") || rv.endsWith("er") || rv.endsWith("ar") || rv.endsWith("ió") || rv.endsWith("an") || rv.endsWith("id") || rv.endsWith("ed") || rv.endsWith("ad") || rv.endsWith("ía")) {
            suffixCase = 1;
            suffixLength = 2;
        }

        switch (suffixCase) {
            case 0:
                str = str.substring(0, str.length() - suffixLength);

                if (str.endsWith("gu"))
                    str = str.substring(0, str.length() - 1);
                break;
            case 1:
                str = str.substring(0, str.length() - suffixLength);
        }

        return str;
    }
}

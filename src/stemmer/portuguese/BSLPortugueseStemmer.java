package stemmer.portuguese;

public class BSLPortugueseStemmer extends PortugueseStemmer {

    public BSLPortugueseStemmer(){
        super();
    }

    @Override
    protected String standardSuffix() {
        String str = current.toString();
        int suffixLength = 0;
        int suffixCase = -1;

        if (str.endsWith("amentos") || str.endsWith("imentos")) {
            suffixLength = 7;
            suffixCase = 0;
        } else if (str.endsWith("uciones")) {
            suffixLength = 7;
            suffixCase = 2;
        } else if (str.endsWith("amento") || str.endsWith("imento") || str.endsWith("adoras") || str.endsWith("adores") || str.endsWith("aço~es")) {
            suffixLength = 6;
            suffixCase = 0;
        } else if (str.endsWith("logías")) {
            suffixLength = 6;
            suffixCase = 1;
        } else if (str.endsWith("ências")) {
            suffixLength = 6;
            suffixCase = 3;
        } else if (str.endsWith("amente")) {
            suffixLength = 6;
            suffixCase = 4;
        } else if (str.endsWith("idades")) {
            suffixLength = 6;
            suffixCase = 6;
        } else if (str.endsWith("ismos") || str.endsWith("istas") || str.endsWith("adora") || str.endsWith("aça~o") || str.endsWith("antes") || str.endsWith("ância")) {
            suffixLength = 5;
            suffixCase = 0;
        } else if (str.endsWith("logía")) {
            suffixLength = 5;
            suffixCase = 1;
        } else if (str.endsWith("ución")) {
            suffixLength = 5;
            suffixCase = 2;
        } else if (str.endsWith("ência")) {
            suffixLength = 5;
            suffixCase = 3;
        } else if (str.endsWith("mente")) {
            suffixLength = 5;
            suffixCase = 5;
        } else if (str.endsWith("idade")) {
            suffixLength = 5;
            suffixCase = 6;
        } else if (str.endsWith("ezas") || str.endsWith("icos") || str.endsWith("icas") || str.endsWith("ismo") || str.endsWith("ável") || str.endsWith("ível") || str.endsWith("ista") || str.endsWith("osos") || str.endsWith("osas") || str.endsWith("ador") || str.endsWith("ante")) {
            suffixLength = 4;
            suffixCase = 0;
        } else if (str.endsWith("ivas") || str.endsWith("ivos")) {
            suffixLength = 4;
            suffixCase = 7;
        } else if (str.endsWith("iras")) {
            suffixLength = 4;
            suffixCase = 8;
        } else if (str.endsWith("eza") || str.endsWith("ico") || str.endsWith("ica") || str.endsWith("oso") || str.endsWith("osa")) {
            suffixLength = 3;
            suffixCase = 0;
        } else if (str.endsWith("iva") || str.endsWith("ivo")) {
            suffixLength = 3;
            suffixCase = 7;
        } else if (str.endsWith("ira")) {
            suffixLength = 3;
            suffixCase = 8;
        }

        String suffix = str.substring(str.length() - suffixLength, str.length());
        String r1 = r1();
        String r2 = r2();
        String rv = rV();

        switch (suffixCase) {
            case 0:
                if (r2.endsWith(suffix))
                    str = str.substring(0, str.length() - suffixLength);
                break;
            case 1:
                if (r2.endsWith(suffix))
                    str = str.substring(0, str.length() - suffixLength) + "log";
                break;
            case 2:
                if (r2.endsWith(suffix))
                    str = str.substring(0, str.length() - suffixLength) + "u";
                break;
            case 3:
                if (r2.endsWith(suffix))
                    str = str.substring(0, str.length() - suffixLength) + "ente";
                break;
            case 4:
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
            case 5:
                if (r2.endsWith(suffix)) {
                    str = str.substring(0, str.length() - suffixLength);

                    if (str.endsWith("ante") || str.endsWith("avel") || str.endsWith("ível")) {
                        if (r2.endsWith("ante" + suffix) || r2.endsWith("avel" + suffix) || r2.endsWith("ível" + suffix))
                            str = str.substring(0, str.length() - 4);
                    }
                }
                break;
            case 6:
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
            case 7:
                if (r2.endsWith(suffix)) {
                    str = str.substring(0, str.length() - suffixLength);

                    if (str.endsWith("at")) {
                        if (r2.endsWith("at" + suffix))
                            str = str.substring(0, str.length() - 2);
                    }
                }
                break;
            case 8:
                if (rv.endsWith(suffix) && str.endsWith("e" + suffix))
                    str = str.substring(0, str.length() - suffixLength) + "ir";
                break;
        }

        s1 = !str.equals(current.toString());
        return str;
    }

    @Override
    protected String verbSuffix() {
        String str = current.toString();
        String rv = rV();
        int suffixLength = 0;

        if (rv.endsWith("íssemos") || rv.endsWith("êssemos") || rv.endsWith("ássemos") || rv.endsWith("iríamos") || rv.endsWith("eríamos") || rv.endsWith("aríamos"))
            suffixLength = 7;
        else if (rv.endsWith("iremos") || rv.endsWith("eremos") || rv.endsWith("aremos") || rv.endsWith("ávamos") || rv.endsWith("íramos") || rv.endsWith("éramos") || rv.endsWith("áramos") || rv.endsWith("ísseis") || rv.endsWith("ésseis") || rv.endsWith("ásseis") || rv.endsWith("iríeis") || rv.endsWith("eríeis") || rv.endsWith("aríeis"))
            suffixLength = 6;
        else if (rv.endsWith("irmos") || rv.endsWith("ermos") || rv.endsWith("armos") || rv.endsWith("íamos") || rv.endsWith("áveis") || rv.endsWith("ireis") || rv.endsWith("íreis") || rv.endsWith("ereis") || rv.endsWith("éreis") || rv.endsWith("areis") || rv.endsWith("áreis") || rv.endsWith("istes") || rv.endsWith("estes") || rv.endsWith("astes") || rv.endsWith("isses") || rv.endsWith("esses") || rv.endsWith("asses") || rv.endsWith("irdes") || rv.endsWith("erdes") || rv.endsWith("ardes") || rv.endsWith("irias") || rv.endsWith("erias") || rv.endsWith("arias") || rv.endsWith("ira~o") || rv.endsWith("era~o") || rv.endsWith("ara~o") || rv.endsWith("issem") || rv.endsWith("essem") || rv.endsWith("assem") || rv.endsWith("iriam") || rv.endsWith("eriam") || rv.endsWith("ariam"))
            suffixLength = 5;
        else if (rv.endsWith("iras") || rv.endsWith("imos") || rv.endsWith("emos") || rv.endsWith("amos") || rv.endsWith("ámos") || rv.endsWith("idos") || rv.endsWith("ados") || rv.endsWith("íeis") || rv.endsWith("ires") || rv.endsWith("eres") || rv.endsWith("ares") || rv.endsWith("avas") || rv.endsWith("irás") || rv.endsWith("eras") || rv.endsWith("erás") || rv.endsWith("aras") || rv.endsWith("arás") || rv.endsWith("idas") || rv.endsWith("adas") || rv.endsWith("indo") || rv.endsWith("endo") || rv.endsWith("ando") || rv.endsWith("irem") || rv.endsWith("erem") || rv.endsWith("arem") || rv.endsWith("avam") || rv.endsWith("iram") || rv.endsWith("eram") || rv.endsWith("aram") || rv.endsWith("irei") || rv.endsWith("erei") || rv.endsWith("arei") || rv.endsWith("iste") || rv.endsWith("este") || rv.endsWith("aste") || rv.endsWith("isse") || rv.endsWith("esse") || rv.endsWith("asse") || rv.endsWith("iria") || rv.endsWith("eria") || rv.endsWith("aria"))
            suffixLength = 4;
        else if (rv.endsWith("ira") || rv.endsWith("eis") || rv.endsWith("ais") || rv.endsWith("ias") || rv.endsWith("ido") || rv.endsWith("ado") || rv.endsWith("iam") || rv.endsWith("ava") || rv.endsWith("irá") || rv.endsWith("era") || rv.endsWith("erá") || rv.endsWith("ara") || rv.endsWith("ará") || rv.endsWith("ida") || rv.endsWith("ada"))
            suffixLength = 3;
        else if (rv.endsWith("ou") || rv.endsWith("iu") || rv.endsWith("eu") || rv.endsWith("is") || rv.endsWith("es") || rv.endsWith("as") || rv.endsWith("ir") || rv.endsWith("er") || rv.endsWith("ar") || rv.endsWith("em") || rv.endsWith("am") || rv.endsWith("ei") || rv.endsWith("ia"))
            suffixLength = 2;

        s2 = !str.substring(0, str.length() - suffixLength).equals(current.toString());
        return str.substring(0, str.length() - suffixLength);
    }
}

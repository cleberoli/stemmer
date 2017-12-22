package stemmer.spanish;

public class ADSSpanishStemmer extends SpanishStemmer {

    public ADSSpanishStemmer() {
        super();
    }

    @Override
    public String standardSuffix() {
        String str = current.toString();
        String suffix = "";
        char ch;
        int suffixSize = 0;
        int suffixCase = -1;
        int cursor = str.length() - 1;
        int state = 0;

        while (cursor >= 0) {
            ch = str.charAt(cursor);

            switch (state) {
                case 0: // a(1), d,(18) e(21), n(28), o(32), r(43), s(46)
                    switch (ch) {
                        case 'a':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 1;
                            break;
                        case 'd':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 18;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 21;
                            break;
                        case 'n':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 28;
                            break;
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 32;
                            break;
                        case 'r':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 43;
                            break;
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 46;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 1: // c(2), i(3), r(6), s(9), t(10), v(12), z(13), í(15)
                    switch (ch) {
                        case 'c':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 2;
                            break;
                        case 'i':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 3;
                            break;
                        case 'r':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 6;
                            break;
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 9;
                            break;
                        case 't':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 10;
                            break;
                        case 'v':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 12;
                            break;
                        case 'z':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 13;
                            break;
                        case 'í':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 15;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 2: // i(200)
                    switch (ch) {
                        case 'i': // ica
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 3: // c(4)
                    switch (ch) {
                        case 'c':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 4;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 4: // n(5)
                    switch (ch) {
                        case 'n':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 5;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 5: // a(200), e(200)
                    switch (ch) {
                        case 'a': // ancia
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // encia
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 4;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 6: // o(7)
                    switch (ch) {
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 7;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 7: // d(8)
                    switch (ch) {
                        case 'd':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 8;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 8: // a(200)
                    switch (ch) {
                        case 'a': // adora
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 9: // o(200)
                    switch (ch) {
                        case 'o': // adora
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 10: // s(11)
                    switch (ch) {
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 11;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 11: // i(200)
                    switch (ch) {
                        case 'i': // ista
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 12: // i(200)
                    switch (ch) {
                        case 'i': // iva
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 8;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 13: // n(14)
                    switch (ch) {
                        case 'n':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 14;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 14: // a(200)
                    switch (ch) {
                        case 'a': // anza
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 15: // g(16)
                    switch (ch) {
                        case 'g':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 16;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 16: // o(17)
                    switch (ch) {
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 17;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 17: // l(200)
                    switch (ch) {
                        case 'l': // logía
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 2;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 18: // a(19)
                    switch (ch) {
                        case 'a':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 19;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 19: // d(20)
                    switch (ch) {
                        case 'd':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 20;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 20: // i(200)
                    switch (ch) {
                        case 'i': // idad
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 7;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 21: // l(22), t(24)
                    switch (ch) {
                        case 'l':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 22;
                            break;
                        case 't':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 24;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 22: // b(23)
                    switch (ch) {
                        case 'b':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 23;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 23: // a(200), i(200)
                    switch (ch) {
                        case 'a': // able
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // ible
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 24: // n(25)
                    switch (ch) {
                        case 'n':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 25;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 25: // a(200), e(26)
                    switch (ch) {
                        case 'a': // ante
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 26;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 26: // m(27)
                    switch (ch) {
                        case 'm':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 27;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 27: // a(200), ~(200)
                    switch (ch) {
                        case 'a': // amente
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 5;
                            cursor--;
                            state = 200;
                            break;
                        default: // mente
                            suffixCase = 6;
                            state = 200;
                            break;
                    }
                    break;
                case 28: // ó(29)
                    switch (ch) {
                        case 'ó':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 29;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 29: // i(30)
                    switch (ch) {
                        case 'i':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 30;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 30: // c(31)
                    switch (ch) {
                        case 'c':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 31;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 31: // a(200), u(200)
                    switch (ch) {
                        case 'a': // ación
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'u': // ución
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 3;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 32: // c(33), m(34), s(36), t(37), v(42)
                    switch (ch) {
                        case 'c':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 33;
                            break;
                        case 'm':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 34;
                            break;
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 36;
                            break;
                        case 't':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 37;
                            break;
                        case 'v':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 42;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 33: // i(200)
                    switch (ch) {
                        case 'i': // ico
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 34: // s(35)
                    switch (ch) {
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 35;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 35: // i(200)
                    switch (ch) {
                        case 'i': // ismo
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 36: // o(200)
                    switch (ch) {
                        case 'o': // oso
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 37: // n(38)
                    switch (ch) {
                        case 'n':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 38;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 38: // e(39)
                    switch (ch) {
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 39;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 39: // i(40)
                    switch (ch) {
                        case 'i':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 40;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 40: // m(41)
                    switch (ch) {
                        case 'm':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 41;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 41: // a(200), i(200)
                    switch (ch) {
                        case 'a': // amiento
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // imiento
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 42: // i(200)
                    switch (ch) {
                        case 'i': // ivo
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 8;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 43: // o(44)
                    switch (ch) {
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 44;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 44: // d(45)
                    switch (ch) {
                        case 'd':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 45;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 45: // a(200)
                    switch (ch) {
                        case 'a': // ador
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 46: // a(47), e(64), o(79)
                    switch (ch) {
                        case 'a':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 47;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 64;
                            break;
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 79;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 47: // c(48), i(49), r(52), s(55), t(56), v(58), z(59), í(61)
                    switch (ch) {
                        case 'c':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 48;
                            break;
                        case 'i':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 49;
                            break;
                        case 'r':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 52;
                            break;
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 55;
                            break;
                        case 't':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 56;
                            break;
                        case 'v':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 58;
                            break;
                        case 'z':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 59;
                            break;
                        case 'í':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 61;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 48: // i(200)
                    switch (ch) {
                        case 'i': // icas
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 49: // c(50)
                    switch (ch) {
                        case 'c':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 50;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 50: // n(51)
                    switch (ch) {
                        case 'n':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 51;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 51: // a(200), e(200)
                    switch (ch) {
                        case 'a': // ancias
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // encias
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 4;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 52: // o(53)
                    switch (ch) {
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 53;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 53: // d(54)
                    switch (ch) {
                        case 'd':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 54;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 54: // a(200)
                    switch (ch) {
                        case 'a': // adoras
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 55: // o(200)
                    switch (ch) {
                        case 'o': // ancias
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 56: // s(57)
                    switch (ch) {
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 57;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 57: // i(200)
                    switch (ch) {
                        case 'i': // istas
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 58: // i(200)
                    switch (ch) {
                        case 'i': // ivas
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 8;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 59: // n(60)
                    switch (ch) {
                        case 'n':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 60;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 60: // a(200)
                    switch (ch) {
                        case 'a': // anzas
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 61: // g(62)
                    switch (ch) {
                        case 'g':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 62;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 62: // o(63)
                    switch (ch) {
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 63;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 63: // l(200)
                    switch (ch) {
                        case 'l': // logías
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 2;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 64: // d(65), l(68), n(70), r(74), t(77)
                    switch (ch) {
                        case 'd':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 65;
                            break;
                        case 'l':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 68;
                            break;
                        case 'n':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 70;
                            break;
                        case 'r':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 74;
                            break;
                        case 't':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 77;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 65: // a(66)
                    switch (ch) {
                        case 'a':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 66;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 66: // d(67)
                    switch (ch) {
                        case 'd':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 67;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 67: // i(200)
                    switch (ch) {
                        case 'i': // idades
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 7;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 68: // b(69)
                    switch (ch) {
                        case 'b':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 69;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 69: // a(200), i(200)
                    switch (ch) {
                        case 'a': // ables
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // ibles
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 70: // o(71)
                    switch (ch) {
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 71;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 71: // i(72)
                    switch (ch) {
                        case 'i':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 72;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 72: // c(73)
                    switch (ch) {
                        case 'c':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 73;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 73: // a(200), u(200)
                    switch (ch) {
                        case 'a': // aciones
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'u': // uciones
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 3;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 74: // o(75)
                    switch (ch) {
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 75;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 75: // d(76)
                    switch (ch) {
                        case 'd':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 76;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 76: // a(200)
                    switch (ch) {
                        case 'a': // adores
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 77: // n(78)
                    switch (ch) {
                        case 'n':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 78;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 78: // a(200)
                    switch (ch) {
                        case 'a': // antes
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 79: // c(80), m(81), s(83), t(84), v(89)
                    switch (ch) {
                        case 'c':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 80;
                            break;
                        case 'm':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 81;
                            break;
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 83;
                            break;
                        case 't':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 84;
                            break;
                        case 'v':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 89;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 80: // i(200)
                    switch (ch) {
                        case 'i': // icos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 81: // s(82)
                    switch (ch) {
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 82;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 82: // i(200)
                    switch (ch) {
                        case 'i': // ismos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 83: // o(200)
                    switch (ch) {
                        case 'o': // osos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 84: // n(85)
                    switch (ch) {
                        case 'n':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 85;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 85: // e(86)
                    switch (ch) {
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 86;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 86: // i(87)
                    switch (ch) {
                        case 'i':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 87;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 87: // m(88)
                    switch (ch) {
                        case 'm':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 88;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 88: // a(200), i(200)
                    switch (ch) {
                        case 'a': // amientos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // imientos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 89: // i(200)
                    switch (ch) {
                        case 'i': // ivos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 8;
                            cursor--;
                            state = 200;
                            break;
                        default:

                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 200:
                    String r2 = r2();
                    String r1 = r1();

                    switch (suffixCase) {
                        case 0:
                            if (r2.endsWith(suffix))
                                str = str.substring(0, str.length() - suffixSize);
                            break;
                        case 1:
                            if (r2.endsWith(suffix)) {
                                str = str.substring(0, str.length() - suffixSize);

                                if (str.endsWith("ic")) {
                                    if (r2.endsWith("ic" + suffix))
                                        str = str.substring(0, str.length() - 2);
                                }
                            }
                            break;
                        case 2:
                            if (r2.endsWith(suffix))
                                str = str.substring(0, str.length() - suffixSize) + "log";
                            break;
                        case 3:
                            if (r2.endsWith(suffix))
                                str = str.substring(0, str.length() - suffixSize) + "u";
                            break;
                        case 4:
                            if (r2.endsWith(suffix))
                                str = str.substring(0, str.length() - suffixSize) + "ente";
                            break;
                        case 5:
                            if (r1.endsWith(suffix)) {
                                str = str.substring(0, str.length() - suffixSize);

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
                                str = str.substring(0, str.length() - suffixSize);

                                if (str.endsWith("ante") || str.endsWith("able") || str.endsWith("ible")) {
                                    if (r2.endsWith("ante" + suffix) || r2.endsWith("able" + suffix) || r2.endsWith("ible" + suffix))
                                        str = str.substring(0, str.length() - 4);
                                }
                            }
                            break;
                        case 7:
                            if (r2.endsWith(suffix)) {
                                str = str.substring(0, str.length() - suffixSize);

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
                                str = str.substring(0, str.length() - suffixSize);

                                if (str.endsWith("at")) {
                                    if (r2.endsWith("at" + suffix))
                                        str = str.substring(0, str.length() - 2);
                                }
                            }
                            break;
                    }
                    cursor = -1;
                    break;
            }
        }

        return str;
    }

    @Override
    protected String verbSuffix() {
        String str = current.toString();
        String rv = '~' + rV();
        String suffix = "";
        char ch;
        int suffixSize = 0;
        int suffixCase = -1;
        int cursor = rv.length() - 1;
        int state = 0;

        while (cursor >= 0) {
            ch = rv.charAt(cursor);

            switch (state) {
                case 0: // a(1), d(8), e(9), n(14), o(29), r(33), s(34), á(74), é(76), ó(78)
                    switch (ch) {
                        case 'a':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 1;
                            break;
                        case 'd':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 8;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 9;
                            break;
                        case 'n':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 14;
                            break;
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 29;
                            break;
                        case 'r':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 33;
                            break;
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 34;
                            break;
                        case 'á':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 74;
                            break;
                        case 'é':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 76;
                            break;
                        case 'ó':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 78;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 1: // b(2), d(3), r(4), í(6)
                    switch (ch) {
                        case 'b':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 2;
                            break;
                        case 'd':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 3;
                            break;
                        case 'r':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 4;
                            break;
                        case 'í':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 6;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 2: // a(200)
                    switch (ch) {
                        case 'a': // aba
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 3: // a(200), i(200)
                    switch (ch) {
                        case 'a': // ada
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // ida
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 4: // a(200), e(5)
                    switch (ch) {
                        case 'a': // ara
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 5;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 5: // i(200)
                    switch (ch) {
                        case 'i': // iera
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 6: // r(7), ~(200)
                    switch (ch) {
                        case 'r': // ada
                            if (rv.charAt(cursor - 1) == 'a' || rv.charAt(cursor - 1) == 'e' || rv.charAt(cursor - 1) == 'i') {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 7;
                            } else {
                                suffixCase = 1;
                                state = 200;
                            }
                            break;
                        default: // ía
                            suffixCase = 1;
                            state = 200;
                            break;
                    }
                    break;
                case 7: // a(200), e(200), i(200)
                    switch (ch) {
                        case 'a': // aría
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // ería
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // iría
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 8: // a(200), e(200), i(200)
                    switch (ch) {
                        case 'a': // ad
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // ed
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // id
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 9: // s(10), t(12)
                    switch (ch) {
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 10;
                            break;
                        case 't':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 12;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 10: // a(200), e(11)
                    switch (ch) {
                        case 'a': // ase
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // ed
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 11;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 11: // i(200)
                    switch (ch) {
                        case 'i': // iese
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 12: // s(13)
                    switch (ch) {
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 13;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 13: // a(200), i(200)
                    switch (ch) {
                        case 'a': // aste
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // iste
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 14: // a(15), e(21), o(24), á(27)
                    switch (ch) {
                        case 'a':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 15;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 21;
                            break;
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 24;
                            break;
                        case 'á':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 27;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 15: // b(16), r(17), í(19), ~(200)
                    switch (ch) {
                        case 'b':
                            if (rv.charAt(cursor - 1) == 'a') {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 16;
                            } else {
                                suffixCase = 1; // an
                                state = 200;
                            }
                            break;
                        case 'r':
                            if (rv.charAt(cursor - 1) == 'a' || (rv.charAt(cursor - 1) == 'e' && (cursor - 2) >= 0 && rv.charAt(cursor - 2) == 'i')) {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 17;
                            } else {
                                suffixCase = 1;
                                state = 200;
                            }
                            break;
                        case 'í':
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 19;
                            break;
                        default: // an
                            suffixCase = 1;
                            state = 200;
                            break;
                    }
                    break;
                case 16: // a(200)
                    switch (ch) {
                        case 'a': // aban
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 17: // a(200), e(18)
                    switch (ch) {
                        case 'a': // aran
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // iste
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 18;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 18: // i(200)
                    switch (ch) {
                        case 'i': // ieran
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 19: // r(20), ~(200)
                    switch (ch) {
                        case 'r':
                            if (rv.charAt(cursor - 1) == 'a' || rv.charAt(cursor - 1) == 'e' || rv.charAt(cursor - 1) == 'i') {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 20;
                            } else {
                                suffixCase = 1;
                                state = 200;
                            }
                            break;
                        default:
                            suffixCase = 1;
                            state = 200;
                            break;
                    }
                    break;
                case 20: // a(200), e(200), i(200)
                    switch (ch) {
                        case 'a': // arían
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // erían
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // irían
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 21: // s(22), ~(200)
                    switch (ch) {
                        case 's':
                            if (rv.charAt(cursor - 1) == 'a' || (rv.charAt(cursor - 1) == 'e' && (cursor - 2) >= 0 && rv.charAt(cursor - 2) == 'i')) {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 22;
                            } else {
                                suffixCase = 1;
                                state = 200;
                            }
                            break;
                        default: // en
                            suffixCase = 0;
                            state = 200;
                            break;
                    }
                    break;
                case 22:  // a(200), e(23)
                    switch (ch) {
                        case 'a': // asen
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // erían
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 23;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 23: // i(200)
                    switch (ch) {
                        case 'i': // iesen
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 24: // r(25)
                    switch (ch) {
                        case 'r': // irían
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 25;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 25: // a(200), e(26)
                    switch (ch) {
                        case 'a': // aron
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 26;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 26: // i(200)
                    switch (ch) {
                        case 'i': // ieron
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 27: // r(28)
                    switch (ch) {
                        case 'r':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 28;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 28: // a(200), i(200)
                    switch (ch) {
                        case 'a': // arán
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // erán
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // irán
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 29: // d(30)
                    switch (ch) {
                        case 'd': // irían
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 30;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 30: // a(200), i(200), n(31)
                    switch (ch) {
                        case 'a': // ado
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // ido
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'n':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 31;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 31: // a(200), e(32)
                    switch (ch) {
                        case 'a': // ando
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // erían
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 32;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 32: // i(200)
                    switch (ch) {
                        case 'i': // iendo
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 33: // a(200), e(200), i(200)
                    switch (ch) {
                        case 'a': // ar
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // er
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // ir
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 34: // a(35), e(42), i(45), o(59), á(72), í(200)
                    switch (ch) {
                        case 'a':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 35;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 42;
                            break;
                        case 'i':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 45;
                            break;
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 59;
                            break;
                        case 'á':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 72;
                            break;
                        case 'í': // ís
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 35: // b(36), d(37), r(38), í(40), ~(200)
                    switch (ch) {
                        case 'b':
                            if (rv.charAt(cursor - 1) == 'a') {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 36;
                            } else {
                                suffixCase = 1;
                                state = 200;
                            }
                            break;
                        case 'd':
                            if (rv.charAt(cursor - 1) == 'a' ||  rv.charAt(cursor - 1) == 'i') {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 37;
                            } else {
                                suffixCase = 1;
                                state = 200;
                            }
                            break;
                        case 'r':
                            if (rv.charAt(cursor - 1) == 'a' || (rv.charAt(cursor - 1) == 'e' && (cursor - 2) >= 0 && rv.charAt(cursor - 2) == 'i')) {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 38;
                            } else {
                                suffixCase = 1;
                                state = 200;
                            }
                            break;
                        case 'í':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 40;
                            break;
                        default: // as
                            suffixCase = 1;
                            state = 200;
                            break;
                    }
                    break;
                case 36: // a(200)
                    switch (ch) {
                        case 'a': // abas
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 37: // a(200), i(200)
                    switch (ch) {
                        case 'a': // adas
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // idas
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 38: // a(200), e(39)
                    switch (ch) {
                        case 'a': // aras
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 39;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 39: // i(200)
                    switch (ch) {
                        case 'i': // ieras
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 40: // r(41), ~(200)
                    switch (ch) {
                        case 'r': // ir
                            if (rv.charAt(cursor - 1) == 'a' || rv.charAt(cursor - 1) == 'e' || rv.charAt(cursor - 1) == 'i') {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 41;
                            } else {
                                suffixCase = 1;
                                state = 200;
                            }
                            break;
                        default: // ías
                            suffixCase = 1;
                            state = 200;
                            break;
                    }
                    break;
                case 41: // a(200), e(200), i(200)
                    switch (ch) {
                        case 'a': // arías
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // erías
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // irías
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 42: // s(43), ~(200)
                    switch (ch) {
                        case 's': // ir
                            if (rv.charAt(cursor - 1) == 'a' || (rv.charAt(cursor - 1) == 'e' && (cursor - 2) >= 0 && rv.charAt(cursor - 2) == 'i')) {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 43;
                            } else {
                                suffixCase = 1;
                                state = 200;
                            }
                            break;
                        default: // es
                            suffixCase = 0;
                            state = 200;
                            break;
                    }
                    break;
                case 43: // a(200), e(44)
                    switch (ch) {
                        case 'a': // ases
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 44;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 44: // i(200)
                    switch (ch) {
                        case 'i': // ieses
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 45: // a(46), e(52), á(200), é(57)
                    switch (ch) {
                        case 'a':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 46;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 52;
                            break;
                        case 'á': // ás
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'é':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 57;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 46: // b(47), r(48), í(50)
                    switch (ch) {
                        case 'b':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 47;
                            break;
                        case 'r':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 48;
                            break;
                        case 'í':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 50;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 47: // a(200)
                    switch (ch) {
                        case 'a': // abais
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 48: // a(200), e(49)
                    switch (ch) {
                        case 'a': // arais
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 49;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 49: // i(200)
                    switch (ch) {
                        case 'i': // ierais
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 50: // r(51), ~(200)
                    switch (ch) {
                        case 'r':
                            if (rv.charAt(cursor - 1) == 'a' || rv.charAt(cursor - 1) == 'e' || rv.charAt(cursor - 1) == 'i') {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 51;
                            } else {
                                suffixCase = 1;
                                state = 200;
                            }
                            break;
                        default: // íais
                            suffixCase = 1;
                            state = 200;
                            break;
                    }
                    break;
                case 51: // a(200), e(200), i(200)
                    switch (ch) {
                        case 'a': // aríais
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // eríais
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // iríais
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 52: // s(53), t(55)
                    switch (ch) {
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 53;
                            break;
                        case 't':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 55;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 53: // a(200), e(54)
                    switch (ch) {
                        case 'a': // aseis
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 54;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 54: // i(200)
                    switch (ch) {
                        case 'i': // ieseis
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 55: // s(56)
                    switch (ch) {
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 56;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 56: // a(200), i(200)
                    switch (ch) {
                        case 'a': // asteis
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // isteis
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 57: // r(58), ~(200)
                    switch (ch) {
                        case 'r':
                            if (rv.charAt(cursor - 1) == 'a' || rv.charAt(cursor - 1) == 'e' || rv.charAt(cursor - 1) == 'i') {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 58;
                            } else {
                                suffixCase = 1;
                                state = 200;
                            }
                            break;
                        default: // éis
                            suffixCase = 0;
                            state = 200;
                            break;
                    }
                    break;
                case 58: // a(200), e(200), i(200)
                    switch (ch) {
                        case 'a': // aréis
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // eréis
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // iréis
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 59: // d(60), m(61)
                    switch (ch) {
                        case 'd':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 60;
                            break;
                        case 'm':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 61;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 60: // a(200), i(200)
                    switch (ch) {
                        case 'a': // ados
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // idos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 61: // a(62), e(68), i(200)
                    switch (ch) {
                        case 'a':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 62;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 68;
                            break;
                        case 'i': // imos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 62: // b(63), r(64), í(66), ~(200)
                    switch (ch) {
                        case 'b':
                            if (rv.charAt(cursor - 1) == 'á' ) {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 63;
                            } else {
                                suffixCase = 1;
                                state = 200;
                            }
                            break;
                        case 'r':
                            if (rv.charAt(cursor - 1) == 'á' || rv.charAt(cursor - 1) == 'é') {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 64;
                            } else {
                                suffixCase = 1;
                                state = 200;
                            }
                            break;
                        case 'í':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 66;
                            break;
                        default: // amos
                            suffixCase = 1;
                            state = 200;
                            break;
                    }
                    break;
                case 63: // á(200)
                    switch (ch) {
                        case 'á': // abamos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 64: // á(200), é(65)
                    switch (ch) {
                        case 'á': // aramos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'é':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 65;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 65: // i(200)
                    switch (ch) {
                        case 'i': // iéramos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 66: // r(67), ~(200)
                    switch (ch) {
                        case 'r':
                            if (rv.charAt(cursor - 1) == 'a' || rv.charAt(cursor - 1) == 'e' || rv.charAt(cursor - 1) == 'i') {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 67;
                            } else {
                                suffixCase = 1;
                                state = 200;
                            }
                            break;
                        default: // íamos
                            suffixCase = 1;
                            state = 200;
                            break;
                    }
                    break;
                case 67: // a(200), e(200), i(200)
                    switch (ch) {
                        case 'a': // aríamos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // eríamos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // iríamos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 68: // r(69), s(70), ~(200)
                    switch (ch) {
                        case 'r':
                            if (rv.charAt(cursor - 1) == 'a' || rv.charAt(cursor - 1) == 'e' || rv.charAt(cursor - 1) == 'i') {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 69;
                            } else {
                                suffixCase = 1;
                                state = 200;
                            }
                            break;
                        case 's': // eríais
                            if (rv.charAt(cursor - 1) == 'á' || rv.charAt(cursor - 1) == 'é' ) {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 70;
                            } else {
                                suffixCase = 1;
                                state = 200;
                            }
                            break;
                        default: // emos
                            suffixCase = 0;
                            state = 200;
                            break;
                    }
                    break;
                case 69: // a(200), e(200), i(200)
                    switch (ch) {
                        case 'a': // aremos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // eremos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // iremos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 70: // á(200), é(71)
                    switch (ch) {
                        case 'á': // ásemos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'é': // eríais
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 71;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 71: // i(200)
                    switch (ch) {
                        case 'i': // iésemos
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 72: // r(73)
                    switch (ch) {
                        case 'r':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 73;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 73: // a(200), e(200), i(200)
                    switch (ch) {
                        case 'a': // arás
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // erás
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // irás
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 74: // r(75)
                    switch (ch) {
                        case 'r':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 75;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 75: // a(200), e(200), i(200)
                    switch (ch) {
                        case 'a': // aré
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // eré
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // iré
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 76: // r(77)
                    switch (ch) {
                        case 'r': // iríais
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 77;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 77: // a(200), e(200), i(200)
                    switch (ch) {
                        case 'a': // ará
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'e': // erá
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        case 'i': // irá
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 78: // i(200)
                    switch (ch) {
                        case 'i': // ió
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 1;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 200:
                    switch (suffixCase) {
                        case 0:
                            str = str.substring(0, str.length() - suffixSize);

                            if (str.endsWith("gu"))
                                str = str.substring(0, str.length() - 1);
                            break;
                        case 1:
                            str = str.substring(0, str.length() - suffixSize);
                    }
                    cursor = -1;
                    break;
            }
        }

        return str;
    }
}

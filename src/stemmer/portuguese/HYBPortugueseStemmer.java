package stemmer.portuguese;

public class HYBPortugueseStemmer extends PortugueseStemmer {

    public HYBPortugueseStemmer() {
        super();
    }

    @Override
    protected String standardSuffix() {
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
                case 0: // a(1), e(16), l(24), n(27), o(31), r(46), s(49)
                    switch (ch) {
                        case 'a':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 1;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 16;
                            break;
                        case 'l':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 24;
                            break;
                        case 'n':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 27;
                            break;
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 31;
                            break;
                        case 'r':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 46;
                            break;
                        case 's':
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
                case 1: // c(2), i(3), r(6), s(9), t(10), v(12), z(13), í(91)
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
                            state = 91;
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
                case 5: // â(200), ê(200)
                    switch (ch) {
                        case 'â': // ância
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        case 'ê': // ência
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
                case 6: // i(200), o(7)
                    switch (ch) {
                        case 'i': // ira
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 8;
                            cursor--;
                            state = 200;
                            break;
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
                case 9: // o(200)
                    switch (ch) {
                        case 'o': // osa
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
                case 91: // g(14)
                    switch (ch) {
                        case 'g':
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
                case 13: // e(200)
                    switch (ch) {
                        case 'e': // eza
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
                case 14: // o(15)
                    switch (ch) {
                        case 'o':
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
                case 15: // l(200)
                    switch (ch) {
                        case 'l': // logía
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
                case 16: // d(17), t(20)
                    switch (ch) {
                        case 'd':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 17;
                            break;
                        case 't':
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
                case 17: // a(18)
                    switch (ch) {
                        case 'a':
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
                case 18: // d(19)
                    switch (ch) {
                        case 'd':
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
                case 19: // i(200)
                    switch (ch) {
                        case 'i': // idade
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 6;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 20: // n(21)
                    switch (ch) {
                        case 'n':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 21;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 21: // a(200), e(22)
                    switch (ch) {
                        case 'a':
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 22;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 22: // m(23)
                    switch (ch) {
                        case 'm':
                            if ((cursor - 1) >= 0 && str.charAt(cursor - 1) == 'a') {
                                suffix = ch + suffix;
                                suffixSize++;
                                cursor--;
                                state = 23;
                            } else {
                                suffix = ch + suffix;
                                suffixSize++;
                                suffixCase = 5;
                                cursor--;
                                state = 200;
                            }
                            break;
                        default: // mente
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 23: // a(200)
                    switch (ch) {
                        case 'a': // amente
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
                case 24: // e(25)
                    switch (ch) {
                        case 'e':
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
                case 25: // v(26)
                    switch (ch) {
                        case 'v':
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
                case 26: // á(200), í(200)
                    switch (ch) {
                        case 'á': // ável
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        case 'í':
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
                case 27: // ó(28)
                    switch (ch) {
                        case 'ó':
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
                case 28: // i(29)
                    switch (ch) {
                        case 'i':
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
                case 29: // c(30)
                    switch (ch) {
                        case 'c':
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
                case 30: // u(200)
                    switch (ch) {
                        case 'd': // úcion
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
                case 31: // c(32), m(33), ~~(35), s(38), t(39), v(45)
                    switch (ch) {
                        case 'c':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 32;
                            break;
                        case 'm':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 33;
                            break;
                        case '~':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 35;
                            break;
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 38;
                            break;
                        case 't':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 39;
                            break;
                        case 'v':
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
                case 32: // i(200)
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
                case 33: // s(34)
                    switch (ch) {
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 34;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 34: // i(200)
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
                case 35: // a(36)
                    switch (ch) {
                        case 'a':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 36;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 36: // ç(37)
                    switch (ch) {
                        case 'ç':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 37;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 37: // a(200)
                    switch (ch) {
                        case 'a': // aça~0
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
                case 38: // o(200)
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
                case 39: // n(40)
                    switch (ch) {
                        case 'n':
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
                case 40: // e(41)
                    switch (ch) {
                        case 'e':
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
                case 41: // m(42)
                    switch (ch) {
                        case 'm':
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
                case 42: // a(200), i(200)
                    switch (ch) {
                        case 'a': // amento
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 0;
                            cursor--;
                            state = 200;
                            break;
                        case 'i':
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
                case 45: // i(200)
                    switch (ch) {
                        case 'i': // ivo
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
                case 46: // o(47)
                    switch (ch) {
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 47;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 47: // d(48)
                    switch (ch) {
                        case 'd':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 48;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 48: // a(200)
                    switch (ch) {
                        case 'a': // ador
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
                case 49: // a(50), e(65), o(81)
                    switch (ch) {
                        case 'a':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 50;
                            break;
                        case 'e':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 65;
                            break;
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 81;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 50: // c(51), i(52), r(55), s(58), t(59), v(61), z(62), í(92)
                    switch (ch) {
                        case 'c':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 51;
                            break;
                        case 'i':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 52;
                            break;
                        case 'r':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 55;
                            break;
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 58;
                            break;
                        case 't':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 59;
                            break;
                        case 'v':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 61;
                            break;
                        case 'z':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 62;
                            break;
                        case 'í':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 92;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 51: // i(200)
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
                case 52: // c(53)
                    switch (ch) {
                        case 'c':
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
                case 53: // n(54)
                    switch (ch) {
                        case 'n':
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
                case 54: // ê(200)
                    switch (ch) {
                        case 'ê': // ências
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
                case 55: // i(200), o(56)
                    switch (ch) {
                        case 'i': // iras
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 8;
                            cursor--;
                            state = 200;
                            break;
                        case 'o':
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
                case 56: // d(57)
                    switch (ch) {
                        case 'd':
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
                case 57: // a(200)
                    switch (ch) {
                        case 'a': // adoras
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
                case 58: // o(200)
                    switch (ch) {
                        case 'o': // osas
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
                case 59: // s(60)
                    switch (ch) {
                        case 's':
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
                case 60: // i(200)
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
                case 61: // i(200)
                    switch (ch) {
                        case 'i': // ivas
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
                case 62: // e(200)
                    switch (ch) {
                        case 'e': // ezas
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
                case 92: // g(63)
                    switch (ch) {
                        case 'g':
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
                case 63: // o(64)
                    switch (ch) {
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 64;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 64: // l(200)
                    switch (ch) {
                        case 'l': // logías
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
                case 65: // d(66), n(69), ~~(73), r(76), t(79)
                    switch (ch) {
                        case 'd':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 66;
                            break;
                        case 'n':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 69;
                            break;
                        case '~':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 73;
                            break;
                        case 'r':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 76;
                            break;
                        case 't':
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
                case 66: // a(67)
                    switch (ch) {
                        case 'a':
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
                case 67: // d(68)
                    switch (ch) {
                        case 'd':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 68;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 68: // i(200)
                    switch (ch) {
                        case 'i': // idades
                            suffix = ch + suffix;
                            suffixSize++;
                            suffixCase = 6;
                            cursor--;
                            state = 200;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 69: // o(70)
                    switch (ch) {
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 70;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 70: // i(71)
                    switch (ch) {
                        case 'i':
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
                case 71: // c(72)
                    switch (ch) {
                        case 'c':
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
                case 72: // u(200)
                    switch (ch) {
                        case 'u': // uciones
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
                case 73: // o(74)
                    switch (ch) {
                        case 'o':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 74;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 74: // ç(75)
                    switch (ch) {
                        case 'ç':
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
                case 75: // a(200)
                    switch (ch) {
                        case 'a': // aço~es
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
                case 76: // o(77)
                    switch (ch) {
                        case 'o':
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
                case 77: // d(78)
                    switch (ch) {
                        case 'd':
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
                        case 'a': // adores
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
                case 79: // n(80)
                    switch (ch) {
                        case 'n':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 80;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 80: // a(200)
                    switch (ch) {
                        case 'a': // antes
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
                case 81: // c(82), m(83), s(85), t(86), v(90)
                    switch (ch) {
                        case 'c':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 82;
                            break;
                        case 'm':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 83;
                            break;
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 85;
                            break;
                        case 't':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 86;
                            break;
                        case 'v':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 90;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 82: // i(200)
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
                case 83: // s(84)
                    switch (ch) {
                        case 's':
                            suffix = ch + suffix;
                            suffixSize++;
                            cursor--;
                            state = 84;
                            break;
                        default:
                            suffixSize = 0;
                            cursor = -1;
                            break;
                    }
                    break;
                case 84: // i(200)
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
                case 85: // o(200)
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
                case 86: // n(87)
                    switch (ch) {
                        case 'n':
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
                case 87: // e(88)
                    switch (ch) {
                        case 'e':
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
                case 88: // m(89)
                    switch (ch) {
                        case 'm':
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
                case 89: // a(200), i(200)
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
                case 90: // i(200)
                    switch (ch) {
                        case 'i': // ivos
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
                case 200:
                    String r1 = r1();
                    String r2 = r2();
                    String rv = rV();

                    switch (suffixCase) {
                        case 0:
                            if (r2.endsWith(suffix))
                                str = str.substring(0, str.length() - suffixSize);
                            break;
                        case 1:
                            if (r2.endsWith(suffix))
                                str = str.substring(0, str.length() - suffixSize) + "log";
                            break;
                        case 2:
                            if (r2.endsWith(suffix))
                                str = str.substring(0, str.length() - suffixSize) + "u";
                            break;
                        case 3:
                            if (r2.endsWith(suffix))
                                str = str.substring(0, str.length() - suffixSize) + "ente";
                            break;
                        case 4:
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
                        case 5:
                            if (r2.endsWith(suffix)) {
                                str = str.substring(0, str.length() - suffixSize);

                                if (str.endsWith("ante") || str.endsWith("avel") || str.endsWith("ível")) {
                                    if (r2.endsWith("ante" + suffix) || r2.endsWith("avel" + suffix) || r2.endsWith("ível" + suffix))
                                        str = str.substring(0, str.length() - 4);
                                }
                            }
                            break;
                        case 6:
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
                        case 7:
                            if (r2.endsWith(suffix)) {
                                str = str.substring(0, str.length() - suffixSize);

                                if (str.endsWith("at")) {
                                    if (r2.endsWith("at" + suffix))
                                        str = str.substring(0, str.length() - 2);
                                }
                            }
                            break;
                        case 8:
                            if (rv.endsWith(suffix) && str.endsWith("e" + suffix))
                                str = str.substring(0, str.length() - suffixSize) + "ir";
                            break;
                    }

                    cursor = -1;
                    break;
            }
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
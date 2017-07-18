package stemmer.english.EnADS;

import stemmer.english.PorterFramework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Porter {

    private static final int STOP = -1;
    public String str = "";

    public String apply(String str){

        // check for zero length
        if (str.length() > 3) {
            // all characters must be letters
            char[] c = str.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (!Character.isLetter(c[i])) {
                    return PorterFramework.toLowerCase(str);
                }
            }
        } else {
            return PorterFramework.toLowerCase(str);
        }


        str = step1a(str);
        str = step1b(str);
        str = step1c(str);
        str = step2(str);
        str = step3(str);
        str = step4(str);
        str = step5a(str);
        str = step5b(str);
        str = PorterFramework.toLowerCase(str);
        return str;
    }

    public void applyTest(String word){
        str = word;
    }

    public String getWord(){
        return str;
    }

    private int m(String word, int position){

        int m = 0;
        int state = 1;
        char letter = ' ';

        while(position >= 0){
            switch(state){
                case 1:
                    letter = word.charAt(position);
                    if(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u'){
                        //stay in state 1
                    }else if(letter == 'y'){
                        state = 6;
                    }else{
                        state = 2;
                    }
                    position--;
                    break;
                case 2:
                    letter = word.charAt(position);
                    if(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u'){
                        state = 3;
                    }else if(letter == 'y'){
                        state = 4;
                    }else{
                        state = 2;
                    }
                    position--;
                    break;
                case 3:
                    m++;
                    letter = word.charAt(position);
                    if(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u'){
                        state = 1;
                    }else if(letter == 'y'){
                        state = 5;
                    }else{
                        state = 2;
                    }
                    position--;
                    break;
                case 4:
                    letter = word.charAt(position);
                    if(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u'){
                        state = 2;
                    }else{
                        state = 3;
                    }
                    position --;
                    break;
                case 5:
                    letter = word.charAt(position);
                    if(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u'){
                        state = 2;
                    }else{
                        state = 1;
                    }
                    position--;
                    break;
                case 6:
                    letter = word.charAt(position);
                    if(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u'){
                        state = 3;
                    }else{
                        state = 2;
                    }
                    position--;
                    break;
            }

        }

        //garante que se terminar a palavra no estado 3, m vai ser incrementado
        if(state == 3){
            m++;
        }

        return m;
    }


    public String step1a(String str){


        int state = 1;
        int position = str.length() - 1;
        boolean accept = false;

        while(position >= 0){
            switch(state){
                case 1:
                    if(str.charAt(position) == 's'){
                        state = 2;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 2:
                    if(accept){
                        //rule S
                        //IF with ACCEPT always has position + 1
                        str = str.substring(0, position + 1);
                        position = STOP;
                    }else{
                        switch(str.charAt(position)){
                            case 's':
                                //rule SS
                                //remove SS and add SS
                                position = STOP;
                                break;
                            case 'e':
                                state = 4;
                                position--;
                                break;
                            default:
                                //rule S
                                //DEFAULT always has position + 1
                                str = str.substring(0, position + 1);
                                position = STOP;
                                break;
                        }
                    }
                    break;
                case 4:
                    switch(str.charAt(position)){
                        case 'i':
                            //rule IES
                            str = str.substring(0, position + 1);
                            position = STOP;
                            break;
                        case 's':
                            state = 6;
                            position--;
                            break;
                        default:
                            state = 2;
                            accept = true;
                            break;
                    }
                    break;
                case 6:
                    if(str.charAt(position) == 's'){
                        //rule SSES
                        str = str.substring(0, position + 2);
                    }
                    position = STOP;
                    break;
                default:
                    position = STOP;
                    break;
            }
        }



        return str;
    }

    public String step1b(String str){



        int state = 1;
        int position = str.length() - 1;

        while(position >= 0){
            switch(state){
                case 1:
                    switch(str.charAt(position)){
                        case 'd':
                            state = 2;
                            position--;
                            break;
                        case 'g':
                            state = 3;
                            position--;
                            break;
                        default:
                            position = STOP;
                            break;
                    }
                    break;
                case 2:
                    if(str.charAt(position) == 'e'){
                        state = 4;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 3:
                    if(str.charAt(position) == 'n'){
                        state = 5;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 4:
                    if(str.charAt(position) == 'e'){
                        //rule EED
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 2);
                        }
                    }else{
                        //rule ED
                        if(PorterFramework.hasVowel(str, position)){
                            str = str.substring(0, position + 1);
                            step1b2(str);
                        }
                    }
                    position = STOP;
                    break;
                case 5:
                    if(str.charAt(position) == 'i'){
                        //rule ING
                        if(PorterFramework.hasVowel(str, position - 1)){
                            str = str.substring(0, position);
                            step1b2(str);
                        }
                    }
                    position = STOP;
                    break;
            }
        }


        return str;
    }

    public String step1b2(String str){
        if(str.endsWith("at")){
            str += "e";
        }else if(str.endsWith("iz")){
            str += "e";
        }else if(str.endsWith("bl")){
            str += "e";
        }else if(str.length() >= 2 && (str.charAt(str.length() - 1) == str.charAt(str.length() - 2))){
            char letter = str.charAt(str.length() - 2);
            if((letter == 'l' || letter == 's' || letter == 'z') == false){
                str = str.substring(0, str.length() - 1);
            }
        }else{
            if(o(str) && m(str, str.length() - 1) == 1){
                str += "e";
            }
        }
        return str;
    }

    public String step1c(String str){


        if(str.endsWith("y")){
            if(PorterFramework.hasVowel(str, str.length() - 2)){
                str = str.substring(0, str.length() - 1) + "i";
            }
        }


        return str;
    }

    public String step2(String str){



        int state = 1;
        int position = str.length() - 1;
        boolean accept = false;

        while(position >= 0){
            switch(state){
                case 1:
                    switch(str.charAt(position)){
                        case 'n':
                            state = 7;
                            position--;
                            break;
                        case 'i':
                            state = 3;
                            position--;
                            break;

                        case 'l':
                            state = 2;
                            position--;
                            break;

                        case 'r':
                            state = 4;
                            position--;
                            break;
                        case 'm':
                            state = 5;
                            position--;
                            break;
                        case 's':
                            state = 6;
                            position--;
                            break;

                        default:
                            position = STOP;
                            break;
                    }
                    break;
                case 2:
                    if(str.charAt(position) == 'a'){
                        state = 8;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 3:
                    switch(str.charAt(position)){
                        case 'l':
                            state = 15;
                            position--;
                            break;
                        case 'c':
                            state = 14;
                            position--;
                            break;

                        case 't':
                            state = 16;
                            position--;
                            break;


                        default:
                            position = STOP;
                            break;
                    }
                    break;
                case 4:
                    switch(str.charAt(position)){
                        case 'o':
                            state = 60;
                            position--;
                            break;
                        case 'e':
                            state = 63;
                            position--;
                            break;
                        default:
                            position = STOP;
                            break;
                    }
                    break;
                case 5:
                    if(str.charAt(position) == 's'){
                        state = 56;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 6:
                    if(str.charAt(position) == 's'){
                        state = 44;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 7:
                    if(str.charAt(position) == 'o'){
                        state = 38;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 8:
                    if(str.charAt(position) == 'n'){
                        state = 9;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 9:
                    if(str.charAt(position) == 'o'){
                        state = 10;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 10:
                    if(str.charAt(position) == 'i'){
                        state = 11;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 11:
                    if(str.charAt(position) == 't'){
                        state = 12;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 12:
                    if(str.charAt(position) == 'a'){
                        //rule ATIONAL
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position) + "ate";
                        }
                    }else{
                        //rule TIONAL
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 5);
                        }
                    }
                    position = STOP;
                    break;
                case 14:
                    if(str.charAt(position) == 'n'){
                        state = 17;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 15:
                    switch(str.charAt(position)){
                        case 'l':
                            state = 21;
                            position--;
                            break;
                        case 'e':
                            //rule ELI
                            if(m(str, position - 1) > 0){
                                str = str.substring(0, position + 1);
                            }
                            position = STOP;
                            break;


                        case 't':
                            state = 22;
                            position--;
                            break;

                        case 's':
                            state = 23;
                            position--;
                            break;

                        case 'b':
                            state = 20;
                            position--;
                            break;
                        default:
                            position = STOP;
                            break;
                    }
                    break;
                case 16:
                    if(str.charAt(position) == 'i'){
                        state = 31;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 17:
                    switch(str.charAt(position)){
                        case 'e':
                            //rule ENCI
                            if(m(str, position - 1) > 0){
                                str = str.substring(0, position + 3) + "e";
                            }
                            break;
                        case 'a':
                            //rule ANCI
                            if(m(str, position - 1) > 0){
                                str = str.substring(0, position + 3) + "e";
                            }
                            break;
                    }
                    position = STOP;
                    break;
                case 20:
                    //rule ABLI
                    if(str.charAt(position) == 'a'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 3) + "e";
                        }
                    }
                    position = STOP;
                    break;
                case 21:
                    //rule ALLI
                    if(str.charAt(position) == 'a'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 2);
                        }
                    }
                    position = STOP;
                    break;
                case 22:
                    if(str.charAt(position) == 'n'){
                        state = 27;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 23:
                    if(str.charAt(position) == 'u'){
                        state = 29;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 27:
                    //rule ENTLI
                    if(str.charAt(position) == 'e'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 3);
                        }
                    }
                    position = STOP;
                    break;
                case 29:
                    //rule OUSLI
                    if(str.charAt(position) == 'o'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 3);
                        }
                    }
                    position = STOP;
                    break;
                case 31:
                    switch(str.charAt(position)){
                        case 'l':
                            state = 32;
                            position--;
                            break;
                        case 'v':
                            state = 33;
                            position--;
                            break;
                        default:
                            position = STOP;
                            break;
                    }
                    break;
                case 32:
                    switch(str.charAt(position)){
                        case 'a':
                            //rule ALITI
                            if(m(str, position - 1) > 0){
                                str = str.substring(0, position + 2);
                            }
                            position = STOP;
                            break;
                        case 'i':
                            state = 35;
                            position--;
                            break;
                        default:
                            position = STOP;
                            break;
                    }
                    break;
                case 33:
                    //rule IVITI
                    if(str.charAt(position) == 'i'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 2) + "e";
                        }
                    }
                    position = STOP;
                    break;
                case 35:
                    //rule BILITI
                    if(str.charAt(position) == 'b'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position) + "ble";
                        }
                    }
                    position = STOP;
                    break;
                case 38:
                    if(str.charAt(position) == 'i'){
                        state = 39;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 39:
                    if(str.charAt(position) == 't'){
                        state = 40;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 40:
                    if(str.charAt(position) == 'a'){
                        state = 41;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 41:
                    if(accept){
                        //rule ATION
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 2) + "e";
                        }
                        position = STOP;
                    }else{
                        if(str.charAt(position) == 'z'){
                            state = 42;
                            position--;
                        }else{
                            if(m(str, position - 1) > 0){
                                str = str.substring(0, position + 3) + "e";
                            }
                            position = STOP;
                        }
                    }
                    break;
                case 42:
                    //rule IZATION
                    if(str.charAt(position) == 'i'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 2) + "e";
                        }
                        position = STOP;
                    }else{
                        accept = true;
                        position += 1;
                        state = 41;
                    }
                    break;
                case 44:
                    if(str.charAt(position) == 'e'){
                        state = 45;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 45:
                    if(str.charAt(position) == 'n'){
                        state = 46;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 46:
                    switch(str.charAt(position)){
                        case 'e':
                            state = 53;
                            position--;
                            break;
                        case 's':
                            state = 47;
                            position--;
                            break;
                        case 'l':
                            state = 50;
                            position--;
                            break;
                        default:
                            position = STOP;
                            break;
                    }
                    break;
                case 47:
                    if(str.charAt(position) == 'u'){
                        state = 48;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 48:
                    //rule OUSNESS
                    if(str.charAt(position) == 'o'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 3);
                        }
                    }
                    position = STOP;
                    break;
                case 50:
                    if(str.charAt(position) == 'u'){
                        state = 51;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 51:
                    //rule FULNESS
                    if(str.charAt(position) == 'f'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 3);
                        }
                    }
                    position = STOP;
                    break;
                case 53:
                    if(str.charAt(position) == 'v'){
                        state = 54;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 54:
                    //rule IVENESS
                    if(str.charAt(position) == 'i'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 3);
                        }
                    }
                    position = STOP;
                    break;
                case 56:
                    if(str.charAt(position) == 'i'){
                        state = 57;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 57:
                    if(str.charAt(position) == 'l'){
                        state = 58;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 58:
                    //rule ALISM
                    if(str.charAt(position) == 'a'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 2);
                        }
                    }
                    position = STOP;
                    break;
                case 60:
                    if(str.charAt(position) == 't'){
                        state = 61;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 61:
                    //rule ATOR
                    if(str.charAt(position) == 'a'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 2) + "e";
                        }
                    }
                    position = STOP;
                    break;
                case 63:
                    if(str.charAt(position) == 'z'){
                        state = 64;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 64:
                    //rule IZER
                    if(str.charAt(position) == 'i'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 3);
                        }
                    }
                    position = STOP;
                    break;
            }

        }


        return str;
    }

    public String step3(String str){


        int state = 1;
        int position = str.length() - 1;

        while(position >= 0){
            switch(state){
                case 1:
                    switch(str.charAt(position)){
                        case 'l':
                            state = 3;
                            position--;
                            break;
                        case 'e':
                            state = 2;
                            position--;
                            break;
                        case 's':
                            state = 5;
                            position--;
                            break;
                        case 'i':
                            state = 4;
                            position--;
                            break;
                        default:
                            position = STOP;
                            break;
                    }
                    break;
                case 2:
                    switch(str.charAt(position)){
                        case 't':
                            state = 18;
                            position--;
                            break;
                        case 'v':
                            state = 22;
                            position--;
                            break;
                        case 'z':
                            state = 26;
                            position--;
                            break;
                        default:
                            position = STOP;
                            break;
                    }
                    break;
                case 3:
                    switch(str.charAt(position)){
                        case 'a':
                            state = 14;
                            position--;
                            break;
                        case 'u':
                            state = 13;
                            position--;
                            break;
                        default:
                            position = STOP;
                            break;
                    }
                    break;
                case 4:
                    if(str.charAt(position) == 't'){
                        state = 9;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 5:
                    if(str.charAt(position) == 's'){
                        state = 6;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 6:
                    if(str.charAt(position) == 'e'){
                        state = 7;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 7:
                    //rule NESS
                    if(str.charAt(position) == 'n'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position);
                        }
                    }
                    position = STOP;
                    break;
                case 9:
                    if(str.charAt(position) == 'i'){
                        state = 10;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 10:
                    if(str.charAt(position) == 'c'){
                        state = 11;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 11:
                    //rule ICITI
                    if(str.charAt(position) == 'i'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 2);
                        }
                    }
                    position = STOP;
                    break;
                case 13:
                    //rule FUL
                    if(str.charAt(position) == 'f'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position);
                        }
                    }
                    position = STOP;
                    break;
                case 14:
                    if(str.charAt(position) == 'c'){
                        state = 15;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 15:
                    //rule ICAL
                    if(str.charAt(position) == 'i'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 2);
                        }
                    }
                    position = STOP;
                    break;
                case 18:
                    if(str.charAt(position) == 'a'){
                        state = 19;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 19:
                    if(str.charAt(position) == 'c'){
                        state = 20;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 20:
                    //rule ICATE
                    if(str.charAt(position) == 'i'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 2);
                        }
                    }
                    position = STOP;
                    break;
                case 22:
                    if(str.charAt(position) == 'i'){
                        state = 23;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 23:
                    if(str.charAt(position) == 't'){
                        state = 24;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 24:
                    //rule ATIVE
                    if(str.charAt(position) == 'a'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position);
                        }
                    }
                    position = STOP;
                    break;
                case 26:
                    if(str.charAt(position) == 'i'){
                        state = 27;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 27:
                    if(str.charAt(position) == 'l'){
                        state = 28;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 28:
                    //rule ALIZE
                    if(str.charAt(position) == 'a'){
                        if(m(str, position - 1) > 0){
                            str = str.substring(0, position + 2);
                        }
                    }
                    position = STOP;
                    break;
            }
        }



        return str;
    }

    public String step4(String str){

        int state = 1;
        int position = str.length() - 1;

        while(position >= 0){
            switch(state){
                case 1:
                    switch(str.charAt(position)){
                        case 'e':
                            state = 2;
                            position--;
                            break;
                        case 'r':
                            state = 20;
                            position--;
                            break;
                        case 'l':
                            state = 18;
                            position--;
                            break;
                        case 't':
                            state = 13;
                            position--;
                            break;
                        case 'c':
                            state = 22;
                            position--;
                            break;
                        case 'i':
                            state = 29;
                            position--;
                            break;
                        case 'n':
                            state = 35;
                            position--;
                            break;
                        case 'u':
                            state = 24;
                            position--;
                            break;
                        case 'm':
                            state = 26;
                            position--;
                            break;
                        case 's':
                            state = 32;
                            position--;
                            break;
                        default:
                            position = STOP;
                            break;
                    }
                    break;
                case 2:
                    switch(str.charAt(position)){
                        case 't':
                            state = 9;
                            position--;
                            break;
                        case 'v':
                            state = 11;
                            position--;
                            break;
                        case 'c':
                            state = 3;
                            position--;
                            break;
                        case 'z':
                            state = 11;
                            position--;
                            break;
                        case 'l':
                            state = 6;
                            position--;
                            break;

                        default:
                            position = STOP;
                            break;
                    }
                    break;
                case 3:
                    if(str.charAt(position) == 'n'){
                        state = 4;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 4:
                    //rule ANCE e ENCE
                    if(str.charAt(position) == 'a' || str.charAt(position) == 'e'){
                        if(m(str, position - 1) > 1){
                            str = str.substring(0, position);
                        }
                    }
                    position = STOP;
                    break;
                case 6:
                    if(str.charAt(position) == 'b'){
                        state = 7;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 7:
                    //rule IBLE e ABLE
                    if(str.charAt(position) == 'a' || str.charAt(position) == 'i'){
                        if(m(str, position - 1) > 1){
                            str = str.substring(0, position);
                        }
                    }
                    position = STOP;
                    break;
                case 9:
                    //rule AT
                    if(str.charAt(position) == 'a'){
                        if(m(str, position - 1) > 1){
                            str = str.substring(0, position);
                        }
                    }
                    position = STOP;
                    break;
                case 11:
                    //rule IVE e IZE
                    if(str.charAt(position) == 'i'){
                        if(m(str, position - 1) > 1){
                            str = str.substring(0, position);
                        }
                    }
                    position = STOP;
                    break;
                case 13:
                    if(str.charAt(position) == 'n'){
                        state = 14;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 14:
                    switch(str.charAt(position)){
                        case 'e':
                            state = 15;
                            position--;
                            break;
                        case 'a':
                            //rule ANT
                            if(m(str, position - 1) > 1){
                                str = str.substring(0, position);
                            }
                            position = STOP;
                            break;
                        default:
                            position = STOP;
                            break;
                    }
                    break;
                case 15:
                    if(str.charAt(position) == 'm'){
                        state = 16;
                        position--;
                    }else{
                        //rule ENT
                        if(m(str, position - 1) > 1){
                            str = str.substring(0, position + 1);
                        }
                        position = STOP;
                    }
                    break;
                case 16:
                    //rule EMENT
                    if(str.charAt(position) == 'e'){
                        if(m(str, position - 1) > 1){
                            str = str.substring(0, position);
                        }
                    }else{
                        //rule MENT
                        if(m(str, position - 1) > 1){
                            str = str.substring(0, position + 1);
                        }
                    }
                    position = STOP;
                    break;
                case 18:
                    //rule AL
                    if(str.charAt(position) == 'a'){
                        if(m(str, position - 1) > 1){
                            str = str.substring(0, position);
                        }
                    }
                    position = STOP;
                    break;
                case 20:
                    if(str.charAt(position) == 'e'){
                        if(m(str, position - 1) > 1){
                            str = str.substring(0, position);
                        }
                    }
                    position = STOP;
                    break;
                case 22:
                    //rule IC
                    if(str.charAt(position) == 'i'){
                        if(m(str, position - 1) > 1){
                            str = str.substring(0, position);
                        }
                    }
                    position = STOP;
                    break;
                case 24:
                    //rule OU
                    if(str.charAt(position) == 'o'){
                        if(m(str, position - 1) > 1){
                            str = str.substring(0, position);
                        }
                    }
                    position = STOP;
                    break;
                case 26:
                    if(str.charAt(position) == 's'){
                        state = 27;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 27:
                    //rule ISM
                    if(str.charAt(position) == 'i'){
                        if(m(str, position - 1) > 1){
                            str = str.substring(0, position);
                        }
                    }
                    position = STOP;
                    break;
                case 29:
                    if(str.charAt(position) == 't'){
                        state = 30;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 30:
                    //rule ITI
                    if(str.charAt(position) == 'i'){
                        if(m(str, position - 1) > 1){
                            str = str.substring(0, position);
                        }
                    }
                    position = STOP;
                    break;
                case 32:
                    if(str.charAt(position) == 'u'){
                        state = 33;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 33:
                    //rule OUS
                    if(str.charAt(position) == 'o'){
                        if(m(str, position - 1) > 1){
                            str = str.substring(0, position);
                        }
                    }
                    position = STOP;
                    break;
                case 35:
                    if(str.charAt(position) == 'o'){
                        state = 36;
                        position--;
                    }else{
                        position = STOP;
                    }
                    break;
                case 36:
                    //rule ION
                    if(str.charAt(position) == 'i'){
                        String stem = str.substring(0, position);
                        if(stem.endsWith("t") || stem.endsWith("s")){
                            if(m(str, position - 1) > 1){
                                str = stem;
                            }
                        }
                    }
                    position = STOP;
                    break;
            }
        }


        return str;
    }

    public String step5a(String str){

        // (m > 1) E ->
        if ((PorterFramework.stringMeasure(str.substring(0, str.length() - 1)) > 1) &&
                str.endsWith("e"))
            str = str.substring(0, str.length() -1);
            // (m = 1 and not *0) E ->
        else if ((PorterFramework.stringMeasure(str.substring(0, str.length() - 1)) == 1) &&
                (!PorterFramework.endsWithCVC(str.substring(0, str.length() - 1))) &&
                (str.endsWith("e")))
            str = str.substring(0, str.length() - 1);



        return str;
    }

    public String step5b(String str){



        if(str.length() >= 2 && str.endsWith("l")){
            if(str.charAt(str.length() - 2) == 'l'){
                if(m(str, str.length() - 1) > 1){
                    str = str.substring(0, str.length() - 1);
                }
            }
        }



        return str;
    }

/*    public boolean hasVowel(String str, int position){

        int state = 1;
        boolean vowel = false;
        char letter;

        while (position >= 0) {
            switch (state) {
                case 1 :
                    letter = str.charAt(position);

                    if (isVowel(letter))
                        state = 2;
                    else if (letter == 'y')
                        state = 3;

                    position--;
                    break;
                case 2 :
                    vowel = true;
                    position = STOP;
                    break;
                case 3 :
                    letter = str.charAt(position);

                    if (isVowel(letter))
                        state = 1;
                    else
                        state = 2;

                    position--;
                    break;
            }
        }

        return vowel;
    }*/

    /**
     * Letter A, E, I, O or U
     */
    protected boolean isVowel (char c) {
        return ((c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u'));
    }

    private boolean o(String str){
        if(str.length() > 3)
            //do not end with w, x or y
            if ((str.endsWith("w") || str.endsWith("x") || str.endsWith("y")) == false) {
                char letter = str.charAt(str.length() - 1);
                //ends with consonant
                if ((letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') == false) {
                    letter = str.charAt(str.length() - 2);
                    //verify if is vowel of type Cy
                    if (letter == 'y') {
                        letter = str.charAt(str.length() - 3);
                        //verify if y is preceded by consonant, make this a vowel
                        if ((letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') == false) {
                            letter = str.charAt(str.length() - 4);
                            //preceded by consonant
                            if ((letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') == false) {
                                //CVC identify
                                return true;
                            }
                        }
                        //CV identify
                    } else if ((letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') == true) {
                        //not a vowel
                        letter = str.charAt(str.length() - 3);
                        if ((letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') == false) {
                            //CVC identify
                            return true;
                        }
                    }
                }
            }
            return false;
    }

    public void test() {

        String file = "wiki_00_en.txt";//dataset file
        String encoding = "ISO-8859-1";
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
                    System.out.println(this.apply(array[0]));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
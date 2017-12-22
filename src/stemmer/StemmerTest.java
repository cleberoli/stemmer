package stemmer;

import stemmer.english.ADSEnglishStemmer;
import stemmer.english.BSLEnglishStemmer;
import stemmer.portuguese.ADSPortugueseStemmer;
import stemmer.portuguese.BSLPortugueseStemmer;
import stemmer.spanish.ADSSpanishStemmer;
import stemmer.spanish.BSLSpanishStemmer;

import java.io.*;

public class StemmerTest {

    private static void usage() {
        System.err.println("Usage: StemmerTest <language> <algorithm> <input file> -o <output file>");
    }

    public static void main(String[] args) throws Throwable {
        if (args.length != 5) {
            usage();
            return;
        }

        Stemmer stemmer = new ADSEnglishStemmer();

        switch (args[0]) {
            case "english" :
                switch (args[1]) {
                    case "bsl" :
                        stemmer = new BSLEnglishStemmer();
                        break;
                    case "ads" :
                        stemmer = new ADSEnglishStemmer();
                        break;
                }
                break;
            case "spanish" :
                switch (args[1]) {
                    case "bsl" :
                        stemmer = new BSLSpanishStemmer();
                        break;
                    case "ads" :
                        stemmer = new ADSSpanishStemmer();
                        break;
                }
                break;
            case "portuguese" :
                switch (args[1]) {
                    case "bsl" :
                        stemmer = new BSLPortugueseStemmer();
                        break;
                    case "ads" :
                        stemmer = new ADSPortugueseStemmer();
                        break;
                }
                break;
        }

        String inputFile = args[2];
        String outputFile = args[4];

        System.out.println(run(stemmer,inputFile, outputFile));

    }

    private static long run(Stemmer stemmer, String inputFile, String outputFile) throws Exception {
        long elapsedTime = 0;
        Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));

        StringBuffer input = new StringBuffer();

        int character;

        while((character = reader.read()) != -1) {
            char ch = (char) character;

            if (Character.isWhitespace(ch)) {
                if (input.length() > 0) {
                    long begin = System.currentTimeMillis();
                    stemmer.setCurrent(input.toString());
                    stemmer.stem();
                    long end = System.currentTimeMillis();
                    elapsedTime += (end - begin);
                    writer.write(stemmer.getCurrent());
                    writer.write('\n');
                    input.delete(0, input.length());
                }
            } else {
                input.append(Character.toLowerCase(ch));
            }
        }

        writer.flush();

        return elapsedTime;
    }

}

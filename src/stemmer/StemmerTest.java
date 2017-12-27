package stemmer;

import stemmer.english.ADSEnglishStemmer;
import stemmer.english.BSLEnglishStemmer;
import stemmer.portuguese.ADSPortugueseStemmer;
import stemmer.portuguese.BSLPortugueseStemmer;
import stemmer.spanish.ADSSpanishStemmer;
import stemmer.spanish.BSLSpanishStemmer;

import java.io.*;

public class StemmerTest {

    private static int INTERVAL = 50;

    private static void usage() {
        System.err.println("Usage: StemmerTest <language> <algorithm> <input file> -o <output file> [-t <interval>]");
    }

    public static void main(String[] args) throws Throwable {
        if (args.length != 5 && args.length != 7) {
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

        if (args.length == 7)
            INTERVAL = Integer.parseInt(args[6]) > 50 ? Integer.parseInt(args[6]) : 50;

        System.out.println("Elapsed time: " + run(stemmer,inputFile, outputFile));
    }

    private static long run(Stemmer stemmer, String inputFile, String outputFile) throws Exception {
        long elapsedTime = 0;
        Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));

        StringBuffer input = new StringBuffer();

        int character;
        int cummulativeWordCount = 0;
        int wordCount = 0;
        int turns = 0;

        while((character = reader.read()) != -1) {
            char ch = (char) character;

            if (Character.isWhitespace(ch)) {
                if (input.length() > 0) {
                    long begin = System.currentTimeMillis();
                    stemmer.setCurrent(input.toString());
                    stemmer.stem();
                    long end = System.currentTimeMillis();
                    elapsedTime += (end - begin);

                    if ((elapsedTime - INTERVAL*turns) >= INTERVAL) {
                        turns++;
                        System.out.printf("Time: %d \t Words: %d \t Total: %d\n", elapsedTime, wordCount, cummulativeWordCount);
                        wordCount = 0;
                    } else {
                        cummulativeWordCount++;
                        wordCount++;
                    }

                    writer.write(stemmer.getCurrent());
                    writer.write('\n');
                    input.delete(0, input.length());
                }
            } else {
                input.append(Character.toLowerCase(ch));
            }
        }

        System.out.printf("Time: %d \t Words: %d \t Total: %d\n", elapsedTime, wordCount, cummulativeWordCount);

        writer.flush();

        return elapsedTime;
    }

}

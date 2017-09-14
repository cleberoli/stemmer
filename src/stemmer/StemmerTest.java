package stemmer;

import stemmer.english.EnADS.Porter2ADS;
import stemmer.english.EnBsl.Porter2Bsl;

import java.io.*;

public class StemmerTest {

    private static void usage() {
        System.err.println("Usage: StemmerTest <language> <algorithm> <input file> -o <output file>");
    }

    public static void main(String[] args) throws Throwable {
        if (args.length < 2) {
            usage();
            return;
        }

        Stemmer stemmer = new Porter2ADS();

        switch (args[0]) {
            case "english" :
                switch (args[1]) {
                    case "bsl" :
                        stemmer = new Porter2Bsl();
                        break;
                    case "ads" :
                        stemmer = new Porter2ADS();
                    defaultm:
                        break;
                }
                break;
            default :
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

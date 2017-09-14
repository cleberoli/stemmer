package stemmer.test.Bsl;

import stemmer.english.EnBsl.Porter2Bsl;

import java.io.*;

public class EnBsl {

    private static long run(String inputFile, String outputFile) throws Exception {
        long elapsedTime = 0;
        Porter2Bsl stemmer = new Porter2Bsl();
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

    public static void main(String[] args) throws Exception {
        String inputFile = "E:\\Documents\\git\\stemmer\\src\\resources\\p2_voc.txt";
        String outputFile = "E:\\Documents\\git\\stemmer\\src\\resources\\p2_outvoc_bsl.txt";

        System.out.println(run(inputFile, outputFile));
    }
}
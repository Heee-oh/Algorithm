import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine().toLowerCase();
        int[] maxWord = new int[26];

        char word = '0';
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            maxWord[s.charAt(i) - 97]++;
            if (Math.max(max, maxWord[s.charAt(i) - 97]) == maxWord[s.charAt(i) - 97]) {
                max = maxWord[s.charAt(i) - 97];
                word = s.charAt(i);
            }
        }
        word -= 32;

        Arrays.sort(maxWord);
        int index = maxWord.length;
        if (maxWord.length > 1 && maxWord[index - 1] == maxWord[index - 2]) {
            word = '?';
        }

        bw.write(word);
        bw.flush();
        bw.close();

    }
}
import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String s = br.readLine();

        char[] word = new char[26];
        for (int i = 0; i < s.length(); i++) {
            word[s.charAt(i) - 'a']++;
        }

        for (int count : word) {
            sb.append(count).append(" ");
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}
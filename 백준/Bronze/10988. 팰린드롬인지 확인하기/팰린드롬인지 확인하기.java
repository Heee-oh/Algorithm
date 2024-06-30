import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        String answer = "1";
        for (int i = 0; i < (s.length() / 2) + 1; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)){
                answer = "0";
                break;
            }
        }

        bw.write(answer);
        bw.flush();
        bw.close();

    }
}
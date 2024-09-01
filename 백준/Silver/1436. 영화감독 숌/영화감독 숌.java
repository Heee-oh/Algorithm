import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = 666; i < Integer.MAX_VALUE; i++) {
            sb.append(i);
            if (sb.toString().contains("666")) {
                count++;
                if (count == n) break;
            }
            sb.delete(0, sb.length());
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }



}
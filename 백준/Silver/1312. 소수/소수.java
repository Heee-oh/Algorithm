import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        long l = 0;
        while (n--> 0) {
            a *= 10;
             l = a / b;
            a %= b;
        }

        String s = String.valueOf(l);
        bw.write(s.charAt(s.length() - 1));
        bw.flush();
        bw.close();
    }

}
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
            if (String.valueOf(i).contains("666")) {
                count++;
                if (count == n) {
                    count = i;
                    break;
                }
            }
        }

        bw.write(count + "");
        bw.flush();
        bw.close();
    }



}
import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        if (n == 0) {
            bw.write("0");
            bw.flush();
            return;
        }

        int count = 0;
        while (n > 2) {
            count += n / 5;
            n /= 5;
        }

        bw.write(count + "");
        bw.flush();
        bw.close();
    }

}
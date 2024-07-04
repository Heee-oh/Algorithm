import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int count = 0;

        while(n > 0) {
            if (n % 5 == 0) {
                n /= 5;
                count += n;
                break;
            }
            n -= 3;
            count++;
        }

        if (n < 0) {

            bw.write("-1\n");
            bw.flush();
            bw.close();
            return;
        }


        bw.write(count + "\n");
        bw.flush();

        bw.close();



        // 최소값은 1부터
        // 최대값은 9부터

    }
}
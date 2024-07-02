import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());


        while(n--> 0) {
            int count = 0;
            int N = Integer.parseInt(br.readLine());

            while(N > 0) {
                count += (N / 5);
                N /= 5;
            }

            bw.write(count+ "\n");
            bw.flush();

        }


        bw.close();


    }

}
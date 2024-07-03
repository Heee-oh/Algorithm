import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int originNum = Integer.parseInt(br.readLine());
        int n = originNum;
        int count = 0;

        do {
           // 각 자리수 합
           int sum = (n / 10) + (n % 10);
           n = ((n % 10) * 10) +  sum % 10 ;
           count++;

        } while (originNum != n);

        bw.write(count + "\n");
        bw.flush();
        bw.close();

    }

}
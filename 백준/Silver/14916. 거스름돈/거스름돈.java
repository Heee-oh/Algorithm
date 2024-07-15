import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
//
//    static int[] arr;
//    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int count = 0;
        while (n > 0) {
            if (n % 5 == 0 ) {
                count += n / 5;
                break;
            }
            n-= 2;
            count++;
        }

        if (n < 0) {
            System.out.println("-1");

        }else {
            System.out.println(count);
        }

    }


}
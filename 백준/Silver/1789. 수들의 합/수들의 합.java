import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long n = Long.parseLong(br.readLine());

        System.out.println(sumSequence(n));



    }

    private static int sumSequence(long n) {
        long tmp = 0;
        int value = 1;
        int count = 0;


        while (n > tmp && tmp >= 0) {
            tmp += value;
            if (n < tmp) {
                return count;
            }
            count++;
            value++;
        }

        return count;
    }


}

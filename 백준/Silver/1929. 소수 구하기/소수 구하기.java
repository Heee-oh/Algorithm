
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int min = Integer.parseInt(st.nextToken());
        int max = Integer.parseInt(st.nextToken());


        bw.write(prime(min,max));
        bw.flush();
        bw.close();

    }

    private static String prime(int min, int max) {
        boolean[] arr = new boolean[1000001];
        StringBuilder sb = new StringBuilder();
        arr[1] = true;

        for (int i = 2; i * i <= max; i++) {
            for (int j = i * i; j <= max; j+= i) {

                if (!arr[j]) {
                    arr[j] = true;
                }
            }
        }


        for (int i = (int) min; i <= max; i++) {
            if (!arr[i]) {
                sb.append(i).append("\n");
            }

        }

        return sb.toString();
    }

}

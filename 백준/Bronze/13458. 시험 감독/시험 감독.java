import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long sum = n;
        for (int i = 0; i < n; i++) {
            if (arr[i] - B < 0) continue;
            
            int cnt = ((arr[i] - B) / C)
                    + ((arr[i] - B) % C == 0 ? 0 : 1);

            sum += cnt;
        }

        System.out.println(sum);

    }
}

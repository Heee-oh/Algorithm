import java.io.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int aSize = Integer.parseInt(st.nextToken());
        int bSize = Integer.parseInt(st.nextToken());

        int[] A = new int[aSize];
        int[] B = new int[bSize];


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < aSize; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < bSize; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        Arrays.sort(B);

        int size = 0;
        for (int num : A) {
            int front = 0, back = bSize - 1;

            while (front < back) {

                int mid = (front + back) >>> 1;

                if (B[mid] < num) {
                    front = mid + 1;
                } else {
                    back = mid;
                }
            }

            if (B[front] != num) {
                sb.append(num).append(" ");
                size++;
            }

        }

        bw.write(sb.length() == 0 ? "0" : sb.insert(0, size + "\n").toString());
        bw.flush();
        bw.close();

    }



}
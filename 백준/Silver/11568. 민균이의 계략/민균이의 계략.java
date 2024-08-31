import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        int[] lis = new int[n]; // 최장 증가 부분 수열
        int[] seq = new int[n]; // 원본 수열
        int[] record = new int[n];  // 기록

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int lastIndex = 1;
        lis[0] = seq[0];
        for (int i = 1; i < n; i++) {
            int num = seq[i];

            if (lis[lastIndex - 1] < num) {
                lastIndex++;
                lis[lastIndex -1] = num;

                record[i] = lastIndex - 1;

            } else {
                int left = 0;
                int right = lastIndex;

                while (left < right) {

                    int mid = (left + right) / 2;

                    if (lis[mid] < num) {
                        left = mid + 1;
                    }else {
                        right = mid;
                    }
                }

                lis[left] = num;
                record[i] = left;
            }
        }


//        int check = lastIndex-1;
        bw.write(lastIndex+ "");
//        int[] result = new int[lastIndex];

        // 역순으로 읽어서 가장 큰 값
//        for (int i = record.length - 1; i >= 0; i--) {
//            if (record[i] == check) {
//                result[check] = seq[i];
//                check--;
//            }
//        }
//
//        for (int i : result) {
//            sb.append(i).append(" ");
//        }


//        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }






}

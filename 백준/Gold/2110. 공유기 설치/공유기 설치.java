import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken()) - 1; // 맨 처음 마지막 설치
        int[] houses = new int[n];
        int max = 0;

        for (int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(br.readLine());
            max = Math.max(houses[i], max);
        }

        Arrays.sort(houses); // 집 좌표대로 정렬



        int front = 1, back = max;
        int tmp  = 5;

        while (front <= back) {


            int mid = (front + back) >>> 1;
            int start = 0, end = 1;
            int cnt = 0; // mid 거리로 설치시 가능한 공유기 카운트


            // 공유기 임시 설치 
            while (end < n) {
                if (houses[end] - houses[start] >= mid) {
                    start = end;
                    end++;
                    cnt++;

                } else {
                    end++;
                }
            }
            
            // 임의로 설치한 공유기 개수가 설치 가능한 개수이상이면 공유기 간 거리를 더 넓힘
            if (cnt >= c) {
                front = mid + 1;
                max = mid;

                // 특정 거리로 설치한 공유기 개수가 설치 가능 개수보다 적다면 공유기 간 거리를 더 좁힘
            } else {
                back = mid - 1;
            }

        }





        bw.write(max + "");
        bw.flush();
        bw.close();
    }
}
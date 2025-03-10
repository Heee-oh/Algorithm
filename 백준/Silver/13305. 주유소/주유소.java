import java.io.*;
import java.util.*;

public class Main {
    //왼쪽에서 오른쪽으로, 처음 기름 없어서 넣고 출발, 기름통 크기 무제한
    //1 km 당 1L 사용
    // 주유소는 하나 존재, 도시 마다 주유소 리터당 가격 다를 수 있음

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        long[] dist = new long[n - 1]; //거리 비용
        int[] city = new int[n]; // 각 도시 주유소 비용

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            city[i] = Integer.parseInt(st.nextToken());
        }

        long min = Integer.MAX_VALUE; // 최소 주유소 가격
        long curCost = 0; // 현재까지 비용

        for (int i = 0; i < dist.length; i++) {
            min = Math.min(min, city[i]); // 주유소 중 최솟값 구하기
            curCost += min * dist[i]; // 주유

        }

        bw.write(curCost + "");
        bw.flush();
        bw.close();
    }

}
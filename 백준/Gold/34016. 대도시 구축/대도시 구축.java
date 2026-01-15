import java.util.*;
import java.io.*;

public class Main {

    // 양방향, 도로건설
    // 비용은 a - b 연결시 a+b
    // 강 있으면 설치 불가
    // 최소비용으로 모든 마을 연결

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 1~N까지 합 - 1 (1과의 연결이므로 1 제외) + (1과 연결했으므로 비용 1씩 (N-1)개)
        if (M == 0) {
            System.out.println((N * (N + 1) / 2) - 1 + (N - 1));
            return;
        }

        int[][] block = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            block[i][0] = Integer.parseInt(st.nextToken());
            block[i][1] = Integer.parseInt(st.nextToken());
        }

        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(1);

        for (int i = 0; i < M; i++) {
            if (block[i][0] == ts.first()) {
                ts.add(block[i][1]);

            } else if (block[i][1] == ts.first()) {
                ts.add(block[i][0]);
            }
        }

        long sum = ts.stream().mapToLong(Long::valueOf).sum();
        long answer = (N * (N + 1) / 2) - sum + (N - ts.size());
        ts.pollFirst(); // 1버리기

        if (ts.isEmpty()) {
            System.out.println(answer);
            return;
        }

        long sum1 = ts.size() == 1 ?
                0  // 1개일 시 1과 연결된 집합중 가장 작은 마을과 연결하면됨
                : ts.stream().mapToLong(Long::valueOf).sum(); // 1과 연결되지 못한 2개의 마을의 합을 구하고 가장 작은 마을과 연결
        int min = 0;

        for (int i = 2; i <= 4; i++) {
            boolean flag = false;

            // block 조건 확인, 현재 탐색하는 1번마을과 연결된 가장 작은 마을 i -> 연결되지 못한 마을 이 있는지 확인
            // 있다면 해당 i번 마을은 불가능
            for (int j = 0; j < M; j++) {
                if (block[j][0] == i && ts.contains(block[j][1])
                        || block[j][1] == i && ts.contains(block[j][0])) {
                    flag = true;
                }
            }

            if (flag || ts.contains(i)) continue;
            min = i; break;
        }

        long case1 = sum1 + ts.first() + min; // 1집합에 못들어간 나머지끼리를 연결한다음 1집합의 가장 작은 마을과 연결
        long case2 = ts.stream().mapToLong(Long::valueOf).sum() + (long) ts.size() * min; // 1집합의 가장 작은 마을과 각각 연결

        answer = Math.min(answer + case1, answer + case2);
        System.out.println(answer);
    }
}
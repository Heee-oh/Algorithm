import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Deque<Integer> dq = new ArrayDeque<>();
        // 나뭐 떨어지면 그 수가 중심
        // 아니라면 두 수를 더햇을 때 나온 값
        for (int i = L; i <= 100; i++) {

            int o2 = (int) Math.floor((double)N / i  * 10);

            int result = N % i;

            if (i % 2 == 0) {
                if (o2 >= 10) {
                    result = o2 % 10;
                } else {
                    result = 0;
                }
            }

            if (result == 5 || result == 0) {
                int tmp = N / i;


                // 짝수 일 때
                if (i % 2 == 0) {

                    if ((tmp + tmp + 1) * i / 2 != N) {
                        continue;
                    }

                    // 연속된 음이 아닌 정수 리스트여야함
                    if (tmp - ((i / 2) - 1) < 0) {
                        continue;
                    }

                    dq.addFirst(tmp);
                    dq.addLast(tmp+1);

                    for (int idx = 1; idx < i / 2; idx++) {
                        dq.addFirst(tmp - idx);
                        dq.addLast(tmp + 1 + idx);
                    }

                    dq.stream().forEach(x -> sb.append(x + " "));
                    System.out.println(sb.toString());
                    return;




                    // 홀 수 일때
                } else {

                    if (tmp * i != N) continue;

                    // 연속된 음이 아닌 정수 리스트여야함
                    if (tmp - (i / 2) < 0) {
                        continue;
                    }

                    dq.add(tmp);

                    for (int idx = 1; idx < i / 2 + 1; idx++) {
                        dq.addFirst(tmp - idx);
                        dq.addLast(tmp + idx);
                    }

                    dq.stream().forEach(x -> sb.append(x + " "));
                    System.out.println(sb.toString());
                    return;
                }

            }
        }


        System.out.println(-1);

    }

}
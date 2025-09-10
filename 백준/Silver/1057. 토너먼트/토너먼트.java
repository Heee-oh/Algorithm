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
        int jimin = Integer.parseInt(st.nextToken());
        int hansu = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            q.add(i);
        }

        int turn = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            turn++;

            int cnt = 1;
            while (size - 2 >= 0) {
                size -= 2;

                int num1 = q.poll();
                int num2 = q.poll();

                if (num1 == jimin && num2 == hansu
                        || num1 == hansu && num2 == jimin) {
                    System.out.println(turn);
                    return;
                }

                if (num1 == jimin || num2 == jimin) {
                    q.add(jimin);
                } else if (num1 == hansu || num2 == hansu) {
                    q.add(hansu);
                } else {
                    q.add(num1);
                }
            }

            if (size == 1) {
                q.add(q.poll());
            }
        }


        System.out.println(-1);

    }

}
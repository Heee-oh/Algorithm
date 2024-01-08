import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    public static Queue<Integer> card;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        // 자료구조 큐 선언
        card = new LinkedList<>();

        // 1부터 ~ N까지 삽입
        for (int i = 0; i < N; i++) {
            card.add(i+1);
        }

        // 특정 동작
        cardAction(card);

        System.out.println(card.poll());



    }

    public static void cardAction(Queue<Integer> queue) {
        int swap = 0;
        while (queue.size() > 1) {
            // 1번 TOP 버리기
            queue.poll();

            // 2번  TOP을 BOTTOM 밑에 넣기
            swap = queue.poll();
            queue.add(swap);

        }

    }
}
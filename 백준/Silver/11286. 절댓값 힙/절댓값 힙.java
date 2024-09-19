import java.io.*;
import java.util.*;

public class Main {

    static class Num implements Comparable<Num>{
        int absN, originNum;

        public Num(int absN, int originNum) {
            this.absN = absN;
            this.originNum = originNum;
        }


        @Override
        public int compareTo(Num n) {

            return absN == n.absN ? originNum - n.originNum : absN - n.absN;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Num> pq = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());

        // 개수 처리를 어떻게 할 것인가

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num ==  0) {
                if(pq.isEmpty()) {
                    sb.append("0\n");
                }else {
                    sb.append(pq.poll().originNum).append("\n");

                }
            } else {
                // 절댓값 , 원래 수
                pq.add(new Num(num < 0 ? num * (-1) : num, num));

            }

        }






        bw.write(sb.toString() );
        bw.flush();
        bw.close();
    }

    // DNA가 맞는지 확인

}

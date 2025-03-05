import java.io.*;
import java.util.*;

public class Main {
    // 몸무게, 키 = 덩치
    // 몸무게 키 둘다 큰사람이 더 큰 덩치

    static class BodySize {
        int weight;
        int height;
        int rank;
        int num;

        public BodySize(int weight, int height, int rank, int num) {
            this.weight = weight;
            this.height = height;
            this.rank = rank;
            this.num = num;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        List<BodySize> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] info = br.readLine().split(" ");
            int w = Integer.parseInt(info[0]);
            int h = Integer.parseInt(info[1]);
            list.add(new BodySize(w, h, 0, i + 1));
        }


        for (int i = 0; i < n; i++) {
            int answer = 0;
            BodySize size = list.get(i);

            for (int j = 0; j < n; j++) {
                if (i == j) continue;


                // 자신보다 다름사람이 덩치가 크면 + 1
                if (size.weight < list.get(j).weight && size.height < list.get(j).height) {
                    answer++;
                }
            }
            bw.write((answer + 1)+ " ");
        }


        bw.flush();
        bw.close();
    }

}



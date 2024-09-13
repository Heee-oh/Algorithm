import java.io.*;
import java.util.*;

public class Main {


    // 기본 크기 2 , 상하좌우 1초 이동
    // 상어 > 물고기 o , 상어 == 물고기 x 대신 통과가능
    // 크기 만큼 물고기를 먹어야함


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        String dnaLine = br.readLine();
        int[] dnaCheck = new int[4];

        st = new StringTokenizer(br.readLine());

        // ACGT의 초소 개수
        for (int i = 0; i < 4; i++) {
            dnaCheck[i] = Integer.parseInt(st.nextToken());
        }


        int answer = 0;
        int front = 0;
        int back = 0;
        int size = 0;
        int[] check = new int[4];
        while (back < s ) {
            int idx = findIdx(dnaLine.charAt(back));

            if (size < p) {
                if (idx >= 0 ) check[idx]++;
                size++;
                back++;

            } else if (size > p){
                int idx1 = findIdx(dnaLine.charAt(front));
                if (idx1 >= 0)  check[idx1]--;
                front++;
                size--;

            }else{

                if (isDna(dnaCheck, check)) answer++;

                int idx1 = findIdx(dnaLine.charAt(front));
                check[idx1]--;
                front++;
                size--;

            }

        }

        if (isDna(dnaCheck, check)) answer++;



        bw.write(answer + "");
        bw.flush();
        bw.close();
    }

    private static boolean isDna(int[] dnaCheck, int[] check) {
        boolean flag = true;
        for (int i = 0; i < 4; i++) {
            if (dnaCheck[i] > check[i]) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private static int findIdx(char word) {
        if (word == 'A') return 0;
        else if (word == 'C') return 1;
        else if (word == 'G') return 2;
        else if (word == 'T') return 3;

        return -1;
    }

}

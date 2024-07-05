import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
//    static boolean visited[][];
//    static int[] dx = {0, 0, -1, 1};
//    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 테스트 케이스 개수
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int count = 0;

        while (a < b) {
            if (b % 10 == 1) {
                b /= 10;
                count++;
            }else if (b % 2 == 0) {
                b /= 2;
                count++;
            } else {
                break;
            }

        }

        if (a == b) {
            System.out.println(count + 1);
        }else {
            System.out.println("-1");
        }
    }



}


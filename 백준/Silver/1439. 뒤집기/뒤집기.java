import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
//    static boolean visited[][];
//    static int[] dx = {0, 0, -1, 1};
//    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스 개수
        String s = br.readLine();

        // 다 1 이거나 0일 때 0출력
        if (!s.contains("0") || !s.contains("1")) {
            System.out.println("0");
            return;
        }

        String[] split = s.split("0+");
        String[] split1 = s.split("1+");

        int oneCount = 0, zeroCount = 0;

        for (String string : split) {
            if (string.contains("1")) {
                oneCount++;
            }
        }

        for (String string : split1) {
            if (string.contains("0")) {
                zeroCount++;
            }
        }

        System.out.println(Math.min(oneCount, zeroCount));


    }



}


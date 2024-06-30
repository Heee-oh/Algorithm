import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int size = Integer.parseInt(br.readLine());
        int[] T = new int[51];
        // Tx + Ty + Ta 형식으로 찾아야함

        T[0] = 0;
        for (int i = 1; i < 51; i++) {
            T[i] = T[i-1] + i;
        }


        while (size-- > 0) {
            int num = Integer.parseInt(br.readLine());
            boolean flag = true;

            for (int i = 1; i < T.length; i++) {
                for (int j = 1; j < T.length; j++) {
                    for (int k = 1; k < T.length; k++) {
                        if (num == T[i] + T[j] + T[k]) {
                            bw.write("1\n");
                            bw.flush();
                            flag = false;
                            break;
                        }
                    } // k
                    if (!flag) break;
                } // j
                if (!flag) break;
            } // i

            if (flag) bw.write("0\n");
        }

        bw.flush();
        bw.close();

    }
}
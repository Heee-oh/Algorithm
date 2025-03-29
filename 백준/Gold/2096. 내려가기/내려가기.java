import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static int[] dx = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][3];
        int[][] maxDp = new int[n][3];
        int[][] minDp = new int[n][3];

        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        for (int i = 0; i < n; i++) {
            Arrays.fill(minDp[i], 1000000000);
        }

        minDp[0][0] = maxDp[0][0] = arr[0][0];
        minDp[0][1] = maxDp[0][1] = arr[0][1];
        minDp[0][2] = maxDp[0][2] = arr[0][2];


        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j + dx[k] < 0 || j + dx[k] >= 3) continue;
                    maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i - 1][j + dx[k]] + arr[i][j]);
                    minDp[i][j] = Math.min(minDp[i][j], minDp[i - 1][j + dx[k]] + arr[i][j]);
                }

            }
        }




        bw.write(Arrays.stream(maxDp[n - 1]).max().getAsInt() + " " + Arrays.stream(minDp[n - 1]).min().getAsInt());
        bw.flush();
        bw.close();

    }



}

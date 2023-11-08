import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder(br.readLine());
        
        int x = Integer.parseInt(sb.toString());
        
        if(x < 100) {
            bw.write(sb.toString());
            bw.flush();
            return;
        }

        int[][] arr = new int[x+1][4];
        
        for(int i = 100; i <= x; i++) {
            arr[i][0] =  i / 100;
            arr[i][1] =  (i / 10) % 10;
            arr[i][2] =  (i % 100) % 10;
        }

        int count = 0;
        for(int i = 100; i <= x; i++) {
            if (arr[i][0] - arr[i][1] == arr[i][1] - arr[i][2] )
                count++;
        }

        bw.write((count + 99) + "");
        bw.flush();



    }
}
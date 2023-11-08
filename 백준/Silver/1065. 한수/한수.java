import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder(br.readLine());
        
        if(sb.length() < 3) {
            bw.write(Integer.parseInt(sb.toString())+"");
            bw.flush();
            return;
        }

        int x = Integer.parseInt(sb.toString());
   
        int[][] arr = new int[x+1][4];
        
        for(int i = 100; i <= x; i++) {
            arr[i][0] =  i / 100;
            arr[i][1] =  (i / 10) % 10;
            arr[i][2] =  (i % 100) % 10;
        }

        int count = 0;
        for(int i = 100; i <= x; i++) {

            if (arr[i][0] - arr[i][1] == arr[i][1] - arr[i][2] && arr[i][0] != arr[i][2])
                count++;
            if(arr[i][0] == arr[i][1] && arr[i][1] == arr[i][2]) {
                count++;
            }
        }

        bw.write((count + 99) + "");
        bw.flush();



    }
}
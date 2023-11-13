import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[n];
        
        for(int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        
        
        int m = Integer.parseInt(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            if(BSearch(A, Integer.parseInt(st1.nextToken()))) 
                bw.write("1\n");
            else
                bw.write("0\n");

        }

        bw.flush();
        bw.close(); // 종료
    }

    public static boolean BSearch(int[] arr, int n) {
        int left = 0;
        int right = arr.length - 1;
        int mid;

        while(left <= right) {
            mid = (left + right) / 2;
            if(arr[mid] < n) left = mid + 1;
            else if(arr[mid] > n) right = mid - 1;
            else return true;

        }

        return false;
    }

}
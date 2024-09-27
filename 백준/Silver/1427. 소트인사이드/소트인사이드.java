import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String num = br.readLine();
        int[] arr = new int[10];

        for (int i = 0; i < num.length(); i++) {
            arr[num.charAt(i)-'0']++;
        }

        for (int i = 9; i >= 0 ; i--) {
            for (int j = 0; j < arr[i]; j++) {
                sb.append(i);
            }
        }





        bw.write(sb.toString()+ "");
        bw.flush();
        bw.close();
    }
}




// 알고리즘 코테 교재에서는 선택정렬로 하라해서 이것도 올림

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String num = br.readLine();

        int[] arr = new int[num.length()];


        for (int i = 0; i < arr.length; i++) {
            arr[i] = num.charAt(i) - '0';
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int idx = i;

            for (int j = 1 + i; j < arr.length; j++) {
                if (arr[j] > arr[idx]) {
                    idx = j;
                }
            }


            int tmp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = tmp;
        }


        for (int i : arr) {
            sb.append(i);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


}

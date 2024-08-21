import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String func = br.readLine();
            int size = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            int[] arr = new int[size];

            // 배열 입력 처리
            String s1 = br.readLine();
            String str = s1.substring(1, s1.length() - 1);
            String[] split = str.split(",");

            for (int j = 0; j < size; j++) {
                arr[j] = Integer.parseInt(split[j]);
            }
            
            int pointer = 0, prePointer = arr.length - 1;
            boolean flag = false; // false = 오름차, true 내림차

            for (int j = 0; j < func.length(); j++) {
                if (func.charAt(j) == 'R') {
                    int tmp = pointer;
                    pointer = prePointer;
                    prePointer = tmp;
                    flag = !flag;
                    
                } else {
                    if (flag) pointer--;
                    else pointer++;
                }
            }


            if (pointer < -1 || pointer > size || prePointer < -1 || prePointer > size) {
                bw.write("error\n");
                continue;
            }


            if (flag) {
                for (int j = pointer; j >= prePointer; j--) {
                    sb.append(arr[j]).append(",");
                }

            } else {
                for (int j = pointer; j <= prePointer; j++) {
                    sb.append(arr[j]).append(",");
                }
            }

            sb.insert(0,"[");
            if(sb.length() > 1) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]\n");


            bw.write(sb.toString());

        }

        bw.flush();
        bw.close();

    }


}
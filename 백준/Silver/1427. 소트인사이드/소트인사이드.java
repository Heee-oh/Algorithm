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
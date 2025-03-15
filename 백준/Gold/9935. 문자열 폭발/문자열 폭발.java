import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        String boomStr = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));

            if(sb.length() >= boomStr.length()
                    && str.charAt(i) == boomStr.charAt(boomStr.length() - 1)) {

                int range = sb.length() - boomStr.length();
                if (sb.substring(range).equals(boomStr)) {
                    sb.delete(range, sb.length());
                }
            }
        }

        bw.write(sb.length() == 0 ? "FRULA" : sb.toString());
        bw.flush();
        bw.close();
    }

}

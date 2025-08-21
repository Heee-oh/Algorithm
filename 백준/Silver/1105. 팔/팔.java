import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String originL = st.nextToken();
        String originR = st.nextToken();
        int L = Integer.parseInt(originL);
        int R = Integer.parseInt(originR);

        if (L == R) {
            int cnt = 0;
            for (int i = 0; i < originR.length(); i++) {
                if (originR.charAt(i) == '8') {
                    cnt++;
                }
            }

            System.out.println(cnt);
            return;
        }

        if (originL.length() < originR.length()) {
            System.out.println(0);
            return;

            // 같다면
        } else {
            int cnt = 0;
            for (int i = 0; i < originR.length(); i++) {
                if (originL.charAt(i) == originR.charAt(i)) {
                    if (originL.charAt(i) == '8') {
                        cnt++;
                    }
                } else {
                    System.out.println(cnt);
                    return;
                }
            }

            System.out.println(cnt);
        }


    }


}

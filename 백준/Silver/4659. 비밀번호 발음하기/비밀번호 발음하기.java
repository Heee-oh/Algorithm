import java.io.*;
import java.util.*;

public class Main {
    static String[] eval = {"is acceptable.", "is not acceptable."};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 패스워드 읽기
        while (true) {
            String password = br.readLine();

            // end면 입력 종료
            if (password.equals("end")) {
                break;
            }

            // 모음 하나 반드시 포함
            if(!password.matches(".*[aeiou].*")
                    || password.matches(".*[aeiou]{3,}.*") // 자음 모음 3개 연속
                    || password.matches(".*[^aeiou]{3,}.*")
                    || password.matches(".*([a-df-np-z])\\1.*")) { // ee, oo 제외한 연속된 같은 글자 제외

                bw.write(String.format("<%s> is not acceptable.\n", password));
                continue;
            }

            bw.write(String.format("<%s> is acceptable.\n", password));
        }

        bw.flush();
        bw.close();
    }

}
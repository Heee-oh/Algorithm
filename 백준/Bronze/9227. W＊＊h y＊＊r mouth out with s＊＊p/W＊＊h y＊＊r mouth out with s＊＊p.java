import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<String> wordList = new ArrayList<>();
        List<char[]> strList = new ArrayList<>();

        boolean flag = false;
        while (true) {
            String line = br.readLine();
            if (line.equals("#")) break;

            if (line.equals("##")) {
                flag = true;
                continue;
            }

            if (!flag) {
                wordList.add(line);
            } else {
                strList.add(line.toCharArray());
            }
        }

        StringBuilder sb = new StringBuilder();

        for (char[] str : strList) {
            int len = str.length;
            int j = 0;

            while (j <= len - 4) {
                boolean replaced = false;
                for (String pair : wordList) {
                    if (str[j] == pair.charAt(0) && str[j + 3] == pair.charAt(1)) {
                        // 가운데가 공백이 아닌 경우만 처리
                        if (Character.isLetter(str[j + 1]) && Character.isLetter(str[j + 2])) {
                            str[j + 1] = '*';
                            str[j + 2] = '*';
                            replaced = true;
                            break;
                        }
                    }
                }

                j += replaced ? 4 : 1;  // 중첩 방지 위해 처리된 경우 4칸 건너뜀
            }

            sb.append(new String(str)).append("\n");
        }

        System.out.println(sb);
    }
}

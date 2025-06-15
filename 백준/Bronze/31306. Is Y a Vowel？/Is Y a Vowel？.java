import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] words = br.readLine().toCharArray();
        int[] arr = new int['z' - 'a' + 1];

        arr[0] = arr['e'- 'a'] = arr['i' - 'a'] = arr['o'- 'a'] = arr['u' - 'a'] = 1;

        int y = 0;
        int cnt = 0;
        for (int i = 0; i < words.length; i++) {
            int idx = words[i] - 'a';
            if (words[i] == 'y') y++;
            cnt += arr[idx];
        }

        System.out.println(cnt + " " + (cnt +y) );

    }



}

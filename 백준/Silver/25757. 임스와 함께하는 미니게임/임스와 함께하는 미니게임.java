import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Set<String> playerCheck = new HashSet<>();
        int n = Integer.parseInt(st.nextToken());
        char gameCode = st.nextToken().charAt(0);


        for (int i = 0; i < n; i++) {
            String player = br.readLine();
            playerCheck.add(player);
        }

        int count = getPlayerCount(gameCode) - 1; // 임스는 항상 참여하므로 게임 인원에서 1을 빼야함
        int playCount = playerCheck.size() / count;

        bw.write(playCount + "");
        bw.flush();
        bw.close();
    }

    private static int getPlayerCount(char code) {
        switch (code) {
            case 'Y' : return 2;
            case 'F' : return 3;
            default : return 4;
        }
    }

}
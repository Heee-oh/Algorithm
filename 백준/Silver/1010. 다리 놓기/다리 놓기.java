import java.util.StringTokenizer;
import java.io.*;

public class Main {
    static int[][] dp = new int[31][31];
    
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine()); 
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        
        for(int i = 0; i < testCase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int west = Integer.parseInt(st.nextToken());
            int east = Integer.parseInt(st.nextToken());    
            
            if(west == east){
                bw.write(1+"\n");
                bw.flush();
            } else if (west == 1) {
                bw.write(east+"\n"); 
                bw.flush();
            } else {
                for(int j = 0; j < 31; j++) {
                dp[j][0] = dp[j][j] = 1; // nC0과 nCn은 1입니다.
                    for(int k = 1; k < j; k++) {
                        dp[j][k] = dp[j-1][k-1] + dp[j-1][k];
                    }
                }
                
                bw.write(dp[east][west]+"\n"); 
                bw.flush();
                
            }
               
        }
        bw.close(); // 종료
    }
    


}
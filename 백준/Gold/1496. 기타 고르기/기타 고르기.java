import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[2 * n];
        long totalSum = 0;
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            // 원형 배열을 쉽게 처리하기 위해 배열을 2배
            arr[i + n] = arr[i]; 
            totalSum += arr[i];
        }
        
        if (n == 1) {
            System.out.println(arr[0]);
            return;
        }

        // dp[i][j]: 인덱스 i부터 j까지의 선형 배열에서 
        // 현재 턴인 플레이어가 얻을 수 있는 최대 점수 차이 (내 점수 - 상대 점수)
        long[][] dp = new long[2 * n][2 * n];
        
        // 1. 기저 상태: 구간 길이가 1일 때 (남은 기타가 1개)
        for (int i = 0; i < 2 * n; i++) {
            dp[i][i] = arr[i];
        }
        
        // 2. 구간 DP: 길이가 2부터 N-1인 선형 구간에 대해 차례대로 계산 (Bottom-up)
        for (int len = 2; len <= n - 1; len++) {
            for (int i = 0; i <= 2 * n - len; i++) {
                int j = i + len - 1;
                long maxDiff = Long.MIN_VALUE;
                
                // 현재 구간 [i, j]에서 k번째 기타를 고르는 모든 경우를 탐색
                for (int k = i; k <= j; k++) {
                    long leftDiff = (k > i) ? dp[i][k - 1] : 0;
                    long rightDiff = (k < j) ? dp[k + 1][j] : 0;
                    
                    // 핵심 로직: (내가 고른 기타 점수) - (상대방이 남은 두 그룹에서 가져갈 점수 차이 합)
                    long currentDiff = arr[k] - (leftDiff + rightDiff);
                    maxDiff = Math.max(maxDiff, currentDiff);
                }
                dp[i][j] = maxDiff;
            }
        }
        
        // 3. 첫 턴 처리 (원형 배열)
        // 세준이는 처음에 N개의 기타 중 하나를 고르며 원형 고리를 끊음
        long finalMaxDiff = Long.MIN_VALUE;
        for (int k = 0; k < n; k++) {
            // 세준이가 k를 고르면, 상대방은 [k+1, k+N-1] 구간에서 시작
            long opponentDiff = dp[k + 1][k + n - 1];
            long currentDiff = arr[k] - opponentDiff;
            finalMaxDiff = Math.max(finalMaxDiff, currentDiff);
        }
        
        // 4. 최종 내 점수 계산
        // 내 점수 - 상대 점수 = finalMaxDiff
        // 내 점수 + 상대 점수 = totalSum
        // 이 두 식을 더해서 2로 나누면 내 점수
        long myScore = (totalSum + finalMaxDiff) / 2;
        System.out.println(myScore);
    }
}
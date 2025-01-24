class Solution {
    boolean[] visited;
    boolean[] check = new boolean[10000000];
    int answer = 0;
    
    public int solution(String numbers) {
        char[] num = numbers.toCharArray();
        visited = new boolean[num.length];
                
        dfs(num, 0);
        return answer;
    }
    
    // 백트래킹 탐색으로 숫자 만들기 
    // 짝수로 끝나면 소수일 리가 없음 
    // 0으로 시작하면 안됨
    // 0, 1, 2는 소수가 아님
    private void dfs(char[] num, int n) {
        
        // 이미 확인한 숫자이면 
        if (check[n]) return;
        check[n] = true;
        
        // 소수 판별 체크
        if (n > 1 && isPrime(n)) {
            answer++;
        } 
        
        
        for (int i = 0; i < num.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(num, (n * 10) + (num[i] - '0'));
                visited[i] = false;
            }
        }
    }
    
    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }   
        }
        
        return true;
    }
}
import java.util.*;

class Solution {
    static int N;
    static int[][] dice;
    static List<int[]> combinations = new ArrayList<>();
    static int maxWinCount = -1;
    static List<Integer> answerList = new ArrayList<>();

    public int[] solution(int[][] diceInput) {
        dice = diceInput;
        N = dice.length;

        // A팀 조합 만들기 (n/2개 선택)
        makeCombinations(0, new int[N / 2], 0);

        // 각 조합에 대해 승리 횟수 계산
        for (int[] teamA : combinations) {
            // B팀은 A에 포함되지 않은 주사위
            int[] teamB = getComplement(teamA);

            int[] sumA = getAllSums(teamA);
            int[] sumB = getAllSums(teamB);

            Arrays.sort(sumB); // 이진 탐색을 위해 정렬

            int winCount = 0;
            for (int s : sumA) {
                // B에서 s보다 작은 합의 개수 → A가 이기는 수
                winCount += countLowerThan(sumB, s);
            }

            if (winCount > maxWinCount) {
                maxWinCount = winCount;
                answerList = new ArrayList<>();
                for (int idx : teamA) answerList.add(idx + 1); // 주사위는 1-indexed
            }
        }

        return answerList.stream().mapToInt(i -> i).toArray();
    }

    // A팀 조합 만들기
    private void makeCombinations(int depth, int[] selected, int start) {
        if (depth == N / 2) {
            combinations.add(selected.clone());
            return;
        }

        for (int i = start; i < N; i++) {
            selected[depth] = i;
            makeCombinations(depth + 1, selected, i + 1);
        }
    }

    // A팀에 포함되지 않은 주사위 인덱스 구하기
    private int[] getComplement(int[] teamA) {
        Set<Integer> set = new HashSet<>();
        for (int i : teamA) set.add(i);

        int[] result = new int[N / 2];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            if (!set.contains(i)) result[idx++] = i;
        }
        return result;
    }

    // 선택한 주사위 조합의 가능한 모든 합 구하기
    private int[] getAllSums(int[] team) {
        List<Integer> sums = new ArrayList<>();
        dfsSum(team, 0, 0, sums);
        return sums.stream().mapToInt(i -> i).toArray();
    }

    private void dfsSum(int[] team, int depth, int sum, List<Integer> result) {
        if (depth == team.length) {
            result.add(sum);
            return;
        }

        for (int face : dice[team[depth]]) {
            dfsSum(team, depth + 1, sum + face, result);
        }
    }

    // 이진 탐색으로 target보다 작은 수의 개수 구하기
    private int countLowerThan(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

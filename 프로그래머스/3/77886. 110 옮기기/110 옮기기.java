class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            String str = s[i];
            int cnt = 0;
            StringBuilder sb = new StringBuilder();

            // "110"을 제거하면서 개수 세기
            for (char c : str.toCharArray()) {
                sb.append(c);
                if (sb.length() >= 3 &&
                    sb.substring(sb.length() - 3).equals("110")) {
                    sb.setLength(sb.length() - 3);
                    cnt++;
                }
            }

            //  남은 문자열에서 '0'의 마지막 위치 찾기
            int idx = sb.lastIndexOf("0");

            //  "110"을 cnt만큼 삽입
            if (idx == -1) {
                // 0이 없다면 맨 앞에 삽입
                sb.insert(0, "110".repeat(cnt));
            } else {
                // 마지막 0 뒤에 삽입
                sb.insert(idx + 1, "110".repeat(cnt));
            }

            answer[i] = sb.toString();
        }

        return answer;
    }
}

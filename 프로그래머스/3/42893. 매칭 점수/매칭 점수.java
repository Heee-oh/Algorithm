import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String word, String[] pages) {
        word = word.toLowerCase();
        
        // url에 따른 외부 링크 리스트 (내가 가리키는 링크들)
        Map<String, List<String>> map = new HashMap<>();
        // 나를 가리키는 페이지 리스트 (역방향 인덱스)
        Map<String, List<String>> linkedMap = new HashMap<>();
        
        // url별 기본 점수 (실수형으로 변경)
        Map<String, Double> scoreMap = new HashMap<>();
        // url별 인덱스
        Map<String, Integer> idxMap = new HashMap<>();
        
        // 정규표현식 패턴 컴파일 (파싱 안정성 확보)
        Pattern urlPattern = Pattern.compile("<meta property=\"og:url\" content=\"https://(.+?)\"");
        Pattern hrefPattern = Pattern.compile("<a href=\"https://(.+?)\"");
        
        for (int i = 0; i < pages.length; i++) {
            String page = pages[i].toLowerCase(); // 전체 소문자 변환
            
            // 현재 페이지 URL 추출
            Matcher urlMatcher = urlPattern.matcher(page);
            String pageUrl = "";
            if (urlMatcher.find()) {
                pageUrl = urlMatcher.group(1);
            }
            
            // 데이터 초기화
            map.put(pageUrl, new ArrayList<>());
            idxMap.put(pageUrl, i);
            scoreMap.put(pageUrl, 0.0);
            
            // 기본 점수 계산 (단어 등장 횟수)
            // 알파벳이 아닌 모든 문자를 기준으로 분리
            String[] tokens = page.split("[^a-z]+"); 
            double baseScore = 0;
            for (String token : tokens) {
                if (token.equals(word)) {
                    baseScore++;
                }
            }
            scoreMap.put(pageUrl, baseScore);

            // 외부 링크 추출
            Matcher hrefMatcher = hrefPattern.matcher(page);
            while (hrefMatcher.find()) {
                String exUrl = hrefMatcher.group(1);
                
                // 내(pageUrl)가 가리키는 리스트에 추가
                map.get(pageUrl).add(exUrl);
                
                // exUrl 입장에서 나(pageUrl)는 자신을 가리키는 페이지임
                linkedMap.computeIfAbsent(exUrl, k -> new ArrayList<>()).add(pageUrl);
            }
        }
        
        // 최종 매칭 점수 계산
        double maxScore = -1.0; 
        int answer = 0;
        
        // 등록된 모든 페이지(idxMap에 있는 URL)에 대해 점수 계산
        for (String keyUrl : idxMap.keySet()) {
            double basicScore = scoreMap.get(keyUrl);
            double linkScore = 0;
            
            // 나를 가리키는 페이지들이 있다면 링크 점수 합산
            if (linkedMap.containsKey(keyUrl)) {
                for (String sourceUrl : linkedMap.get(keyUrl)) {
                    // 나를 가리키는 페이지(sourceUrl)가 pages 배열 안에 존재하는 유효한 페이지인지 확인
                    if (scoreMap.containsKey(sourceUrl)) {
                        double sourceBasic = scoreMap.get(sourceUrl);
                        int sourceLinkCnt = map.get(sourceUrl).size();
                        linkScore += sourceBasic / sourceLinkCnt;
                    }
                }
            }
            
            double totalScore = basicScore + linkScore;
            
            // 최대 점수 갱신 (점수가 같으면 인덱스가 작은 순서 유지)
            int currentIdx = idxMap.get(keyUrl);
            if (totalScore > maxScore) {
                maxScore = totalScore;
                answer = currentIdx;
            } else if (totalScore == maxScore) {
                if (currentIdx < answer) {
                    answer = currentIdx;
                }
            }
        }
        
        return answer;
    }
}
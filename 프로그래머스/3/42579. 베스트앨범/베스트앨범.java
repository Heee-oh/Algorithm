import java.util.*;

class Solution {
    // 장르 최대 재생수
    // 노래 최대 재생 수
    // 고유번호 낮은 순
    
    class SongInfo {
        int uniqueNum;
        int playCount;
        
        public SongInfo(int idx, int play) {
            uniqueNum = idx;
            playCount = play;
        }    
    }
    
    class GenreInfo {
        String name;
        int playCount;
        
        public GenreInfo(String name, int play) {
            this.name = name;
            playCount = play;
        }    
    }
    
    public int[] solution(String[] genres, int[] plays) {
        PriorityQueue<GenreInfo> pq = new PriorityQueue<>((o1, o2) -> o2.playCount - o1.playCount);
        Map<String,ArrayList<SongInfo>> map = new HashMap<>();
        
        // 장르에 맞는 노래의 정보를 해시맵에 저장
        for (int i = 0; i < genres.length; i++) {
            ArrayList<SongInfo> song = map.getOrDefault(genres[i], new ArrayList<>());
            song.add(new SongInfo(i, plays[i]));
            map.put(genres[i], song);
        }
        
        int size = 0;
        // 장르 최대 재생 수
        // 각 노래 재생 수
        // 고유번호 오름차순 정렬
        for (String key : map.keySet()) {
            Collections.sort(map.get(key), (o1, o2) -> 
                             o2.playCount - o1.playCount == 0 ? o1.uniqueNum - o2.uniqueNum : o2.playCount - o1.playCount);
            int sum = map.get(key).stream().mapToInt(x -> x.playCount).sum();
            size += map.get(key).size() >= 2 ? 2 : 1;
            pq.add(new GenreInfo(key, sum));
        }
        
        int[] answer = new int[size];
        int idx = 0;
        
        
        while (!pq.isEmpty()) {
            GenreInfo genre = pq.poll();    
            for (int i = 0; i < (map.get(genre.name).size() >= 2 ? 2 : 1); i++) {
                answer[idx++] = map.get(genre.name).get(i).uniqueNum;
            }
        }
        
        return answer;
    }
}
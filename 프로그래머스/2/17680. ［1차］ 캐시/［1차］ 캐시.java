import java.util.*;

class Solution {
    
    class City {
        String name; 
        int priority;
        
        public City (String name, int priority) {
            this.name = name;
            this.priority = priority;
        }
        
        // 1. 이름(name)이 같으면 같은 도시로 판단
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            City city = (City) o;
            return Objects.equals(name, city.name);
        }

        // 2. equals의 기준인 name을 가지고 해시코드 생성
        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
        
    }
    
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        if (cacheSize == 0) return cities.length * 5;
        TreeSet<City> ts = new TreeSet<>((a, b) -> a.priority - b.priority);
        Map<String, City> map = new HashMap<>();
        int priority = 0;
        
        for (String str : cities) {
            String name = str.toLowerCase();
            // miss
            if (!map.containsKey(name)) {
                
                if (ts.size() >= cacheSize) {
                    City poll = ts.pollFirst();
                    map.remove(poll.name);
                }
                City newCity = new City(name, priority);
                ts.add(newCity);
                map.put(name, newCity);
                
                priority++;
                answer += 5;
                
                // hit
            } else {
                City tmp = map.get(name);
                ts.remove(tmp);
                
                City newCity = new City(name, priority);
                map.put(name, newCity);
                ts.add(newCity);
                
                priority++;
                answer++;
            }
        }
        return answer;
    }
}
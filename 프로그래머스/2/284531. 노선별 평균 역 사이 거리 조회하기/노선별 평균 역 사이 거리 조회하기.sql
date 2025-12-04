-- 노선별 노선 총누계 거리, 평균 역 사이 거리 조회
-- 총 누계 거리 = 역 사이 거리의 총 합
-- 총 누계 2, 평균 역 사이 3 에서 반올림, km 단위 출력
-- 총 누계 기준 내림차순

SELECT ROUTE, 
        CONCAT(ROUND(SUM(D_BETWEEN_DIST), 1), 'km') AS TOTAL_DISTANCE, 
        CONCAT(ROUND(AVG(D_BETWEEN_DIST), 2), 'km') AS AVERAGE_DISTANCE
FROM SUBWAY_DISTANCE
GROUP BY ROUTE
ORDER BY SUM(D_BETWEEN_DIST) DESC
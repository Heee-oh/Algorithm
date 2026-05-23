# 처음 로그인 여부
# 그 다음날 로그인 여부

SELECT ROUND(SUM(CASE WHEN DATE_ADD(t.first, INTERVAL 1 DAY) = a.event_date THEN 1 ELSE 0 END) / COUNT(DISTINCT a.player_id), 2) as fraction  
FROM Activity a
JOIN (
    SELECT player_id, MIN(event_date) as first
    FROM Activity
    GROUP BY player_id
    ) as t 
ON a.player_id = t.player_id




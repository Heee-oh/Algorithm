# 차단되지 않는 사용자가 요청 (클,드로 인한 취소) 비율
# 각 날짜별 취소율
# 클, 드 둘다 차단되지 않아야함
# 소수점 둘째자리 반오림
# 날짜 별 1개 이상 요청이 있는 경우만 결과 포함

SELECT request_at AS 'DAY', ROUND(SUM(status != 'completed') / COUNT(*), 2) AS 'Cancellation Rate'
FROM Trips t
JOIN Users client on t.client_id = client.users_id AND client.banned = 'NO'
JOIN Users driver on t.driver_id = driver.users_id AND driver.banned = 'NO'
WHERE request_at BETWEEN '2013-10-01' AND '2013-10-03'
GROUP BY request_at





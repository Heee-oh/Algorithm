# 서로 자리 바꾸기, 홀수는 마지막 체인지 x 
# id 오름차순

SELECT IF(t.id % 2 = 1, IF(t.post IS NULL, t.id, t.id +1), t.id - 1) AS id, t.student
FROM (
    SELECT LAG(id) OVER(ORDER BY id) AS prev, id, LEAD(id) OVER(ORDER BY id) AS post,  student
    FROM Seat
    ORDER BY id
) AS t
ORDER BY id asc
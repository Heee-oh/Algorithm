SELECT t.id, COUNT(*) as num
FROM 
(
    (
        SELECT requester_id as id
        FROM RequestAccepted
    )
    UNION ALL
    (
        SELECT accepter_id as id
        FROM RequestAccepted
    ) 
) as t
GROUP BY t.id
ORDER BY num DESC
LIMIT 1
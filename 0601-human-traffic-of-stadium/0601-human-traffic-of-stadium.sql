WITH t AS (
    SELECT LAG(id) OVER(ORDER BY id asc) AS prev, id, LEAD(id) OVER (ORDER BY id ASC) AS post 
        FROM Stadium
        WHERE people >= 100
)

SELECT *
FROM stadium
WHERE id IN (
    (
        SELECT t.prev
        FROM t
        WHERE post - prev = 2
    ) UNION (
        SELECT t.id
        FROM t
        WHERE post - prev = 2
    ) UNION (
        SELECT t.post
        FROM t
        WHERE post - prev = 2
    )
)
ORDER BY visit_date

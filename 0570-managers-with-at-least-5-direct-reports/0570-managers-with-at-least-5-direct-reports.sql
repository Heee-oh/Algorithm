SELECT t.name
FROM (
    SELECT e2.id, e2.name
    FROM Employee e1 
    JOIN Employee e2 ON e1.managerId = e2.id
    GROUP BY id, name
    HAVING COUNT(e1.id) >= 5
) as t

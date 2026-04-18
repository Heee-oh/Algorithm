SELECT IF(COUNT(*) = 1, NULL, MIN(e.salary)) as SecondHighestSalary 
FROM (
    SELECT DISTINCT(salary)
    FROM Employee
    ORDER BY salary DESC
    LIMIT 2
) AS e



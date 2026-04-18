SELECT MAX(salary) as SecondHighestSalary 
FROM employee
WHERE salary < (
    SELECT salary 
    FROM employee 
    ORDER BY salary DESC 
    LIMIT 1
    )



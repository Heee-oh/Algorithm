SELECT d.name AS Department, e.name AS Employee, e.Salary
FROM Employee e 
JOIN Department d ON e.departmentId = d.id
WHERE (d.id, e.salary) IN (
    SELECT e.departmentId, MAX(e.salary)
    FROM Employee e 
    GROUP BY e.departmentId
)

# 각 그룹별 상위 3위인 급여를 구함
SELECT 
    t.depart_name AS Department,
    t.emp_name AS Employee,
    t.salary AS Salary
FROM (
    SELECT 
        d.name as depart_name,
        e.name as emp_name,
        e.salary, 
        DENSE_RANK() OVER (PARTITION BY e.departmentId ORDER BY e.salary DESC) AS rnk # 각 부서별 상위 3위 구함
    FROM Employee e
    JOIN Department d ON e.departmentId = d.id
) AS t
WHERE t.rnk <= 3;



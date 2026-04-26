CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (

    SELECT e.salary AS 'getNthHighestSalary(2)'
    FROM(
        SELECT salary, DENSE_RANK() OVER(ORDER BY salary DESC) AS row_num
        FROM Employee
    ) AS e
    WHERE e.row_num = N
    LIMIT 1

  );
END
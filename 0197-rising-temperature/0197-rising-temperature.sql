SELECT t.id
FROM (
    SELECT id, temperature, recordDate, LAG(temperature) over(order by recordDate asc) as prevTemp, LAG(recordDate) over(order by recordDate asc) as prevDate
    FROM Weather
) as t
WHERE DATEDIFF(recordDate, prevDate) = 1 AND t.temperature > t.prevTemp

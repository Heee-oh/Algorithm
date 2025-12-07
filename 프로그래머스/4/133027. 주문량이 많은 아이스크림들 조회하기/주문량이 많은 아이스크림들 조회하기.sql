-- FH - SHIPMENT_ID(FK) = J - SHIPMENT_ID
-- J - FLAVOR(FK) = FH - FLAVOR
-- 7월 아이스크림 총 주문량, 상반기 아이스크림 총 주문량을 더한 값이 큰 순서대로 상위 3개 
SELECT TT.FLAVOR
FROM (
    SELECT T.FLAVOR, SUM(T.TOTAL_ORDER) AS TOTAL 
    FROM (
        SELECT * FROM FIRST_HALF
        UNION
        SELECT * FROM JULY
        ) AS T
    GROUP BY T.FLAVOR
    ORDER BY TOTAL DESC
) AS TT
LIMIT 3
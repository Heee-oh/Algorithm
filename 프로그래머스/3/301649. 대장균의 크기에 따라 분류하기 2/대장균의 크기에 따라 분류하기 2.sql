-- 코드를 작성해주세요
-- 1 ~ 총데이터 / 4 개 상위 ?

SELECT EC.ID, (CASE EC.COLONY_RANK
                WHEN 1 THEN 'CRITICAL'
                WHEN 2 THEN 'HIGH'
                WHEN 3 THEN 'MEDIUM'
                ELSE 'LOW'
                END
               ) AS COLONY_NAME
FROM (
    SELECT ID, NTILE(4) OVER (ORDER BY SIZE_OF_COLONY DESC) AS COLONY_RANK
    FROM ECOLI_DATA
) AS EC
ORDER BY EC.ID 


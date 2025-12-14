-- 개체 크기 내림차순 정렬시
-- ID, 분류된 이름 (COLONY_NAME)
-- 개체 ID 에 대해 오름차순
-- 총 데이터 수 4의 배수, 같은 사이즈의 대장균 개체가 서로 다른 이름으로 분류되는 경우 X



SELECT T.ID,(CASE 
                WHEN T.SEQ <= (SELECT MAX(ID) 
                            FROM ECOLI_DATA
                           ) / 4 THEN 'CRITICAL'
                WHEN T.SEQ <= (SELECT MAX(ID) 
                            FROM ECOLI_DATA
                           ) / 2 THEN 'HIGH'
                WHEN T.SEQ <= (SELECT MAX(ID) 
                            FROM ECOLI_DATA
                           ) * 3 / 4 THEN 'MEDIUM'
              ELSE 'LOW'
            END
           ) AS COLONY_NAME
FROM (SELECT ID, ROW_NUMBER() OVER (ORDER BY SIZE_OF_COLONY DESC) AS SEQ
        FROM ECOLI_DATA
        ORDER BY SIZE_OF_COLONY DESC
     ) AS T
     
ORDER BY ID ASC
-- 총 대여 횟수가 5회 이상
-- 해당 기간 동안 월별 자동차 ID 별 총 대여 횟수 
-- 월을 기준으로 오름차순 정렬, == 자동차 ID를 기준으로 내림차순 정렬
-- 특정 월 총 대여 횟수 0은 제외
SELECT MONTH(START_DATE) AS MONTH, CAR_ID, COUNT(*) AS RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
AND CAR_ID IN (
               SELECT CAR_ID
                FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                WHERE START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
                GROUP BY CAR_ID
                HAVING COUNT(*) >= 5
                )
GROUP BY CAR_ID, MONTH
ORDER BY MONTH ASC, CAR_ID DESC
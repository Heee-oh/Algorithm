-- 예약 취소 여부 확인
-- 22년5월, 예약한 환자수 오름차순 , 같다면 진료과 코드 오름차순 
-- 컬럼명 : 진료과 코드, 5월 예약건수 




-- 예약 취소 여부 확인
-- 22년5월, 예약한 환자수 오름차순 , 같다면 진료과 코드 오름차순 
-- 컬럼명 : 진료과 코드, 5월 예약건수 

SELECT MCDP_CD AS 진료과코드, COUNT(MCDP_CD) AS 5월예약건수
FROM APPOINTMENT
WHERE DATE_FORMAT(APNT_YMD, '%Y-%m') = '2022-05'
GROUP BY MCDP_CD
ORDER BY 5월예약건수, 진료과코드
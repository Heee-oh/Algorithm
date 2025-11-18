-- UGB 와 UGU 테이블에서 중고 거래 게시물을 3건 이상 등록한 사용자의 사용자ID, 닉네임, 전체 주소, 전화번호를 조회
-- 전체 주소는 시, 도로명 주소, 상세 주소가 함께 출력
-- 전화번호 -삽입
-- 결과는 회원ID 기준 내림차순 정렬

SELECT  UGU.USER_ID, 
        UGU.NICKNAME, 
        CONCAT(UGU.CITY, ' ', UGU.STREET_ADDRESS1, ' ', STREET_ADDRESS2) 전체주소, 
        CONCAT(SUBSTRING(UGU.TLNO, 1, 3), '-', 
                SUBSTRING(UGU.TLNO, 4, 4), '-',
                SUBSTRING(UGU.TLNO, 8)
              ) AS 전화번호
FROM USED_GOODS_USER UGU 
WHERE UGU.USER_ID IN (
                    SELECT WRITER_ID
                    FROM USED_GOODS_BOARD 
                    GROUP BY WRITER_ID
                    HAVING COUNT(*) >= 3
                   )
ORDER BY UGU.USER_ID DESC;


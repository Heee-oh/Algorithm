-- 만원 단위 가격대별 상품 개수 출력 (price_group products)
-- 가격대 정보는 각 구간의 최소금액으로 표시
-- 가격대를 기준으로 오름차순
SELECT (FLOOR((PRICE / 10000))) * 10000 AS PRICE_GROUP, COUNT(*) AS PRODUCTS
FROM PRODUCT
GROUP BY PRICE_GROUP
ORDER BY PRICE_GROUP;
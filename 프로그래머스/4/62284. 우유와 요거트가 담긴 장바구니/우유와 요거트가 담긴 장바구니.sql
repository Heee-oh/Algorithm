-- 우유 요거트 동시 구입 장바구니, ID 순 정렬
SELECT CART_ID
FROM CART_PRODUCTS
WHERE NAME = 'Milk'
AND CART_ID IN (SELECT CART_ID
           FROM CART_PRODUCTS
           WHERE NAME = 'Yogurt')
ORDER BY CART_ID  
         
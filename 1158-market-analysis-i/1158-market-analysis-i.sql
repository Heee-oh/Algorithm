# 각 유저별 가입일(join_date)과 2019년에 구매자(buyer)로서 주문한 횟수
# 순서 상관 X

SELECT user_id as 'buyer_id', join_date, IFNULL(SUM(YEAR(o.order_date) = 2019), 0) AS 'orders_in_2019'
FROM Users u
LEFT JOIN Orders o ON u.user_id = o.buyer_id 
GROUP BY user_id, join_date


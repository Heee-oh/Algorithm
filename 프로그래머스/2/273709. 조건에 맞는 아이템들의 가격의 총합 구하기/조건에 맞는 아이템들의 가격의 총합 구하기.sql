-- 코드를 작성해주세요
SELECT SUM(price) AS TOTAL_PRICE
FROM item_info
WHERE rarity like "LEGEND"
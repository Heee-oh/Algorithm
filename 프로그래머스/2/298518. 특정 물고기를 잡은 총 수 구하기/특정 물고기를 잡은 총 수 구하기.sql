-- FISH_INFO 에서 잡은 BASS 와 SNAPPER의 수 
SELECT COUNT(*) AS FISH_COUNT
FROM FISH_INFO FI
WHERE EXISTS (
                SELECT 1
                FROM FISH_NAME_INFO FNI
                WHERE FI.FISH_TYPE = FNI.FISH_TYPE
                    AND FNI.FISH_NAME IN ('BASS', 'SNAPPER')
                )
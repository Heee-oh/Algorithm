SELECT FI.ID, FNI.FISH_NAME, FI.LENGTH
FROM FISH_INFO FI
JOIN FISH_NAME_INFO FNI ON FI.FISH_TYPE = FNI.FISH_TYPE
WHERE (FI.FISH_TYPE, FI.LENGTH) IN (
                                SELECT FI.FISH_TYPE, MAX(LENGTH) AS LENGTH
                                FROM FISH_INFO FI
                                GROUP BY FI.FISH_TYPE
                            )
ORDER BY ID

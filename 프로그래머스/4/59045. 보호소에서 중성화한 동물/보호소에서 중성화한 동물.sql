-- 보호소, 중성화 수술 거친 동물 정보 조회
-- 들어올 당시 중성화 X, 나갈 때 중성화된애들
-- ID, 생물 종, 이름, ID 순 조회
SELECT AO.ANIMAL_ID, AO.ANIMAL_TYPE, AO.NAME
FROM ANIMAL_OUTS AO 
WHERE EXISTS (SELECT 1
                           FROM ANIMAL_INS AI
                           WHERE AI.ANIMAL_ID = AO.ANIMAL_ID
                           AND AI.SEX_UPON_INTAKE LIKE 'Intact%'
                          )
AND (AO.SEX_UPON_OUTCOME LIKE 'Spayed%' 
OR AO.SEX_UPON_OUTCOME LIKE 'Neutered%')
ORDER BY AO.ANIMAL_ID ASC;
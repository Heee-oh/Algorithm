-- 동물 보호소에 들어옴, Lucy, Ella, Pickle,Rogan, Sabrina, Mitty
-- id 순으로 조회
SELECT ANIMAL_ID, NAME, SEX_UPON_INTAKE
FROM ANIMAL_INS
WHERE NAME IN ('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty')
ORDER BY ANIMAL_ID;
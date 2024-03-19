class Solution {
    public static int solution(int[] bandage, int health, int[][] attacks) {
        int time = 0;
        int timeIndex = 0;
        int healthTime = 0;
        int maxHp = health;

        //공격 시간 
        int attackTime = attacks[timeIndex][0];

        while(health > 0) {
            time++;

            if (timeIndex == attacks.length ) {
                return health;
            }
            // 공격
            if (time == attackTime) {
                health -= attacks[timeIndex++][1];
                if (timeIndex != attacks.length) {
                    attackTime = attacks[timeIndex][0];
                }
                healthTime = 0;

                // 회복
            } else {
                if (maxHp > health + bandage[1]) {
                    health += bandage[1];
                    healthTime++;
                } else {
                    health = maxHp;
                }

                if (healthTime == bandage[0]) {
                    healthTime = 0;

                    // 추가 회복량 
                    if (maxHp >= health + bandage[2]) {
                        health += bandage[2];
                    } else {
                        health = maxHp;
                    }
                }
            }

        }
        return -1;


    }
}
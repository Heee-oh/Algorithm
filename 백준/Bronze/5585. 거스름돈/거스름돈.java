import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int money = 1000 - Integer.parseInt(br.readLine());

        int count = 0;
        while(money > 0) {
            if (money >= 500) {
                count += money / 500;
                money = money % 500;
            } else if (money >= 100) {
                count += money / 100;
                money = money % 100;
            } else if  (money >= 50) {
                count += money / 50;
                money = money % 50;
            } else if (money >= 10) {
                count += money / 10;
                money = money % 10;
            } else if (money >= 5) {
                count += money / 5;
                money = money % 5;
            } else {
                money--;
                count++;
            }


        }
        System.out.println(count);

    }
}
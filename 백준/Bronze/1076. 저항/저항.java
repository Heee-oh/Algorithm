import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 여기에 코드 작성
        String color1 = br.readLine();
        String color2 = br.readLine();
        String color3 = br.readLine();

        long sum = Long.parseLong(transOhm(color1)) * 10 + Long.parseLong(transOhm(color2));

        System.out.println(sum * (long)Math.pow(10, Integer.parseInt(transOhm(color3))));
    }


    private static String transOhm(String color) {
        switch (color) {
            case "black" : return "0";
            case "brown" : return "1";
            case "red" : return "2";
            case "orange" : return "3";
            case  "yellow" : return "4";
            case  "green" : return "5";
            case  "blue" : return "6";
            case  "violet" : return "7";
            case "grey" : return "8";
            case "white" : return "9";
            default: return "-1";
        }

    }



}
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    static class Info {
        int age;
        String name;

        public Info(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Info[] people = new Info[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            people[i] = new Info(age, name);
        }

        Arrays.sort(people, (o1, o2) -> o1.age - o2.age);


        for (Info person : people) {
            sb.append(person.age).append(" ").append(person.name).append("\n");
        }



        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }




}
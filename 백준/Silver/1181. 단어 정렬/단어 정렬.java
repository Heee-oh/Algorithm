import java.util.*;
import java.io.*;


public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int words = Integer.parseInt(st.nextToken());
        ArrayList<String> list = new ArrayList<>();

        for(int i = 0; i < words; i++) {
            String word = br.readLine();
            if(!list.contains(word))
                list.add(word);

        }

        list.sort(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length()) {
                    char[] c1 = o1.toCharArray();
                    char[] c2 = o2.toCharArray();
                    for(int i = 0; i < o1.length(); i++) {
                        if(c1[i] == c2[i]) {
                            continue;
                        }else if(c1[i] < c2[i]) {
                            return -1;
                        }else{
                            return 1;
                        }
                    }

                }else if(o1.length() > o2.length()) {
                    return 1;
                }else {
                    return -1;
                }
                return 0;
            }
        });
        
        for(String wd : list) {
            bw.write(wd+"\n");
        }
  
         
        bw.flush(); // 내용 출력
        bw.close(); // 종료
    }
    
}
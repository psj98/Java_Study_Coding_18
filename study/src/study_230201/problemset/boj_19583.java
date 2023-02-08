package study_230201.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class boj_19583 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<String> in = new HashSet<>(); // 들어온 사람 이름
        Set<String> out = new HashSet<>(); // 나간 사람 이름

        String line = null;
        int ans = 0;

        String[] time = br.readLine().split(" "); // S, E, Q

        while ((line = br.readLine()) != null) {
            String[] str = line.split(" "); // time, name

            if (str[0].compareTo(time[0]) <= 0) // 입장 확인
                in.add(str[1]);
            else if (str[0].compareTo(time[1]) >= 0 && str[0].compareTo(time[2]) <= 0) // 퇴장 확인
                out.add(str[1]);
        }

        // 입장한 사람이 퇴장한 Set에 있으면 증가
        for (String s : in) {
            if (out.contains(s))
                ans++;
        }

        System.out.println(ans);
    }
}

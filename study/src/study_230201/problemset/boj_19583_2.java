package study_230201.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_19583_2 {
    static StringTokenizer stk;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> mp = new HashMap<>(); // 이름, 시간(분)
        int[] time = new int[3]; // S, E, Q
        int ans = 0;

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            String str = stk.nextToken();
            int h = Integer.parseInt(str.substring(0, 2)) * 60;
            int m = Integer.parseInt(str.substring(3));
            time[i] = h + m; // 분으로 계산해서 저장
        }

        String line;
        while ((line = br.readLine()) != null) {
            stk = new StringTokenizer(line);
            String str = stk.nextToken();
            int chat = Integer.parseInt(str.substring(0, 2)) * 60 + Integer.parseInt(str.substring(3)); // 시간 계산
            String name = stk.nextToken();

            if (chat <= time[0]) // 입장 확인
                mp.put(name, chat);
            else if (chat >= time[1] && chat <= time[2] && mp.containsKey(name)) { // 퇴장 확인
                mp.remove(name); // 확인된 사람 제거
                ans++;
            }
        }

        System.out.println(ans);
    }
}

package study_230201.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class boj_25972 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> mp = new HashMap<>(); // 좌표, 도미노 높이
        int last = -1, ans = 0;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            mp.put(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }

        List<Integer> keySet = new ArrayList<>(mp.keySet());
        Collections.sort(keySet); // 키 값 기준으로 정렬

        // 바로 옆의 도미노만 체크
		for(int key : keySet) {
            if (key > last)
                ans++;
            
            last = mp.get(key) + key;
        }

        System.out.println(ans);
    }
}

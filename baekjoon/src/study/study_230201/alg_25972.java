package study.study_230201;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class alg_25972 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> mp = new HashMap<>();
        int last = -1, ans = 0;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            mp.put(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }

        List<Integer> keySet = new ArrayList<>(mp.keySet());
        Collections.sort(keySet);

        for (int key : keySet) {
            if (key > last)
                ans++;

            last = Math.max(last, mp.get(key) + key);
        }

        System.out.println(ans);
    }
}

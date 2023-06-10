package study_230607.problemset;

import java.io.*;
import java.util.*;

public class boj_1027 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] buildings = new int[n];
        int[] ans = new int[n];

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            buildings[i] = Integer.parseInt(stk.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            ans[i]++;
            ans[i + 1]++;

            double cur = buildings[i + 1] - buildings[i];
            for (int j = i + 2; j < n; j++) {
                double next = (double) (buildings[j] - buildings[i]) / (j - i);
                if (next <= cur)
                    continue;

                ans[i]++;
                ans[j]++;
                cur = next;
            }
        }

        Arrays.sort(ans);

        sb.append(ans[n - 1]);
        System.out.println(sb);
    }
}
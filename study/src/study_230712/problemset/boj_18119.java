package study_230712.problemset;

import java.io.*;
import java.util.*;

public class boj_18119 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        int[] words = new int[n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            int bit = 0;
            for (int j = 0; j < str.length(); j++) {
                bit |= (1 << (str.charAt(j) - 'a'));
            }

            words[i] = bit;
        }

        int flag = (1 << 26) - 1;
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(stk.nextToken());
            char alpha = stk.nextToken().charAt(0);

            if (op == 1) { // 알파벳을 잊어버림
                flag ^= (1 << (alpha - 'a')); // 반전
            } else if (op == 2) { // 알파벳을 기억해냄
                flag |= (1 << (alpha - 'a'));
            }

            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if ((words[j] & flag) >= words[j]) {
                    cnt++;
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}
package study_230614.problemset;

import java.io.*;
import java.util.*;

public class boj_5052_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            String[] words = new String[n];

            for (int i = 0; i < n; i++) {
                words[i] = br.readLine(); // 단어 입력
            }

            Arrays.sort(words); // 정렬

            // 정답 출력
            if (!check(n, words))
                sb.append("NO\n");
            else
                sb.append("YES\n");
        }

        System.out.println(sb);
    }

    // 인접한 값이 이전 값으로 시작하는지 체크
    static boolean check(int n, String[] words) {
        for (int i = 0; i < n - 1; i++) {
            if (words[i + 1].startsWith(words[i])) { // 이전 값으로 시작한 경우, 일관성 X
                return false;
            }
        }

        return true; // 일관성 O
    }
}
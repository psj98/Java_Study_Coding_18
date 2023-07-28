package study_230726.problemset;

import java.io.*;

public class boj_5582 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        /**
         * DP
         * 각 자릿수를 비교하면서 DP 진행
         * (i, j)가 같으면 (i-1, j-1)(이전 값) + 1 로 DP 값 갱신
         */

        // 문자열 입력
        String str1 = br.readLine();
        String str2 = br.readLine();

        int ans = 0;
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }
}

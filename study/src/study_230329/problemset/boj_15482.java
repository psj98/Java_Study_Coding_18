package study_230329.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_15482 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 입력
        String str1 = br.readLine();
        String str2 = br.readLine();
        int str1Size = str1.length(); // 각 문자열의 길이
        int str2Size = str2.length();

        int[][] dp = new int[str1Size + 1][str2Size + 1]; // dp 배열

        for (int i = 1; i <= str1Size; i++) {
            for (int j = 1; j <= str2Size; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) // 값이 같은 경우, 왼쪽 위의 값 + 1 로 갱신
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else // 값이 다른 경우, 왼쪽이나 오른쪽 중 max 값으로 갱신
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        // 최대 길이 출력
        sb.append(dp[str1Size][str2Size]);
        System.out.println(sb);
    }
}

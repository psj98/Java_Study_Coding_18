package study_230329.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_1958 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 입력
        String str1 = br.readLine();
        String str2 = br.readLine();
        String str3 = br.readLine();
        int str1Size = str1.length(); // 각 문자열의 길이
        int str2Size = str2.length();
        int str3Size = str3.length();

        int[][][] dp = new int[str1Size + 1][str2Size + 1][str3Size + 1]; // dp 배열

        for (int i = 1; i <= str1Size; i++) {
            for (int j = 1; j <= str2Size; j++) {
                for (int k = 1; k <= str3Size; k++) {
                    // 값이 같은 경우, 왼쪽 위의 값 + 1 로 갱신
                    if (str1.charAt(i - 1) == str2.charAt(j - 1) && str2.charAt(j - 1) == str3.charAt(k - 1))
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    else // 값이 다른 경우, 왼쪽이나 오른쪽 중 max 값으로 갱신
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
                }
            }
        }

        // 최대 길이 출력
        sb.append(dp[str1Size][str2Size][str3Size]);
        System.out.println(sb);
    }
}

package study_230628.problemset;

import java.io.*;

public class boj_2011 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        /**
         * - 어떻게 할 지 모르겠다면 직접 써보면서 점화식을 구하자
         * ex) 테스트 코드 : 25114
         * - 1번째 자리 : 2 -> 1개
         * - 2번째 자리 : 2, 5 / 25 -> 2개
         * - 3번째 자리 : 2, 5, 1 / 25, 1 -> 2개
         * - 4번째 자리 : 2, 5, 1, 1 / 2, 5, 11 / 25, 1, 1 / 25, 11 -> 4개
         * - 5번째 자리 : 2, 5, 1, 1, 4 / 2, 5, 1, 14 / 2, 5, 11, 4 / 25, 1, 1, 4 / 25, 11, 4 / 25, 1, 14-> 6개
         * 
         * 4번째 자리에서 규칙을 보면 현재 자리가 1이다.
         * - 1은 가능한 알파벳이 있기 때문에 3번째 자리에서 가능한 모든 것을 가져와서 더한다. (dp[4] += dp[3])
         * - 현재 자리와 이전 자리를 같이 본다. => 11 => 11은 알파벳이 있기 때문에 2번째 자리에서 가능한 모든 것을 가져와서 더한다. (dp[4] += dp[2])
         *  
         * 점화식 : dp[n] = dp[n-1] + dp[n-2]
         * => 단, n-1일 때는 0이 아니어야 하고, n-2일 때는 십의 자리가 0이 아니어야 한다.
         * => 또한, 현재 자리가 첫 번째 자리일 때는 n-2를 스킵한다.
         */

        final int MOD = 1000000;
        String str = br.readLine();

        int[] dp = new int[str.length() + 1];
        dp[0] = 1;

        for (int i = 1; i <= str.length(); i++) {
            int cur = str.charAt(i - 1) - '0';
            if (cur != 0) {
                dp[i] += dp[i - 1];
            }

            if (i == 1)
                continue;

            int pre = str.charAt(i - 2) - '0';
            if (pre == 0)
                continue;

            int sum = pre * 10 + cur;
            if (sum >= 1 && sum <= 26) {
                dp[i] = (dp[i] + dp[i - 2]) % MOD;
            }
        }

        sb.append(dp[str.length()]);
        System.out.println(sb);
    }
}

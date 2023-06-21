package study_230621.problemset;

import java.io.*;

public class boj_4811 {
    public static long[][] dp = new long[31][31];
    public static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        find(30, 0); // 30일부터 역순으로 찾기

        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) // 0이면 종료
                break;
            else // 정답 저장
                sb.append(dp[n][0]).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 문자열 개수 찾기 - DP
    static long find(int whole, int half) {
        // 한 조각이 없는 경우, 무조건 반 조각만 있음 => return 1 
        if (whole == 0) {
            return 1;
        }

        // dp값이 갱신되어 있는 경우, 해당 DP 값 return
        if (dp[whole][half] != 0) {
            return dp[whole][half];
        }

        dp[whole][half] = find(whole - 1, half + 1); // 한 조각이 있는 경우, 한 조각을 쪼개고 반 조각을 추가

        // 반 조각이 있는 경우, 반 조각을 먹는 경우
        if (half > 0) {
            dp[whole][half] += find(whole, half - 1);
        }

        return dp[whole][half]; // 해당 값 반환
    }
}

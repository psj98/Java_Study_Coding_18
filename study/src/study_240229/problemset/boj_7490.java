package study_240229.problemset;

import java.io.*;

public class boj_7490 {
    static int n;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            n = Integer.parseInt(br.readLine());

            sb = new StringBuilder();
            find(1, 1, 0, 1, "1");

            // 정답 출력
            System.out.println(sb);
        }
    }

    // 재귀 => (현재 들어가야 할 값, 현재 가지고 있는 값, 현재까지의 합, 부호, 식)
    static void find(int idx, int cur, int sum, int op, String str) {
        // n과 같은 경우, return
        if (idx == n) {
            sum += cur * op;

            if (sum == 0) {
                sb.append(str).append("\n");
            }

            return;
        }

        // 재귀
        find(idx + 1, cur * 10 + idx + 1, sum, op,
                new StringBuilder().append(str).append(" ").append(idx + 1).toString()); // 이어 붙이기
        find(idx + 1, idx + 1, sum + cur * op, 1,
                new StringBuilder().append(str).append("+").append(idx + 1).toString()); // +
        find(idx + 1, idx + 1, sum + cur * op, -1,
                new StringBuilder().append(str).append("-").append(idx + 1).toString()); // -
    }
}

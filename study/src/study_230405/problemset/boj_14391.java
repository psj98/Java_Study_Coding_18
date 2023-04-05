package study_230405.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14391 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int ans = 0;

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        // 값 저장
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++)
                map[i][j] = str.charAt(j) - '0';
        }

        // 0 ~ 1111 1111 1111 1111 (가로 0, 세로 1) - 2^15
        // 2*2라고 하면, 1010일 때, 모든 값에 대하여 탐색하기 때문에 1010의 거꾸로인 0101로 탐색해도 동일
        for (int bit = 0; bit < 1 << (n * m); bit++) {
            int totalSum = 0;

            // 가로 체크
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = 0; j < m; j++) {
                    int flag = 1 << (i * m + j);

                    if ((bit & flag) == 0) { // 가로인 경우 (0)
                        sum = sum * 10 + map[i][j];
                    } else { // 세로인 경우, totalSum에 d여태까지 구한 sum 더하고, sum 초기화
                        totalSum += sum;
                        sum = 0;
                    }
                }

                totalSum += sum; // 마지막 값 더하기
            }

            // 세로 체크
            for (int i = 0; i < m; i++) {
                int sum = 0;
                for (int j = 0; j < n; j++) {
                    int flag = 1 << (i + j * m);

                    if ((bit & flag) != 0) { // 세로인 경우 (1)
                        sum = sum * 10 + map[j][i];
                    } else { // 가로인 경우, totalSum에 d여태까지 구한 sum 더하고, sum 초기화
                        totalSum += sum;
                        sum = 0;
                    }
                }

                totalSum += sum; // 마지막 값 더하기
            }

            ans = Math.max(ans, totalSum);
        }

        sb.append(ans);
        System.out.println(sb);
    }
}

package study_230802.problemset;

import java.io.*;
import java.util.*;

public class boj_12785_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        long[][] map = new long[401][401];
        for (int i = 0; i < 401; i++) {
            map[i][0] = map[i][i] = 1;
        }

        // 파스칼의 삼각형
        for (int i = 2; i < 401; i++) {
            for (int j = 1; j < i; j++) {
                map[i][j] = (map[i - 1][j - 1] + map[i - 1][j]) % 1000007;
            }
        }

        stk = new StringTokenizer(br.readLine());
        int tx = Integer.parseInt(stk.nextToken());
        int ty = Integer.parseInt(stk.nextToken());

        long ans = map[tx + ty - 2][tx - 1] * map[n + m - tx - ty][n - tx] % 1000007;

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }
}

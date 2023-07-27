package study_230726.problemset;

import java.io.*;
import java.util.*;

public class boj_1011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        // k광년 => k-1, k, k+1 이동 가능
        // 처음은 1만 가능
        // y지점 도착 직전은 1로

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int diff = Integer.parseInt(stk.nextToken()) - x;

            int root = (int) Math.sqrt(diff);
            int ans = 0;
            
            // diff보다 작은 제곱수 찾기
            if (root * root == diff) {
                ans = root;
            } else {
                ans = root + 1;
            }

            // 정답 구하기
            if (ans * ans - ans >= diff) {
                sb.append(ans * 2 - 2);
            } else {
                sb.append(ans * 2 - 1);
            }

            sb.append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}

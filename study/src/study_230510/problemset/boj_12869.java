package study_230510.problemset;

import java.io.*;
import java.util.*;

public class boj_12869 {
    static int n, ans = Integer.MAX_VALUE;
    static int[][][] dp = new int[61][61][61];
    static int[][] attack = {
            { -1, -3, -9 }, { -1, -9, -3 },
            { -3, -1, -9 }, { -3, -9, -1 },
            { -9, -1, -3 }, { -9, -3, -1 }
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        /*
         * DP
         * 
         * 1. (1, 3, 9)로 할 수 있는 모든 경우의 수 저장 -> attack 배열
         * 2. 처음 scv 체력을 초기 정보로 잡고 DFS
         * 3. cnt를 증가시키면서 DFS 실행
         *  3-1. cnt가 ans보다 커진 경우, 뒤를 실행할 필요 없음
         *  3-2. 현재 scv 값의 dp 값이 0이 아니고 (이미 갱신됨), cnt가 더 큰 경우, 뒤를 실행할 필요 없음
         *  3-3. 현재 scv 값에 cnt 저장
         *  3-4. 모두 0이면 ans에 최솟값 갱신
         *  3-5. attack 배열로 scv 갱신하면서 DFS
         */

        n = Integer.parseInt(br.readLine());

        // scv 정보
        int[] scv = new int[3];
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            scv[i] = Integer.parseInt(stk.nextToken());
        }

        dfs(scv, 0); // DFS

        sb.append(ans);
        System.out.println(sb);
    }

    // DFS -> dp 배열 갱신
    static void dfs(int[] scv, int cnt) {
        if (ans <= cnt) { // cnt가 ans보다 커진 경우, 뒤를 실행할 필요 없음
            return;
        }

        // 현재 scv 값의 dp 값이 0이 아니고 (이미 갱신됨), cnt가 더 큰 경우, 뒤를 실행할 필요 없음
        if (dp[scv[0]][scv[1]][scv[2]] != 0 && dp[scv[0]][scv[1]][scv[2]] <= cnt) {
            return;
        }

        dp[scv[0]][scv[1]][scv[2]] = cnt; // 현재 scv 값에 cnt 저장

        // 모두 0인 경우, ans에 최솟값 갱신
        if (scv[0] == 0 && scv[1] == 0 && scv[2] == 0) {
            ans = Math.min(ans, cnt);
            return;
        }

        // attack 배열로 scv 갱신 -> DFS
        for (int i = 0; i < 6; i++) {
            int[] arr = new int[3];

            for (int j = 0; j < 3; j++) {
                arr[j] = Math.max(scv[j] + attack[i][j], 0);
            }

            dfs(arr, cnt + 1);
        }
    }
}

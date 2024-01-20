package study_240118.problemset;

import java.io.*;
import java.util.*;

public class boj_2666 {
    static int m, ans = Integer.MAX_VALUE;
    static int[] doors;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        br.readLine();

        stk = new StringTokenizer(br.readLine());

        // 열린 문 정보
        int open1 = Integer.parseInt(stk.nextToken());
        int open2 = Integer.parseInt(stk.nextToken());

        m = Integer.parseInt(br.readLine());
        doors = new int[m];

        // 열어야 할 문 정보
        for (int i = 0; i < m; i++) {
            doors[i] = Integer.parseInt(br.readLine());
        }

        dfs(open1, open2, 0, 0); // DFS

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }

    // DFS
    static void dfs(int open1, int open2, int cnt, int sum) {
        // 기저 조건 => 모든 문을 다 연 경우
        if (cnt == m) {
            ans = Math.min(ans, sum); // 최솟값 갱신
            return;
        }

        // 거리 측정
        int num1 = Math.abs(open1 - doors[cnt]);
        int num2 = Math.abs(open2 - doors[cnt]);

        // 각 거리 별로 수행하여 최솟값 찾기
        dfs(doors[cnt], open2, cnt + 1, sum + num1);
        dfs(open1, doors[cnt], cnt + 1, sum + num2);
    }
}

package study_240201.problemset;

import java.io.*;
import java.util.*;

public class boj_9466 {
    static int cnt;
    static int[] arr;
    static boolean[] visited;
    static boolean[] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine()); // 학생 수

            // 초기화
            cnt = 0;
            arr = new int[n + 1];
            visited = new boolean[n + 1];
            check = new boolean[n + 1];

            // 학생 번호
            stk = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(stk.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                // 이미 끝난 경우, 제외
                if (check[i]) {
                    continue;
                }

                dfs(i); // DFS
            }

            sb.append(n - cnt).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // DFS
    static void dfs(int n) {
        // 이미 끝난 경우
        if (check[n]) {
            return;
        }

        // 방문했던 경우, 개수 증가
        if (visited[n]) {
            check[n] = true;
            cnt++;
        }

        visited[n] = true;
        dfs(arr[n]);
        visited[n] = false;

        check[n] = true;
    }
}

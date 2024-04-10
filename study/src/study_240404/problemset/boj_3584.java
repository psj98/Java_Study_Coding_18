package study_240404.problemset;

import java.io.*;
import java.util.*;

public class boj_3584 {
    static int[] parents;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine()); // testcase 개수

        for (int t = 0; t < tc; t++) {
            int n = Integer.parseInt(br.readLine()); // 노드 개수

            // 초기화
            parents = new int[n + 1];
            visited = new boolean[n + 1];

            for (int i = 0; i < n - 1; i++) {
                stk = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(stk.nextToken());
                int y = Integer.parseInt(stk.nextToken());

                parents[y] = x; // 자식에 대한 부모 저장
            }

            // 공통 조상을 찾아야 할 자식 노드
            stk = new StringTokenizer(br.readLine());

            int node1 = Integer.parseInt(stk.nextToken());
            int node2 = Integer.parseInt(stk.nextToken());

            // 정답 저장
            sb.append(find(node1, node2)).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 가장 가까운 공통 조상 찾기
    static int find(int x, int y) {
        // x를 루트로 계속 이동
        while (x > 0) {
            visited[x] = true;
            x = parents[x];
        }

        // y를 루트로 계속 이동
        while (y > 0) {
            // 공통 조상을 찾은 경우, 해당 값 반환
            if (visited[y]) {
                return y;
            }

            y = parents[y];
        }

        return -1;
    }
}

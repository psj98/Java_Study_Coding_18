package study_240118.problemset;

import java.io.*;
import java.util.*;

public class boj_2668 {
    static ArrayList<Integer> list = new ArrayList<>();
    static int[] num;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        // 초기화
        num = new int[n + 1]; // 값 정보
        visited = new boolean[n + 1]; // 방문 체크

        // 값 정보
        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        // DFS => 각 값에서 순회하면 됨
        for (int i = 1; i <= n; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        // 정답 출력
        sb.append(list.size()).append("\n");

        for (int cur : list) {
            sb.append(cur).append("\n");
        }

        System.out.println(sb);
    }

    // DFS => 순회 체크
    static void dfs(int idx, int target) {
        // 방문하지 않은 경우, 다음 위치 방문
        if (!visited[num[idx]]) {
            visited[num[idx]] = true;
            dfs(num[idx], target);
            visited[num[idx]] = false;
        }

        // 순회한 경우, list에 추가
        if (num[idx] == target) {
            list.add(target);
        }
    }
}

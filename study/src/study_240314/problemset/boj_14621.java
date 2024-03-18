package study_240314.problemset;

import java.io.*;
import java.util.*;

public class boj_14621 {
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken()); // 정점 개수
        int m = Integer.parseInt(stk.nextToken()); // 간선 개수

        make(n); // 부모 정점 초기화

        // 성별 정보
        char[] gender = new char[n + 1];

        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            gender[i] = stk.nextToken().charAt(0);
        }

        // 간선 정보
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());

            list.add(new int[] { from, to, cost });
        }

        // 간선 비용 기준 오름차순 정렬
        Collections.sort(list, (o1, o2) -> o1[2] - o2[2]);

        int cnt = 0, sum = 0; // 연결 간선 개수, 가중치 합
        for (int[] cur : list) {
            int s = find(cur[0]); // 시작 위치
            int e = find(cur[1]); // 종료 위치

            // 성별이 같을 경우, 스킵
            if (gender[cur[0]] == gender[cur[1]]) {
                continue;
            }

            // 부모가 같을 경우, 스킵
            if (s == e) {
                continue;
            }

            union(cur[0], cur[1]); // Union

            sum += cur[2];
            cnt++;
        }

        // 정답 출력
        sb.append((cnt == n - 1) ? sum : -1);
        System.out.println(sb);
    }

    // parents 초기화
    static void make(int n) {
        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }

    // Union
    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parents[y] = x;
        }
    }

    // Find
    static int find(int x) {
        if (x == parents[x]) {
            return x;
        }

        return parents[x] = find(parents[x]);
    }
}

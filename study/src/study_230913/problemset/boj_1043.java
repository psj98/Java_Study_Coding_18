package study_230913.problemset;

import java.io.*;
import java.util.*;

public class boj_1043 {
    static int[] parents;
    static boolean[] knowPeople = new boolean[51];
    static ArrayList<Integer>[] partyPeople;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken()); // 사람 수
        int m = Integer.parseInt(stk.nextToken()); // 파티 개수

        // 초기화
        parents = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }

        // 파티에 오는 사람 번호 리스트
        partyPeople = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            partyPeople[i] = new ArrayList<>();
        }

        // 진실을 알고 있는 사람
        stk = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(stk.nextToken());
        for (int i = 0; i < k; i++) {
            knowPeople[Integer.parseInt(stk.nextToken())] = true;
        }

        // 파티에 오는 사람
        int from, to;
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int partyNum = Integer.parseInt(stk.nextToken()); // 파티 오는 사람 수
            from = Integer.parseInt(stk.nextToken());

            // 1명인 경우, 추가만
            if (partyNum <= 1) {
                partyPeople[i].add(from);
                continue;
            }

            // 1명이 아닌 경우, union
            for (int j = 1; j < partyNum; j++) {
                to = Integer.parseInt(stk.nextToken());
                partyPeople[i].add(to);

                union(from, to);

                from = to;
            }
        }

        // 진실 아는 사람 체크
        for (int i = 1; i <= 50; i++) {
            if (!knowPeople[i]) {
                continue;
            }

            knowPeople[find(i)] = true;
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            int cur = find(partyPeople[i].get(0));
            if (knowPeople[cur]) {
                continue;
            }

            ans++;
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }

    static int find(int x) {
        if (parents[x] == x)
            return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parents[x] = y;
        }
    }
}

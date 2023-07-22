package study_230719.problemset;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class boj_2623 {
    static int n, m;
    static ArrayList<Integer>[] list;
    static ArrayList<Integer> ans = new ArrayList<>();
    static int[] indegree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        // 초기화
        indegree = new int[n + 1];
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // 서로 연결된 간선 정보를 저장
        // indegree는 앞에서 몇 번째인지 저장
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());

            int cnt = Integer.parseInt(stk.nextToken());
            int cur = Integer.parseInt(stk.nextToken());
            for (int j = 1; j < cnt; j++) {
                int next = Integer.parseInt(stk.nextToken());
                list[cur].add(next);
                indegree[next]++; // 자신이 몇 번째 있는지 앞에 있는 개수만큼 더함
                cur = next;
            }
        }

        topology(); // 위상정렬

        // 정답 출력
        if (ans.size() != n) {
            sb.append(0);
        } else {
            for (int cur : ans) {
                sb.append(cur).append("\n");
            }
        }

        System.out.println(sb);
    }

    // 위상 정렬
    static void topology() {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] != 0)
                continue;
            queue.add(i); // 맨 앞에 있는 (0인 것) 것만 저장
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            ans.add(cur); // 정답에 저장

            for (int next : list[cur]) {
                indegree[next]--; // 맨 앞이 사라졌으므로 순서가 한 칸 앞으로 땡겨짐

                if (indegree[next] == 0) // 맨 앞이면 queue에 추가
                    queue.add(next);
            }
        }
    }
}

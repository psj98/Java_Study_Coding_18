package study_230607.problemset;

import java.io.*;
import java.util.*;

public class boj_1976 {
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        /**
         * 1. 모든 여행 경로가 연결되어 있으면 여행 가능
         * 2. Union-Find로 parents를 저장
         * 3. 시작 좌표와 find(여행 경로 지점)이 같으면 해당 지점으로 이동할 수 있음
         */

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        // 초기화
        parents = new int[n + 1];
        for (int i = 0; i <= n; i++)
            parents[i] = i;

        // 여행 경로가 있을 때만 union 실행
        for (int i = 1; i <= n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int num = Integer.parseInt(stk.nextToken());

                if (num == 1)
                    union(i, j);
            }
        }

        stk = new StringTokenizer(br.readLine());
        int start = find(Integer.parseInt(stk.nextToken())); // 여행 시작 좌표

        // 여행 경로 확인
        for (int i = 1; i < m; i++) {
            int cur = Integer.parseInt(stk.nextToken());

            // 여행 경로가 연결되어 있지 않은 경우, NO
            if (start != find(cur)) {
                sb.append("NO");
                System.out.println(sb);
                return;
            }
        }

        // 모든 여행 경로가 연결되어 있는 경우, YES
        sb.append("YES");
        System.out.println(sb);
    }

    // UNION
    static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot)
            return;
        parents[yRoot] = xRoot;
    }

    // FIND
    static int find(int num) {
        if (num == parents[num])
            return num;
        return parents[num] = find(parents[num]);
    }
}

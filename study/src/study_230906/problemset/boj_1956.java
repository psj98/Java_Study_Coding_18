package study_230906.problemset;

import java.io.*;
import java.util.*;

public class boj_1956 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int V = Integer.parseInt(stk.nextToken());
        int E = Integer.parseInt(stk.nextToken());

        int[][] map = new int[V + 1][V + 1];
        for (int i = 0; i <= V; i++) {
            Arrays.fill(map[i], 987654321);
        }

        for (int i = 0; i < E; i++) {
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());

            map[from][to] = cost;
        }

        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= V; i++) {
            ans = Math.min(ans, map[i][i]);
        }

        if (ans == 987654321) {
            sb.append(-1);
        } else {
            sb.append(ans);
        }

        System.out.println(sb);
    }
}

package study_230503.problemset;

import java.io.*;
import java.util.*;

public class boj_19535 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        long[] node = new long[n + 1]; // 노드에 연결된 노드 개수 정보
        long dTree = 0, gTree = 0; // ㄷ-트리, ㅈ-트리
        ArrayList<int[]> pos = new ArrayList<>(); // 간선 정보

        for (int i = 0; i < n - 1; i++) {
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());

            node[from]++;
            node[to]++;

            pos.add(new int[] { from, to });
        }

        /*
         * D-Tree
         *
         * 연결된 두 노드에 대해 노드 개수 세기
         * -> 연결된 노드 제외
         * -> (1번 노드 연결된 개수 - 1) * (2번 노드 연결된 개수 - 1)
         */
        for (int[] cur : pos) {
            if (node[cur[0]] == 1 || node[cur[1]] == 1) // 서로 연결된 노드만 있는 경우, 스킵
                continue;

            dTree += (node[cur[0]] - 1) * (node[cur[1]] - 1);
        }

        /*
         * G-Tree
         *
         * 한 노드에 대해 연결된 노드가 3개 이상인 경우
         * -> 조합 : nC3 (299999C3 이 최대 -> long)
         */
        for (int i = 1; i <= n; i++) {
            if (node[i] < 3) // 연결된 노드가 3개 미만인 경우, 스킵
                continue;

            gTree += node[i] * (node[i] - 1) * (node[i] - 2) / 6; // nC3
        }

        // 정답 출력
        if (dTree > gTree * 3)
            sb.append("D");
        else if (dTree < gTree * 3)
            sb.append("G");
        else
            sb.append("DUDUDUNGA");
        System.out.println(sb);
    }
}

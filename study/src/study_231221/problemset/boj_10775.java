package study_231221.problemset;

import java.io.*;

public class boj_10775 {
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int G = Integer.parseInt(br.readLine()); // 게이트 수
        int P = Integer.parseInt(br.readLine()); // 비행기 수

        // 초기화
        parents = new int[G + 1];
        for (int i = 1; i <= G; i++) {
            parents[i] = i;
        }

        int cnt = 0; // 도킹할 수 있는 비행기 개수
        for (int i = 0; i < P; i++) {
            int num = Integer.parseInt(br.readLine()); // 비행기가 도킹할 수 있는 게이트 번호

            int curParents = find(num); // 부모 게이트 번호

            // 0이면 폐쇄
            if (curParents == 0) {
                break;
            }

            union(curParents, curParents - 1); // 현재 게이트, 부모 게이트
            cnt++;
        }

        // 정답 출력
        sb.append(cnt);
        System.out.println(sb);
    }

    // Union-Find
    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        parents[x] = y;
    }

    static int find(int x) {
        if (x == parents[x])
            return x;
        return parents[x] = find(parents[x]);
    }
}

package study_230510.problemset;

import java.io.*;
import java.util.*;

public class boj_18869_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        /*
         * 좌표 압축
         * 
         * 1. lowerbound로 좌표 압축
         * 2. 모든 쌍 비교
         */

        int m = Integer.parseInt(stk.nextToken()); // 우주 수
        int n = Integer.parseInt(stk.nextToken()); // 행성 수

        int[][] multiverse = new int[m][n];

        for (int i = 0; i < m; i++) {
            int[] space = new int[n]; // 행성 정보
            int[] sortArr = new int[n]; // 행성 정보를 입력받고 오름차순으로 정렬할 배열

            // 행성 정보 저장
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                sortArr[j] = space[j] = Integer.parseInt(stk.nextToken());

            Arrays.sort(sortArr); // 오름차순 정렬

            HashMap<Integer, Integer> rank = new HashMap<>(); // lowerbound
            int cnt = 0;

            // lowerbound -> 순위 매기기 (좌표 압축)
            for (int cur : sortArr) {
                if (rank.containsKey(cur))
                    continue;

                rank.put(cur, cnt++);
            }

            // 좌표 압축 정보 저장
            for (int j = 0; j < n; j++)
                multiverse[i][j] = rank.get(space[j]);
        }

        // 모든 쌍 비교 -> 좌표 압축했기 때문에 각 우주의 행성의 값이 같은지만 비교
        int ans = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = i + 1; j < m; j++) {
                boolean check = true;
                for (int k = 0; k < n; k++) {
                    if (multiverse[i][k] != multiverse[j][k]) { // 다르면 균등 X
                        check = false;
                        break;
                    }
                }

                // 균등한 경우, ++
                if (check)
                    ans++;
            }
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }
}

package study_230510.problemset;

import java.io.*;
import java.util.*;

public class boj_18869_1 {
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
            Set<Integer> set = new HashSet<>(); // 중복 개수 줄이기

            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(stk.nextToken());
                multiverse[i][j] = num;
                set.add(num);
            }

            ArrayList<Integer> list = new ArrayList<>(set);

            Collections.sort(list); // lowerbound를 위한 오름차순 정렬

            // 이분탐색으로 좌표 압축
            for (int j = 0; j < n; j++) {
                multiverse[i][j] = Collections.binarySearch(list, multiverse[i][j]);
            }
        }

        // 모든 쌍 비교
        int ans = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = i + 1; j < m; j++) {
                // 배열이 같은지 확인 -> 첨 봄;;
                if (Arrays.equals(multiverse[i], multiverse[j]))
                    ans++;
            }
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }
}
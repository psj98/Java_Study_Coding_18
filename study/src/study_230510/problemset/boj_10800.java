package study_230510.problemset;

import java.io.*;
import java.util.*;

public class boj_10800 {
    static class Ball implements Comparable<Ball> {
        int idx, color, size;

        Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }

        @Override
        public int compareTo(Ball o) { // 크기를 오름차순으로 정렬
            return this.size - o.size;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        /*
         * 누적합 (Prefix Sum)
         * 
         * 1. 공 정보를 입력 받고, 크기를 기준으로 오름차순 정렬
         * 2. 크기가 현재 공보다 작을 때까지 합을 구하기
         * > 2-1. 더해진 공의 색을 기준으로 color 배열에 크기 더하기 -> 색이 겹치면 X
         * 3. ans 배열에 "현재까지의 합 - 현재 공과 같은 색을 가진 공의 크기의 합" 을 저장
         * 4. 정답 출력
         */

        int n = Integer.parseInt(br.readLine()); // 공의 개수

        // 공 정보 저장
        Ball[] balls = new Ball[n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(stk.nextToken());
            int size = Integer.parseInt(stk.nextToken());
            balls[i] = new Ball(i, color, size);
        }

        Arrays.sort(balls); // 크기를 오름차순으로 정렬

        int idx = 0, sum = 0;
        int[] color = new int[n + 1]; // 색 번호에 따른 배열
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            Ball cur = balls[i]; // 현재 공 정보

            while (balls[idx].size < cur.size) { // 현재 공의 크기보다 작을 때까지 더함
                sum += balls[idx].size; // 크기 더함 -> 누적합
                color[balls[idx].color] += balls[idx++].size; // 더한 공의 색을 기준으로 크기를 더함 -> 색 겹치면 X
            }

            ans[cur.idx] = sum - color[cur.color]; // 현재까지의 합 - 현재 공과 같은 색을 모두 더한 값
        }

        // 정답 출력
        for (int i = 0; i < n; i++)
            sb.append(ans[i]).append("\n");
        System.out.println(sb);
    }
}

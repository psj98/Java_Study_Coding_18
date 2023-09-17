package study_230823.problemset;

import java.io.*;
import java.util.*;

public class boj_8982 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        /**
         * 왼쪽 / 오른쪽 나눠 높이 구하기
         * - 높이가 높으면 바꿈
         * - 높이가 낮은게 나오면 낮은걸로 바꾸고 갱신
         */

        ArrayList<Integer> water = new ArrayList<>(); // 처음 물 높이
        int n = Integer.parseInt(br.readLine());

        br.readLine(); // (0, 0) 제외
        for (int i = 0; i < n / 2 - 1; i++) {
            stk = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(stk.nextToken());
            int x = Integer.parseInt(stk.nextToken());

            stk = new StringTokenizer(br.readLine());
            int y2 = Integer.parseInt(stk.nextToken());
            stk.nextToken();

            // 물 높이 저장
            for (int j = y1; j < y2; j++) {
                water.add(x);
            }
        }

        br.readLine(); // (x, 0) 제외

        int[] leave = new int[water.size()]; // 물이 남을 수 있는 높이
        PriorityQueue<int[]> hole = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0]; // 깊이 오름차순 정렬
            }
        });

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            stk = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(stk.nextToken());
            int x = Integer.parseInt(stk.nextToken());
            stk.nextToken();
            stk.nextToken();

            hole.add(new int[] { x, y }); // (깊이, 위치) 저장
        }

        int len = water.size();
        while (!hole.isEmpty()) {
            int[] cur = hole.poll(); // 깊이, 위치

            // 왼쪽
            int depth = cur[0];
            for (int i = cur[1]; i >= 0; i--) {
                depth = Math.min(depth, water.get(i));
                leave[i] = Math.max(leave[i], depth);
            }

            // 오른쪽
            depth = cur[0];
            for (int i = cur[1]; i < len; i++) {
                depth = Math.min(depth, water.get(i));
                leave[i] = Math.max(leave[i], depth);
            }
        }

        // 정답 출력
        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans += water.get(i) - leave[i];
        }

        sb.append(ans);
        System.out.println(sb);
    }
}

package study_230412.problemset;

import java.io.*;
import java.util.*;

/* 왜 안되는지 모르겠음 -> max, min 찾을 때 노드 번호 체크안하고 갱신해줘서? + 마지막 left==right일 때, 체크 안해줘서? */
public class boj_2357_XX {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        int height = (int) Math.ceil(Math.log(n) / Math.log(2));
        int[] minTree = new int[1 << (height + 1)];
        int[] maxTree = new int[1 << (height + 1)];

        Arrays.fill(minTree, Integer.MAX_VALUE);

        int size = 1 << height;
        for (int i = size; i < size + n; i++)
            maxTree[i] = minTree[i] = Integer.parseInt(br.readLine());

        // 최솟값으로 부모 갱신
        for (int i = size - 1; i > 0; i--) {
            minTree[i] = Math.min(minTree[i * 2], minTree[i * 2 + 1]);
            maxTree[i] = Math.max(maxTree[i * 2], maxTree[i * 2 + 1]);
        }

        // 범위를 idx로 잡고 탐색
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken()) + size - 1;
            int b = Integer.parseInt(stk.nextToken()) + size - 1;

            int left = a;
            int right = b;

            // 최솟값 찾기
            int leftMin = minTree[a];
            int rightMin = minTree[b];
            while (left < right) {
                leftMin = Math.min(leftMin, minTree[(left + 1) / 2]);
                rightMin = Math.min(rightMin, minTree[(right - 1) / 2]);

                left = (left + 1) / 2;
                right = (right - 1) / 2;
            }

            left = a;
            right = b;

            // 최댓값 찾기
            int leftMax = maxTree[a];
            int rightMax = maxTree[b];
            while (left < right) {
                leftMax = Math.max(leftMax, maxTree[(left + 1) / 2]);
                rightMax = Math.max(rightMax, maxTree[(right - 1) / 2]);

                left = (left + 1) / 2;
                right = (right - 1) / 2;
            }

            sb.append(Math.min(leftMin, rightMin)).append(" ").append(Math.max(leftMax, rightMax)).append("\n");
        }

        System.out.println(sb);
    }
}

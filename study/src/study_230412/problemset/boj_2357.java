package study_230412.problemset;

import java.io.*;
import java.util.*;

public class boj_2357 {
    /*
     * 1. maxTree, minTree 구하기
     * 2. 부모로 올라가면서 최솟값, 최댓값 갱신
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        int height = (int) Math.ceil(Math.log(n) / Math.log(2)); // 트리 높이
        int[] minTree = new int[1 << (height + 1)]; // 최솟값 찾는 트리
        int[] maxTree = new int[1 << (height + 1)]; // 최댓값 찾는 트리

        Arrays.fill(minTree, Integer.MAX_VALUE); // 최댓값으로 저장 & 값이 1부터 들어오기 때문에 maxTree는 갱신 필요 없음

        // 트리에 값 저장
        int size = 1 << height;
        for (int i = 0; i < n; i++)
            maxTree[size + i] = minTree[size + i] = Integer.parseInt(br.readLine());

        for (int i = size - 1; i > 0; i--) {
            minTree[i] = Math.min(minTree[i * 2], minTree[i * 2 + 1]); // 최솟값으로 부모 갱신
            maxTree[i] = Math.max(maxTree[i * 2], maxTree[i * 2 + 1]); // 최댓값으로 부모 갱신
        }

        // 범위를 idx로 잡고 탐색
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken()) + size - 1;
            int b = Integer.parseInt(stk.nextToken()) + size - 1;

            int left = a;
            int right = b;

            /* 최솟값 찾기 */
            int result = Integer.MAX_VALUE;
            while (left <= right) {
                if (left % 2 == 1) // 노드 번호가 홀수인 경우, 오른쪽 위에 위치한 부모로 이동
                    result = Math.min(result, minTree[left]);
                if (right % 2 == 0) // 노드 번호가 짝수인 경우, 왼쪽 위에 위치한 부모로 이동
                    result = Math.min(result, minTree[right]);

                // 부모로 이동
                left = (left + 1) / 2;
                right = (right - 1) / 2;
            }

            sb.append(result).append(" ");

            left = a;
            right = b;

            /* 최댓값 찾기 */
            result = 0;
            while (left <= right) {
                if (left % 2 == 1)
                    result = Math.max(result, maxTree[left]);
                if (right % 2 == 0)
                    result = Math.max(result, maxTree[right]);

                // 부모로 이동
                left = (left + 1) / 2;
                right = (right - 1) / 2;
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }
}

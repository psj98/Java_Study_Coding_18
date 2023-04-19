package study_230412.problemset;

import java.io.*;
import java.util.*;

public class boj_14428 {
    static class Node {
        int val, idx; // 값, 위치

        Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int height = (int) Math.ceil(Math.log(n) / Math.log(2)); // 트리 높이
        int size = 1 << height; // 수열 시작 위치

        Node[] tree = new Node[1 << (height + 1)]; // 트리 초기화
        Arrays.fill(tree, new Node(Integer.MAX_VALUE, Integer.MAX_VALUE));

        // 값, 위치(idx) 저장
        stk = new StringTokenizer(br.readLine());
        for (int i = size; i < size + n; i++)
            tree[i] = new Node(Integer.parseInt(stk.nextToken()), i - size + 1);

        // 최솟값 갱신
        for (int i = size - 1; i > 0; i--) {
            if (tree[i * 2].val <= tree[i * 2 + 1].val)
                tree[i] = tree[i * 2];
            else
                tree[i] = tree[i * 2 + 1];
        }

        // 쿼리 시작
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(stk.nextToken());
            int a = Integer.parseInt(stk.nextToken()) + size - 1;
            int b = Integer.parseInt(stk.nextToken());

            if (op == 1) { // 값 변경
                tree[a].val = b;
                a /= 2;

                // 부모 노드를 따라가면서 최솟값 갱신
                while (a > 0) {
                    if (tree[a * 2].val <= tree[a * 2 + 1].val)
                        tree[a] = tree[a * 2];
                    else
                        tree[a] = tree[a * 2 + 1];
                    a /= 2;
                }
            } else if (op == 2) { // 최솟값 구하기
                int num = Integer.MAX_VALUE;
                int idx = Integer.MAX_VALUE;
                b += size - 1;

                while (a <= b) {
                    // 값이 작으면, num, idx 갱신
                    // 값이 같으면, idx가 작은 것으로 갱신
                    if (a % 2 == 1) {
                        if (num > tree[a].val) {
                            num = tree[a].val;
                            idx = tree[a].idx;
                        } else if (num == tree[a].val) {
                            idx = Math.min(idx, tree[a].idx);
                        }
                    }

                    if (b % 2 == 0) {
                        if (num > tree[b].val) {
                            num = tree[b].val;
                            idx = tree[b].idx;
                        } else if (num == tree[b].val) {
                            idx = Math.min(idx, tree[b].idx);
                        }
                    }

                    a = (a + 1) / 2;
                    b = (b - 1) / 2;
                }

                sb.append(idx).append("\n");
            }
        }

        System.out.println(sb);
    }
}

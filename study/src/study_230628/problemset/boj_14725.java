package study_230628.problemset;

import java.io.*;
import java.util.*;

public class boj_14725 {
    static StringBuffer sb = new StringBuffer();

    static class Tree {
        HashMap<String, Tree> node = new HashMap<>();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        Tree tree = new Tree(); // 트리 생성

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());

            Tree cur = tree; // 트리 Root로 초기화

            // 노드를 따라가면서 문자열 저장
            int k = Integer.parseInt(stk.nextToken());
            for (int j = 0; j < k; j++) {
                String str = stk.nextToken();

                // 문자열이 없는 경우, 저장 후 Child 생성
                if (!cur.node.containsKey(str)) {
                    cur.node.put(str, new Tree());
                }

                cur = cur.node.get(str); // Child로 이동
            }
        }

        // 정답 출력
        print(tree, 0);
        System.out.println(sb);
    }

    // 정답 출력하기 (현재 Tree, 깊이)
    static void print(Tree tree, int cnt) {
        // 오름차순 정렬
        Object[] keys = tree.node.keySet().toArray();
        Arrays.sort(keys);

        for (Object cur : keys) {
            // 깊이 출력
            for (int i = 0; i < cnt; i++)
                sb.append("--");
            sb.append(cur).append("\n"); // 문자열 출력

            print(tree.node.get(cur), cnt + 1); // Child로 이동
        }
    }
}

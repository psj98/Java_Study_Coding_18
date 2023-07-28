package study_230726.problemset;

import java.io.*;
import java.util.*;

public class boj_14395 {
    static class Number {
        long num;
        String str;

        Number(long num, String str) {
            this.num = num;
            this.str = str;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        // s -> t | 같은 경우 0, 못하면 -1, 되면 사칙연산 순서
        long s = Long.parseLong(stk.nextToken());
        long t = Long.parseLong(stk.nextToken());

        // 같은 경우 => 0 출력
        if (s == t) {
            System.out.println(0);
            return;
        }

        bfs(s, t); // BFS
    }

    // BFS
    static void bfs(long s, long t) {
        HashSet<Long> set = new HashSet<>();
        String[] op = { "*", "+", "-", "/" }; // *, +, -, /
        Queue<Number> queue = new ArrayDeque<>();

        queue.add(new Number(s, "")); // 초기 빈 문자열

        while (!queue.isEmpty()) {
            Number cur = queue.poll();

            // 같아지면 문자열 출력
            if (cur.num == t) {
                System.out.println(cur.str);
                return;
            }

            // 연산 후, set에 있는지 체크
            long next = 0;
            for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    next = cur.num * cur.num;
                } else if (i == 1) {
                    next = cur.num * 2;
                } else if (i == 2) {
                    next = 0;
                } else {
                    if (cur.num == 0)
                        continue;
                    next = 1;
                }

                // set에 있는 경우, 스킵
                if (set.contains(next))
                    continue;

                // set에 추가 & 문자열 추가
                set.add(next);
                queue.add(new Number(next, cur.str + op[i]));
            }
        }

        System.out.println(-1); // 같아지지 못한 경우 => -1 출력
    }
}

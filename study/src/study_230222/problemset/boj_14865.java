package study_230222.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_14865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();
        Stack<long[]> stack = new Stack<>();
        ArrayList<long[]> pos = new ArrayList<>();
        ArrayList<long[]> list = new ArrayList<>();
        long a = 0; // 다른 봉우리에 의해 포함되지 않는 봉우리 개수
        long b = 0; // 다른 봉우리를 포함하지 않는 봉우리 개수
        int minNum = Integer.MAX_VALUE, idx = 0;

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            pos.add(new long[] { x, y });
            if (x < minNum) {
                minNum = x;
                idx = i;
            }
        }

        Collections.rotate(pos, -idx);
        if (pos.get(0)[0] != pos.get(1)[0])
            Collections.rotate(pos, 1);
        pos.add(new long[] { pos.get(0)[0], pos.get(0)[1] });

        long check = 0, matchNum = 1;
        for (int i = 0; i < n; i++) {
            long num = pos.get(i)[1] * pos.get(i + 1)[1];
            if (num < 0) {
                list.add(new long[] { pos.get(i)[0], matchNum });
                check++;
                if (check % 2 == 0)
                    matchNum++;
            }
        }

        list.sort((o1, o2) -> o1[0] < o2[0] ? -1 : ((o1[0] == o2[0]) ? 0 : 1));

        for (int i = 0; i < list.size(); i++) {
            long num = list.get(i)[1];

            if (stack.empty()) {
                stack.push(new long[] { num, 0 });
                a++;
            } else {
                if (stack.peek()[0] == num) {
                    if (stack.peek()[1] == 0) {
                        b++;
                    }
                    stack.pop();
                } else {
                    stack.peek()[1] = 1;
                    stack.push(new long[] { num, 0 });
                }
            }
        }

        sb.append(a).append(" ").append(b);
        System.out.println(sb);
    }
}

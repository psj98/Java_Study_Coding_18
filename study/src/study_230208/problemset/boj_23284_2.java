package study_230208.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class boj_23284_2 {
    static StringBuilder sb = new StringBuilder();
    static Stack<Integer> stack = new Stack<>();
    static List<Integer> list = new ArrayList<>();
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        recursion(1);

        System.out.println(sb);
    }

    // 넣을 값
    public static void recursion(int idx) {
        if (list.size() == n) {
            for (int i : list)
                sb.append(i).append(" ");
            sb.append("\n");

            return;
        }

        // 스택에서 값을 빼는 경우, 정답 리스트에 저장
        if (!stack.empty()) {
            int num = stack.peek();
            list.add(num);
            stack.pop();
            recursion(idx);
            stack.push(num);
            list.remove(list.size() - 1);
        }

        // 스택에 값을 넣는 경우
        if (idx <= n) {
            stack.push(idx++);
            recursion(idx);
            stack.pop();
        }
    }
}

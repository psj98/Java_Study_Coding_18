import java.io.*;
import java.util.*;

public class boj_1863 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        // 높이 배열
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            Integer.parseInt(stk.nextToken());
            height[i] = Integer.parseInt(stk.nextToken());
        }

        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int cur : height) {
            if (cur == 0) { // 높이가 0이면 구간 1개 종료
                ans += stack.size();
                stack.clear();
                continue;
            }

            // 현재 값보다 높은 값 체크 및 정답 증가
            while (!stack.isEmpty() && stack.peek() > cur) {
                stack.pop();
                ans++;
            }

            // 최댓값이 현재값과 같으면 스킵
            if (!stack.isEmpty() && stack.peek() == cur) {
                continue;
            }

            stack.push(cur); // 높이 저장
        }

        ans += stack.size(); // 정답 계산

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }
}

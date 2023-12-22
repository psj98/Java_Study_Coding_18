package study_231221.problemset;

import java.io.*;
import java.util.*;

public class boj_1461 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken()); // 책 개수
        int m = Integer.parseInt(stk.nextToken()); // 한 번에 들 수 있는 책 개수

        ArrayList<Integer> plus = new ArrayList<>(); // 양수
        ArrayList<Integer> minus = new ArrayList<>(); // 음수

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int book = Integer.parseInt(stk.nextToken()); // 책 위치
            if (book > 0) {
                plus.add(book);
            } else {
                minus.add(Math.abs(book));
            }
        }

        // 내림차순 정렬
        Collections.sort(plus, Collections.reverseOrder());
        Collections.sort(minus, Collections.reverseOrder());

        // m의 배수에 위치한 값만 sum에 더함
        int sum = 0, max = 0;
        for (int i = 0; i < plus.size(); i += m) {
            sum += plus.get(i);
            max = Math.max(max, plus.get(i)); // 가장 큰 값을 빼기 위해 최댓값 찾음
        }

        for (int i = 0; i < minus.size(); i += m) {
            sum += minus.get(i);
            max = Math.max(max, minus.get(i));
        }

        sum = sum * 2 - max; // 정답 계산

        // 정답 출력
        sb.append(sum);
        System.out.println(sb);
    }
}

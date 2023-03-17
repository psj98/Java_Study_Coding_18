package study_230315.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class boj_1644 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        boolean[] prime = new boolean[n + 1];
        ArrayList<Integer> num = new ArrayList<>();

        // 에라토스테네스의 체
        for (int i = 2; i <= n; i++)
            for (int j = 2; i * j <= n; j++)
                prime[i * j] = true;

        // 소수 저장
        for (int i = 2; i <= n; i++)
            if (!prime[i])
                num.add(i);

        int start = 0, end = 0, sum = 0, cnt = 0;

        // 투 포인터
        while (start <= end) {
            if (sum > n) // 합이 크면, start를 ++
                sum -= num.get(start++);
            else if (end == num.size()) // n이 소수인 것까지 세기
                break;
            else // 합이 작으면, end를 ++
                sum += num.get(end++);

            if (sum == n)
                cnt++;
        }

        // 정답 출력
        sb.append(cnt);
        System.out.println(sb);
    }
}
package study_230315.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_12738 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        // 입력 및 초기화
        int n = Integer.parseInt(br.readLine());
        int[] cost = new int[n];
        ArrayList<Integer> dp = new ArrayList<>();

        // 값 저장
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            cost[i] = Integer.parseInt(stk.nextToken());

        dp.add(cost[0]); // 최장 증가 수열의 길이는 무조건 1 이상

        // 그 다음 값부터 이분탐색
        for (int i = 1; i < n; i++) {
            // 현재 값이 dp의 마지막 값보다 크면 뒤에 값 저장
            if (dp.get(dp.size() - 1) < cost[i])
                dp.add(cost[i]);
            else { // 이분 탐색 - 중간에 값 갱신
                int left = 0, right = dp.size() - 1;

                while (left < right) {
                    int mid = (left + right) / 2;
                    if (cost[i] > dp.get(mid)) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                dp.set(right, cost[i]); // 값 갱신
            }
        }

        // 출력
        sb.append(dp.size());
        System.out.println(sb);
    }
}

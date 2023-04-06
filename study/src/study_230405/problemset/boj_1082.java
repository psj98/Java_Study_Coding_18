package study_230405.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1082 {
    static class Number implements Comparable<Number> {
        int num, cost;

        Number(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Number o) {
            return this.cost - o.cost; // 비용 오름차순 정렬
        }
    }

    /*
     * 1. 최대 자리 찾기
     * 2. 맨 앞자리가 0인 경우, 그 다음 숫자의 cost 체크
     *    -> cost가 money보다 클 경우 종료 / cost가 money보다 작을 경우, 해당 숫자로 첫 번째 자리 갱신
     * 3. 최대 자리까지 ans 배열에 숫자 저장
     * 4. 맨 앞자리부터 큰 숫자로 교체
     * 5. 정답 출력
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        if (n == 1) { // 0 밖에 없는 경우, 0 출력
            sb.append(0);
            System.out.println(sb);
            return;
        }

        Number[] sortArr = new Number[n]; // 비용 오름차순 배열
        int[] number = new int[n]; // 각 숫자 별 cost 배열
        int[] ans = new int[51]; // 정답 배열

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(stk.nextToken());
            sortArr[i] = new Number(i, number[i]);
        }

        Arrays.sort(sortArr); // 오름차순 정렬

        int money = Integer.parseInt(br.readLine());

        int idx = 0; // 자릿수 체크

        // 맨 앞자리가 0인 경우
        if (sortArr[0].num == 0) {
            if (sortArr[1].cost > money) { // 그 다음 숫자의 비용이 현재 가지고 있는 돈보다 많은 경우, 0 출력
                sb.append(0);
                System.out.println(sb);
                return;
            }

            // 첫 번째 자리를 0 다음 숫자로 저장
            ans[idx++] = sortArr[1].num;
            money -= sortArr[1].cost;
        }

        while (sortArr[0].cost <= money) { // 최대 자리까지 숫자 저장
            ans[idx++] = sortArr[0].num;
            money -= sortArr[0].cost;
        }

        for (int i = 0; i < idx; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == 0 && j == 0) // 1번째 자리는 0이 되면 X
                    continue;

                if (money + number[ans[i]] >= number[j]) { // 현재 자리를 다른 숫자로 교체 가능한 경우
                    money = money + number[ans[i]] - number[j];
                    ans[i] = j;
                    break;
                }
            }
        }

        // 정답 출력
        for (int i = 0; i < idx; i++)
            sb.append(ans[i]);

        System.out.println(sb);
    }
}

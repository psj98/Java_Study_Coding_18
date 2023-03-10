package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_16987 {
    static int n, ans = 0;
    static Egg[] egg;

    static class Egg {
        int durability, weight; // 내구도, 무게

        Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        egg = new Egg[n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int durability = Integer.parseInt(stk.nextToken());
            int weight = Integer.parseInt(stk.nextToken());
            egg[i] = new Egg(durability, weight);
        }

        eggBreak(0, 0); // 계란 깨기

        sb.append(ans);
        System.out.println(sb);
    }

    // 계란 깨기 - (몇 번째 계란, 깨진 계란 수)
    static void eggBreak(int cnt, int sum) {
        if (cnt == n) { // 마지막 계란까지 다 들어본 경우
            ans = Math.max(ans, sum); // 최댓값 갱신
            return;
        }

        // 현재 계란 내구도가 0 이하면 다음 계란으로
        // 남은 계란의 내구도가 모두 0 이하면 다음 계란으로
        if (egg[cnt].durability <= 0 || sum == n - 1) {
            eggBreak(cnt + 1, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            // cnt번째 계란을 들었을 때, 든 계란은 깰 수 없음 / 내구도 1이상
            if (i != cnt && egg[i].durability >= 1) {
                int broken = 0; // 깨진 계란 개수
                egg[cnt].durability -= egg[i].weight;
                egg[i].durability -= egg[cnt].weight;

                // 깨진 계란 개수 세기
                if (egg[cnt].durability <= 0)
                    broken++;
                if (egg[i].durability <= 0)
                    broken++;

                eggBreak(cnt + 1, sum + broken); // 백트래킹

                // 원래대로
                egg[cnt].durability += egg[i].weight;
                egg[i].durability += egg[cnt].weight;
            }
        }
    }
}

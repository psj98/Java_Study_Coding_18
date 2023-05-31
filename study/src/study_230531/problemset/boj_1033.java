package study_230531.problemset;

import java.io.*;
import java.util.*;

public class boj_1033 {
    static int[] ingredients;
    static ArrayList<Integer>[] list;
    static boolean[] visited;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 재료 개수

        ingredients = new int[n]; // 각 재료의 합
        Arrays.fill(ingredients, 1); // 1로 초기화

        // 초기화
        list = new ArrayList[n]; // 해당 재료와 관련된 재료 번호 저장 -> DFS
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        // a번 재료의 질량을 b번 재료의 질량으로 나눈 값이 p/q
        for (int i = 0; i < n - 1; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int p = Integer.parseInt(stk.nextToken());
            int q = Integer.parseInt(stk.nextToken());

            int num = gcd(p, q); // 최대공약수

            /**
             * 비율 체크
             * a : b = p : q => a * q = b * p
             */
            int aVal = ingredients[b] * p / num;
            int bVal = ingredients[a] * q / num;

            int numGcd = gcd(aVal, bVal);

            visited = new boolean[n];

            // 각 재료와 연관된 재료를 해당 비율에 맞게 갱신
            dfs(a, aVal / numGcd);
            dfs(b, bVal / numGcd);

            // 연관 재료 추가
            list[a].add(b);
            list[b].add(a);
        }

        // 정답 출력
        for (int i = 0; i < n; i++)
            sb.append(ingredients[i]).append(" ");
        System.out.println(sb);
    }

    // 최대공약수
    static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // 연관된 재료 갱신
    static void dfs(int idx, int mod) {
        visited[idx] = true;
        ingredients[idx] *= mod;

        for (int next : list[idx]) {
            if (visited[next])
                continue;

            dfs(next, mod);
        }
    }
}

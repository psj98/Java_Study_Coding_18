package study_240208.problemset;

import java.io.*;

public class boj_1344 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] notPrime = { 0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18 }; // 소수가 아닌 수

        // A와 B의 득점 확률
        double a = Double.parseDouble(br.readLine()) / 100;
        double b = Double.parseDouble(br.readLine()) / 100;

        double scoreA = 0, scoreB = 0; // 둘 다 소수로 득점하지 못할 확률

        // 확률 계산
        for (int i : notPrime) {
            scoreA += Math.pow(a, i) * Math.pow(1 - a, 18 - i) * comb(18, i);
            scoreB += Math.pow(b, i) * Math.pow(1 - b, 18 - i) * comb(18, i);
        }

        // 정답 출력
        sb.append(1 - scoreA * scoreB);
        System.out.println(sb);
    }

    // 조합 값 구하기
    static double comb(int n, int r) {
        double num = 1;

        r = Math.min(r, n - r);

        // ex) 5C2인 경우, 5 * 4 / 2 * 1
        int curN = n, curR = r;
        for (int i = 0; i < r; i++) {
            num *= curN;
            num /= curR;

            curN--;
            curR--;
        }

        return num;
    }
}

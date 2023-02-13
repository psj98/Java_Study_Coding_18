package study_230208.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2447_2 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                recursion(i, j);
            sb.append("\n");
        }

        System.out.println(sb);

    }

    // 가운데 공간 확인 -> 좌표 기준으로 나눔 (n=3일 때, [1, 1], n=9일 때, [3~5, 3~5])
    public static void recursion(int x, int y) {
        if (x % 3 == 1 && y % 3 == 1) {
            sb.append(" ");
        } else {
            if (x < 3 && y < 3)
                sb.append("*");
            else
                recursion(x / 3, y / 3);
        }
    }

}

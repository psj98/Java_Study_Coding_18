package study_230531.problemset;

import java.io.*;
import java.util.*;

public class boj_10830 {
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // n*n 행렬 -> a^b
        // 1000으로 나눈 나머지
        n = Integer.parseInt(stk.nextToken());
        long b = Long.parseLong(stk.nextToken());

        long[][] matrix = new long[n][n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                matrix[i][j] = Integer.parseInt(stk.nextToken());
        }

        long[][] ans = divide(matrix, b);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                sb.append(ans[i][j]).append(" ");
            sb.append("\n");
        }

        System.out.println(sb);
    }

    // 분할 정복
    static long[][] divide(long[][] mat, long b) {
        if (b == 1) {
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    mat[i][j] %= 1000;

            return mat;
        }

        long[][] newMat = divide(mat, b / 2);

        // 제곱의 값이 홀수 & 짝수에 따라 계산
        if (b % 2 == 1) { // 홀수 -> 원래꺼 제곱 * 기존 행렬
            return calMatrix(calMatrix(newMat, newMat), mat);
        } else { // 짝수 -> 원래꺼 제곱
            return calMatrix(newMat, newMat);
        }
    }

    // 행렬 계산
    static long[][] calMatrix(long[][] matA, long[][] matB) {
        long tempMat[][] = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long sum = 0;
                for (int k = 0; k < n; k++)
                    sum += matA[i][k] * matB[k][j];

                tempMat[i][j] = sum % 1000;
            }
        }

        return tempMat;
    }
}
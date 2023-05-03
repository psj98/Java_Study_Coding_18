package study_230503.problemset;

import java.io.*;
import java.util.*;

public class boj_2981 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        /*
         * n1 = m * a1 + r1
         * n2 = m * a2 + r2
         * 
         * n1 - n2 = m * (a1 - a2) + (r1 - r2)
         * n1 - n2 = m * (a1 - a2) <- m은 (n1 - n2)의 공약수
         * 
         * 따라서 모든 (n1 - n2) 의 공약수를 찾으면 됨
         */
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = Integer.parseInt(br.readLine());

        Arrays.sort(nums); // 오름차순 정렬

        // (n1 - n2) 의 최대공약수 찾기
        int val = nums[1] - nums[0];
        for (int i = 2; i < n; i++)
            val = gcd(val, nums[i] - nums[i - 1]);

        // 최대공약수의 약수들 출력
        for (int i = 2; i <= val / 2; i++)
            if (val % i == 0)
                sb.append(i).append(" ");

        sb.append(val);
        System.out.println(sb);
    }

    // GCD
    static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}

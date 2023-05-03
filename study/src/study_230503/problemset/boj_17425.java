package study_230503.problemset;

import java.io.*;
import java.util.*;

public class boj_17425 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        long[] nums = new long[1_000_001]; // 값을 index로 하는 배열

        Arrays.fill(nums, 1); // 1로 채움 -> 1도 약수 포함

        for (int i = 2; i <= 1_000_000; i++) {
            for (int j = 1; i * j <= 1_000_000; j++)
                nums[i * j] += i; // i * j 값의 약수가 i이기 때문에 i를 더함
            nums[i] += nums[i - 1]; // 현재 값보다 작거나 같은 모든 자연수 y의 f(y) 값을 더한 값
        }

        // 정답 출력
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++)
            sb.append(nums[Integer.parseInt(br.readLine())]).append("\n");

        System.out.println(sb);
    }
}

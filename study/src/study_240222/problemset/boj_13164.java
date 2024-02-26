package study_240222.problemset;

import java.io.*;
import java.util.*;

public class boj_13164 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken()); // n명
        int k = Integer.parseInt(stk.nextToken()); // k개 조

        int[] student = new int[n]; // 학생 키 정보

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            student[i] = Integer.parseInt(stk.nextToken());
        }

        // 인접한 값의 차이를 저장할 배열
        ArrayList<Integer> diff = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            diff.add(student[i] - student[i - 1]);
        }

        Collections.sort(diff); // 오름차순 정렬

        // 합 구하기 => 학생들 사이에 막대를 세운다고 생각
        int sum = 0;
        for (int i = 0; i < n - k; i++) {
            sum += diff.get(i);
        }

        // 정답 출력
        sb.append(sum);
        System.out.println(sb);
    }
}

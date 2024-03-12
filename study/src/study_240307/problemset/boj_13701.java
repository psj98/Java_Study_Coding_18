package study_240307.problemset;

import java.io.*;
import java.util.*;

public class boj_13701 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        BitSet bitSet = new BitSet(33554432); // BitSet

        int size = stk.countTokens();

        for (int i = 0; i < size; i++) {
            int num = Integer.parseInt(stk.nextToken());

            // BitSet에 값이 있는 경우, 스킵
            if (bitSet.get(num)) {
                continue;
            }

            bitSet.set(num); // BitSet에 저장
            sb.append(num).append(" "); // 정답 저장
        }

        // 정답 출력
        System.out.println(sb);
    }
}

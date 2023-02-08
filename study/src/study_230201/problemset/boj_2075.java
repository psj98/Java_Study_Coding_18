package study_230201.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class boj_2075 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순 우선순위 큐

        int n = Integer.parseInt(br.readLine());

        // 값 저장
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < str.length; j++) {
                pq.add(Integer.parseInt(str[j]));
            }
        }

        for (int i = 0; i < n - 1; i++)
            pq.remove();

        System.out.println(pq.peek());
    }

}

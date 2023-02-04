package study_230201.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class alg_14235 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");

            if (str[0].equals("0")) {
                if (pq.size() == 0)
                    sb.append("-1\n");
                else {
                    sb.append(pq.peek() + "\n");
                    pq.remove();
                }
            }

            else {
                for (int j = 1; j <= Integer.parseInt(str[0]); j++) {
                    pq.add(Integer.parseInt(str[j]));
                }
            }
        }

        System.out.println(sb);
    }
}

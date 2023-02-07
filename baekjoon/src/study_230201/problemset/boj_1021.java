package study_230201.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class boj_1021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> dq = new ArrayDeque<>();
        int cnt = 0;

        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        for (int i = 1; i <= n; i++)
            dq.add(i);

        str = br.readLine().split(" ");

        for (int i = 0; i < m; i++) {
            int right = 0, left = 0;
            Iterator<Integer> it = dq.iterator();

            while (it.hasNext()) {
                int num = it.next();
                if (num == Integer.parseInt(str[i]))
                    break;
                left++;
            }

            right = dq.size() - left;

            if (left <= right) {
                for (int j = 0; j < left; j++) {
                    dq.addLast(dq.getFirst());
                    dq.removeFirst();
                    cnt++;
                }

                dq.removeFirst();
            }

            else {
                for (int j = 0; j < right; j++) {
                    dq.addFirst(dq.getLast());
                    dq.removeLast();
                    cnt++;
                }

                dq.removeFirst();
            }

        }

        System.out.println(cnt);
    }
}

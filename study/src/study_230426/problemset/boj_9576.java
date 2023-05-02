package study_230426.problemset;

import java.io.*;
import java.util.*;

public class boj_9576 {
    static class Book implements Comparable<Book> {
        int x, y, diff;

        Book(int x, int y, int diff) {
            this.x = x;
            this.y = y;
            this.diff = diff;
        }

        @Override
        public int compareTo(Book o) {
            if (this.y == o.y) // 끝 범위가 같으면 차이가 작은 순으로 정렬
                return this.diff - o.diff;
            return this.y - o.y; // 끝 범위 오름차순 정렬
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine()); // testcase
        for (int tc = 1; tc <= t; tc++) {
            stk = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(stk.nextToken());
            int m = Integer.parseInt(stk.nextToken());

            Book[] books = new Book[m]; // Book 배열
            for (int i = 0; i < m; i++) {
                stk = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(stk.nextToken());
                int end = Integer.parseInt(stk.nextToken());

                books[i] = new Book(start, end, end - start); // Book 정보 저장
            }

            Arrays.sort(books); // 정렬

            int ans = 0;
            boolean[] check = new boolean[n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = books[i].x; j <= books[i].y; j++) { // Book 범위 -> 가장 안겹치는 (맨 앞) 책부터 나눠줌
                    if (check[j]) // 이미 준 경우 스킵
                        continue;

                    check[j] = true; // 책을 줄 수 있는 경우, true로 변경
                    ans++; // 정답 증가
                    break; // 책을 줬으므로 반복문 탈출
                }
            }

            // 정답 저장
            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
}

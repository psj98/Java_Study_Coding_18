package study_240208.problemset;

import java.io.*;
import java.util.*;

public class boj_2251 {
    static int[] maxBucket = new int[3]; // 최대 가능 물통 범위
    static ArrayList<Integer> list = new ArrayList<>(); // C 물통에 가능한 값

    // 물통
    static class Bucket {
        int a, b, c;

        Bucket(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 최대 물통 용량
        for (int i = 0; i < 3; i++) {
            maxBucket[i] = Integer.parseInt(stk.nextToken());
        }

        bfs(); // BFS

        Collections.sort(list); // 오름차순 정렬

        for (int i : list) {
            sb.append(i).append(" ");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // BFS
    static void bfs() {
        ArrayDeque<Bucket> buckets = new ArrayDeque<>();
        boolean[][][] visited = new boolean[maxBucket[0] + 1][maxBucket[1] + 1][maxBucket[2] + 1];

        buckets.add(new Bucket(0, 0, maxBucket[2])); // 초기 물통 용량

        while (!buckets.isEmpty()) {
            Bucket cur = buckets.poll();

            // 방문 체크
            if (visited[cur.a][cur.b][cur.c]) {
                continue;
            }

            // A 물통이 비어있을 경우, C 물통에 있는 용량을 list에 추가
            if (cur.a == 0) {
                list.add(cur.c);
            }

            visited[cur.a][cur.b][cur.c] = true; // 방문 체크

            // A에 물이 있는지 체크
            if (cur.a != 0) {
                // A => B
                if (cur.a + cur.b <= maxBucket[1]) {
                    buckets.add(new Bucket(0, cur.a + cur.b, cur.c));
                } else {
                    buckets.add(new Bucket(cur.a + cur.b - maxBucket[1], maxBucket[1], cur.c));
                }

                // A => C
                if (cur.a + cur.c <= maxBucket[2]) {
                    buckets.add(new Bucket(0, cur.b, cur.a + cur.c));
                } else {
                    buckets.add(new Bucket(cur.a + cur.c - maxBucket[2], cur.b, maxBucket[2]));
                }
            }

            // B에 물이 있는지 체크
            if (cur.b != 0) {
                // B => A
                if (cur.a + cur.b <= maxBucket[0]) {
                    buckets.add(new Bucket(cur.a + cur.b, 0, cur.c));
                } else {
                    buckets.add(new Bucket(maxBucket[0], cur.a + cur.b - maxBucket[0], cur.c));
                }

                // B => C
                if (cur.b + cur.c <= maxBucket[2]) {
                    buckets.add(new Bucket(cur.a, 0, cur.b + cur.c));
                } else {
                    buckets.add(new Bucket(cur.a, cur.b + cur.c - maxBucket[2], maxBucket[2]));
                }
            }

            // C에 물이 있는지 체크
            if (cur.c != 0) {
                // C => A
                if (cur.a + cur.c <= maxBucket[0]) {
                    buckets.add(new Bucket(cur.a + cur.c, cur.b, 0));
                } else {
                    buckets.add(new Bucket(maxBucket[0], cur.b, cur.a + cur.c - maxBucket[0]));
                }

                // C => B
                if (cur.b + cur.c <= maxBucket[1]) {
                    buckets.add(new Bucket(cur.a, cur.b + cur.c, 0));
                } else {
                    buckets.add(new Bucket(cur.a, maxBucket[1], cur.b + cur.c - maxBucket[1]));
                }
            }
        }
    }
}

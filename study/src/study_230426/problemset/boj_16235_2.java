package study_230426.problemset;

import java.io.*;
import java.util.*;

/* 시간 초과 */
public class boj_16235_2 {
    static int n, m;
    static ArrayList<Tree> tree; // 나무
    static int[][] food;
    static int[][] addFood;
    static int[] dx = { 1, -1, 0, 0, 1, 1, -1, -1 };
    static int[] dy = { 0, 0, 1, -1, 1, -1, 1, -1 };

    // 나무 클래스
    static class Tree implements Comparable<Tree> {
        int x, y, age;

        Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return o.age - this.age;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        food = new int[n][n];
        addFood = new int[n][n];
        tree = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                food[i][j] = 5;
                addFood[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken()) - 1;
            int y = Integer.parseInt(stk.nextToken()) - 1;
            int age = Integer.parseInt(stk.nextToken());
            tree.add(new Tree(x, y, age));
        }

        // 봄, 여름, 가을, 겨울
        while (k-- != 0) {
            springAndSummer();
            autumn();
            winter();
        }

        sb.append(tree.size());
        System.out.println(sb);
    }

    static void springAndSummer() {
        Queue<Tree> dead = new ArrayDeque<>();
        Collections.sort(tree);

        int size = tree.size() - 1;
        for (int i = size; i >= 0; i--) {
            Tree cur = tree.get(i);

            if (cur.age <= food[cur.x][cur.y]) {
                food[cur.x][cur.y] -= cur.age;
                cur.age++;
                tree.set(i, cur);
            } else {
                cur.age /= 2;
                dead.add(cur);
                tree.remove(i);
            }
        }

        while (!dead.isEmpty()) {
            Tree cur = dead.poll();
            food[cur.x][cur.y] += cur.age;
        }
    }

    static void autumn() {
        int size = tree.size() - 1;

        for (int i = size; i >= 0; i--) {
            Tree cur = tree.get(i);

            if (cur.age % 5 != 0)
                continue;

            for (int j = 0; j < 8; j++) {
                int nx = cur.x + dx[j];
                int ny = cur.y + dy[j];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;

                tree.add(new Tree(nx, ny, 1));
            }
        }
    }

    static void winter() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                food[i][j] += addFood[i][j];
    }
}

package study_230719.problemset;

import java.io.*;
import java.util.*;

public class boj_3967 {
    static ArrayList<Node> nodes = new ArrayList<>(); // x표시 좌표
    static char[][] map = new char[5][9]; // 맵 정보
    static boolean[] visited = new boolean[12]; // 알파벳 체크
    static int[][][] check = {
            { { 0, 4 }, { 1, 3 }, { 2, 2 }, { 3, 1 } },
            { { 3, 1 }, { 3, 3 }, { 3, 5 }, { 3, 7 } },
            { { 0, 4 }, { 1, 5 }, { 2, 6 }, { 3, 7 } },
            { { 1, 1 }, { 1, 3 }, { 1, 5 }, { 1, 7 } },
            { { 1, 1 }, { 2, 2 }, { 3, 3 }, { 4, 4 } },
            { { 1, 7 }, { 2, 6 }, { 3, 5 }, { 4, 4 } }
    };

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /**
         * 없는 알파벳을 모든 위치에 넣어보고 되는지 판단 => 백트래킹
         */

        // 맵 정보
        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '.') // 빈 공간
                    continue;

                if (map[i][j] != 'x') { // 알파벳 있는 경우
                    visited[map[i][j] - 'A'] = true;
                    continue;
                }

                // 알파벳 있는 경우
                nodes.add(new Node(i, j));
            }
        }

        find(0); // 백트래킹
    }

    // 백트래킹
    static void find(int cnt) {
        // 알파벳이 없는 위치에 모두 알파벳을 배치한 경우
        if (cnt == nodes.size()) {
            for (int i = 0; i < 6; i++) {
                int sum = 0;
                for (int j = 0; j < 4; j++) {
                    sum += (map[check[i][j][0]][check[i][j][1]] - 'A' + 1);
                }

                // 값의 합이 26이어야 함
                if (sum != 26) {
                    return;
                }
            }

            // 정답 출력
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                sb.append(map[j]).append("\n");
            }

            System.out.println(sb);
            System.exit(0);
        }

        // 빈 자리에 알파벳 저장
        for (int i = 0; i < 12; i++) {
            if (visited[i])
                continue;

            Node cur = nodes.get(cnt);
            visited[i] = true;
            map[cur.x][cur.y] = (char) (i + 65);
            find(cnt + 1);
            visited[i] = false;
        }
    }
}

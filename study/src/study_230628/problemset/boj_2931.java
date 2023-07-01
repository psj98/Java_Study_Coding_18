package study_230628.problemset;

import java.io.*;
import java.util.*;

public class boj_2931 {
    static int n, m;
    static char[][] map;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    static class Block {
        int x, y, dir; // 좌표, 방향

        Block(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        int startX = 0, startY = 0;
        map = new char[n][m];

        // 맵 정보
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'M') {
                    startX = i;
                    startY = j;
                }
            }
        }

        Block blockM = findAdjBlock(startX, startY); // M과 인접한 블록 찾기

        // 방향을 따라가면서 . 이 나올 때까지 이동
        while (true) {
            blockM.x += dx[blockM.dir];
            blockM.y += dy[blockM.dir];

            if (map[blockM.x][blockM.y] == '.') {
                break;
            }

            blockM.dir = findNext(blockM.dir, map[blockM.x][blockM.y]);
        }

        // 갈 수 있는 곳 체크 => 갈 수 없으면 true로 변경
        boolean[] check = new boolean[4];
        for (int i = 0; i < 4; i++) {
            int nx = blockM.x + dx[i];
            int ny = blockM.y + dy[i];

            // 범위 체크
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                check[i] = true;
                continue;
            }

            // '.', 'M', 'Z'로 못감
            if (map[nx][ny] == '.' || map[nx][ny] == 'M' || map[nx][ny] == 'Z') {
                check[i] = true;
                continue;
            }

            // 각 방향으로 들어갔을 때 못가는 경우 체크 => true로 변경
            if (i == 0) { // 아래
                if (map[nx][ny] == '-' || map[nx][ny] == '1' || map[nx][ny] == '4') {
                    check[i] = true;
                    continue;
                }
            } else if (i == 1) { // 위
                if (map[nx][ny] == '-' || map[nx][ny] == '2' || map[nx][ny] == '3') {
                    check[i] = true;
                    continue;
                }
            } else if (i == 2) { // 오른쪽
                if (map[nx][ny] == '|' || map[nx][ny] == '1' || map[nx][ny] == '2') {
                    check[i] = true;
                    continue;
                }
            } else if (i == 3) { // 왼쪽
                if (map[nx][ny] == '|' || map[nx][ny] == '3' || map[nx][ny] == '4') {
                    check[i] = true;
                    continue;
                }
            }
        }

        // 행, 열, 어떤 블록
        sb.append(blockM.x + 1).append(" ").append(blockM.y + 1).append(" ").append(findAns(check));
        System.out.println(sb);
    }

    // 시작, 끝 지점과 인접한 블록 찾기
    static Block findAdjBlock(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 체크
            if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                continue;

            // '.', 'M', 'Z'로 못감
            if (map[nx][ny] == '.' || map[nx][ny] == 'M' || map[nx][ny] == 'Z')
                continue;

            // 다음 방향 구하기
            int nextDir = findNext(i, map[nx][ny]);
            return new Block(nx, ny, nextDir);
        }

        return null;
    }

    // 방향 전환
    static int findNext(int cur, char block) {
        if (cur == 0) { // 아래
            if (block == '2') { // 아래 -> 오른쪽
                return 2;
            } else if (block == '3') { // 아래 -> 왼쪽
                return 3;
            }
        } else if (cur == 1) { // 위
            if (block == '1') { // 위 -> 오른쪽
                return 2;
            } else if (block == '4') { // 위 -> 왼쪽
                return 3;
            }
        } else if (cur == 2) { // 오른쪽
            if (block == '3') { // 오른쪽 -> 위
                return 1;
            } else if (block == '4') { // 오른쪽 -> 아래
                return 0;
            }
        } else if (cur == 3) { // 왼쪽
            if (block == '1') { // 왼쪽 -> 아래
                return 0;
            } else if (block == '2') { // 왼쪽 -> 위
                return 1;
            }
        }

        return cur; // 가로, 세로, 사거리일 때는 원래 가던 방향
    }

    // 정답 블록 찾기
    static char findAns(boolean[] check) {
        char ans = 'x';

        if (!check[0]) { // 아래
            if (!check[1]) { // 위
                if (!check[2]) // 오른쪽 => '+'
                    ans = '+';
                else // 아래, 위만 가능 => '|'
                    ans = '|';
            } else if (!check[2]) { // 오른쪽 => '1'
                ans = '1';
            } else if (!check[3]) { // 왼쪽 => '4'
                ans = '4';
            }
        } else if (!check[1]) { // 위
            if (!check[2]) { // 오른쪽 => '2'
                ans = '2';
            } else if (!check[3]) { // 왼쪽 => '3'
                ans = '3';
            }
        } else { // 위, 아래 둘 다 불가능 => '-'
            ans = '-';
        }

        return ans;
    }
}

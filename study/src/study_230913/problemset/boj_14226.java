package study_230913.problemset;

import java.io.*;
import java.util.*;

public class boj_14226 {
    // [화면에 출력된 이모티콘 개수][clipboard에 복사된 이모티콘 개수]
    static boolean[][] visited = new boolean[1001][1001];

    static class Emoticon {
        int emoji, clipboard, time;

        // 화면에 출력된 이모티콘 개수
        // 클립보드에 복사된 이모티콘 개수
        // 시간
        Emoticon(int emoji, int clipboard, int time) {
            this.emoji = emoji;
            this.clipboard = clipboard;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bfs(Integer.parseInt(br.readLine())); // BFS
    }

    // BFS
    static void bfs(int s) {
        Queue<Emoticon> queue = new ArrayDeque<>();
        queue.add(new Emoticon(1, 0, 0)); // 화면에 출력된 이모티콘 개수, 클립보드에 복사된 이모티콘 개수, 시간

        visited[1][0] = true; // 초기 체크

        while (!queue.isEmpty()) {
            Emoticon cur = queue.poll();

            // 화면에 출력된 이모티콘 개수가 s인 경우, 종료
            if (cur.emoji == s) {
                System.out.println(cur.time);
                break;
            }

            // 클립보드에 저장
            if (cur.emoji != 0 && !visited[cur.emoji][cur.emoji]) {
                queue.add(new Emoticon(cur.emoji, cur.emoji, cur.time + 1));
                visited[cur.emoji][cur.emoji] = true;
            }

            // 클립보드에 있는 모든 이모티콘 붙여넣기
            if (cur.clipboard != 0 && cur.emoji + cur.clipboard <= 1000
                    && !visited[cur.emoji + cur.clipboard][cur.clipboard]) {
                queue.add(new Emoticon(cur.emoji + cur.clipboard, cur.clipboard, cur.time + 1));
                visited[cur.emoji + cur.clipboard][cur.clipboard] = true;
            }

            // 화면에 있는 이모티콘 중 하나 삭제
            if (cur.emoji - 1 >= 0 && !visited[cur.emoji - 1][cur.clipboard]) {
                queue.add(new Emoticon(cur.emoji - 1, cur.clipboard, cur.time + 1));
                visited[cur.emoji - 1][cur.clipboard] = true;
            }
        }
    }
}
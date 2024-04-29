package study_240425.problemset;

import java.io.*;
import java.util.*;

public class boj_16434 {
    // 방 class
    static class Room {
        int t, a, h;

        Room(int t, int a, int h) {
            this.t = t;
            this.a = a;
            this.h = h;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken()); // 방 개수
        int atk = Integer.parseInt(stk.nextToken()); // 초기 공격력

        Room[] rooms = new Room[n];

        long start = 1, end = Long.MAX_VALUE; // 이분 탐색을 위한 초기값

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(stk.nextToken());
            int a = Integer.parseInt(stk.nextToken());
            int h = Integer.parseInt(stk.nextToken());

            rooms[i] = new Room(t, a, h); // 방 정보 저장
        }

        // 이분 탐색
        while (start <= end) {
            boolean flag = true;
            long mid = (start + end) / 2; // 가능한 최대 체력
            long curHp = mid, curAtk = atk; // 현재 체력 & 공격력

            for (Room room : rooms) {
                if (room.t == 1) { // 몬스터
                    if (room.h % curAtk == 0) {
                        curHp -= room.a * ((room.h / curAtk) - 1);
                    } else {
                        curHp -= room.a * (room.h / curAtk);
                    }

                    // hp가 0이 되는 경우, 종료
                    if (curHp <= 0) {
                        flag = false;
                        break;
                    }
                } else { // 포션
                    curHp = Math.min(mid, curHp + room.h);
                    curAtk += room.a;
                }
            }

            if (flag) { // 성공 시
                end = mid - 1;
            } else { // 실패 시
                start = mid + 1;
            }
        }

        // 정답 출력
        sb.append(start);
        System.out.println(sb);
    }
}

package study_230419.problemset;

import java.io.*;
import java.util.*;

public class boj_1092 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        ArrayList<Integer> crane = new ArrayList<>();
        ArrayList<Integer> box = new ArrayList<>();

        // 크레인 입력
        int n = Integer.parseInt(br.readLine());
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            crane.add(Integer.parseInt(stk.nextToken()));

        Collections.sort(crane, Collections.reverseOrder()); // 내림차순 정렬

        // 박스 입력
        int m = Integer.parseInt(br.readLine());
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++)
            box.add(Integer.parseInt(stk.nextToken()));

        Collections.sort(box); // 오름차순 정렬

        // 박스의 최대 무게가 크레인이 들 수 있는 최대 무게보다 크면 -1
        if (box.get(m - 1) > crane.get(0)) {
            sb.append(-1);
            System.out.println(sb);
            return;
        }

        // 박스가 없어질 때까지 실행
        // 크레인 앞에서부터 비교해서 하나씩 들기
        int ans = 0;
        while (!box.isEmpty()) {
            int boxIdx = box.size() - 1;
            for (int i = 0; i < n;) {
                if (boxIdx == -1)
                    break;

                if (box.get(boxIdx) <= crane.get(i)) {
                    box.remove(boxIdx--);
                    i++;
                } else
                    boxIdx--;
            }

            ans++;
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }
}

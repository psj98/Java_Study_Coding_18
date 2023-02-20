package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class boj_5397 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            String str = br.readLine();
            LinkedList<Character> linkedList = new LinkedList<>();
            ListIterator<Character> it = linkedList.listIterator(); // linkedlist의 위치를 알 수 있는 iterator

            // 문자열에 대해 탐색
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (c == '<') { // 커서 왼쪽으로 이동
                    if (it.hasPrevious())
                        it.previous();
                } else if (c == '>') { // 커서 오른쪽으로 이동
                    if (it.hasNext())
                        it.next();
                } else if (c == '-') { // 값 지우기
                    if (it.hasPrevious()) {
                        it.previous();
                        it.remove();
                    }
                } else { // 값 추가
                    it.add(c);
                }
            }

            // 전체 값 저장
            it = linkedList.listIterator();
            while (it.hasNext()) {
                sb.append(it.next());
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

}

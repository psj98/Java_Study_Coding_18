package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class boj_1406_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();
        LinkedList<Character> linkedList = new LinkedList<>(); // LinkedList

        String str = br.readLine();
        for (int i = 0; i < str.length(); i++)
            linkedList.add(str.charAt(i)); // 각 자리수를 Character형으로 저장

        ListIterator<Character> it = linkedList.listIterator(); // 커서를 위한 ListIterator 사용
        while (it.hasNext()) { // 커서를 맨 마지막으로 이동
            it.next();
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            char op = stk.nextToken().charAt(0);

            if (op == 'L') { // 커서 왼쪽으로
                if (it.hasPrevious())
                    it.previous();
            } else if (op == 'D') { // 커서 오른쪽으로
                if (it.hasNext())
                    it.next();
            } else if (op == 'B') { // 커서 왼쪽 값 삭제
                if (it.hasPrevious()) {
                    it.previous();
                    it.remove();
                }
            } else if (op == 'P') { // 커서 왼쪽에 추가
                it.add(stk.nextToken().charAt(0));
            }
        }

        it = linkedList.listIterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }

        System.out.println(sb);
    }
}

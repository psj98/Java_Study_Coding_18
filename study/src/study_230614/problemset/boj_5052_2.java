package study_230614.problemset;

import java.io.*;
import java.util.*;

public class boj_5052_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            Trie trie = new Trie(); // Trie 생성

            boolean check = false; // 일관성 여부
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                String word = br.readLine(); // 단어 입력
                if (!trie.insert(word)) { // 단어 삽입 후, 마지막 단어인지 체크 -> 마지막 단어가 아닌 경우, true
                    check = true;
                }
            }

            // 정답 출력
            if (check)
                sb.append("NO\n");
            else
                sb.append("YES\n");
        }

        System.out.println(sb);
    }

    static class Node {
        Map<Character, Node> childNodes = new HashMap<>(); // <문자, 다음 노드>
        boolean isLast; // 말단 노드인지 체크하기 위한 값
    }

    // Trie 클래스
    static class Trie {
        Node root = new Node(); // root 노드 생성

        // 단어 삽입 후, 마지막 단어인지 체크
        boolean insert(String word) {
            Node node = this.root;

            for (int i = 0; i < word.length(); i++) {
                // 단어를 포함하지 않은 경우, 새로운 Node 생성
                if (!node.childNodes.containsKey(word.charAt(i))) {
                    node.childNodes.put(word.charAt(i), new Node());
                }

                node = node.childNodes.get(word.charAt(i)); // 해당 위치로 이동

                // 마지막 단어 표시가 있는 경우, 일관성 X
                if (node.isLast)
                    return false;
            }

            // 단어의 끝까지 왔는데 뒤에 다른 노드가 더 있는 경우, 일관성 X
            if (node.childNodes.size() != 0)
                return false;

            node.isLast = true; // 마지막 단어 체크
            return true; // 일관성 O
        }
    }
}
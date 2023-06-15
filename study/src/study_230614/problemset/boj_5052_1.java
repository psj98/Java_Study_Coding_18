package study_230614.problemset;

import java.io.*;
import java.util.*;

public class boj_5052_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine()); // testcase
        for (int tc = 0; tc < t; tc++) {
            Trie trie = new Trie(); // Trie 생성

            int n = Integer.parseInt(br.readLine());
            String[] words = new String[n];

            // 단어 입력
            for (int i = 0; i < n; i++) {
                words[i] = br.readLine();
            }

            Arrays.sort(words); // 단어가 포함되어 있는지 확인하기 위해 정렬

            // 단어 삽입
            for (int i = 0; i < n; i++) {
                trie.insert(words[i]);
            }

            boolean check = false;
            for (int i = 0; i < n; i++) {
                if (!trie.searchLast(words[i])) { // 마지막 문자가 아닌 경우, NO
                    check = true;
                    break;
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
        Node root = new Node();

        // 단어 삽입
        void insert(String word) {
            Node node = this.root;

            for (int i = 0; i < word.length(); i++) {
                if (node.childNodes.containsKey(word.charAt(i))) { // 단어가 포함되어 있는 경우
                    node = node.childNodes.get(word.charAt(i)); // 해당 위치로 이동
                    node.isLast = false; // 마지막 단어가 아닌 것으로 수정
                } else { // 단어가 포함되어 있지 않은 경우
                    node.childNodes.put(word.charAt(i), new Node()); // 새로운 Node 생성
                    node = node.childNodes.get(word.charAt(i)); // 해당 위치로 이동
                }
            }

            node.isLast = true; // 마지막 단어 표시
        }

        // 말단 노드인지 확인 -> 이전에 나온 단어로만 판별하기 때문에 끝까지 이동하고 체크
        boolean searchLast(String str) {
            Node node = this.root;

            // 단어의 끝까지 이동
            for (int i = 0; i < str.length(); i++)
                node = node.childNodes.get(str.charAt(i));

            return node.isLast; // 마지막 단어 여부 체크
        }
    }
}
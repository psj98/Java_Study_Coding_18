package study_231018.problemset;

import java.util.*;

class Solution {
    class Music implements Comparable<Music> {
        int idx, play;

        public Music(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }

        @Override
        public int compareTo(Music o) {
            if (this.play == o.play) {
                return this.idx - o.idx;
            }

            return o.play - this.play;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> ansList = new ArrayList<>();
        HashMap<String, Integer> total = new HashMap<>();
        HashMap<String, List<Music>> musicMap = new HashMap<>();

        // 1. 전체 재생 횟수 계산 => hashmap
        for (int i = 0; i < genres.length; i++) {
            total.put(genres[i], total.getOrDefault(genres[i], 0) + plays[i]);
        }

        // 2. key값으로 정렬
        ArrayList<String> keyList = new ArrayList<>();
        for (String cur : total.keySet()) {
            keyList.add(cur);
        }

        Collections.sort(keyList, (o1, o2) -> (total.get(o2) - total.get(o1)));

        // 3. 해당 장르에 따른 값을 (idx, play)로 저장
        for (int i = 0; i < genres.length; i++) {
            Music music = new Music(i, plays[i]);

            if (musicMap.containsKey(genres[i])) {
                List<Music> cur = musicMap.get(genres[i]);
                cur.add(music);
                musicMap.put(genres[i], cur);
            } else {
                List<Music> cur = new ArrayList<>();
                cur.add(music);

                musicMap.put(genres[i], cur);
            }
        }

        // 4. play 횟수로 정렬 => 같으면 idx 작은 것부터 정렬
        for (String key : keyList) {
            List<Music> cur = musicMap.get(key);

            Collections.sort(cur);

            // 5. 2개 뽑고 다음 장르로 이동
            ansList.add(cur.get(0).idx);
            if (cur.size() > 1) {
                ansList.add(cur.get(1).idx);
            }
        }

        int[] answer = new int[ansList.size()];
        for (int i = 0; i < ansList.size(); i++) {
            answer[i] = ansList.get(i);
        }

        return answer;
    }
}
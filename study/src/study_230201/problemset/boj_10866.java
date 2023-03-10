package study_230201.problemset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class boj_10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<String> list = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");

            // push_front
            if (temp[0].equals("push_front")) {
                list.add(0, temp[1]);
            }

            // push_back
            else if (temp[0].equals("push_back")) {
                list.add(temp[1]);
            }

            // pop_front
            else if (temp[0].equals("pop_front")) {
                if (list.size() != 0) {
                    bw.write(list.get(0) + "\n");
                    list.remove(0);
                }

                else
                    bw.write(-1 + "\n");
            }

            // pop_back
            else if (temp[0].equals("pop_back")) {
                if (list.size() != 0) {
                    bw.write(list.get(list.size() - 1) + "\n");
                    list.remove(list.size() - 1);
                }

                else
                    bw.write(-1 + "\n");
            }

            // size
            else if (temp[0].equals("size")) {
                bw.write(list.size() + "\n");
            }

            // empty
            else if (temp[0].equals("empty")) {
                bw.write(list.isEmpty() ? "1\n" : "0\n");
            }

            // front
            else if (temp[0].equals("front")) {
                bw.write(list.isEmpty() ? "-1\n" : list.get(0) + "\n");
            }

            // back
            else if (temp[0].equals("back")) {
                bw.write(list.isEmpty() ? "-1\n" : list.get(list.size() - 1) + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}

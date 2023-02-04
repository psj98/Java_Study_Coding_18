package study_230201.problemset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class alg_10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<String> list = new ArrayList<>();

        int n = Integer.parseInt(bf.readLine());

        for (int i = 0; i < n; i++) {
            String[] temp = bf.readLine().split(" ");
            if (temp[0].equals("push")) {
                list.add(temp[1]);
            }

            else if (temp[0].equals("pop")) {
                if (list.size() != 0) {
                    bw.write(list.get(list.size() - 1) + "\n");
                    list.remove(list.size() - 1);
                } else
                    bw.write(-1 + "\n");
            }

            else if (temp[0].equals("size")) {
                bw.write(list.size() + "\n");
            }

            else if (temp[0].equals("empty")) {
                if (list.size() == 0)
                    bw.write(1 + "\n");
                else
                    bw.write(0 + "\n");
            }

            else if (temp[0].equals("top")) {
                if (list.size() != 0)
                    bw.write(list.get(list.size() - 1) + "\n");
                else
                    bw.write(-1 + "\n");
            }
            bw.flush();
        }

        bw.close();
    }
}

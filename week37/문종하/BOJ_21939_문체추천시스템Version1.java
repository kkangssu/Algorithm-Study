import java.io.*;
import java.util.*;

public class BOJ_21939_문체추천시스템Version1 {
    static class Problem implements Comparable<Problem> {
        int number;
        int level;

        Problem(int number, int level) {
            this.number = number;
            this.level = level;
        }

        @Override
        public int compareTo(Problem other) {
            // 난이도, 문제 번호
            if (this.level != other.level)
                return Integer.compare(this.level, other.level);
            return Integer.compare(this.number, other.number);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        TreeSet<Problem> problems = new TreeSet<>(); // 자동 정렬되는 집합
        Map<Integer, Integer> problemLevel = new HashMap<>(); // 문제번호 -> 난이도

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int problemNum = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());

            problems.add(new Problem(problemNum, level));
            problemLevel.put(problemNum, level);
        }

        int m = Integer.parseInt(br.readLine());

        // 명령어 처리
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("add")) {
                int problemNum = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());

                problems.add(new Problem(problemNum, level));
                problemLevel.put(problemNum, level);

            } else if (command.equals("solved")) {
                int problemNum = Integer.parseInt(st.nextToken());
                int level = problemLevel.get(problemNum);

                problems.remove(new Problem(problemNum, level));
                problemLevel.remove(problemNum);

            } else if (command.equals("recommend")) {
                int x = Integer.parseInt(st.nextToken());

                if (x == 1)
                    sb.append(problems.last().number).append("\n"); // 가장 어려운 문제
                else
                    sb.append(problems.first().number).append("\n"); // 가장 쉬운 문제
            }
        }

        System.out.print(sb);
    }
}
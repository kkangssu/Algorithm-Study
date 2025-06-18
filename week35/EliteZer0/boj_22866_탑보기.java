package week35.EliteZer0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class boj_22866_탑보기 {
    static class Building {
        int index, height;

        Building(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        // 모든 보이는 건물 번호를 저장
        // List<Integer>[] visible = new ArrayList[N];
        // for (int i = 0; i < N; i++) visible[i] = new ArrayList<>();

        // 개수와 가장 가까운 건물 번호만 저장
        int[] count = new int[N]; // 볼 수 있는 건물 수
        int[] closest = new int[N]; // 가장 가까운 건물 번호

        // 왼쪽에서 볼 수 있는 건물 계산
        Deque<Building> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && stack.peek().height <= heights[i]) {
                stack.pop(); // 자신보다 낮거나 같은 건물은 가림
            }

            // 스택 전체 순회
            // for (Building b : stack) {
            // visible[i].add(b.index);
            // }

            // 스택 크기 → 볼 수 있는 개수, peek → 가장 가까운 건물
            if (!stack.isEmpty()) {
                count[i] += stack.size();
                closest[i] = stack.peek().index;
            }

            stack.push(new Building(i + 1, heights[i]));
        }

        // 오른쪽에서 볼 수 있는 건물 계산
        stack.clear();
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek().height <= heights[i]) {
                stack.pop();
            }

            // 스택 전체 순회
            // for (Building b : stack) {
            // visible[i].add(b.index);
            // }

            // 마찬가지로 peek만 사용
            if (!stack.isEmpty()) {
                count[i] += stack.size();

                int candidate = stack.peek().index;
                // 기존보다 가깝거나, 같으면 번호가 더 작은 쪽으로 선택
                if (closest[i] == 0 ||
                        Math.abs(candidate - (i + 1)) < Math.abs(closest[i] - (i + 1)) ||
                        (Math.abs(candidate - (i + 1)) == Math.abs(closest[i] - (i + 1)) && candidate < closest[i])) {
                    closest[i] = candidate;
                }
            }

            stack.push(new Building(i + 1, heights[i]));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(count[i]);
            if (count[i] > 0) {
                sb.append(" ").append(closest[i]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

import java.io.*;
import java.util.*;

/*
    사대의 수 M (1 ≤ M ≤ 100,000),
    동물의 수 N (1 ≤ N ≤ 100,000),
    사정거리 L (1 ≤ L ≤ 1,000,000,000)

    사대의 위치 xi와 동물의 위치 (aj, bj) 간의 거리는 |xi-aj| + bj로 계산
*/
public class BOJ_8983_사냥꾼 {
    static class Animal {
        int y, x;
        Animal(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static int[] arr; // 사대 위치 배열
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken()); // 사대의 수
        int N = Integer.parseInt(st.nextToken()); // 동물의 수
        int L = Integer.parseInt(st.nextToken()); // 사정거리

        arr = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        Animal[] animals = new Animal[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            animals[i] = new Animal(y, x);
        }

        // 각 동물마다 가장 가까운 사대 찾기
        for (Animal a : animals) {
            int pos = bs(a.x);

            boolean flag = false;

            // 왼쪽 사대 확인
            if (pos > 0) {
                int distance = Math.abs(arr[pos - 1] - a.x) + a.y;
                if (distance <= L)
                    flag = true;
            }

            // 오른쪽 사대 확인
            if (!flag && pos < arr.length) {
                int distance = Math.abs(arr[pos] - a.x) + a.y;
                if (distance <= L)
                    flag = true;
            }

            if (flag) cnt++;
        }

        System.out.println(cnt);
    }

    // Upper Bound
    static int bs (int target) {
        int l = 0;
        int r = arr.length;

        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] <= target)
                l = mid + 1;
            else
                r = mid;
        }

        return l;
    }
}
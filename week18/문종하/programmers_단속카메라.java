import java.util.Arrays;

class Solution {
    // routes: 차량의 이동 경로 배열 [진입 지점, 진출 지점]
    static int[][] routes = { { -5, 3 }, { -3, 0 }, { 0, 4 }, { 2, 5 } };

    public static void main(String[] args) {
        // 필요한 카메라의 최소 개수
        int ans = 1;

        // 진출 지점을 기준으로 오름차순 정렬
        // 이렇게 하면 가장 빨리 끝나는 차량부터 처리할 수 있음
        Arrays.sort(routes, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        // 첫 번째 차량의 진출 지점을 카메라 설치 지점으로 설정
        int point = routes[0][1];

        // 모든 차량의 경로를 순회
        for (int i = 1; i < routes.length; i++) {
            // 현재 차량의 진입 지점이 이전 카메라 설치 지점보다 나중이면
            // 새로운 카메라를 설치해야 함
            if (routes[i][0] > point) {
                point = routes[i][1];  // 새로운 카메라 설치 지점 갱신
                ans++;                 // 카메라 개수 증가
            }
            // 현재 차량의 진입 지점이 이전 카메라 설치 지점보다 이전이면
            // 기존 카메라로 해당 차량을 촬영할 수 있음
        }

        System.out.println(ans);
    }
}
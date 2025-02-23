package week18.강수진;


/*
정확성  테스트
테스트 1 〉	통과 (0.66ms, 87MB)
테스트 2 〉	통과 (0.79ms, 87.3MB)
테스트 3 〉	통과 (0.70ms, 75.6MB)
테스트 4 〉	통과 (0.57ms, 73.3MB)
테스트 5 〉	통과 (0.52ms, 83.4MB)
효율성  테스트
테스트 1 〉	통과 (3.67ms, 55.7MB)
테스트 2 〉	통과 (1.84ms, 54.5MB)
테스트 3 〉	통과 (7.77ms, 58.5MB)
테스트 4 〉	통과 (0.93ms, 54.1MB)
테스트 5 〉	통과 (8.81ms, 55.4MB)
 */

import java.util.Arrays;

public class programmers_단속카메라 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        int end = routes[0][1];
        int camera = 1;

        for(int i = 1; i < routes.length; i++){
            if(routes[i][0] > end) {
                end = routes[i][1];
                camera++;
            }
        }
        return camera;
    }
}

import java.util.Arrays;
// 정확성 worst (1.01ms, 85.6MB) 효율성 worst (6.62ms, 60.9MB)
public class programmers_42884_단속카메라 {
    class Solution {
        public int solution(int[][] routes) {
            Arrays.sort(routes, (a,b) -> Integer.compare(a[1], b[1]));
            int curCam = routes[0][1];
            int camCnt = 1;
            for(int[] route : routes) {
                if(curCam < route[0] ) {
                    camCnt++;
                    curCam = route[1];
                }
            }
            return camCnt;
        }
    }
}
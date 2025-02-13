package week19.강수진;


/*
정확성  테스트
테스트 1 〉	통과 (0.03ms, 80.3MB)
테스트 2 〉	통과 (0.03ms, 75.7MB)
테스트 3 〉	통과 (0.11ms, 76.9MB)
테스트 4 〉	통과 (0.10ms, 84.1MB)
테스트 5 〉	통과 (0.15ms, 73.4MB)
테스트 6 〉	통과 (0.23ms, 92MB)
테스트 7 〉	통과 (0.50ms, 80.3MB)
테스트 8 〉	통과 (0.70ms, 90.5MB)
테스트 9 〉	통과 (0.93ms, 82.9MB)
테스트 10 〉	통과 (0.83ms, 88.6MB)
효율성  테스트
테스트 1 〉	통과 (51.45ms, 212MB)
테스트 2 〉	통과 (46.71ms, 213MB)
테스트 3 〉	통과 (48.85ms, 216MB)
테스트 4 〉	통과 (48.63ms, 212MB)
테스트 5 〉	통과 (44.01ms, 217MB)
테스트 6 〉	통과 (43.14ms, 215MB)
테스트 7 〉	통과 (43.52ms, 220MB)
 */

public class programmers_파괴되지않은건물 {
    public int solution(int[][] board, int[][] skill) {
        int n = skill.length;
        int r = board.length;
        int c = board[0].length;
        int[][] sumMap = new int[r+1][c+1];
        for(int i = 0; i < n; i++){
            int degree = skill[i][5];
            if(skill[i][0] == 1) degree *= -1;
            sumMap[skill[i][1]][skill[i][2]] += degree;
            sumMap[skill[i][3]+1][skill[i][2]] -= degree;
            sumMap[skill[i][1]][skill[i][4]+1] -= degree;
            sumMap[skill[i][3]+1][skill[i][4]+1] += degree;
        }
        for(int i = 0; i < r + 1; i++) {
            for(int j = 1; j < c + 1; j++) {
                sumMap[i][j] += sumMap[i][j-1];
            }
        }
        for(int j = 0; j < c + 1; j++) {
            for(int i = 1; i < r + 1; i++) {
                sumMap[i][j] += sumMap[i-1][j];
            }
        }

        int building = 0;
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(board[i][j] + sumMap[i][j] > 0) building++;
            }
        }

        return building;
    }
}

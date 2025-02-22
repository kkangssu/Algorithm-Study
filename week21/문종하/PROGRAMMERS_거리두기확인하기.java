package study;

import java.util.Arrays;

public class PROGRAMMERS_거리두기확인하기 {

    static char[][] board;
    static char[] pg;
    
    class Solution {
        public int[] solution(String[][] places) {
            int[] answer = new int[5];
            
            int cnt = 0;
            for (String[] s : places) {
                //파티션
                board = new char[5][5];
                // 최대 13명            	
                pg = new char[25];
                
                int idx = 0;
                
                //대기실 정리
                for (int i = 0; i < s.length; i++) {
                    char[] c = s[i].toCharArray();
                    board[i] = c;
                    for(char cc : c) {
                        if(cc=='P') {
                            pg[idx++]=cc;
                        }
                    }
                    if(idx == 14) break;
                }
                
                if(idx == 14 || check()) {
                    answer[cnt++]=0;
                } else {
                    answer[cnt++]=1;
                }
            }
            
            return answer;
        }
    }
    
    static boolean check() {
        // 모든 위치에서 맨하튼 거리 2 이하 확인
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == 'P') {
                    // 거리가 1인 상하좌우
                    if (i > 0 && board[i-1][j] == 'P') return true;
                    if (i < 4 && board[i+1][j] == 'P') return true;
                    if (j > 0 && board[i][j-1] == 'P') return true;
                    if (j < 4 && board[i][j+1] == 'P') return true;
                    
                    // 거리가 2인 경우
                    // 상하좌우
                    if (i > 1 && board[i-2][j] == 'P' && board[i-1][j] == 'O') return true;
                    if (i < 3 && board[i+2][j] == 'P' && board[i+1][j] == 'O') return true;
                    if (j > 1 && board[i][j-2] == 'P' && board[i][j-1] == 'O') return true;
                    if (j < 3 && board[i][j+2] == 'P' && board[i][j+1] == 'O') return true;
                    
                    // 대각선
                    if (i > 0 && j > 0 && board[i-1][j-1] == 'P' &&
                        (board[i-1][j] == 'O' || board[i][j-1] == 'O')) return true;
                    if (i > 0 && j < 4 && board[i-1][j+1] == 'P' &&
                        (board[i-1][j] == 'O' || board[i][j+1] == 'O')) return true;
                    if (i < 4 && j > 0 && board[i+1][j-1] == 'P' &&
                        (board[i+1][j] == 'O' || board[i][j-1] == 'O')) return true;
                    if (i < 4 && j < 4 && board[i+1][j+1] == 'P' &&
                        (board[i+1][j] == 'O' || board[i][j+1] == 'O')) return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        // 외부 클래스의 인스턴스 생성
        PROGRAMMERS_거리두기확인하기 outer = new PROGRAMMERS_거리두기확인하기();
        // 내부 클래스의 인스턴스 생성
        Solution sol = outer.new Solution();
        
        // 테스트 케이스
        String[][] places = {
            {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
            {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
            {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
            {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
            {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };
        
        // 결과 출력
        int[] result = sol.solution(places);
        System.out.println("Expected: [1, 0, 1, 1, 1]");
        System.out.println("Result: " + Arrays.toString(result));
    }
}
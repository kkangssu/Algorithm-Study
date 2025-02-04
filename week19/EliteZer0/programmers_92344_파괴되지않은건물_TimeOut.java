class programmers_92344_파괴되지않은건물_TimeOut {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        for(int i = 0; i < skill.length; i++){
            for(int j = skill[i][1]; j <= skill[i][3]; j++){
                for (int k = skill[i][2]; k <= skill[i][4]; k++) {
                    if(skill[i][0] == 1) {
                        board[j][k] -= skill[i][5];
                    } else {
                        board[j][k] += skill[i][5];
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] > 0){
                    answer++;
                }
            }
        }
        return answer;
    }
}
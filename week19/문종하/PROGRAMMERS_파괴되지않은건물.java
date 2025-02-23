package bbb;

// 일반적인 방법 250,000,000,000
// 250,000 + 2,000,000
class PROGRAMMERS_파괴되지않은건물 {
    static int[][] board = {
        {5, 5, 5, 5, 5},
        {5, 5, 5, 5, 5},
        {5, 5, 5, 5, 5},
        {5, 5, 5, 5, 5}
    };
    
    // [type, r1, c1, r2, c2, degree]
    static int[][] skill = {
	    {1, 0, 0, 3, 4, 4}, // Attack: Decrease durability
	    {1, 2, 0, 2, 3, 2}, 
	    {2, 1, 0, 3, 1, 2}, // Heal: Increase durability
	    {1, 0, 1, 3, 3, 1}
    };
    
	static class Magic {
		int r1, r2, c1, c2, p;
		public Magic(int[] sk) {
			r1 = sk[1];
			r2 = sk[3];
			c1 = sk[2];
			c2 = sk[4];
			p = sk[0] == 1?-sk[5]:sk[5];
		}
	}
    
	public static void main(String[] args) {
	    int ans = 0;
		
		int N = board.length, M = board[0].length;
		int [][] nb = new int[N+1][M+1];
		
		int cnt = skill.length;
		
		for (int i = 0; i < cnt; i++) {
	        Magic m = new Magic(skill[i]);
	        nb[m.r1][m.c1]+=m.p;
	        nb[m.r1][m.c2+1]-=m.p;
	        nb[m.r2+1][m.c1]-=m.p;
	        nb[m.r2+1][m.c2+1]+=m.p;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				nb[i][j+1] +=nb[i][j];
			}
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				nb[j+1][i] +=nb[j][i];
				if(board[j][i]+nb[j][i]>=1)ans++;
			}
		}
		
		System.out.println(ans);
	}
}

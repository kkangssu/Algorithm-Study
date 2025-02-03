package ccc;

class PROGRAMMERS_가장긴팰린드롬 {
	   static String s = "abcdcba"; 

	   public static void main(String[] args) {
	       int ans = 1;  // 최소 팰린드롬 길이는 1
	       int N = s.length();
	       char[] c = s.toCharArray();
	       boolean[][] dp = new boolean[N][N];  // dp[i][j]: i~j 구간이 팰린드롬인지 여부

	       // 길이 1인 팰린드롬 초기화
	       for (int i = 0; i < N; i++) {
	           dp[i][i] = true;
	       }

	       // 길이 2인 팰린드롬 체크
	       for (int i = 0; i < N - 1; i++) {
	           if (c[i] == c[i + 1]) {
	               dp[i][i + 1] = true;
	               ans = 2;
	           }
	       }

	       // 길이 3 이상인 팰린드롬 체크
	       for (int len = 3; len <= N; len++) {
	           for (int i = 0; i <= N - len; i++) {
	               int j = i + len - 1;
	               // 양 끝 문자가 같고 그 사이가 팰린드롬이면
	               if (dp[i + 1][j - 1] && c[i] == c[j]) {
	                   dp[i][j] = true;
	                   ans = len;  // 현재까지의 최장 팰린드롬 길이 갱신
	               }
	           }
	       }

	       System.out.println(ans);
	   }
	}

//ps. Manacher's 알고리즘 알아보기
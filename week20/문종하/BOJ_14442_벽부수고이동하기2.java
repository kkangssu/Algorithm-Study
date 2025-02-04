package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 벽 
// 보드 
// 움직임 기록
public class BOJ_14442_벽부수고이동하기2 {
	static int N, M, K, ans, N1, M1;
	static int[][] board;
	static boolean[][] isWall;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		N1 = N - 1;
		M1 = M - 1;

		isWall = new boolean[N][M];
		board = new int[N][M];

		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (c[j] == '1')
					isWall[i][j] = true;
			}
		}

		bfs();

		System.out.println(ans);
	}

	static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { 0, 0, 0, 0 });
		ans = -1;

		while (!q.isEmpty()) {
			int[] info = q.poll();
			int r = info[0];
			int c = info[1];
			int k = info[2];
			int m = info[3];

			if (r == N1 && c == M1) {
				ans = m + 1;
				return;
			}

			for (int i = 0; i < 4; i++) {
				int rr = r + dr[i];
				int cc = c + dc[i];
				if (check(rr, cc))
					continue;
				if (board[rr][cc] != 0)
					continue;
				if (isWall[rr][cc]) {
					if (k+1 < K) {
						q.offer(new int[] { rr, cc, k + 1, m + 1 });
						board[rr][cc]=k+1;
					}
					else
						continue;
				} else {
					q.offer(new int[] { rr, cc, k, m + 1 });
					board[rr][cc]=k;
				}
				board[rr][cc] = k;
			}
		}

	}

	static boolean check(int r, int c) {
		return r >= N || r < 0 || c >= M || c < 0;
	}
}

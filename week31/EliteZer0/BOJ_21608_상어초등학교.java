import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];
		int[] students = new int[N * N];
		Map<Integer, boolean[]> preferences = new HashMap<>(); // 각 학생의 좋아하는 친구 정보를 저장

		// 학생 정보 입력 받기
		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			students[i] = student;
			boolean[] prefs = new boolean[N * N + 1]; // 1번부터 N*N번 학생까지
			for (int j = 0; j < 4; j++) {
				prefs[Integer.parseInt(st.nextToken())] = true;
			}
			preferences.put(student, prefs);
		}
        
    // 상, 우, 하, 좌
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };
		
    // 모든 학생에 대해 자리를 배정
    for (int student : students) {
			int maxLike = -1;
			int maxEmpty = -1;
			int bestX = -1;
      int bestY = -1;

			// 교실 전체를 돌며 최적의 자리 찾기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > 0) continue; // 이미 누군가 앉은 자리이면 패스

					int likeCount = 0, emptyCount = 0;
					// 4방 탐색
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
            int ny = j + dy[d];
            
						if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

						if (map[nx][ny] == 0) emptyCount++; // 인접 칸이 비었으면 빈칸 수 증가
						else if (preferences.get(student)[map[nx][ny]]) likeCount++; // 좋아하는 친구면 카운트 증가
					}

					// 자리 선정 조건 비교 및 갱신
          // 1단계: 좋아하는 학생 수가 더 많은가?
          // 2단계: 좋아하는 친구 수는 같은데, 인접한 빈칸 수가 더 많은가?
          // 3단계: 친구 수도 같고, 빈칸 수도 같다면? → 행 번호가 작은 칸, 그다음 열 번호가 작은 칸
					if (likeCount > maxLike || 
					   (likeCount == maxLike && emptyCount > maxEmpty) || 
					   (likeCount == maxLike && emptyCount == maxEmpty && (i < bestX || (i == bestX && j < bestY)))) {
						maxLike = likeCount;
						maxEmpty = emptyCount;
						bestX = i;
						bestY = j;
					}
				}
			}
			map[bestX][bestY] = student; // 최적 자리에 현재 학생 배치
		}

		// 만족도 계산
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int student = map[i][j];
				int likeCount = 0;
				// 4방 탐색으로 좋아하는 친구 수 카운트
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d], ny = j + dy[d];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					if (preferences.get(student)[map[nx][ny]]) likeCount++;
				}
				// 만족도 점수 더하기
				if (likeCount > 0)
					sum += (int)Math.pow(10, likeCount - 1); // 1명: 1점, 2명: 10점, 3명: 100점, 4명: 1000점
			}
		}
        
		System.out.println(sum);
	}
}

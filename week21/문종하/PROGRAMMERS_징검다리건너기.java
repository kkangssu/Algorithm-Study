package study;

public class PROGRAMMERS_징검다리건너기 {
	public static void main(String[] args) {
		Solution sol = new Solution();

		// 테스트 케이스
		int[] stones = { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 };
		int k = 3;

		// 결과 출력
		int result = sol.solution(stones, k);
		System.out.println("Expected: 3");
		System.out.println("Result: " + result);
	}
}

class Solution {
	public int solution(int[] stones, int k) {
		// 이진 탐색
		int answer = 0;
		int l = 1;
		// 돌의 최소, 최대
		int r = stones[0];
		for (int stone : stones) {
			r = Math.max(r, stone);
		}

		while (l <= r) {
			int cnt = 0; // 건너지 못하는 연속된 횟수
			int mid = (l + r) / 2;

			// stones 확인
			for (int s : stones) {
				if (s - mid <= 0) {
					cnt++;
					if (cnt >= k) { // 건너지 못하는 연속된 횟수가 k명 이상이면
						break;
					}
				} else { // 연속 끊김!
					cnt = 0;
				}
			}

			if (cnt >= k) {
				r = mid - 1; // 돌 줄여보기
			} else {
				l = mid + 1; // 돌 늘려보기
				answer = l;
			}
		}

		return answer;
	}
}

//class Solution {
//    public int solution(int[] stones, int k) {
//        int answer = 0;
//        
//        int result = parametric(stones, k);
//
//        return result;
//    }
//
//
//	static int parametric(int[] stones, int k) {
//		int l = 1;
//		int r = 10;
//      
//		while (l < r) {
//			int mid = (l + r) / 2;
//          
//			if (check(mid, stones, k)) {
//				r = mid;
//			} else {
//				l = mid+1;
//			}
//		}
//
//		return l;
//	}
//
//	static boolean check(int val, int[] stones, int k) {
//		int cnt = 0;
//		for (int i : stones) {
//			if (i < val) {
//				cnt++;
//			}else {
//				cnt = 0;
//			}
//			if(cnt>k) {
//				return true;
//			}
//		}
//
//		return false;
//	}
//}
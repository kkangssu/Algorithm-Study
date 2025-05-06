import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
    static int N, score;
    static int[][] classroom;
    static Map<Integer, int[]> map;
    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {-1, 0, 0, 1};

    static class Seat {
        int y, x, emptyCnt, friendCnt;

        public Seat(int y, int x, int emptyCnt, int friendsCnt) {
            this.x = x;
            this.y = y;
            this.emptyCnt = emptyCnt;
            this.friendCnt = friendsCnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        makeset(); // 초기화

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());

            int student = Integer.parseInt(st.nextToken());
            int[] likeFriends = new int[4];
            for (int j = 0; j < 4; j++) {
                likeFriends[j] = Integer.parseInt(st.nextToken());
            }

            map.put(student, likeFriends); // 학생 - 좋아하는 친구 등록
            putStudentSeat(student); // 자리 배치
        }

        getScore(); // 만족도 계산
        System.out.println(score);
    }

    static void makeset() {
        classroom = new int[N][N];
        map = new HashMap<>();
    }

    static void putStudentSeat(int student) {
        int[] friends = map.get(student);
        ArrayList<Seat> seats = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (classroom[i][j] != 0) continue; // 이미 배치된 자리 skip

                int friendCnt = 0;
                int emptyCnt = 0;

                for (int d = 0; d < 4; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];

                    if (!check(ny, nx)) continue;

                    for (int f = 0; f < 4; f++) {
                        if (classroom[ny][nx] == friends[f]) friendCnt++;
                    }

                    if (classroom[ny][nx] == 0) emptyCnt++;
                }

                seats.add(new Seat(i, j, emptyCnt, friendCnt));
            }
        }

        // 조건 우선순위대로 정렬
        seats.sort((s1, s2) -> {
            if (s1.friendCnt == s2.friendCnt) {
                if (s1.emptyCnt == s2.emptyCnt) {
                    if (s1.y == s2.y) return s1.x - s2.x;
                    return s1.y - s2.y;
                }
                return s2.emptyCnt - s1.emptyCnt;
            }
            return s2.friendCnt - s1.friendCnt;
        });

        // 첫 번째 적합한 자리에 배치
        for (Seat seat : seats) {
            if (classroom[seat.y][seat.x] != 0) continue;
            classroom[seat.y][seat.x] = student;
            return;
        }
    }

    static void getScore() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                int[] friends = map.get(classroom[i][j]);

                for (int d = 0; d < 4; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];

                    if (!check(ny, nx)) continue;

                    for (int f = 0; f < 4; f++) {
                        if (classroom[ny][nx] == friends[f]) cnt++;
                    }
                }

                // 만족도 누적
                switch (cnt) {
                    case 1: score += 1; break;
                    case 2: score += 10; break;
                    case 3: score += 100; break;
                    case 4: score += 1000; break;
                }
            }
        }
    }

    static boolean check(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}

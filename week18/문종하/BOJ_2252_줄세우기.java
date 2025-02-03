import java.io.*;
import java.util.*;

class Main {
    // N: 학생 수, M: 키 비교 횟수
    static int N, M, ans;
    // cnt: 각 노드로 들어오는 간선의 개수(진입차수)를 저장하는 배열
    static int[] cnt;
    // adj: 인접 리스트로 그래프 표현 (각 학생별 자신보다 키가 큰 학생들의 리스트)
    static List<Integer> adj[];
    // q: 위상 정렬에 사용할 큐 (진입차수가 0인 노드들을 저장)
    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        
        // 학생 수와 키 비교 횟수 입력
        N = readInt();
        M = readInt();
        
        // 진입차수 배열과 인접 리스트 초기화
        cnt = new int[N+1];
        adj = new ArrayList[N+1];
        for (int i = 1; i <= N; i++)
            adj[i] = new ArrayList<>();
        
        // 키 비교 정보 입력
        // in: 앞에 서야 하는 학생, out: 뒤에 서야 하는 학생
        for (int i = 0; i < M; i++) {
            int in = readInt();
            int out = readInt();
            cnt[out]++; // out 학생의 진입차수 증가
            adj[in].add(out); // in 학생 다음에 out 학생이 와야 함
        }
        
        // 진입차수가 0인 노드(가장 앞에 올 수 있는 학생들)를 큐에 삽입
        for (int i = 1; i <= N; i++)
            if(cnt[i]==0) q.offer(i);

        // 위상 정렬 수행
        while(!q.isEmpty()) {
            int cur = q.poll(); // 현재 처리할 학생
            sb.append(cur).append(" "); // 현재 학생을 줄 세우기 결과에 추가
            
            // 현재 학생 다음에 올 수 있는 학생들의 진입차수를 감소
            // 진입차수가 0이 되면 큐에 추가 (해당 학생 앞의 모든 학생이 줄을 섰다는 의미)
            for (int i : adj[cur])
                if(--cnt[i] == 0)
                    q.offer(i);
        }
        
        // 결과 출력
        System.out.println(sb);
    }

    // 입력 최적화를 위한 커스텀 readInt 메소드
    static int readInt() throws IOException {
        int result = 0;
        int read = System.in.read();
        
        // 숫자가 아닌 문자는 건너뛰기
        while(read < '0' || read>'9') read = System.in.read();
        
        // 숫자를 읽어서 정수로 변환
        while(read>='0' && read<='9') {
            result = result * 10 + read - '0';
            read = System.in.read();
        }
        
        return result;
    }
}
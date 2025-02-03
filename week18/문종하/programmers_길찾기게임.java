import java.io.*;
import java.util.*;
/*
	tree 만들고 정답 배열 만드는 방식
	
	1. 배열을 노드배열로 만들기
	2. 배열 y좌표, x좌료 순으로 정렬
	3. 높이 바뀔 때마다
*/
class Solution {
    // nodeinfo: 각 노드의 x, y 좌표 정보
    static int[][] nodeinfo = {{5, 4}, {3, 3}, {2, 2}, {1, 1}};
    
    // 트리의 노드를 표현하는 클래스
    static class Node {
        Node l, r;          // 왼쪽, 오른쪽 자식 노드
        int n;             // 노드 번호
        int h;             // y좌표 (높이)
        int w;             // x좌표 (너비)
        int max, min;      // 현재 노드가 가질 수 있는 x좌표의 범위 (min < x < max)
        
        public Node(int n, int h, int w, int max, int min) {
            this.n = n;
            this.h = h;
            this.w = w;
            this.max = max;
            this.min = min;
        }
    }
    
    static Node root;      // 트리의 루트 노드
    static int idx;        // 순회 결과를 저장할 때 사용하는 인덱스
    static int[][] ans;    // 전위/후위 순회 결과를 저장할 배열
    
    public static void main(String[] args) {
        ans = new int[2][nodeinfo.length];  // [0]: 전위 순회, [1]: 후위 순회 결과
        
        // 트리 구성 시 사용할 노드들을 임시 저장하는 큐
        Queue<Node> q = new ArrayDeque<>();
        
        // nodeinfo를 처리하기 쉽게 3차원 배열로 변환 [x좌표, y좌표, 노드번호]
        int arr[][] = new int[nodeinfo.length][3];
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(nodeinfo[i], 0, arr[i], 0, 2);
            arr[i][2] = i+1;  // 노드 번호는 1부터 시작
        }
        
        // y좌표 내림차순, y좌표가 같으면 x좌표 오름차순으로 정렬
        Arrays.sort(arr, (o1,o2) -> o2[1]-o1[1] == 0 ? o1[0]-o2[0] : o2[1]-o1[1]);
        
        // 루트 노드 생성 (가장 높은 y좌표를 가진 노드)
        root = new Node(arr[0][2], arr[0][1], arr[0][0], 100_001, -1);
        
        Node cur = root;
        
        // 나머지 노드들을 트리에 추가
        for (int i = 1; i < arr.length; i++) {
            int h = arr[i][1];  // y좌표
            int w = arr[i][0];  // x좌표
            int n = arr[i][2];  // 노드 번호
            
            // 현재 노드의 왼쪽 자식이 될 수 있는 경우
            if (cur.w > w && cur.min < w) {
                if(cur.l == null) {
                    cur.l = new Node(n, h, w, cur.w, cur.min);
                    q.offer(cur.l);
                } else {
                    cur = q.poll();
                    i--;
                }
            }
            // 현재 노드의 오른쪽 자식이 될 수 있는 경우
            else if (cur.w < w && cur.max > w) {
                if(cur.r == null) {
                    cur.r = new Node(n, h, w, cur.max, cur.w);
                    q.offer(cur.r);
                    cur = q.poll();
                } else {
                    cur = q.poll();
                    i--;
                }
            }
            // 현재 노드의 자식이 될 수 없는 경우
            else {
                cur = q.poll();
                i--;
            }
        }
        
        // 전위 순회 수행
        preOrder(root);
        idx = 0;
        // 후위 순회 수행
        postOrder(root);
    }
    
    // 전위 순회: 루트 -> 왼쪽 -> 오른쪽
    static void preOrder(Node n) {
        if(n == null) return;
        ans[0][idx++] = n.n;
        preOrder(n.l);
        preOrder(n.r);
    }
    
    // 후위 순회: 왼쪽 -> 오른쪽 -> 루트
    static void postOrder(Node n) {
        if(n == null) return;
        postOrder(n.l);
        postOrder(n.r);
        ans[1][idx++] = n.n;
    }
}
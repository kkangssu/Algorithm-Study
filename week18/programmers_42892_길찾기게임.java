import java.util.Arrays;
import java.util.Comparator;
// 정확성 worst (19.80ms, 99.4MB)
public class programmers_42892_길찾기게임 {
    class Node {
        int x, y, value;
        Node left, right;
        public Node(int x, int y, int value, Node left, Node right) {
            this.x = x;
            this.y = y;
            this.value = value; // 노드 번호
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        private int[][] answer;
        private int preIdx = 0;
        private int postIdx = 0;

        public int[][] solution(int[][] nodeinfo) {
            Node[] node = new Node[nodeinfo.length];
            for (int i = 0; i < nodeinfo.length; i++) {
                // 노드 번호는 1부터 시작
                node[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1, null, null);
            }

            Arrays.sort(node, new Comparator<Node>() {
                @Override
                // y 좌표가 같으면 x가 작은 순으로 정렬
                // 아니면 y가 큰 값으로 정렬
                public int compare(Node o1, Node o2) {
                    if(o1.y == o2.y) return o1.x - o2.x;
                    else return o2.y - o1.y;
                }
            });

            Node root = node[0];
            for(int i = 1; i < node.length; i++) {
                addNode(root, node[i]);
            }
            answer = new int[2][node.length];
            int idx = 0;
            preOrder(root);
            postOrder(root);
            return answer;
        }

        private void postOrder(Node root) {
            if(root != null){
                postOrder(root.left);
                postOrder(root.right);
                answer[1][postIdx++] = root.value;
            }
        }

        private void preOrder(Node root) {
            if(root != null) {
                answer[0][preIdx++] = root.value;
                preOrder(root.left);
                preOrder(root.right);
            }
        }

        private void addNode(Node parent, Node child) {
            if(parent.x > child.x) {
                if(parent.left == null) {
                    parent.left = child;
                } else {
                    addNode(parent.left, child);
                }
            } else {
                if(parent.right == null) {
                    parent.right = child;
                } else {
                    addNode(parent.right, child);
                }
            }
        }
    }
}

package week18.강수진;

/*
정확성  테스트
테스트 1 〉	통과 (0.49ms, 72.7MB)
테스트 2 〉	통과 (0.40ms, 72.5MB)
테스트 3 〉	통과 (0.47ms, 83.4MB)
테스트 4 〉	통과 (0.49ms, 75.9MB)
테스트 5 〉	통과 (0.55ms, 84.9MB)
테스트 6 〉	통과 (7.52ms, 78.6MB)
테스트 7 〉	통과 (8.40ms, 86MB)
테스트 8 〉	통과 (8.10ms, 97.4MB)
테스트 9 〉	통과 (27.66ms, 108MB)
테스트 10 〉	통과 (3.52ms, 79.8MB)
테스트 11 〉	통과 (29.23ms, 104MB)
테스트 12 〉	통과 (41.63ms, 106MB)
테스트 13 〉	통과 (1.01ms, 88.8MB)
테스트 14 〉	통과 (2.70ms, 78.5MB)
테스트 15 〉	통과 (11.12ms, 101MB)
테스트 16 〉	통과 (12.07ms, 103MB)
테스트 17 〉	통과 (4.28ms, 89.7MB)
테스트 18 〉	통과 (18.13ms, 112MB)
테스트 19 〉	통과 (4.94ms, 89.7MB)
테스트 20 〉	통과 (6.08ms, 90.2MB)
테스트 21 〉	통과 (7.69ms, 105MB)
테스트 22 〉	통과 (12.68ms, 106MB)
테스트 23 〉	통과 (16.42ms, 108MB)
테스트 24 〉	통과 (0.49ms, 80.4MB)
테스트 25 〉	통과 (0.46ms, 83.5MB)
테스트 26 〉	통과 (9.98ms, 79.1MB)
테스트 27 〉	통과 (0.37ms, 84.5MB)
테스트 28 〉	통과 (0.36ms, 79.3MB)
테스트 29 〉	통과 (0.33ms, 74.6MB)
*/

import java.util.ArrayList;
import java.util.Collections;

public class programmers_길찾기게임 {
    static class Node implements Comparable<Node>{
        int num, x, y;
        Node left, right;
        Node(int num, int x, int y){
            this.num = num;
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Node other) {
            if(this.y == other.y) {
                return this.x - other.x;
            }
            return other.y - this.y;
        }
    }
    static ArrayList<Node> tree = new ArrayList<>();
    static int[][] answer;
    static int idx;

    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        answer = new int[2][n];
        for(int i = 0; i < n; i++){
            tree.add(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        Collections.sort(tree);
        Node root = tree.get(0);
        for(int i = 1; i < tree.size(); i++){
            binaryTree(root, tree.get(i));
        }

        preorder(root);
        idx = 0;
        postorder(root);


        return answer;
    }

    static void binaryTree(Node parent, Node child){
        if(child.x < parent.x) {
            if(parent.left == null) parent.left = child;
            else binaryTree(parent.left, child);
        }
        else {
            if(parent.right == null) parent.right = child;
            else binaryTree(parent.right, child);
        }
    }

    static void preorder(Node node){
        if(node == null) return;

        answer[0][idx++] = node.num;
        preorder(node.left);
        preorder(node.right);
    }

    static void postorder(Node node){
        if(node == null) return;

        postorder(node.left);
        postorder(node.right);
        answer[1][idx++] = node.num;
    }
}


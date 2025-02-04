import java.util.*;

class Solution {
    static class Node implements Comparable<Node> {
        int x,y,n;
        Node left, right;
        public Node(int x, int y, int n){
            this.x = x;
            this.y = y;
            this.n = n;
        }
        @Override
        public int compareTo(Node o){
            if(this.y != o.y){
                return o.y- this.y;
            }
            return this.x -o.x;
        }
    }
    static List<Integer> pre ;
    static List<Integer> post ;
    
    public int[][] solution(int[][] nodeinfo) {
        int N = nodeinfo.length;
        Node[] list = new Node[N];
        for(int i = 0 ; i<N ; i++){
            list[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1);
        }
        Arrays.sort(list);
        
        Node root = list[0];
        for(int i = 1; i < N; i++){
            addNode(root, list[i]);
        }
        pre = new ArrayList<>();
        post = new ArrayList<>();
        preorder(root);
        postorder(root);
        
        int[] preorder = new int[pre.size()];
        int[] postorder = new int[post.size()];
    
        for(int i = 0; i < pre.size(); i++) {
            preorder[i] = pre.get(i);
        }
        for(int i = 0; i < post.size(); i++) {
            postorder[i] = post.get(i);
        }
   
        return new int[][] {preorder, postorder};
    }
    
    void addNode(Node p, Node c){
        if(p.x > c.x){ // 자식이 왼쪽
            if(p.left == null){
            p.left = c;
            }else{
                addNode(p.left,c);
            }
        }else{
            if(p.right == null){
                p.right = c;    
            }else{
                addNode(p.right, c);
            }
        }
    }
    void preorder(Node n){
        if(n!=null){
            pre.add(n.n);
            preorder(n.left);
            preorder(n.right);
        }
    }
    void postorder(Node n){
        if(n!=null){
            postorder(n.left);
            postorder(n.right);
            post.add(n.n);
        }
    }
}
import java.io.*;
import java.util.*;

class Main {
    public static int N;

    public static class Node {
        Map<Character, Node> child = new HashMap<>();
        int isEnd = 0;
    }

    public static class Trie {
        Node root = new Node();

        public String insert(String in) {
            Node node = root;
            StringBuilder result = new StringBuilder();
            boolean endFlag = false;
            for (int i = 0; i < in.length(); i++) {
                if (node.child.get(in.charAt(i)) != null) {
                    node = node.child.get(in.charAt(i));
                    result.append(in.charAt(i));
                }
                else {
                    Node next = new Node();
                    node.child.put(in.charAt(i), next);
                    node = next;
                    if (!endFlag) {
                        result.append(in.charAt(i));
                        endFlag = true;
                    }
                }
            }
            if (node.isEnd == 0) {
                node.isEnd = 1;
                return result.toString();
            }
            else {
                node.isEnd += 1;
                return result.append(node.isEnd).toString();
            }


        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Trie trie = new Trie();
        N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            String res = trie.insert(br.readLine());
            sb.append(res).append("\n");
        }
        System.out.println(sb.toString());
    }
}

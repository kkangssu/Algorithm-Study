import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        // "::" 기준으로 주소를 나눔
        String[] parts = input.split("::", -1);
        List<String> blocks = new ArrayList<>();

        // "::" 왼쪽 부분 처리
        if (!parts[0].isEmpty()) {
            for (String part : parts[0].split(":")) {
                blocks.add(pad(part));  // 4자리 0으로 패딩 후 추가
            }
        }

        // "::" 오른쪽 부분 처리
        List<String> right = new ArrayList<>();
        if (parts.length > 1 && !parts[1].isEmpty()) {
            for (String part : parts[1].split(":")) {
                right.add(pad(part));  // 4자리 0으로 패딩 후 오른쪽 리스트에 저장
            }
        }

        // 총 블록 수가 8개가 되도록 중간에 "0000"을 채워줌
        while (blocks.size() + right.size() < 8) {
            blocks.add("0000");
        }

        // 오른쪽 블록들을 왼쪽 리스트에 이어 붙임
        blocks.addAll(right);

        System.out.println(String.join(":", blocks));
    }

    static String pad(String s) {
        return String.format("%4s", s).replace(' ', '0');
    }
}

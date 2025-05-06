import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        String[] address = input.replace("::", ":zero:").split(":"); // ::에 표시
        for(int i=0; i < address.length; i++) {
            if(address[i].equals("zero")) {
                for(int j=0; j < 8 - address.length + 1; j++) sb.append("0000:"); // zero면 생략된 수 계산해서 0000: 추가
            } else
                sb.append(String.format("%4s", address[i]).replace(" ", "0")).append(":"); // 아니면 4자리 맞춰서 추가
        }
        sb.deleteCharAt(sb.length() - 1); // 맨 뒤 : 제거
        System.out.println(sb.toString());
    }
}

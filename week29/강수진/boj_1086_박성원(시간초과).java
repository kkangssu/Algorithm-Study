import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class Num{
        String num;  // 50자리 자연수가 들어 올 수 있어서 String으로 받음
        int l;
        Num(String num, int l){
            this.num = num;
            this.l = l;
        }
    }
    static long[][] dp;
    static Num[] nums;
    static int n, k;
    static long nu, de;    //분자, 분모
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new Num[n];
        for(int i = 0; i < n; i++){
            String s = br.readLine();
            nums[i] = new Num(s, s.length());
        }
        k = Integer.parseInt(br.readLine());

        // DP 배열 초기화: dp[사용한 숫자의 비트마스크][현재까지의 나머지] = 경우의 수
        dp = new long[1<<n][k];
        for(int i = 0; i < (1<<n); i++){
            Arrays.fill(dp[i], -1);
        }

        // 분모 계산: 전체 순열의 수 (n!)
        de = 1;
        for(int i = 1; i <= n; i++) {
            de *= i;
        }

        // 분자 계산: K로 나누어떨어지는 순열의 수
        nu = psw(0, 0);

        // 분수 약분 (최대공약수로 나누기)
        long gcd = gcd(nu, de);
        nu /= gcd;
        de /= gcd;
        
        System.out.println(nu + "/" + de);
    }

    // visited: 현재까지 사용한 숫자들의 비트마스크, remainder: 현재까지 만든 수의 k로 나눈 나머지
    static long psw(int visited, int remainder){
        // 모든 숫자를 사용했을 때
        if(visited == (1<<n)-1){
            return remainder == 0? 1 : 0;
        }

        // 이미 계산된 상태라면 저장된 값 반환: 메모이제이션
        if(dp[visited][remainder] != -1) return dp[visited][remainder];

        long result = 0;
        for(int i = 0; i < n; i++){
            if((visited & (1<<i)) != 0) continue;  // 이미 사용한 숫자 -> continue
            int nextRemainder = cal(remainder, i);  // 새로운 나머지 계산
            int nextVisited = (visited | (1<<i));  // 방문 처

            // 재귀 호출: 현재 숫자를 사용한 후 나머지 숫자들로 가능한 경우의 수 더하기
            result += psw(nextVisited, nextRemainder);
        }

        return dp[visited][remainder] = result;
    }

    // 현재 나머지에 idx번째 숫자를 이어붙였을 때의 새로운 나머지 계산
    static int cal(int remainder, int idx){        
        for(int i = 0; i < nums[idx].l; i++){
            remainder = (remainder*10) % k;
            remainder = (remainder + (nums[idx].num.charAt(i) - '0')) % k;
        }

        return remainder;
    }

    // 최대공약수(GCD) 계산 함수: 유클리드
    static long gcd(long n, long d){
        while(d != 0){
            long temp = n % d;
            n = d;
            d = temp;
        }
        return n;
    }
}

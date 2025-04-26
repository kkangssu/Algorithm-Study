import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
    최대 2명
    무인도에 갇힌 사람은 1명 이상 50,000명 이하입니다.
    각 사람의 몸무게는 40kg 이상 240kg 이하입니다.
    구명보트의 무게 제한은 40kg 이상 240kg 이하입니다.
    구명보트의 무게 제한 항상 몸무게  최댓값보다 큼

    x - 40 >= limit  그냥 answer + 1
    x1 + x2 같이 나가기
    남은 인원 더하기
*/
public class PROGRAMMERS_구명보트 {
    public static void main(String[] args) {
        // Test case 1
        int[] people1 = {70, 50, 80, 50};
        int limit1 = 100;
        Solution1 solution = new Solution1();
        int result1 = solution.solution(people1, limit1);
        System.out.println("Test Case 1 Result: " + result1);

        // Test case 2
        int[] people2 = {70, 80, 50};
        int limit2 = 100;
        int result2 = solution.solution(people2, limit2);
        System.out.println("Test Case 2 Result: " + result2);
    }
}

class Solution1 {
    static int [] p;
    static int answer;
    public int solution(int[] people, int limit) {

        // 정렬
        Arrays.sort(people);
        
        // x - 40 >= limit  그냥 answer + 1
        int idx = people.length-1;
        for (; idx >= 0; idx--) {
            if(people[idx]<limit) break;
        }
        p = Arrays.copyOfRange(people,0,idx+1);
        answer = people.length - 1 - idx;

        // x1 + x2 같이 나가기
        solve(0,p.length-1,limit);

        return answer;
    }
    static void solve(int l, int r, int limit){
        int ans = 0;
        while(l<r){
            int sum = p[l]+p[r];
            if(sum>limit){
                r--;
                ans++;
            }else{
                l++; r--;
                ans++;
            }
        }
        // 남은 인원 더하기
        if(l==r)ans++;
        answer += ans;
    }
}
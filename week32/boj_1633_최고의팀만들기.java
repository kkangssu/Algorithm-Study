package week32;

import java.util.*;
import java.io.*;

public class boj_1633_최고의팀만들기 {
    private static final int MAX_MEMBERS = 1000;
    private static final int MAX_TEAM_SIZE = 15;
    private static final int NOT_CALCULATED = -1;
    
    private static int[] whiteTeamScores;
    private static int[] blackTeamScores;
    private static int[][][] memo;
    private static int totalMembers;
    
    public static void main(String[] args) throws IOException {
        readMemberScores();
        initializeMemo();
        System.out.println(selectOptimalTeams(0, 0, 0));
    }
    
    private static void readMemberScores() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        whiteTeamScores = new int[MAX_MEMBERS];
        blackTeamScores = new int[MAX_MEMBERS];
        
        String line;
        while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
            StringTokenizer st = new StringTokenizer(line);
            if (st.countTokens() >= 2) {
                whiteTeamScores[totalMembers] = Integer.parseInt(st.nextToken());
                blackTeamScores[totalMembers] = Integer.parseInt(st.nextToken());
                totalMembers++;
            }
        }
        br.close();
    }
    
    private static void initializeMemo() {
        memo = new int[totalMembers + 1][MAX_TEAM_SIZE + 1][MAX_TEAM_SIZE + 1];
        for (int[][] twoDArray : memo) {
            for (int[] oneDArray : twoDArray) {
                Arrays.fill(oneDArray, NOT_CALCULATED);
            }
        }
    }
    
    private static int selectOptimalTeams(int memberIndex, int whiteTeamSize, int blackTeamSize) {
        // 기저 조건: 양팀 모두 15명씩 선택했거나 모든 팀원을 확인했을 때
        if ((whiteTeamSize == MAX_TEAM_SIZE && blackTeamSize == MAX_TEAM_SIZE) || memberIndex == totalMembers) {
            return 0;
        }
        
        // 메모이제이션 확인
        if (memo[memberIndex][whiteTeamSize][blackTeamSize] != NOT_CALCULATED) {
            return memo[memberIndex][whiteTeamSize][blackTeamSize];
        }
        
        // 현재 팀원을 선택하지 않는 경우
        int maxScore = selectOptimalTeams(memberIndex + 1, whiteTeamSize, blackTeamSize);
        
        // 화이트팀에 선택하는 경우 
        if (whiteTeamSize < MAX_TEAM_SIZE) {
            maxScore = Math.max(maxScore, 
                selectOptimalTeams(memberIndex + 1, whiteTeamSize + 1, blackTeamSize) + whiteTeamScores[memberIndex]);
        }
        
        // 블랙팀에 선택하는 경우
        if (blackTeamSize < MAX_TEAM_SIZE) {
            maxScore = Math.max(maxScore, 
                selectOptimalTeams(memberIndex + 1, whiteTeamSize, blackTeamSize + 1) + blackTeamScores[memberIndex]);
        }
        
        return memo[memberIndex][whiteTeamSize][blackTeamSize] = maxScore;
    }
}
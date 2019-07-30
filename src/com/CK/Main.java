package com.CK;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<String>(Arrays.asList("cats", "dog", "sand", "and", "cat"));

//        String s = "aaaaaaa";
//        List<String> wordDict = new ArrayList<String>(Arrays.asList("aaaa", "aaa"));
//        String s = "aaaaaaa";
//        List<String> wordDict = new ArrayList<String>(Arrays.asList("aaaa", "aa", "a"));
        Solution2 solution = new Solution2();
        System.out.println(solution.wordBreak(s, wordDict).toString());
    }
}

class Solution {
    List<String> res = new ArrayList<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) return res;
        String dfs = "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (wordDict.contains(s.substring(j, i + 1))) {
                    dp[j][i] = true;
                }
            }
        }
        helper(s, dfs, dp, 0);
        return res;
    }

    private void helper(String s, String dfs, boolean[][] dp, int pos) {
        if (pos == s.length()) {
            res.add(dfs.trim());
            return;
        }

        for (int i = pos; i < s.length(); i++) {
            if (dp[pos][i]) {
                int originalLength = dfs.length();
                dfs = dfs + s.substring(pos, i + 1) + " ";
                helper(s, dfs, dp, i + 1);
                dfs = dfs.substring(0, originalLength);
            }
        }
    }
}

// Memorized DFS
class Solution2 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
    }

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, List<String> wordDict, HashMap<String, LinkedList<String>> map) {
        if (map.containsKey(s))
            return map.get(s);

        LinkedList<String> res = new LinkedList<String>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }
}

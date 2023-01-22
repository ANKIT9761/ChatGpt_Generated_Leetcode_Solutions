// link- https://leetcode.com/problems/number-of-ways-to-separate-numbers/

/*

This solution uses dynamic programming to find the number of possible lists of integers that could have been written down to get the given string. The combinations variable is a 2D array that stores the number of ways to separate the number string up to the current index. The outer loop iterates through the characters in the number string. The base case is when there are no characters in the number string, in which case there is only one way to separate the numbers. If a substring is not a valid number, it doesn't add any ways to separate the numbers. If substring is a valid number, it adds the number of ways to separate the number string up to the (i-1)th digit or up to the (i-2)th digit to the number of ways to separate the number string up to the ith digit.

The solution uses two nested loops to check for the number of combinations in substring, first one checks for the long sequence of same characters and the second one checks for the number of digits in the substring. It also checks if the next substring of j digits is bigger or not and if it is bigger then it picks the continuation of j or j+1 digits.

This solution is more efficient because it checks for the long sequence of same characters and the number of digits in the substring and picks the continuation accordingly which reduces the overall time complexity of the solution.



*/

class Solution {
    public int numberOfCombinations(String num) {
        final int MOD = 1000000007;
        int n = num.length();
        int[][] combinations = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            if (num.charAt(i) == '0') {
                continue;
            }
            int end = i;
            while (end < n && num.charAt(end) == num.charAt(i)) {
                end++;
            }
            int currentCount = 1;
            combinations[i][n - i] = currentCount;
            for (int j = n - i - 1; j > 0; j--) {
                if (i + j + j > n) {
                    combinations[i][j] = currentCount;
                    continue;
                }
                int length;
                if (i + j + j <= end) {
                    length = j;
                } else {
                    length = 0;
                    while (length < j && num.charAt(i + length) == num.charAt(i + j + length)) {
                        length++;
                    }
                }
                if (length == j || num.charAt(i + length) < num.charAt(i + j + length)) {
                    currentCount = (currentCount + combinations[i + j][j]) % MOD;
                } else if (j < n) {
                    currentCount = (currentCount + combinations[i + j][j + 1]) % MOD;
                }
                combinations[i][j] = currentCount;
            }
        }
        return combinations[0][1];
    }
}

package com.phantomfury.leetcode.junechallenge;

import java.util.Arrays;

public class SurroundedRegions
{
    public void fill(char[][] board, int sr, int sc, char newVal, char originalVal)
    {
        if(sr < 0 || sc < 0 || sr >= board.length || sc >= board[0].length)
            return;
        if(board[sr][sc] != originalVal)
            return;

        board[sr][sc] = newVal;

        fill(board, sr - 1, sc, newVal, originalVal);
        fill(board, sr, sc + 1, newVal, originalVal);
        fill(board, sr + 1, sc, newVal, originalVal);
        fill(board, sr, sc - 1, newVal, originalVal);
        return;

    }

    public void solve(char[][] board) {
        int m = board.length;
        if(m == 0)
            return;
        int n = board[0].length;
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                if(board[i][j] == 'O')
                    board[i][j] = '$';

        for(int i = 0; i < n; i++)
        {
            fill(board, 0, i, 'O', '$');
            fill(board, m - 1, i, 'O', '$');
        }

        for(int i = 0; i < m; i++)
        {
            fill(board, i, 0, 'O', '$');
            fill(board, i, n - 1, 'O', '$');
        }

        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                if(board[i][j] == '$')
                    board[i][j] = 'X';
    }

    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        SurroundedRegions obj = new SurroundedRegions();
        obj.solve(board);
        System.out.println(Arrays.deepToString(board));
    }
}

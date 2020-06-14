package com.phantomfury.interviewbit.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ValidateSudoku {

    public boolean isRowValid(int [][] board)
    {
        HashSet<Integer> set = new HashSet<>();
        for(int row = 0; row < 9; row++)
        {
            for(int col = 0; col < 9; col++)
            {
                if(board[row][col] < 0 || board[row][col] > 9)
                    return false;

                if(board[row][col] != 0 && set.contains(board[row][col]))
                    return false;

                set.add(board[row][col]);
            }
            set.clear();
        }
        return true;
    }

    public boolean isColValid(int [][] board)
    {
        HashSet<Integer> set = new HashSet<>();
        for(int col = 0; col < 9; col++)
        {
            for(int row = 0; row < 9; row++)
            {
                if(board[row][col] < 0 || board[row][col] > 9)
                    return false;

                if(board[row][col] != 0 && set.contains(board[row][col]))
                    return false;

                set.add(board[row][col]);
            }
            set.clear();
        }
        return true;
    }

    public boolean isBlockValid(int blockRowStart, int blockColStart, int [][] board)
    {
        HashSet<Integer> set = new HashSet<>();
        for(int row = blockRowStart; row < blockRowStart + 3; row++)
        {
            for(int col = blockColStart; col < blockColStart + 3; col++)
            {
                if(board[row][col] < 0 || board[row][col] > 9)
                    return false;

                if(board[row][col] != 0 && set.contains(board[row][col]))
                    return false;
                set.add(board[row][col]);
            }
        }
        return true;
    }

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int isValidSudoku(final List<String> A) {
        int[][] board = new int[9][9];
        for(int i = 0; i < A.size(); i++)
        {
            String s = A.get(i);
            for(int j = 0; j < 9; j++)
                board[i][j] = s.charAt(j) == '.' ? 0 : Integer.parseInt(s.charAt(j) + "");
        }

        if(!isRowValid(board) || !isColValid(board))
            return 0;
        else
        {
            boolean flag = false;
            for(int i = 0; i < 9; i += 3)
            {
                for(int j = 0; j < 9; j += 3)
                {
                    if(!isBlockValid(i, j, board))
                    {
                        flag = true;
                        break;
                    }
                }
                if(flag)
                    break;
            }
            if(flag)
                return 0;
            else
                return 1;
        }
    }

    public static void main(String[] args)
    {
        String[] array = {"53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1", "7...2...6", ".6....28.", "...419..5", "....8..79"};
        List<String> boardString = Arrays.asList(array);

        ValidateSudoku obj = new ValidateSudoku();
        System.out.println(obj.isValidSudoku(boardString));
    }
}

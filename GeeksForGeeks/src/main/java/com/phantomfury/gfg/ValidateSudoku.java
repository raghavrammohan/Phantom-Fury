package com.phantomfury.gfg;

import java.util.HashSet;
import java.util.Scanner;

public class ValidateSudoku
{
    public static boolean isRowValid(int [][] board)
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

    public static boolean isColValid(int [][] board)
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

    public static boolean isBlockValid(int blockRowStart, int blockColStart, int [][] board)
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

    public static void main (String[] args) {

        // Sample Testcases
        /*
        2
        3 0 6 5 0 8 4 0 0 5 2 0 0 0 0 0 0 0 0 8 7 0 0 0 0 3 1 0 0 3 0 1 0 0 8 0 9 0 0 8 6 3 0 0 5 0 5 0 0 9 0 6 0 0 1 3 0 0 0 0 2 5 0 0 0 0 0 0 0 0 7 4 0 0 5 2 0 6 3 0 0
        3 6 7 5 3 5 6 2 9 1 2 7 0 9 3 6 0 6 2 6 1 8 7 9 2 0 2 3 7 5 9 2 2 8 9 7 3 6 1 2 9 3 1 9 4 7 8 4 5 0 3 6 1 0 6 3 2 0 6 1 5 5 4 7 6 5 6 9 3 7 4 5 2 5 4 7 4 4 3 0 7
        */

        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for(int t = 0; t < testCases; t++)
        {
            int [][] board = new int [9][9];
            for(int i = 0; i < 9; i++)
                for(int j = 0; j < 9; j++)
                    board[i][j] = sc.nextInt();

            if(!isRowValid(board) || !isColValid(board))
                System.out.println(0);
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
                    System.out.println(0);
                else
                    System.out.println(1);
            }
        }
    }
}


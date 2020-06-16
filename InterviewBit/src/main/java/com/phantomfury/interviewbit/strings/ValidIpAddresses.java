package com.phantomfury.interviewbit.strings;

import java.util.ArrayList;

public class ValidIpAddresses {

    public boolean isPartValid(String s)
    {
        int num = Integer.parseInt(s);
        if(s == null || s.equals("") || num < -1 || num > 255 || s.length() > 3 || (s.charAt(0) == '0' && s.length() > 1))
            return false;
        return true;
    }

    public ArrayList<String> restoreIpAddresses(String A)
    {
        ArrayList<String> results = new ArrayList<>();
        if(A.length() < 4 || A.length() > 12)
            return results;

        String s1 = "", s2 = "", s3 = "", s4 = "";
        int len = A.length();
        for(int i = 1; i < len - 2; i++)
        {
            for(int j = i + 1; j < len - 1; j++)
            {
                for(int k = j + 1; k < len; k++)
                {
                    s1 = A.substring(0, i);
                    s2 = A.substring(i, j);
                    s3 = A.substring(j, k);
                    s4 = A.substring(k, len);

                    if(isPartValid(s1) && isPartValid(s2) && isPartValid(s3) && isPartValid(s4))
                        results.add(s1 + "." + s2 + "." + s3 + "." + s4);
                }
            }
        }

        return results;
    }

    public static void main(String[] args)
    {
        String s1 = "25525511135";
        String s2 = "0100100";

        ValidIpAddresses obj = new ValidIpAddresses();
        System.out.println(obj.restoreIpAddresses(s1).toString());
        System.out.println(obj.restoreIpAddresses(s2).toString());
    }
}

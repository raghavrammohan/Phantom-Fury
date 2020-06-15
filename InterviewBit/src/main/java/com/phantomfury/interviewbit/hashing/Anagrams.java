package com.phantomfury.interviewbit.hashing;

import java.util.*;

public class Anagrams {

    public boolean isAnagram(String s1, String s2)
    {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s1.length(); i++)
        {
            char ch = s1.charAt(i);
            if(map.get(ch) == null)
                map.put(ch, 1);
            else
                map.put(ch, map.get(ch) + 1);
        }

        for(int j = 0; j < s2.length(); j++)
        {
            char ch = s2.charAt(j);
            if(map.get(ch) == null || map.get(ch) == 0)
                return false;
            else
                map.put(ch, map.get(ch) - 1);
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet())
            if(entry.getValue() != 0)
                return false;

        return true;
    }
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        ArrayList<Boolean> isIncluded = new ArrayList<>();
        for(int i = 0; i < A.size(); i++)
            isIncluded.add(false);

        for(int i = 0; i < A.size(); i++)
        {
            String s1 = A.get(i);
            ArrayList<Integer> list = new ArrayList<>();
            if(!isIncluded.get(i))
            {
                list.add(i + 1);
                isIncluded.set(i, true);
                for(int j = i + 1; j < A.size(); j++)
                {
                    String s2 = A.get(j);
                    if(!isIncluded.get(j) && isAnagram(s1, s2))
                    {
                        list.add(j + 1);
                        isIncluded.set(j, true);
                    }
                }
            }
            if(!list.isEmpty())
                results.add(list);
        }
        return results;
    }

    public static void main(String[] args) {
        String[] arr = {"cat", "dog", "god", "tca"};
        Anagrams obj = new Anagrams();
        System.out.println(obj.anagrams(Arrays.asList(arr)));
    }
}


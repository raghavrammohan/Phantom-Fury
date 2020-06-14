package com.phantomfury.leetcode.junechallenge;

import java.util.ArrayList;

public class CheapestFlightsWithinKStops
{
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        ArrayList<Integer> possibleDests = new ArrayList<>();
        ArrayList<Integer> costs = new ArrayList<>();
        ArrayList<Integer> possibleResults = new ArrayList<>();
        ArrayList<Integer> addedInRound = new ArrayList<>();
        for(int i = 0; i <= K; i++)
        {
            if(i == 0)
            {
                for(int j = 0; j < flights.length; j++)
                {
                    if(flights[j][0] == src)
                    {
                        if(flights[j][1] == dst)
                            possibleResults.add(flights[j][2]);
                        else
                        {
                            if(!possibleDests.contains(flights[j][1]))
                            {
                                possibleDests.add(flights[j][1]);
                                costs.add(flights[j][2]);
                                addedInRound.add(i);
                            }
                            else if(possibleDests.contains(flights[j][1]) && addedInRound.get(possibleDests.lastIndexOf(flights[j][1])) != i)
                            {
                                possibleDests.add(flights[j][1]);
                                costs.add(flights[j][2]);
                                addedInRound.add(i);
                            }
                            else if(possibleDests.contains(flights[j][1]) && addedInRound.get(possibleDests.lastIndexOf(flights[j][1])) == i)
                            {
                                int currCost = costs.get(j) + flights[j][2];
                                int prevCost = costs.get(possibleDests.lastIndexOf(flights[j][1]));
                                int index = possibleDests.lastIndexOf(flights[j][1]);
                                if(currCost < prevCost)
                                {
                                    possibleDests.set(index, flights[j][1]);
                                    costs.set(index, costs.get(j) + flights[j][2]);
                                    addedInRound.set(index, i);
                                }

                            }
                        }
                    }
                }
            }
            else
            {
                int prevPossibleDestsLen = possibleDests.size();
                for(int j = 0; j < prevPossibleDestsLen; j++)
                {
                    for(int k = 0; k < flights.length; k++)
                    {
                        if(flights[k][0] == possibleDests.get(j))
                        {
                            if(flights[k][1] == dst)
                                possibleResults.add(costs.get(j) + flights[k][2]);
                            else
                            {
                                if(!possibleDests.contains(flights[k][1]))
                                {
                                    possibleDests.add(flights[k][1]);
                                    costs.add(costs.get(j) + flights[k][2]);
                                    addedInRound.add(i);
                                }
                                else if(possibleDests.contains(flights[k][1]) && addedInRound.get(possibleDests.lastIndexOf(flights[k][1])) != i)
                                {
                                    possibleDests.add(flights[k][1]);
                                    costs.add(costs.get(j) + flights[k][2]);
                                    addedInRound.add(i);
                                }
                                else if(possibleDests.contains(flights[k][1]) && addedInRound.get(possibleDests.lastIndexOf(flights[k][1])) == i)
                                {
                                    int currCost = costs.get(j) + flights[k][2];
                                    int prevCost = costs.get(possibleDests.lastIndexOf(flights[k][1]));
                                    int index = possibleDests.lastIndexOf(flights[k][1]);
                                    if(currCost < prevCost)
                                    {
                                        possibleDests.set(index, flights[k][1]);
                                        costs.set(index, costs.get(j) + flights[k][2]);
                                        addedInRound.set(index, i);
                                    }

                                }
                            }
                        }
                    }
                }

                for(int j = 0; j < prevPossibleDestsLen; j++)
                {
                    possibleDests.remove(0);
                    costs.remove(0);
                    addedInRound.remove(0);
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        for(int i = 0; i < possibleResults.size(); i++)
        {
            if(possibleResults.get(i) < minCost)
                minCost = possibleResults.get(i);
        }
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    public static void main(String[] args) {
        // Testcase 2
        int n = 5;
        int[][] flights = {{1,2,10},{2,0,7},{1,3,8},{4,0,10},{3,4,2},{4,2,10},{0,3,3},{3,1,6},{2,4,5}};
        int src = 0, dst = 4, k = 1;

        // Testcase 1
        /*
        int n = 3;
        int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
        int src = 0, dst = 2, k = 1;
        */

        // Testcase 3
        /*
        int n = 17;
        int[][] flights = {{0,12,28},{5,6,39},{8,6,59},{13,15,7},{13,12,38},{10,12,35},{15,3,23},{7,11,26},{9,4,65},{10,2,38},{4,7,7},{14,15,31},{2,12,44},{8,10,34},{13,6,29},{5,14,89},{11,16,13},{7,3,46},{10,15,19},{12,4,58},{13,16,11},{16,4,76},{2,0,12},{15,0,22},{16,12,13},{7,1,29},{7,14,100},{16,1,14},{9,6,74},{11,1,73},{2,11,60},{10,11,85},{2,5,49},{3,4,17},{4,9,77},{16,3,47},{15,6,78},{14,1,90},{10,5,95},{1,11,30},{11,0,37},{10,4,86},{0,8,57},{6,14,68},{16,8,3},{13,0,65},{2,13,6},{5,13,5},{8,11,31},{6,10,20},{6,2,33},{9,1,3},{14,9,58},{12,3,19},{11,2,74},{12,14,48},{16,11,100},{3,12,38},{12,13,77},{10,9,99},{15,13,98},{15,12,71},{1,4,28},{7,0,83},{3,5,100},{8,9,14},{15,11,57},{3,6,65},{1,3,45},{14,7,74},{2,10,39},{4,8,73},{13,5,77},{10,0,43},{12,9,92},{8,2,26},{1,7,7},{9,12,10},{13,11,64},{8,13,80},{6,12,74},{9,7,35},{0,15,48},{3,7,87},{16,9,42},{5,16,64},{4,5,65},{15,14,70},{12,0,13},{16,14,52},{3,10,80},{14,11,85},{15,2,77},{4,11,19},{2,7,49},{10,7,78},{14,6,84},{13,7,50},{11,6,75},{5,10,46},{13,8,43},{9,10,49},{7,12,64},{0,10,76},{5,9,77},{8,3,28},{11,9,28},{12,16,87},{12,6,24},{9,15,94},{5,7,77},{4,10,18},{7,2,11},{9,5,41}};
        int src = 13, dst = 4, k = 13;
        */

        // Testcase 4
        /*
        int n = 15;
        int[][] flights = {{10,14,43},{1,12,62},{4,2,62},{14,10,49},{9,5,29},{13,7,53},{4,12,90},{14,9,38},{11,2,64},{2,13,92},{11,5,42},{10,1,89},{14,0,32},{9,4,81},{3,6,97},{7,13,35},{11,9,63},{5,7,82},{13,6,57},{4,5,100},{2,9,34},{11,13,1},{14,8,1},{12,10,42},{2,4,41},{0,6,55},{5,12,1},{13,3,67},{3,13,36},{3,12,73},{7,5,72},{5,6,100},{7,6,52},{4,7,43},{6,3,67},{3,1,66},{8,12,30},{8,3,42},{9,3,57},{12,6,31},{2,7,10},{14,4,91},{2,3,29},{8,9,29},{2,11,65},{3,8,49},{6,14,22},{4,6,38},{13,0,78},{1,10,97},{8,14,40},{7,9,3},{14,6,4},{4,8,75},{1,6,56}};
        int src = 1, dst = 4, k = 10;
        */
        CheapestFlightsWithinKStops obj = new CheapestFlightsWithinKStops();
        System.out.println(obj.findCheapestPrice(n, flights, src, dst, k));
    }
}
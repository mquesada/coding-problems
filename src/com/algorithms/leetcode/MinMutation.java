package com.algorithms.leetcode;

import java.util.*;

/**
 * A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".
 * <p>
 * Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is
 * defined as ONE single character changed in the gene string.
 * <p>
 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 * <p>
 * Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank
 * to make it a valid gene string.
 * <p>
 * Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations
 * needed to mutate from "start" to "end". If there is no such a mutation, return -1.
 * <p>
 * Note:
 * <p>
 * Starting point is assumed to be valid, so it might not be included in the bank.
 * If multiple mutations are needed, all mutations during in the sequence must be valid.
 * You may assume start and end string is not the same.
 * <p>
 * https://leetcode.com/problems/minimum-genetic-mutation/description/
 */
public class MinMutation {

    private static final char[] CHOICES = new char[]{'A', 'C', 'G', 'T'};

    private class GeneCount {
        private String gene;
        private int count;

        GeneCount(String gene, int count) {
            this.gene = gene;
            this.count = count;
        }
    }

    private Set<String> generateNeighbors(String gene, Set<String> bank) {
        StringBuilder sb = new StringBuilder(gene);
        Set<String> generated = new HashSet<>();

        for (char c : CHOICES) {
            for (int j = 0; j < sb.length(); j++) {
                char o = sb.charAt(j);

                if (c != o) {
                    sb.setCharAt(j, c);
                    String newString = sb.toString();
                    if (bank.contains(newString)) {
                        generated.add(newString);
                    }
                    sb.setCharAt(j, o);
                }
            }
        }

        return generated;
    }

    public int minMutation(String start, String end, String[] bank) {
        if (start == null || start.isEmpty()
                || end == null || end.isEmpty()
                || bank == null || bank.length == 0) {
            return -1;
        }

        Set<String> genesBank = new HashSet<>(Arrays.asList(bank));
        if (!genesBank.contains(end)) {
            return -1;
        }

        Queue<GeneCount> queue = new LinkedList<>();
        queue.offer(new GeneCount(start, 0));
        genesBank.remove(start);

        while (!queue.isEmpty()) {
            GeneCount geneCount = queue.poll();
            if (geneCount.gene.equals(end)) {
                return geneCount.count;
            }

            for (String gene : generateNeighbors(geneCount.gene, genesBank)) {
                queue.offer(new GeneCount(gene, geneCount.count + 1));
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MinMutation minMutation = new MinMutation();

        System.out.println(minMutation.minMutation("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"}));
        System.out.println(minMutation.minMutation("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"}));
        System.out.println(minMutation.minMutation("AAAAACCC", "AACCCCCC", new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"}));
    }


}

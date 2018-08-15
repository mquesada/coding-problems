package com.algorithms.leetcode;

import java.util.*;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
 * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * <p>
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * <p>
 * Example:
 * <p>
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * <p>
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 * <p>
 * https://leetcode.com/problems/repeated-dna-sequences/description/
 */
public class RepeatedDnaSequences {

    private static final int NUM_NUCLEOTIDES = 4;
    private static final int SEQUENCE_LEN = 10;
    private static final int POW_OF_TEN = (int) Math.pow(NUM_NUCLEOTIDES, SEQUENCE_LEN);
    private static final Map<Character, Integer> GEN_VALUE = new HashMap<Character, Integer>() {{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};

    private int generateHash(String dna) {
        int hash = 0;
        for (int i = SEQUENCE_LEN; i > 0; i--) {
            hash += Math.pow(NUM_NUCLEOTIDES, i) * GEN_VALUE.get(dna.charAt(SEQUENCE_LEN - i));
        }
        return hash;
    }

    private int rollHash(int hash, String dna, int i) {
        hash -= POW_OF_TEN * GEN_VALUE.get(dna.charAt(i));
        hash *= NUM_NUCLEOTIDES;
        hash += NUM_NUCLEOTIDES * GEN_VALUE.get(dna.charAt(i + SEQUENCE_LEN));
        return hash;
    }

    public List<String> findRepeatedDnaSequences(String dna) {
        if (dna == null || dna.isEmpty() || dna.length() < SEQUENCE_LEN) {
            return Collections.emptyList();
        }

        int hash = generateHash(dna);

        Set<Integer> found = new HashSet<>();
        found.add(hash);
        Set<String> result = new HashSet<>();

        for (int i = 0; i < dna.length() - SEQUENCE_LEN; i++) {
            hash = rollHash(hash, dna, i);
            if (found.contains(hash)) {
                result.add(dna.substring(i + 1, i + SEQUENCE_LEN + 1));
            } else {
                found.add(hash);
            }
        }

        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        RepeatedDnaSequences dnaSequences = new RepeatedDnaSequences();
        System.out.println(dnaSequences.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(dnaSequences.findRepeatedDnaSequences("ACCCTCCCACTTGGATGCCGCACGTGTCGACTAACCTTACATTGTCCCCCCACCTCCAGACGGTTAACTCTTGAAATGGGGGAATAGCTGCTTGCGCGTG"));
        System.out.println(dnaSequences.findRepeatedDnaSequences("AAAAAAAAAAAAAAAAAAAAA"));
    }
}

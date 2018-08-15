package com.algorithms.leetcode;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
 * sequence from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 * <p>
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * <p>
 * https://leetcode.com/problems/word-ladder/description/
 */
public class WordLadder {

    private class WordCount {
        private String word;
        private int count;

        public WordCount(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

    private Set<String> generateNeighbors(String word, Set<String> words) {
        StringBuilder sb = new StringBuilder(word);
        Set<String> generated = new HashSet<>();

        for (char c = 'a'; c <= 'z'; c++) {
            for (int j = 0; j < sb.length(); j++) {
                char o = sb.charAt(j);

                if (c != o) {
                    sb.setCharAt(j, c);
                    String newString = sb.toString();
                    if (words.contains(newString)) {
                        generated.add(newString);
                        words.remove(newString);
                    }
                    sb.setCharAt(j, o);
                }
            }
        }

        return generated;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || beginWord.isEmpty()
                || endWord == null || endWord.isEmpty()
                || wordList == null || wordList.isEmpty()
                || beginWord.equals(endWord)) {
            return 0;
        }

        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return 0;
        }

        Queue<WordCount> queue = new LinkedList<>();
        queue.offer(new WordCount(beginWord, 1));

        while (!queue.isEmpty()) {
            WordCount wordCount = queue.poll();
            if (wordCount.word.equals(endWord)) {
                return wordCount.count;
            }

            for (String word : generateNeighbors(wordCount.word, words)) {
                queue.offer(new WordCount(word, wordCount.count + 1));
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        WordLadder ladder = new WordLadder();

        System.out.println(ladder.ladderLength("hit", "cog", new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));
        System.out.println(ladder.ladderLength("hit", "cog", new ArrayList<>(Arrays.asList("hot", "hog", "dot", "dog", "lot", "log", "cog"))));
        System.out.println(ladder.ladderLength("hit", "cog", new ArrayList<>(Arrays.asList("hot", "hog", "dot", "dog", "lot", "log"))));
    }

}

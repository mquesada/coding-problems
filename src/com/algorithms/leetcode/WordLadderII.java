package com.algorithms.leetcode;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s)
 * from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 *
 * https://leetcode.com/problems/word-ladder-ii
 */
public class WordLadderII {

    private class WordNode {
        private String word;
        private WordNode parent;

        public WordNode(String word, WordNode parent) {
            this.word = word;
            this.parent = parent;
        }
    }

    private List<String> getNodeList(WordNode wordNode) {
        List<String> list = new LinkedList<>();

        WordNode current = wordNode;
        while (current != null) {
            list.add(0, current.word);
            current = current.parent;
        }

        return list;
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
                    }
                    sb.setCharAt(j, o);
                }
            }
        }

        return generated;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || beginWord.isEmpty()
                || endWord == null || endWord.isEmpty()
                || wordList == null || wordList.isEmpty()
                || beginWord.equals(endWord)) {
            return Collections.emptyList();
        }

        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return Collections.emptyList();
        }

        List<List<String>> result = new ArrayList<>();
        int shortest = Integer.MAX_VALUE;

        Queue<WordNode> queue = new LinkedList<>();
        queue.offer(new WordNode(beginWord, null));
        words.remove(beginWord);

        while (!queue.isEmpty()) {
            WordNode wordNode = queue.poll();
            if (wordNode.word.equals(endWord)) {
                List<String> list = getNodeList(wordNode);
                if (list.size() <= shortest) {
                    result.add(list);
                    shortest = list.size();
                }
            } else {
                words.remove(wordNode.word);
                for (String word : generateNeighbors(wordNode.word, words)) {
                    queue.offer(new WordNode(word, wordNode));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        WordLadderII ladder = new WordLadderII();

        System.out.println(ladder.findLadders("hit", "cog", new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));
        System.out.println(ladder.findLadders("hit", "cog", new ArrayList<>(Arrays.asList("hot", "hog", "dot", "dog", "lot", "log", "cog"))));
        System.out.println(ladder.findLadders("hit", "cog", new ArrayList<>(Arrays.asList("hot", "hog", "dot", "dog", "lot", "log"))));
        System.out.println(ladder.findLadders("a", "c", new ArrayList<>(Arrays.asList("a", "b", "c"))));
        System.out.println(ladder.findLadders("red", "tax", new ArrayList<>(Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee"))));
    }
}

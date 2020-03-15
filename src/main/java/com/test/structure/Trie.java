package com.test.structure;

import java.util.*;

class TrieNode {
    // Initialize your data structure here.
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean hasWord;

    public TrieNode() {

    }

    public TrieNode(char c) {
        this.c = c;
    }
}

public class Trie {
    private TrieNode root;
    private Map<String, String> normalizeMap = new HashMap<>();

    public Trie() {
        root = new TrieNode();
    }

    public void addNormalizeMap(String norm, String word) {
        this.normalizeMap.put(norm, word);
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode cur = root;
        HashMap<Character, TrieNode> curChildren = root.children;
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordArray.length; i++) {
            char wc = wordArray[i];
            if (curChildren.containsKey(wc)) {
                cur = curChildren.get(wc);
            } else {
                TrieNode newNode = new TrieNode(wc);
                curChildren.put(wc, newNode);
                cur = newNode;
            }
            curChildren = cur.children;
            if (i == wordArray.length - 1) {
                cur.hasWord = true;
            }
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (searchWordNodePos(word) == null) {
            return false;
        } else if (searchWordNodePos(word).hasWord)
            return true;
        else return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (searchWordNodePos(prefix) == null) {
            return false;
        } else return true;
    }

    public TrieNode searchWordNodePos(String s) {
        HashMap<Character, TrieNode> children = root.children;
        TrieNode cur = null;
        char[] sArray = s.toCharArray();
        for (int i = 0; i < sArray.length; i++) {
            char c = sArray[i];
            if (children.containsKey(c)) {
                cur = children.get(c);
                children = cur.children;
            } else {
                return null;
            }
        }
        return cur;
    }

    public List<String> wordBreak(String sentence) {
        List<String> result = new ArrayList<>();
        List<String> resultWithInvalidWords = new ArrayList<>();
        List<String> resultWithMinimumInvalidWords = new ArrayList<>();
        Tree root = wordBreakTree(sentence);
        listTree(root, 0, "", result, resultWithInvalidWords);
        if (result.size() > 0) {
            return result;
        }

        Collections.sort(resultWithInvalidWords, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.valueOf(o1.split("::")[0]) - Integer.valueOf(o2.split("::")[0]);
            }
        });
        int minimumInvalidWordCount = Integer.valueOf(resultWithInvalidWords.get(0).split("::")[0]);
        for (int i = 0; i < resultWithInvalidWords.size(); i++) {
            int invalidWordCount = Integer.valueOf(resultWithInvalidWords.get(i).split("::")[0]);
            if (invalidWordCount > minimumInvalidWordCount) {
                break;
            }
            resultWithMinimumInvalidWords.add(resultWithInvalidWords.get(i).split("::")[1]);
        }
        return resultWithMinimumInvalidWords;
    }

    private void listTree(Tree tree, Integer invalidWordCount, String wordBreak, List<String> result, List<String> resultWithInvalidWords) {
        if (wordBreak != null && wordBreak.length() == 0) {
            wordBreak = tree.getWord();
        } else {
            wordBreak = wordBreak + " " + (normalizeMap.containsKey(tree.getWord()) ? normalizeMap.get(tree.getWord()) : tree.getWord());
        }
        if (tree.isInvalidWord()) {
            invalidWordCount++;
        }
        if (tree.getChildren() == null || tree.getChildren().size() == 0) {
            resultWithInvalidWords.add(invalidWordCount + "::" + wordBreak);
            if (invalidWordCount <= 0) {
                result.add(wordBreak);
            }
        } else {
            for (Tree t : tree.getChildren()) {
                listTree(t, invalidWordCount, wordBreak, result, resultWithInvalidWords);
            }
        }
    }

    private Tree wordBreakTree(String sentence) {
        Tree root = new Tree("");
        buildWordBreakTree(sentence, root);
        return root;
    }

    private void buildWordBreakTree(String sentence, Tree prevNode) {
        HashMap<Character, TrieNode> children = root.children;
        TrieNode cur = null;
        char[] sArray = sentence.toCharArray();
        boolean foundValidWordPrefix = false;
        for (int i = 0; i < sArray.length; i++) {
            char c = sArray[i];
            if (children.containsKey(c)) {
                cur = children.get(c);
                if (cur.hasWord) {
                    // if found one word, add as one branch, and go on.
                    Tree currNode = new Tree(prevNode, sentence.substring(0, i + 1));
                    buildWordBreakTree(sentence.substring(i + 1), currNode);
                    foundValidWordPrefix = true;
                } else {
                    // if only contains in the children, but not a word and not end of sentence, go on.

                    // if its end of sentence
                    if (i == sArray.length - 1) {
                        // already found suitable word prefixes, and now stop searching
                        if (foundValidWordPrefix) {

                        } else {
                            // if not found suitable word, add the first char to the invalid word
                            if (prevNode.isInvalidWord()) {
                                prevNode.setWord(prevNode.getWord() + sArray[0]);
                                buildWordBreakTree(sentence.substring(1), prevNode);
                            } else {
                                Tree currNode = new Tree(prevNode, sArray[0] + "");
                                currNode.setInvalidWord(true);
                                buildWordBreakTree(sentence.substring(1), currNode);
                            }
                        }
                        break;
                    }
                }
                children = cur.children;
            } else {
                // already found suitable word prefixes, and now stop searching
                if (foundValidWordPrefix) {

                } else {
                    // if not found suitable word, add the first char to the invalid word
                    if (prevNode.isInvalidWord()) {
                        prevNode.setWord(prevNode.getWord() + sArray[0]);
                        buildWordBreakTree(sentence.substring(1), prevNode);
                    } else {
                        Tree currNode = new Tree(prevNode, sArray[0] + "");
                        currNode.setInvalidWord(true);
                        buildWordBreakTree(sentence.substring(1), currNode);
                    }
                }
                break;
            }
        }
    }
}

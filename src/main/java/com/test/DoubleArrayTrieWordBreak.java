package com.test;

import com.test.structure.DoubleArrayTrie;
import com.test.structure.Tree;

import java.util.*;
import java.util.stream.Collectors;

public class DoubleArrayTrieWordBreak {

    DoubleArrayTrie dat = new DoubleArrayTrie();
    List<String> words = new ArrayList<String>();

    public List<String> wordBreak(String sentence) {
        Tree root = new Tree("");
        Tree tree = wordBreak(sentence, root);
        List<String> WordBreakedSentences = new ArrayList<>();
        listTree(tree, "", WordBreakedSentences);
        return WordBreakedSentences;
    }

    public void listTree(Tree tree, String wordBreak, List<String> list) {
        wordBreak = wordBreak + " " + tree.getWord();
        if (tree.getChildren() == null || tree.getChildren().size() == 0) {
            list.add(wordBreak);
        } else {
            for (Tree t : tree.getChildren()) {
                listTree(t, wordBreak, list);
            }
        }
    }


    public Tree wordBreak(String sentence, Tree tree) {
        List<String> currWords = dat.commonPrefixSearch(sentence).stream().map(i -> words.get(i)).collect(Collectors.toList());
        Collections.sort(currWords);
        for (String currWord : currWords) {
            if (sentence.length() > 0) {
                String remainSentence = sentence.substring(currWord.length());
                Tree childTree = new Tree(tree, currWord);
                wordBreak(remainSentence, childTree);
            }
        }
        return tree;
    }


    public DoubleArrayTrieWordBreak(List<String> dict, List<String> customizedDict, boolean useCustomizedDictionaryOnly) {
        if (useCustomizedDictionaryOnly) {
            if (customizedDict != null) {
                words = customizedDict;
            }
        } else {
            if (dict != null) {
                words.addAll(dict);
            }
            if (customizedDict != null) {
                words.addAll(customizedDict);
            }
        }
        if (words.size() <= 0) {
            throw new RuntimeException("error: no dictionary provided!");
        }
        Collections.sort(words);
        int error = dat.build(words);
        if (error != 0) {
            throw new RuntimeException("build double array tie failed, error code:" + error);
        }
    }

}

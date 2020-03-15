package com.test;

import com.test.structure.Trie;

import java.util.List;
import java.util.Map;

public class TrieWordBreak {

    Trie trie = new Trie();

    public TrieWordBreak(List<String> dict) {
        if (dict != null && dict.size() > 0) {
            dict.stream().forEach(w -> trie.insert(w));
        }
    }

    public TrieWordBreak(List<String> dict, Map<String, String> normalizeMap) {
        if (dict != null && dict.size() > 0) {
            dict.stream().forEach(w -> trie.insert(w));
            if (normalizeMap != null) {
                normalizeMap.forEach((k, v) -> {
                    trie.insert(k);
                    trie.addNormalizeMap(k, v);
                });
            }
        }
    }

    public void addExtraDict(List<String> dict) {
        if (dict != null && dict.size() > 0) {
            dict.stream().forEach(w -> trie.insert(w));
        }
    }

    public List<String> wordBreak(String sentence) {
        return trie.wordBreak(sentence);
    }
}

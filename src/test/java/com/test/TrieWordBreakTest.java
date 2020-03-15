package com.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TrieWordBreakTest {

    @Test
    public void wordBreak0() {
        List<String> dict = Arrays.asList("i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "man go");
        TrieWordBreak trieWordBreak = new TrieWordBreak(dict);
        List<String> wordBreaks = trieWordBreak.wordBreak("ilikesamsungmobile");
        wordBreaks.stream().forEach(
                s -> System.out.println(s)
        );
        Assert.assertEquals(wordBreaks.size(), 2);
    }

    @Test
    public void WordBreak1() {
        List<String> dict = Arrays.asList("i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "man go");
        Map<String, String> normalizeMap = new HashMap<>();
        normalizeMap.put("mango", "man go");
        TrieWordBreak trieWordBreak = new TrieWordBreak(dict, normalizeMap);
        List<String> wordBreaks = trieWordBreak.wordBreak("ilikeicecreamandmango");
        wordBreaks.stream().forEach(
                s -> System.out.println(s)
        );
        Assert.assertEquals(wordBreaks.size(), 1);
        Assert.assertEquals("i like ice cream and man go", wordBreaks.get(0));
    }

    @Test
    public void WordBreak2() {
        List<String> dict = Arrays.asList("i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "mango");
        TrieWordBreak trieWordBreak = new TrieWordBreak(dict);
        List<String> wordBreaks = trieWordBreak.wordBreak("ilikeicecreamandmango");
        wordBreaks.stream().forEach(
                s -> System.out.println(s)
        );
        Assert.assertEquals(wordBreaks.size(), 1);
        Assert.assertEquals("i like ice cream and mango", wordBreaks.get(0));
    }

    @Test
    public void WordBreak3() {
        List<String> customizedDict = Arrays.asList("i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "mango");
        TrieWordBreak trieWordBreak = new TrieWordBreak(customizedDict);
        List<String> wordBreaks = trieWordBreak.wordBreak("ilikeicecreamandmango");
        wordBreaks.stream().forEach(
                s -> System.out.println(s)
        );
        Assert.assertEquals(wordBreaks.size(), 1);
        Assert.assertEquals("i like ice cream and mango", wordBreaks.get(0));
    }

    @Test
    public void WordBreak4() {
        List<String> dict = Arrays.asList("i", "like", "sam", "sung", "samsung");
        TrieWordBreak trieWordBreak = new TrieWordBreak(dict);
        List<String> customizedDict = Arrays.asList("mobile", "ice", "cream", "mango");
        trieWordBreak.addExtraDict(customizedDict);
        List<String> wordBreaks = trieWordBreak.wordBreak("ilikeicecreamandmango");
        wordBreaks.stream().forEach(
                s -> System.out.println(s)
        );
        Assert.assertEquals(wordBreaks.size(), 1);
        Assert.assertEquals("i like ice cream and mango", wordBreaks.get(0));
    }
}
package com.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * only did one test case for dat, all test cases for trie
 */
public class DoubleArrayTrieWordBreakTest {

    @Test
    public void wordBreak0() {
        List<String> dict = Arrays.asList("i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "man go");
        DoubleArrayTrieWordBreak doubleArrayTrieWordBreak = new DoubleArrayTrieWordBreak(dict, null, false);
        List<String> WordBreaks = doubleArrayTrieWordBreak.wordBreak("ilikesamsungmobile");
        WordBreaks.stream().forEach(
                s -> System.out.println(s)
        );
        Assert.assertEquals(WordBreaks.size(), 2);
    }

}

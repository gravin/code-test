package com.test.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * to keep the sentence WordBreak result
 */
public class Tree {
    private Tree parent;
    private String word;
    private boolean invalidWord;
    private List<Tree> children = new ArrayList<>();

    public Tree(Tree parent, String word) {
        this.parent = parent;
        this.parent.getChildren().add(this);
        this.word = word;
    }

    public Tree(String word) {
        this.word = word;
    }

    public Tree getParent() {
        return parent;
    }

    public void setParent(Tree parent) {
        this.parent = parent;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isInvalidWord() {
        return invalidWord;
    }

    public void setInvalidWord(boolean invalidWord) {
        this.invalidWord = invalidWord;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }
}

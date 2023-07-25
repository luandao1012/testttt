package com.anondev.testtt;

import java.util.List;

public class Item {
    String text;
    List<String> list;

    public Item(String text, List<String> list) {
        this.text = text;
        this.list = list;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}

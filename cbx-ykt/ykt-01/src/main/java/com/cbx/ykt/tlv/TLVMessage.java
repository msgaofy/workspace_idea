package com.cbx.ykt.tlv;

import java.util.Arrays;

public class TLVMessage {
    private String tag;
    private int len;
    private String value;

    public TLVMessage() {
    }

    public TLVMessage(String tag, int len, String value) {
        this.tag = tag;
        this.len = len;
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TLVMessage{" +
                "tag='" + tag + '\'' +
                ", len=" + len +
                ", value='" + value + '\'' +
                '}';
    }
}

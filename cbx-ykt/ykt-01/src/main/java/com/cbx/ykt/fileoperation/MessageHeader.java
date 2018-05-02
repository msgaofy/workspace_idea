package com.cbx.ykt.fileoperation;

public class MessageHeader {
    /**

     * 帧头（0xFFBB）

     */

    private int tag;

    /**

     * 报文长度

     */

    private int length;

    /**

     * 记录条数

     */

    private int count;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

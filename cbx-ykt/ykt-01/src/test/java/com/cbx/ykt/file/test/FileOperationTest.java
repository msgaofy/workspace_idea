package com.cbx.ykt.file.test;

import com.cbx.ykt.FileOperation.FileOperation;
import com.cbx.ykt.FileOperation.MessageHeader;

import java.io.IOException;

public class FileOperationTest {
    public static void main(String[] args) throws IOException {
        FileOperation fileOperation = new FileOperation();
        byte[] fileByte = fileOperation.getContent("E:\\fileoperation");
        for (byte b:fileByte) {
            System.out.println(b);
        }
        MessageHeader messageHeader = fileOperation.getMessageHeader(fileByte);
        System.out.println("个数====="+messageHeader.getCount());
        System.out.println("帧头====="+messageHeader.getTag());
        System.out.println("长度====="+messageHeader.getLength());

    }
}

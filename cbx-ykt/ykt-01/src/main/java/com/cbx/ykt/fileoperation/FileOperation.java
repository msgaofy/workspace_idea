package com.cbx.ykt.fileoperation;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileOperation {
    public FileOperation() {
    }

    @SuppressWarnings("resource")
    public byte[] getContent(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize>Integer.MAX_VALUE){
            System.out.println("file is too big.....");
            return null;
        }
        FileInputStream fis = new FileInputStream(file);
        byte[] fileBuffer = new byte[(int)fileSize];
        int offset = 0;
        int numRead;
        while(offset<fileBuffer.length&&(numRead=fis.read(fileBuffer,offset,fileBuffer.length-offset))>=0){
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != fileBuffer.length) {
            throw new IOException("Could not completely read file "    + file.getName());
        }
        fis.close();
        return fileBuffer;
    }
    public static byte[] copyBytes(byte[] bytes, int offset, int size){
        byte[] newBytes = new byte[size];
        System.arraycopy(bytes, offset, newBytes, 0, size);
        return newBytes;
    }
    public static int byte2Short(byte[] b) {
        short shortValue = 0;
        for (int i = 0; i < b.length; i++) {
            shortValue += (b[i] & 0x00FF) << (8 * (1 - i));
        }
        return shortValue & 0x0FFFF;
    }
    public MessageHeader getMessageHeader(byte[] bytes){
        MessageHeader result = new MessageHeader();
        int offset = 0;
        int size = 0;
        byte[] b;
        /**

         * 开始解析

         */
        size = 2;
        b = copyBytes(bytes, offset, size);
        int tag = byte2Short(b);
        result.setTag(tag);
        offset += size;
        b = copyBytes(bytes, offset, size);
        int length = byte2Short(b);
        result.setLength(length);
        offset += size;
        return result;
    }
}

package com.cbx.ykt.communication.test;

import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import java.io.InputStream;

public class RS232EventListener implements SerialPortEventListener {
    //1.定义变量
    CommPortIdentifier com = null;//未打卡的端口
    SerialPort serialCom = null;//打开的端口
    InputStream inputStream = null;//输入流

    public RS232EventListener(String portName){
        try {
            //获取串口、打开窗串口、获取串口的输入流。
            com = CommPortIdentifier.getPortIdentifier(portName);
            serialCom = (SerialPort) com.open(portName, 1000);
            inputStream = serialCom.getInputStream();

            //向串口添加事件监听对象。
            serialCom.addEventListener(this);
            //设置当端口有可用数据时触发事件，此设置必不可少。
            serialCom.notifyOnDataAvailable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {

        //定义用于缓存读入数据的数组
        byte[] cache;
        //记录已经到达串口COM且未被读取的数据的字节（Byte）数。
        int availableBytes = 0;

        //如果是数据可用的时间发送，则进行数据的读写
        if(serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE){
            try {
                availableBytes = inputStream.available();
                while(availableBytes > 0){
                    cache = new byte[availableBytes];
                    int ss = inputStream.read(cache);
                    for(int i = 0; i < ss && i < availableBytes; i++){
                        //解码并输出数据
                        System.out.print((char)cache[i]);
                    }
                    availableBytes = inputStream.available();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

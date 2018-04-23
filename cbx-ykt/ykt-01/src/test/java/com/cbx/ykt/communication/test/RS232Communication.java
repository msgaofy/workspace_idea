package com.cbx.ykt.communication.test;

import javax.comm.CommPort;
import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RS232Communication {

    private InputStream in;
    private OutputStream out;
    private SerialPort serialPort;
    private CommPort commPort;

    //打开串口
    public boolean openPort(String portName){
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            commPort = portIdentifier.open(portName, 2000);
            // 判断是不是串口
            if (commPort instanceof SerialPort) {
                serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_2,SerialPort.PARITY_NONE);
                //获取数据流
                in = serialPort.getInputStream();
                out = serialPort.getOutputStream();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //读数据，设置监听器
    //发送数据
    public boolean sendToPort(byte[] order){

        try {
            out.write(order);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    //关闭串口
    public boolean closePort(){
        try {
            in.close();
            serialPort.close();
            commPort.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public InputStream getIn() {
        return in;
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public void setIn(InputStream in) {
        this.in = in;
    }

    public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public OutputStream getOut() {
        return out;
    }

    public void setOut(OutputStream out) {
        this.out = out;
    }
}

package com.cbx.ykt.communication.test;

import javax.comm.CommPortIdentifier;
import java.util.Enumeration;

public class RS232Test {
    public static void main(String[] args) {
        CommPortIdentifier portId;
        //本机串口列表
        Enumeration portIdentifiers = CommPortIdentifier.getPortIdentifiers();
        while(portIdentifiers.hasMoreElements()){
            portId=(CommPortIdentifier)portIdentifiers.nextElement();
            if(portId.getPortType()==CommPortIdentifier.PORT_SERIAL){
                System.out.println("port name :"+portId.getName());
            }
        }

        //给COM4添加监听，接收数据
        new RS232EventListener("COM4");

        //COM3发送数据
        RS232Communication communication = new RS232Communication();
        Boolean resultOpenCOM3 = communication.openPort("COM3");
        System.out.println("resultOpenCOM4==="+resultOpenCOM3);

        if (resultOpenCOM3){

            Boolean resultSendToPort = communication.sendToPort(new byte[]{'H','e','l','l','o',
                    ' ','W','o','r','l','d','!'});
            System.out.println("sendToPortResult==="+resultSendToPort );
            Boolean resultClose = communication.closePort();
            System.out.println("resultClose==="+resultClose);
        }


    }
}

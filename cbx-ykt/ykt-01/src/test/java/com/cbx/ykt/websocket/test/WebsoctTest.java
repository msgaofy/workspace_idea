package com.cbx.ykt.websocket.test;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

public class WebsoctTest {
    public static void main(String[] args) throws IOException {
        WebsocketMessage ws = new WebsocketMessage();
        JSONObject jo = new JSONObject();
        jo.put("message", "这是后台返回的消息！");
        jo.put("To","小和尚0817");
        ws.onMessage(jo.toString());

    }
}

package com.cbx.ykt.websocket;

import com.alibaba.fastjson.JSONObject;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/webSocket/{username}")
public class WebsocketMessage {
    private static int onlineCount = 0;
    private static Map<String, WebsocketMessage> clients = new ConcurrentHashMap<String, WebsocketMessage>();
    private Session session;
    private String username;

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {

        this.username = username;
        this.session = session;

        addOnlineCount();
        clients.put(username, this);
        System.out.println("已连接");
    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(username);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        JSONObject jsonTo = JSONObject.parseObject(message);
        String mes = (String) jsonTo.get("message");

        if (!jsonTo.get("To").equals("All")){
            sendMessageTo(mes, jsonTo.get("To").toString());
        }else{
            sendMessageAll("给所有人");
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String To) throws IOException {
        // session.getBasicRemote().sendText(message);
        //session.getAsyncRemote().sendText(message);
        for (WebsocketMessage item : clients.values()) {
            if (item.username.equals(To) )
                item.session.getAsyncRemote().sendText(message);
        }
    }

    public void sendMessageAll(String message) throws IOException {
        for (WebsocketMessage item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebsocketMessage.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebsocketMessage.onlineCount--;
    }

    public static synchronized Map<String, WebsocketMessage> getClients() {
        return clients;
    }


    public Session getSession() {
        return session;
    }

    public String getUsername() {
        return username;
    }

    public static void setOnlineCount(int onlineCount) {
        WebsocketMessage.onlineCount = onlineCount;
    }

    public static void setClients(Map<String, WebsocketMessage> clients) {
        WebsocketMessage.clients = clients;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package com.example.chapter6_wzy.WebSocket;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @author wsh
 */
@Component
@Slf4j
@ServerEndpoint("/websocket/{tid}")
public class DownloadSocket {
    private static ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();
    @OnOpen
    public void onOpen(Session session, @PathParam("tid") String userId) {
        sessions.put(userId, session);
    }
    @OnClose
    public void onClose(Session session, @PathParam("tid") String userId) {
        sessions.remove(userId);
    }
    public  void sendProgressUpdate(String userId, String message) {
        Session session = sessions.get(userId);
        if (session != null && session.isOpen()) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

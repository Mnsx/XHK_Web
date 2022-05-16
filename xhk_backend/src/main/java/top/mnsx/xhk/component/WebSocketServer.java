package top.mnsx.xhk.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.mnsx.xhk.utils.SpringUtils;

import javax.websocket.*;
import java.util.Map;

@Component
@ServerEndpoint(value = "/messages/{username}")
public class WebSocketServer {
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        sessionMap.put(username, session);
        log.info("有新用户加入，username={}, 当前在线人数为：{}", username, sessionMap.size());
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        result.put("users", array);
        for (Object key : sessionMap.keySet()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", key);

            array.add(jsonObject);
        }

        sendAllMessage(JSON.toJSONString(result));
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        sessionMap.remove(username);
        log.info("有一连接关闭，移除username={}的用户session, 当前在线人数为：{}", username, sessionMap.size());
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String username) {
        log.info("服务端收到用户username={}的消息:{}", username, message);
        JSONObject obj = JSON.parseObject(message);
        String toUsername = (String) obj.get("to");
        String text = (String) obj.get("text");
        StringRedisTemplate redisTemplate = SpringUtils.getBean(StringRedisTemplate.class);
        redisTemplate.opsForList().leftPush((String) obj.get("from") + ":" + (String)obj.get("to"), (String)obj.get("text"));

        Session toSession = sessionMap.get(toUsername);
        if (toSession != null) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("from", username);
            jsonObject.put("text", text);
            this.sendMessage(jsonObject.toString(), toSession);
            log.info("发送给用户username={}，消息：{}", toUsername, jsonObject.toString());
        } else {
            log.info("发送失败，未找到用户username={}的session", toUsername);
        }
    }
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    private void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }

    private void sendAllMessage(String message) {
        try {
            for (Session session : sessionMap.values()) {
                log.info("服务端给客户端[{}]发送消息{}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }
}

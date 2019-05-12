package websockets;

import domain.post.Post;
import domain.post.PostDTO;
import repository.PostRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.ejb.EJB;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
//ws://localhost:8081/server/1/channel/1/chat/123
@ServerEndpoint(value = "/chat/{channelId}/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class ChatEndPoint {
    private Session session;
    private static final Set<ChatEndPoint> chatEndpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();

    @EJB
    PostRepository postRepository;

    @OnOpen
    public void onOpen(Session session, @PathParam("channelId") String channelId, @PathParam("username") String username) throws IOException, EncodeException {

        this.session = session;
        chatEndpoints.add(this);
        users.put(session.getId(), username);

//        Message message = new Message();
//        message.setFrom(username);
//        message.setContent("Connected!");
//        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        message.setFrom(users.get(session.getId()));
        broadcast(message);

        PostDTO dto = new PostDTO();
        dto.setMessage(message.getMessage());
        dto.setChannelId(Integer.parseInt(message.getTo()));
        dto.setSender(message.getFrom());
        Post post = new Post(dto);
        postRepository.save(post);
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        chatEndpoints.remove(this);
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setMessage("Disconnected!");
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private static void broadcast(Message message) throws IOException, EncodeException {
        chatEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote()
                            .sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

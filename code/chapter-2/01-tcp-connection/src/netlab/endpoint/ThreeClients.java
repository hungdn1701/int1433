package netlab.endpoint;

import static netlab.endpoint.SlideSnippets.show;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * V08, đọc thêm: một listening socket nhận ba kết nối. Ba connected socket dùng chung
 * local port 9199 nhưng khác remote endpoint, nên là ba kết nối khác nhau.
 *
 * <p>Chạy: {@code java -cp out netlab.endpoint.ThreeClients}
 */
public final class ThreeClients {

    public static void main(String[] args) throws IOException {
        // địa chỉ literal: không cần resolve, log in ra đúng 127.0.0.1
        InetAddress loopback = InetAddress.getByName("127.0.0.1");
        List<Socket> open = new ArrayList<>();
        try (ServerSocket server = SlideSnippets.bindExplicit(9199, 50, loopback)) {
            System.out.println("listening   " + show((InetSocketAddress) server.getLocalSocketAddress()));

            for (int i = 1; i <= 3; i++) {
                Socket client = new Socket();
                open.add(client);
                client.connect(server.getLocalSocketAddress(), 3_000);

                Socket connected = server.accept();
                open.add(connected);
                System.out.printf("accept #%d   local %s  remote %s%n", i,
                        show((InetSocketAddress) connected.getLocalSocketAddress()),
                        show((InetSocketAddress) connected.getRemoteSocketAddress()));

                if (i == 1) {
                    // cùng một kết nối, nhìn từ phía client: local và remote đổi chỗ
                    System.out.printf("client #1   local %s  remote %s%n",
                            show((InetSocketAddress) client.getLocalSocketAddress()),
                            show((InetSocketAddress) client.getRemoteSocketAddress()));
                }
            }
        } finally {
            for (Socket socket : open) {
                socket.close();
            }
        }
    }
}

package netlab.endpoint;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * V08, đọc thêm: một tên có thể cho ra nhiều địa chỉ; tên không resolve được
 * không làm hỏng việc tạo InetSocketAddress mà chỉ lộ ra ở bước connect.
 *
 * <p>Chạy: {@code java -cp out netlab.endpoint.ResolveDemo}
 */
public final class ResolveDemo {

    public static void main(String[] args) throws IOException {
        for (InetAddress address : InetAddress.getAllByName("localhost")) {
            System.out.println("localhost -> " + address.getHostAddress());
        }

        // .invalid là tên miền dành riêng, không bao giờ resolve được (RFC 6761)
        InetSocketAddress remote = new InetSocketAddress("lookup.invalid", 9199);
        System.out.println("isUnresolved() = " + remote.isUnresolved());

        try (Socket socket = new Socket()) {
            socket.connect(remote, 3_000);
        } catch (UnknownHostException e) {
            System.out.println("connect() -> UnknownHostException: " + e.getMessage());
        }
    }
}

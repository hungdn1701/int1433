package netlab.endpoint;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * V08, đọc thêm: không có chương trình nào nghe ở port đích thì kernel phía kia trả RST,
 * và connect() ném ConnectException ngay, không phải chờ timeout.
 *
 * <p>Chạy: {@code java -cp out netlab.endpoint.RefusedDemo}
 */
public final class RefusedDemo {

    public static void main(String[] args) throws IOException {
        // port 9198: không có service nào nghe trên máy chạy demo
        InetSocketAddress remote = new InetSocketAddress("127.0.0.1", 9198);
        long start = System.nanoTime();
        try (Socket socket = new Socket()) {
            socket.connect(remote, 3_000);
        } catch (ConnectException e) {
            long ms = (System.nanoTime() - start) / 1_000_000;
            System.out.println("connect() -> ConnectException: " + e.getMessage());
            System.out.println("after " + ms + " ms (timeout was 3000 ms)");
        }
    }
}

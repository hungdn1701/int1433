package netlab.first;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * V08: client TCP tối thiểu. Kết nối tới 127.0.0.1:9199 (active open), gửi một dòng, in dòng trả về.
 *
 * <p>Chạy ở terminal thứ hai, sau khi server đã chạy: {@code java -cp out netlab.first.FirstClient}
 * <br>Chạy khi chưa có server: connect bị từ chối ngay (connection refused).
 */
public final class FirstClient {

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("127.0.0.1", 9199)) {  // active open: kết nối tới server
            System.out.println("connected: local port " + socket.getLocalPort()
                    + ", server port " + socket.getPort());
            var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), UTF_8));
            var in = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
            out.write("hello\n");  // gửi một dòng
            out.flush();
            System.out.println("reply: " + in.readLine());  // đọc dòng trả về
        }
    }
}

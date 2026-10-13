package netlab.first;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * V08: server TCP tối thiểu. Mở port 9199 rồi chờ (passive open), nhận đúng một kết nối,
 * đọc một dòng và trả lại dòng đó. Bản tra cứu thật ở V10.
 *
 * <p>Chạy ở terminal thứ nhất: {@code java -cp out netlab.first.FirstServer}
 */
public final class FirstServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket listener = new ServerSocket(9199)) {  // passive open: mở port, chờ
            System.out.println("listening on port " + listener.getLocalPort());
            try (Socket connected = listener.accept()) {  // lấy một kết nối khỏi hàng đợi
                System.out.println("accepted: local port " + connected.getLocalPort()
                        + ", client port " + connected.getPort());
                var in = new BufferedReader(new InputStreamReader(connected.getInputStream(), UTF_8));
                var out = new BufferedWriter(new OutputStreamWriter(connected.getOutputStream(), UTF_8));
                String line = in.readLine();  // đọc một dòng
                out.write("echo: " + line + "\n");  // trả lại một dòng
                out.flush();
            }
        }
    }
}

package netlab.endpoint;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/** V08, đọc thêm: tham số tường minh của ServerSocket (port, backlog, bind address) và cách in endpoint. */
final class SlideSnippets {

    private SlideSnippets() {
    }

    /** Slide "ServerSocket bind vào listening address": ba tham số khai báo tường minh. */
    static ServerSocket bindExplicit(int port, int backlog, InetAddress bindAddress)
            throws IOException {
        // port 0: hệ điều hành cấp một port còn trống, đọc lại bằng getLocalPort()
        return new ServerSocket(port, backlog, bindAddress);
    }

    /** In endpoint mà không kích hoạt reverse lookup (khác với getHostName). */
    static String show(InetSocketAddress endpoint) {
        return endpoint.getHostString() + ":" + endpoint.getPort();
    }
}

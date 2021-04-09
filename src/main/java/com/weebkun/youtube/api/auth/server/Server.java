package com.weebkun.youtube.api.auth.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * server class for listening on the localhost for the redirect from oauth authorisation.
 */
public class Server {

    private final HttpServer server;

    /**
     * creates a new server that listens on localhost:5000.
     * @param host the host to host this server on
     * @param port the port to listen on
     * @param path the path of the redirect
     * @throws IOException if this server cannot bind to the host or if the server is already bound.
     */
    public Server(String host, int port, String path) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(host, port), 10);
        this.server.createContext("/");
        this.server.setExecutor(Executors.newFixedThreadPool(10));
        this.server.start();
    }

    /**
     * creates a server using the default values of 'localhost' and port 5000.
     * @param path the path of the redirect
     * @throws IOException if this server cannot bind to localhost or if it is already bound.
     */
    public Server(String path) throws IOException{
        this.server = HttpServer.create(new InetSocketAddress("localhost", 5000), 10);
        // handle /auth path
        this.server.createContext("/auth", new RedirectHandler());
        this.server.setExecutor(Executors.newFixedThreadPool(10));
        this.server.start();
    }
}

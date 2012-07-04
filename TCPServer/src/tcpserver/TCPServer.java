package tcpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;

public class TCPServer {

    private int serverPort = 15000;
    private ServerSocket serverSocket;
    private Hashtable<String, ConnectionService> connections = new Hashtable<String, ConnectionService>();

    public TCPServer() {
        try {
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Waiting...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connected: " + socket);
                ConnectionService service = new ConnectionService(socket);
                service.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new TCPServer();
    }

    class ConnectionService extends Thread {

        private Socket socket;
        private BufferedReader inputReader;
        private PrintWriter outputWriter;
        private String username;

        public ConnectionService(Socket socket) {
            this.socket = socket;
            try {
                inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                outputWriter = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        @Override
        public void run() {
            try {
                while (true) {

                    String receivedMessage = inputReader.readLine();
                    System.out.println(receivedMessage);
                    StringTokenizer stoken = new StringTokenizer(receivedMessage);
                    String fargument = stoken.nextToken();
                    if (receivedMessage.startsWith("Users")) {
                        sendMessage(getUsers());
                    } else if (receivedMessage.startsWith("Signout")) {
                        connections.remove(this.username);
                        sendToAnyone(username + " has Signed Out.");
                    } else if (receivedMessage.startsWith("Signin")) {
                        username = stoken.nextToken();
                        if (connections.containsKey(username)) {
                            sendMessage("username taken apply with a different username");                                                  //Βγαινει και συνεχίζει το loop?
                        } else {
                            sendToAnyone(username + " signed in");
                            connections.put(username, this);
                        }
                    } else if (fargument.equals("Message")) {
                        String sargument = stoken.nextToken("\n");
                        this.sendToAnyone(username + " : " + sargument);
                    } else if (fargument.equals("MessageTo")) {
                        String sargument = stoken.nextToken();
                        String targument = stoken.nextToken("\n");
                        this.sendToSomeOne(sargument, username + " : " + targument);
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            } finally {
                outputWriter.close();
            }

        }

        protected void sendMessage(String message) {
            outputWriter.println(message);
        }

        private String getUsers() {
            Iterator iterator = connections.keySet().iterator();
            StringBuilder users = new StringBuilder("||");
            while (iterator.hasNext()) {
                users.append(iterator.next() + "||");
            }
            return users.toString();
        }

        private void sendToSomeOne(String usrname, String message) {
            ConnectionService connection = connections.get(usrname);
            connection.sendMessage(message);
        }

        private void sendToAnyone(String message) {
            Iterator iterator = connections.keySet().iterator();
            while (iterator.hasNext()) {
                ConnectionService connection = connections.get(iterator.next());
                connection.sendMessage(message);
            }
        }
    }
}

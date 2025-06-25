package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        StudentRepository studentRepository=new StudentRepository();
        ServerSocket serverSocket=new ServerSocket(6666);
        while (true) {
            Runnable runnable = new MultithreadedServer(studentRepository, serverSocket);
            new Thread(runnable).start();
        }


    }
}

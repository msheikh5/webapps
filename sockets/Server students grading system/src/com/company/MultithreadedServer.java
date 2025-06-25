package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class MultithreadedServer implements Runnable{
    StudentRepository studentRepository;
    Socket s;
    MultithreadedServer(StudentRepository studentRepository, ServerSocket serverSocket) throws IOException {
        this.studentRepository=studentRepository;
         s=serverSocket.accept();

    }
    @Override
    public void run() {
        try {
            DataInputStream dis=new DataInputStream(s.getInputStream());
            DataOutputStream dout =new DataOutputStream(s.getOutputStream());
            int id;
            while (true) {
                id = dis.read();
                System.out.println(id);
                String password = dis.readUTF();
                System.out.println(password);
                if (studentRepository.login(id, password)) {
                    dout.writeBoolean(true);
                    dout.writeUTF("you are login correctly\n");
                    break;
                }
                else {
                    dout.writeBoolean(false);
                    dout.writeUTF("your id or password is wrong , please try again");
                }
            }
            int choice=0;
            while(choice!=3){
                choice= dis.read();
                switch (choice){
                    case 1:
                        dout.writeUTF(studentRepository.showMarks(id));
                        break;
                    case 2:
                        int courseId=dis.read();
                        try {
                            dout.writeUTF(studentRepository.getCourseInformation(id,courseId));
                        }
                        catch (SQLException e)
                        {
                            dout.writeUTF("no course with this id");
                        }
                        break;
                    case 3:
                        break;

                }
            }
        }
        catch (Exception e){

        }

    }
}

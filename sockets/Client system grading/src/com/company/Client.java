package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
	// write your code here
        Socket s=new Socket("localhost",6666);
        DataInputStream dis=new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("please enter your id: ");
            dout.write(scanner.nextInt());
            System.out.println("please enter your password: ");
            dout.writeUTF(scanner.next());
            boolean login=dis.readBoolean();
            if (login)
            {
                System.out.println(dis.readUTF());
                break;
            }
            else
                System.out.println(dis.readUTF());
            }

        while (true)
        {
            System.out.println("welcome to the system , please choose one of this choices\n " +
                    "1. show your marks\n 2. show statistics of specific  course\n " +
                    "3. log out");
            int choice = 0;
                 choice = scanner.nextInt();
                 dout.write(choice);
                if (choice==1)
                        System.out.println(dis.readUTF());
                    else if (choice==2) {
                    System.out.println("Enter course id: ");
                    int courseId = scanner.nextInt();
                    dout.write(courseId);
                    System.out.println(dis.readUTF());
                }
                    else
                        break;


            }
        }

    }


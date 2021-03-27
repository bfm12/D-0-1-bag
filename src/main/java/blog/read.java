package main.java.blog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class read {
    public static void main(String[] args) throws IOException {
        System.out.println("请输入文件的路径：");
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        File file = new File(path);
        if(file.exists()){
            InputStream input = new FileInputStream(file);
            byte[] data = new byte[1024];
            int len=input.read(data);
            System.out.println(data);
            System.out.println(new String(data,0,len));
        }
    }
}

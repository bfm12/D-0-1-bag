package main.java.blog;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class read1 {
        public static void main(String[] args) throws IOException
        {
            System.out.println("请输入文件的路径：");
            Scanner in = new Scanner(System.in);
            String path = in.nextLine();
            //BufferedReader是可以按行读取文件
            FileInputStream inputStream = new FileInputStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));



            String str = null;
            str = bufferedReader.readLine();
            while(str != null)
            {
                str = bufferedReader.readLine();
                System.out.println(str);
                String str1="The diemnsion is d=3*10,the cubage of knapsack is";
                //if(str.indexOf("RO")>=0 ||str1.indexOf("EL")>=0 ||str1.indexOf("RO")>=0)
                if(str.contains("the cubage of knapsack is")){
                    str = str.trim();
                    int j=0;
                    String [] str2 = new String [20];
                    if (str!= null && !"".equals(str)) {
                        for (int i = str.length()-1; i>0; i--) {
                            if(str.charAt(i)!=',' && str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                                str2[j] += str.charAt(i);
                            }
                            System.out.println("!!!!!!!!!!!!!!!!!the cubage of knapsack is "+str2);
                        }
                    }
                    System.out.println("字符串中提取的数字为： " + str2);
                }

            }

            //close
            inputStream.close();
            bufferedReader.close();

        }
}

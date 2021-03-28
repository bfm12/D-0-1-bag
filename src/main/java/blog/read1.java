package main.java.blog;

import com.sun.org.apache.xpath.internal.objects.XString;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class read1 {
        public static void main(String[] args) throws IOException
        {

            //BufferedReader是可以按行读取文件
            FileInputStream inputStream = new FileInputStream("E:\\idea_workspace\\data\\idkp1-10.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String str = null;
//            读取文件中的一行数据

//            存放物品价值和质量的列表
            ArrayList<String> profitList = new ArrayList<>();
            ArrayList<String> weightList = new ArrayList<>();
            while((str = bufferedReader.readLine()) != null)
            {
                if(str.contains("The profit of")){
                    profitList.add(bufferedReader.readLine());
                }
                if(str.contains("The weight of")){
                    weightList.add(bufferedReader.readLine());
                }
            }
//            System.out.println("======================这是每一组所有的物品价值======================");
//            for (String s : profitList) {
//                System.out.println(s);
//            }
//            System.out.println("================================这是每一组物品的重量================================");
//            for (String s : weightList) {
//                System.out.println(s);
//            }

            ArrayList<Integer> integers = new ArrayList<>();
//            遍历物品重量，将其分割出来
            for (String s : profitList) {
                String replace = s.replace(".", "");
                String[] split = replace.split(",");
                for (String s1 : split) {
//                    字符型转整形
                    int profit  =  Integer.parseInt(s1);
                    System.out.println(profit);
                }
                System.out.println();
                System.out.println();
            }
//            遍历物品重量，将其分割出来
            for (String s : weightList){
                String replace = s.replace(".", "");
                String[] split = replace.split(",");
                for (String s1 : split){
//                    字符型转整形
                    int weight = Integer.parseInt(s1);
                    System.out.println(weight);
                }
                System.out.println();
                System.out.println();
            }
                //close
            inputStream.close();
            bufferedReader.close();

        }
}

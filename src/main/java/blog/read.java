package main.java.blog;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class read {

    private static int profits[][] = new int[12][4000];
    private static int weights[][] = new int[12][4000];



    public static void main(String[] args) throws IOException
    {

        Scanner scanner = new Scanner(System.in);
        int choice=0;
        do {
            System.out.println("|*************************************************************************");
            System.out.println("|**************************输入1：绘制散点图********************************");
            System.out.println("|**************************输入2：数据项集排序******************************");
            System.out.println("|**************************输入3：D{0-1}问题求解****************************");
            System.out.println("|**************************输入4：D{0-1}问题求解并保存文件*******************");
            System.out.println("|**************************输入5：退出程序**********************************");
            System.out.println("|**************************************************************************" +
                    "" +
                    "");
            System.out.println("请输入你的选择：");
            choice=scanner.nextInt();
            if (choice==1) {
                readFile();
                draw();

            }else if(choice==2){
                readFile();
                dataOrder(scanner);
            }
        }while(choice!=5);
    }

    private static void dataOrder(Scanner scanner) {
        System.out.println("输入将要排序的组数:");
        int group=scanner.nextInt();
        List<item> items = new ArrayList<>();
        for (int i1 = 0; i1 < profits[group - 1].length; i1++) {
            if(profits[group - 1][i1]!=0){
                item item = new item();
                item.setProfit(profits[group - 1][i1]);
                item.setWeight(weights[group - 1][i1]);
                items.add(item);
            }

        }
        ArrayList<sortList> sortLists = new ArrayList<>();
        for (int k = 0; k < items.size(); k=k+3) {
            List<item> items1 = items.subList(k, k + 3);
            sortList sortList = new sortList();
            sortList.setData(items1);
            item item = items1.get(items1.size() - 1);
            float v = item.getProfit() / new Float(item.getWeight());
            sortList.setRate(v);
            sortLists.add(sortList);
        }

        Collections.sort(sortLists);
        for (sortList sortList : sortLists) {
            System.out.println(sortList.toString());
        }
    }

    /**
     * 画散点图
     * @throws IOException
     */
    private static void drawScatter(int[] profitList,int[] weightList) throws IOException {
        //设置散点图数据集
        //绘制每一个点
        XYSeries firefox = new XYSeries("背包问题");
        for (int i = 0,j=0; i < profitList.length; i++,j++) {
            firefox.add(weightList[i],profitList[j]);
        }
        //添加到数据集
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(firefox);

        //实现简单的散点图，设置基本的数据
        JFreeChart freeChart = ChartFactory.createScatterPlot(
                "数据散点图",// 图表标题
                "重量",//x轴方向数据标签
                "价值",//y轴方向数据标签
                dataset,//数据集，即要显示在图表上的数据
                PlotOrientation.VERTICAL,//设置方向
                true,//是否显示图例
                true,//是否显示提示
                false//是否生成URL连接
        );

        //以面板显示
        ChartPanel chartPanel = new ChartPanel(freeChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 400));

        //创建一个主窗口来显示面板
        JFrame frame = new JFrame("散点图");
        frame.setLocation(500, 200);
        frame.setSize(1000, 800);

        //将主窗口的内容面板设置为图表面板
        frame.setContentPane(chartPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void draw() throws IOException {
        //绘制散点图
        System.out.println("请输入所要绘制的散点图的组数： ");
        Scanner scanner = new Scanner(System.in);
        int group=scanner.nextInt();
        drawScatter(profits[group-1],weights[group-1]);
    }
    private static void readFile() throws IOException {
        String rootPath="E:\\idea_workspace\\data\\idkp1-10.txt";
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入文件名 (1、idkp1-10；  2、sdkp1-10；  3、udkp1-10；  4、wdkp1-10)");
        int fileName=scanner.nextInt();
        switch (fileName){
            case 1:
                rootPath="E:\\idea_workspace\\data\\idkp1-10.txt";
                break;
            case 2:
                rootPath="E:\\idea_workspace\\data\\sdkp1-10.txt";
                break;
            case 3:
                rootPath="E:\\idea_workspace\\data\\udkp1-10.txt";
                break;
            case 4:
                rootPath="E:\\idea_workspace\\data\\wdkp1-10.txt";
                break;
        }
        //BufferedReader是可以按行读取文件
        FileInputStream inputStream = new FileInputStream(rootPath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = null;
        //读取文件中的一行数据

        //存放物品价值和质量的列表
        ArrayList<String> profitList = new ArrayList<>();
        ArrayList<String> weightList = new ArrayList<>();
        String[] cubage = new String[12];
        int packnum=0;
        while((str = bufferedReader.readLine()) != null)
        {
            if(str.contains("cubage of knapsack")){
                String replace = str.replace(".", "");
                String[] s = replace.split(" ");
                cubage[packnum]=s[s.length-1];
                //System.out.println("==========请输入背包的容量："+cubage[packnum]);
                packnum++;
            }
            if(str.contains("The profit of")){
                profitList.add(bufferedReader.readLine());
            }
            if(str.contains("The weight of")){
                weightList.add(bufferedReader.readLine());
            }
        }

        //遍历物品重量，将其分割出来
        //System.out.println("输出所有物品价值：");
        int i=0,j=0;
        for (String s : profitList) {
            j=0;
            String replace = s.replace(".", "");
            String[] split = replace.split(",");
            for (String s1 : split) {
                //字符型转整形
                profits[i][j] =  Integer.parseInt(s1);
                //System.out.println("输出第"+i+"组第"+j+"个物品的价值："+profits[i][j]);
                j++;
            }
            i++;
        }

        //遍历物品重量，将其分割出来
        //System.out.println("\n输出所有物品重量：\n");
        int m=0,n;
        for (String s : weightList) {
            n=0;
            String replace = s.replace(".", "");
            String[] split = replace.split(",");
            for (String s1 : split){
                //字符型转整形
                weights[m][n] = Integer.parseInt(s1);
                //System.out.println("输出第"+m+"组第"+n+"个物品的重量："+weights[m][n]);
                n++;
            }
            m++;
        }
    }
}

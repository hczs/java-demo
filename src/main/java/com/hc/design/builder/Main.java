package com.hc.design.builder;

/**
 * @author ：hc
 * @date ：Created in 2021/9/8 21:10
 * @modified By：
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("编写纯文本文档");
        TextBuilder textBuilder = new TextBuilder();
        Director textDirector = new Director(textBuilder);
        textDirector.construct();
        String textBuilderResult = textBuilder.getResult();
        System.out.println(textBuilderResult);

        System.out.println("编写html文档");
        HtmlBuilder htmlBuilder = new HtmlBuilder();
        Director htmlDirector = new Director(htmlBuilder);
        htmlDirector.construct();
        String htmlBuilderResult = htmlBuilder.getResult();
        System.out.println(htmlBuilderResult + "文件编写完成！");
    }
}

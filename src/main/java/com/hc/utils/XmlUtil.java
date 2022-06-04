package com.hc.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.xml.sax.InputSource;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author hc
 * @date Created in 2022/6/4 18:49
 * @modified
 */
public class XmlUtil {

    private static final XStream xStream = new XStream(new StaxDriver());

    static {
        // 允许注解
        xStream.autodetectAnnotations(true);
        // 设置权限为最低
        xStream.addPermission(AnyTypePermission.ANY);
        // 忽略未知元素
        xStream.ignoreUnknownElements();
    }

    private XmlUtil() {}

    /**
     * bean 转 xml
     * @param obj bean
     * @param cls bean class
     * @return
     */
    public static String beanToXml(Object obj, Class<?> cls) {
        xStream.processAnnotations(cls);
        String xml = xStream.toXML(obj);
        try {
            // 下面操作是格式化 xml
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            // 设置缩进,  yes:有缩进 no:没有缩进
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            // 设置是否忽略xml声明，　yes:忽略　no:不忽略
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            // 设置编码
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
            StreamResult res = new StreamResult(new ByteArrayOutputStream());
            transformer.transform(xmlSource, res);
            xml = res.getOutputStream().toString();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return xml;
    }

    /**
     * xml 转 bean
     * @param xml xml 字符串
     * @param cls bean class
     * @param <T> bean type
     * @return bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T xmlToBean(String xml, Class<T> cls) {
        xStream.processAnnotations(cls);
        return (T) xStream.fromXML(xml);
    }

}

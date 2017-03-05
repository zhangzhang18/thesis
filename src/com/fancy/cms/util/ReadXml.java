package com.fancy.cms.util;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.fancy.cms.model.TFormInfo;

/**
 * 利用dom4j进行XML编程
 * 
 * @author wangyp
 * @since 2007.8.10
 */
public class ReadXml {

	/**
	 * 遍历整个XML文件，获取所有节点的值与其属性的值，并放入HashMap中
	 * 
	 * @param filename
	 *            String 待遍历的XML文件（相对路径或者绝对路径）
	 * @param hm
	 *            HashMap
	 *            存放遍历结果，格式：<nodename,nodevalue>或者<nodename+attrname,attrvalue>
	 */

	public static List<String> iterateCcflowXML(String filename, LinkedList<String> list, TFormInfo tFormInfo) {
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(new File(filename));
			Element root = document.getRootElement();
			Element memberElm = root.element("Sys_MapData");
			String tableText = memberElm.elementText("PTable");
			tFormInfo.setTabletext(tableText);
			@SuppressWarnings("unchecked")
			List<Node> nodes = root.elements("Sys_MapAttr");
			for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
				Element elm = (Element) it.next();
				String key = elm.elementText("KeyOfEn");
				String name = elm.elementText("Name");
				String finalString = key + "|" + name;
				// do something
				if (key != null && !key.endsWith("OID"))
					list.add(finalString);
			}

		}
		catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}

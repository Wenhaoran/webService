package cn.digitalpublishing.ufinterface;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import cn.com.daxtech.framework.exception.CcsException;

import uk.ltd.getahead.dwr.util.Logger;

/**
 * 生成用友数据传输xml文件工具类
 * @author liminghao
 */
public class XMLUtil {
	
	public static final Logger log = Logger.getLogger(XMLUtil.class);
	
	/**
	 * 用友xml根名称
	 */
	private static final String UF_ROOT = "ufinterface";
	
	public static synchronized String BuildXMLDoc(Map<String,String> rootMap,Map<String,String> headMap, Map<String,String> bodyMap, Map<String,String> rootTagAttrMap) throws IOException, JDOMException, CcsException {
		log.info("ufinterface.BuildXMLDoc.starting...");
		String xmlStr = "";
		try {
			// 创建根节点 并设置它的属性 ;
			Element root = new Element(UF_ROOT);
			if(!rootMap.isEmpty()){
				Iterator<Entry<String, String>> i = rootMap.entrySet().iterator();
				while (i.hasNext()) {
					Map.Entry<String, String> e = i.next();
					root.setAttribute(e.getKey(),e.getValue());
				}
			}
			
			// 将根节点添加到文档中；
			Document doc = new Document(root);
			Element  roottag= new Element(root.getAttributeValue("roottag"));
			if(!rootTagAttrMap.isEmpty()){
				Iterator<Entry<String, String>> i = rootTagAttrMap.entrySet().iterator();
				while (i.hasNext()) {
					Map.Entry<String, String> e = i.next();
					roottag.setAttribute(e.getKey(),e.getValue());
				}
			}
			root.addContent(roottag);
			//添加head中的内容
			if(!headMap.isEmpty()){
				Element head = new Element(root.getAttributeValue("roottag")+"_head");
				roottag.addContent(head);
				Iterator<Entry<String, String>> i = headMap.entrySet().iterator();
				while (i.hasNext()) {
					Map.Entry<String, String> e = i.next();
					head.addContent(new Element(e.getKey()).setText(e.getValue()));
				}
			}
			
			//添加body中的内容
			if (!bodyMap.isEmpty()) {
				Element body = new Element(root.getAttributeValue("roottag")+"_body");
				roottag.addContent(body);
				Element entry = new Element("entry");
				Iterator<Entry<String, String>> i = bodyMap.entrySet().iterator();
				while (i.hasNext()) {
					Map.Entry<String, String> e = i.next();
					entry.addContent(new Element(e.getKey()).setText(e.getValue()));
				}
				body.addContent(entry);
			}
			
			// 使xml文件 缩进效果
			Format format = Format.getPrettyFormat();
			XMLOutputter xmlOut = new XMLOutputter(format);
			StringWriter sw = new StringWriter();
			xmlOut.output(doc, sw);
			xmlStr = sw.getBuffer().toString();
			log.info("ufinterface.BuildXMLDoc.end");
		} catch (Exception e) {
			log.error("ufinterface.BuildXMLDoc.error" + e);
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "ufinterface.BuildXMLDoc.error", e);
		}
		
		return xmlStr;
	}
}

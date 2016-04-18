package cn.digitalpublishing.ufinterface;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.codehaus.jackson.map.ObjectMapper;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import uk.ltd.getahead.dwr.util.Logger;
import cn.com.daxtech.framework.exception.CcsException;

/**
 * 用友接口连接工具类
 * @author liminghao
 */
public class UFConnectionUtil {

	public static final Logger log = Logger.getLogger(UFConnectionUtil.class);
	private static final String ACCOUNT = "01";
	private static final String RECEIVER = "FLZS";
	private static final String BASE_URL = getProperties().get("url").toString();
	private static final String URL = BASE_URL + "?account="+ACCOUNT+"&receiver="+RECEIVER;

	/**
	 * 上传文件方法
	 * @param xmlStr
	 * @throws Exception
	 */
	public static void uploadFile(String xmlStr) throws Exception {
		try {
			log.info("uploadFile.URL: " + URL);
			URL realURL = new URL(URL);
			HttpURLConnection connection = (HttpURLConnection) realURL.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "text/html");
			connection.setRequestMethod("POST");
			BufferedOutputStream out = new BufferedOutputStream(connection.getOutputStream());	// URL连接输出流
			out.write(xmlStr.getBytes());
			out.close();
			
			ArrayList<LogVO> resultLists = getStringResult(connection);
			for (int i = 0; i < resultLists.size(); i++) {
				LogVO vo = (LogVO) resultLists.get(i);
//				System.out.println("*****************************************************");
//				System.out.println("主键:" + vo.getBdocid());
//				System.out.println("文件名:" + vo.getFilename());
//				System.out.println("导入结果标志:" + vo.getResultcode());
//				System.out.println("反馈信息:" + vo.getResultdescription());
//				System.out.println("生成的凭证信息:" + vo.getContent());
//				System.out.println("*****************************************************");
				log.info("UFConnectionUtil.uploadFile.result: " + new ObjectMapper().writeValueAsString(vo));
			}
			// 关闭连接
			connection.disconnect();
			log.info("ufinterface.UFConnectionUtil.uploadFile.success");
		} catch (Exception e) {
			log.error("ufinterface.UFConnectionUtil.error"+ e);
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "ufinterface.UFConnectionUtil.error", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<LogVO> getStringResult(HttpURLConnection connection) throws Exception {
		InputStream inputStream = null;
		// 存放日志结果的集合
		ArrayList<LogVO> resultLists = new ArrayList<LogVO>();
		try {
			if (connection != null) {
				inputStream = connection.getInputStream();
				// 将获取的数据流转换为Document
				SAXBuilder builder = new SAXBuilder();
				Document d = builder.build(inputStream);
				// 解析该Document
				Element root = d.getRootElement();
				List<Element> list = root.getChildren();
				for (Element element : list) {
					LogVO vo = new LogVO();
					List<Element> tmpList = element.getChildren();
					for (Element tmp : tmpList) {
						if ("bdocid".equals(tmp.getName())) {
							vo.setBdocid(tmp.getValue());
						} else if ("filename".equals(tmp.getName())) {
							vo.setFilename(tmp.getValue());
						} else if ("resultcode".equals(tmp.getName())) {
							vo.setResultcode(tmp.getValue());
						} else if ("resultdescription".equals(tmp.getName())) {
							vo.setResultdescription(tmp.getValue());
						} else if ("content".equals(tmp.getName())) {
							vo.setContent(tmp.getValue());
						}
					}
					resultLists.add(vo);
				}
				
			}
		} catch (Exception e) {
			log.error("无法读取数据流: " + e);
			throw new Exception("无法读取数据流.", e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultLists;
	}
	
	@SuppressWarnings("rawtypes")
	public static Map getProperties() {
		Properties prop = new Properties();
		InputStream inputFile = null;
		try {
			inputFile = UFConnectionUtil.class.getResourceAsStream("/ufinterface.properties");
			prop.load(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return (Map) prop;
	}
}

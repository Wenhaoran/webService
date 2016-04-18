package cn.digitalpublishing.ufinterface;

/**
 * 存放日志
 * <P><B>类功能名称</B></P>
 * <P>功能说明:
 * <BR>
 * @author 何冰
 * @version 1.0
 * @date 2011-9-21
 */
public class LogVO {

	private String bdocid; //主键
	private String filename; //文件名
	private String resultcode; //导入结果标志
	private String resultdescription; //反馈信息
	private String content; //生成的凭证信息

	public String getBdocid() {
		return bdocid;
	}
	public void setBdocid(String bdocid) {
		this.bdocid = bdocid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public String getResultdescription() {
		return resultdescription;
	}
	public void setResultdescription(String resultdescription) {
		this.resultdescription = resultdescription;
	}
}
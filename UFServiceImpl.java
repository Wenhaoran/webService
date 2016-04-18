package cn.digitalpublishing.ufinterface;

import java.util.HashMap;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.service.impl.BaseServiceImpl;
import uk.ltd.getahead.dwr.util.Logger;

/**
 * 用友NC接口实现类
 * @author liminghao
 */
public class UFServiceImpl extends BaseServiceImpl implements IUFService{

	public static final Logger log = Logger.getLogger(UFServiceImpl.class);
	
	/**
	 * 补贴款收款单(销售收款单)
	 * 
	 * headMap.put("djdl","");	//单据大类，不用修改
	 * headMap.put("prepay","n");	//是否预收预付标志 非空字段
	 * headMap.put("corp","");	//单位编码  非空字段，需要基础数据对照(公司目录)
	 * headMap.put("businessType","D2");	//交易类型 非空字段
	 * headMap.put("saleType","");	//销售类型编码,如果有数据，需要基础数据对照(业务类型)
	 * headMap.put("sysid","");	//系统编号 0应收、收款单，1应付、付款单，2报账中心 非空字段
	 * headMap.put("initFlag","n");	//是否期初单据 非空字段
	 * headMap.put("billDate","");	//单据日期 非空字段
	 * headMap.put("effectdate","");	//起效日期 非空字段
	 * headMap.put("inputOp","");	//录入人 非空字段,需要基础数据对照(操作员)
	 * headMap.put("checkman","");	//审核人 非空字段,需要基础数据对照(操作员)
	 * headMap.put("billstatus","");	//单据状态
	 * headMap.put("scomment","");	//备注，可空
	 * headMap.put("freeitem30","");	//英捷特系统单据号 非空字段
	 * 
	 * bodyMap.put("customer","");	//客户档案 非空
	 * bodyMap.put("deptid","");	//部门编码可空，如果有值需要建立对照(部门档案)
	 * bodyMap.put("employeeId","");	//业务员编码，可空，如果有值需要建立对照(人员档案)
	 * bodyMap.put("orderId","");	//订单号，可空
	 * bodyMap.put("inventoryId","");	//存货编码，如果有值，需要基础数据对照(存货档案)
	 * bodyMap.put("sum_direction","1");	//金额方向 非空字段
	 * bodyMap.put("digest","");	//摘要
	 * bodyMap.put("accsubjId","");	//收支项目，或者为空，如果有值一定要建立对照(收支项目)
	 * bodyMap.put("currencyId","人民币");	//币种编码 非空字段，需要基础数据对照(外币档案)
	 * bodyMap.put("original_exchange_rate","");	//本币汇率 非空字段，默认1
	 * bodyMap.put("debit_original_sum","");	//借方原币金额 非空字段
	 * bodyMap.put("debit_local_sum","");	//借方本币金额 非空字段，应与debit_original_sum值相同
	 * bodyMap.put("tax_rate","");	//税率，可空
	 * bodyMap.put("debit_original_tax","");	//借方原币税金 非空字段
	 * bodyMap.put("debit_local_tax","");	//借方本币税金 非空字段
	 * bodyMap.put("debit_original_noTax","");	//借方原币无税金额 非空字段
	 * bodyMap.put("debit_local_noTax","");	//借方本币无税金额 非空字段
	 * bodyMap.put("original_balance","");	//原币余额 非空字段，应与debit_original_sum值相同
	 * bodyMap.put("local_balance","");	//本币余额 非空字段
	 * bodyMap.put("object","");	//往来对象 非空字段，应收单中应取值为0，应付单中应为1
	 * bodyMap.put("invoicebillno","");	//NC发票号，用于核销 非空字段
	 * bodyMap.put("pay_agreementId","");	//收款协议号，或者为空，如果有值一定要建立对照(收付款协议)
	 * 
	 * rootAttrMap.put("id","");
	 * rootAttrMap.put("subdoctype","test");
	 */
	@Override
	public void subsidyReceivable(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		
		//TODO 未测试
		log.info("调用 【补贴款收款单】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("roottag", "voucher");
			rootMap.put("billtype", "D2");
			rootMap.put("subtype", "run");
			rootMap.put("replace", "Y");
			rootMap.put("receiver", "0001");
			rootMap.put("sender", "1101");
			rootMap.put("proc", "add");
			rootMap.put("isexchange", "Y");
			rootMap.put("filename", "收款单demo.xml");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("subsidyReceivable.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.subsidyReceivable.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.subsidyReceivable.error", e);
		}
		log.info("调用 【补贴款收款单】 接口方法结束...");
	}

	/**
	 * 材料出库
	 */
	@Override
	public void materialLibrary(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【材料出库】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("roottag", "ia_bill");
			rootMap.put("billtype", "IA");
			rootMap.put("replace", "Y");
			rootMap.put("receiver", "0101");
			rootMap.put("sender", "erp");
			rootMap.put("proc", "add");
			rootMap.put("isexchange", "Y");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("materialLibrary.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.materialLibrary.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.materialLibrary.error", e);
		}
		log.info("调用 【材料出库】 接口方法结束...");
	}

	/**
	 * 材料应付单
	 */
	@Override
	public void materialHandle(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【材料应付单】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("roottag", "voucher");
			rootMap.put("billtype", "D1");
			rootMap.put("isexchange", "Y");
			rootMap.put("replace", "Y");
			rootMap.put("receiver", "0101");
			rootMap.put("sender", "erp");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("materialHandle.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.materialHandle.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.materialHandle.error", e);
		}
		log.info("调用 【材料应付单】 接口方法结束...");
	}

	/**
	 * 采购入库(图书纸张)
	 */
	@Override
	public void purchaseStorage(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【采购入库(图书纸张)】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("billtype", "IA");
			rootMap.put("isexchange", "Y");
			rootMap.put("replace", "Y");
			rootMap.put("roottag", "ia_bill");
			rootMap.put("sender", "erp");
			rootMap.put("receiver", "0101");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("purchaseStorage.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.purchaseStorage.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.purchaseStorage.error", e);
		}
		log.info("调用 【采购入库(图书纸张)】 接口方法结束...");
	}

	/**
	 * 产成品入库
	 */
	@Override
	public void finishedProductStorage(Map<String, String> headMap,Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【产成品入库】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("billtype", "IA");
			rootMap.put("isexchange", "Y");
			rootMap.put("replace", "Y");
			rootMap.put("roottag", "ia_bill");
			rootMap.put("sender", "erp");
			rootMap.put("receiver", "0101");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("finishedProductStorage.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.finishedProductStorage.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.finishedProductStorage.error", e);
		}
		log.info("调用 【产成品入库】 接口方法结束...");
	}

	/**
	 * 存货(材料)
	 */
	@Override
	public void inventoryMaterial(Map<String, String> headMap,Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【存货(材料)】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("roottag", "basdoc");
			rootMap.put("billtype", "bs");
			rootMap.put("replace", "Y");
			rootMap.put("isexchange", "Y");
			rootMap.put("sender", "erp");
			rootMap.put("receiver", "0101");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("inventoryMaterial.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.inventoryMaterial.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.inventoryMaterial.error", e);
		}
		log.info("调用 【存货(材料)】 接口方法结束...");
	}

	/**
	 * 稿费结算(编录经费)应付单
	 */
	@Override
	public void paymentSettlement(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【稿费结算(编录经费)应付单】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("roottag", "voucher");
			rootMap.put("billtype", "D1");
			rootMap.put("isexchange", "Y");
			rootMap.put("replace", "Y");
			rootMap.put("receiver", "0101");
			rootMap.put("sender", "erp");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("paymentSettlement.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.paymentSettlement.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.paymentSettlement.error", e);
		}
		log.info("调用 【稿费结算(编录经费)应付单】 接口方法结束...");
	}

	/**
	 * 稿费预支应付单
	 */
	@Override
	public void advanceRoyaltiesPayableList(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【稿费预支应付单】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("roottag", "voucher");
			rootMap.put("billtype", "D1");
			rootMap.put("isexchange", "Y");
			rootMap.put("replace", "Y");
			rootMap.put("receiver", "0101");
			rootMap.put("sender", "erp");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("advanceRoyaltiesPayableList.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.advanceRoyaltiesPayableList.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.advanceRoyaltiesPayableList.error", e);
		}
		log.info("调用 【稿费预支应付单】 接口方法结束...");
	}

	/**
	 * 稿费暂估应付单
	 */
	@Override
	public void estimatedRoyaltiesPayableList(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【稿费暂估应付单】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("roottag", "voucher");
			rootMap.put("billtype", "D1");
			rootMap.put("isexchange", "Y");
			rootMap.put("replace", "Y");
			rootMap.put("receiver", "0101");
			rootMap.put("sender", "erp");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("estimatedRoyaltiesPayableList.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.estimatedRoyaltiesPayableList.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.estimatedRoyaltiesPayableList.error", e);
		}
		log.info("调用 【稿费暂估应付单】 接口方法结束...");
	}

	/**
	 * 客商(发行)
	 */
	@Override
	public void customerIssue(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		log.info("调用 【客商(发行)】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("roottag", "basdoc");
			rootMap.put("billtype", "bscubas");
			rootMap.put("replace", "Y");
			rootMap.put("isexchange", "Y");
			rootMap.put("sender", "erp");
			rootMap.put("receiver", "0101");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, null);	//生成xml文件字符串
			log.info("customerIssue.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.customerIssue.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.customerIssue.error", e);
		}
		log.info("调用 【客商(发行)】 接口方法结束...");
	}

	/**
	 * 客商(印纸厂)
	 */
	@Override
	public void customerPrintingFactory(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【客商(印纸厂)】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("roottag", "basdoc");
			rootMap.put("billtype", "bscubas");
			rootMap.put("replace", "Y");
			rootMap.put("isexchange", "Y");
			rootMap.put("sender", "erp");
			rootMap.put("receiver", "0101");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("customerPrintingFactory.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.customerPrintingFactory.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.customerPrintingFactory.error", e);
		}
		log.info("调用 【客商(印纸厂)】 接口方法结束...");
	}

	/**
	 * 其他出库
	 */
	@Override
	public void otherOutLibrary(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【其他出库】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("billtype", "IA");
			rootMap.put("isexchange", "Y");
			rootMap.put("replace", "Y");
			rootMap.put("roottag", "ia_bill");
			rootMap.put("sender", "erp");
			rootMap.put("receiver", "0101");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("otherOutLibrary.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.otherOutLibrary.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.otherOutLibrary.error", e);
		}
		log.info("调用 【其他出库】 接口方法结束...");
	}

	/**
	 * 其他入库
	 */
	@Override
	public void otherInLibrary(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【其他入库】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("billtype", "IA");
			rootMap.put("isexchange", "Y");
			rootMap.put("replace", "Y");
			rootMap.put("roottag", "ia_bill");
			rootMap.put("sender", "erp");
			rootMap.put("receiver", "0101");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("otherInLibrary.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.otherInLibrary.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.otherInLibrary.error", e);
		}
		log.info("调用 【其他入库】 接口方法结束...");
	}

	/**
	 * 其他应付
	 */
	@Override
	public void otherPayable(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【其他应付】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("roottag", "voucher");
			rootMap.put("billtype", "D1");
			rootMap.put("isexchange", "Y");
			rootMap.put("replace", "Y");
			rootMap.put("receiver", "0101");
			rootMap.put("sender", "erp");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("otherPayable.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.otherPayable.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.otherPayable.error", e);
		}
		log.info("调用 【其他应付】 接口方法结束...");
	}

	/**
	 * 其他应收单
	 */
	@Override
	public void otherReceivable(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【其他应收单】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("roottag", "voucher");
			rootMap.put("billtype", "D0");
			rootMap.put("isexchange", "Y");
			rootMap.put("replace", "Y");
			rootMap.put("receiver", "0101");
			rootMap.put("sender", "erp");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("otherReceivable.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.otherReceivable.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.otherReceivable.error", e);
		}
		log.info("调用 【其他应收单】 接口方法结束...");
	}

	/**
	 * 生产费用结算应付单
	 */
	@Override
	public void productionCostSettlementPayable(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【生产费用结算应付单】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("roottag", "voucher");
			rootMap.put("billtype", "D1");
			rootMap.put("isexchange", "Y");
			rootMap.put("replace", "Y");
			rootMap.put("receiver", "0101");
			rootMap.put("sender", "erp");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("productionCostSettlementPayable.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.productionCostSettlementPayable.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.productionCostSettlementPayable.error", e);
		}
		log.info("调用 【生产费用结算应付单】 接口方法结束...");
	}

	/**
	 * 生产费用暂估应付单
	 */
	@Override
	public void productionCostEstimatePayable(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【生产费用暂估应付单】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("roottag", "voucher");
			rootMap.put("billtype", "D1");
			rootMap.put("isexchange", "Y");
			rootMap.put("replace", "Y");
			rootMap.put("receiver", "0101");
			rootMap.put("sender", "erp");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("productionCostEstimatePayable.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.productionCostEstimatePayable.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.productionCostEstimatePayable.error", e);
		}
		log.info("调用 【生产费用暂估应付单】 接口方法结束...");
	}

	/**
	 * 项目信息登记单
	 */
	@Override
	public void projectInformationRegistration(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【项目信息登记单】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("account", "01");
			rootMap.put("billtype", "TI55");
			rootMap.put("filename", "");
			rootMap.put("isexchange", "Y");
			rootMap.put("proc", "add");
			rootMap.put("receiver", "xmxx");
			rootMap.put("replace", "Y");
			rootMap.put("roottag", "");
			rootMap.put("sender", "xmxx");
			rootMap.put("subbilltype", "");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("projectInformationRegistration.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.projectInformationRegistration.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.projectInformationRegistration.error", e);
		}
		log.info("调用 【项目信息登记单】 接口方法结束...");
	}

	/**
	 * 销售成本结转单
	 */
	@Override
	public void salesSingleCost(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【销售成本结转单】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("billtype", "IA");
			rootMap.put("isexchange", "Y");
			rootMap.put("replace", "Y");
			rootMap.put("roottag", "ia_bill");
			rootMap.put("sender", "erp");
			rootMap.put("receiver", "0101");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("salesSingleCost.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.salesSingleCost.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.salesSingleCost.error", e);
		}
		log.info("调用 【销售成本结转单】 接口方法结束...");
	}

	/**
	 * 销售应付单
	 */
	@Override
	public void salePayable(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【销售应付单】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("roottag", "voucher");
			rootMap.put("billtype", "D1");
			rootMap.put("isexchange", "Y");
			rootMap.put("replace", "Y");
			rootMap.put("receiver", "0101");
			rootMap.put("sender", "erp");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("salePayable.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.salePayable.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.salePayable.error", e);
		}
		log.info("调用 【销售应付单】 接口方法结束...");
	}

	/**
	 * 销售应收单
	 */
	@Override
	public void salesReceivable(Map<String, String> headMap, Map<String, String> bodyMap, Map<String,String> rootAttrMap) throws Exception {
		// TODO Auto-generated method stub
		log.info("调用 【销售应收单】 接口方法开始...");
		try {
			Map<String,String> rootMap = new HashMap<String,String>();	//设置文件头
			rootMap.put("roottag", "voucher");
			rootMap.put("billtype", "D0");
			rootMap.put("isexchange", "Y");
			rootMap.put("replace", "Y");
			rootMap.put("receiver", "0101");
			rootMap.put("sender", "erp");
			rootMap.put("proc", "add");
			
			String xmlStr = XMLUtil.BuildXMLDoc(rootMap, headMap, bodyMap, rootAttrMap);	//生成xml文件字符串
			log.info("salesReceivable.xmlStr: " + xmlStr);
			UFConnectionUtil.uploadFile(xmlStr);	//上传xml文件流
		} catch (Exception e) {
			log.error("UFServiceImpl.salesReceivable.error" + e); 
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "UFServiceImpl.salesReceivable.error", e);
		}
		log.info("调用 【销售应收单】 接口方法结束...");
	}

}

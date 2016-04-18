package cn.digitalpublishing.ufinterface;

import java.util.Map;

/**
 * 用友NC接口
 * @author liminghao
 */
public interface IUFService {
	/**
	 * 补贴款收款单(销售收款单)
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void subsidyReceivable(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap)throws Exception;
	/**
	 * 材料出库
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void materialLibrary(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap)throws Exception;
	/**
	 * 材料应付单
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void materialHandle(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap)throws Exception;
	/**
	 * 采购入库(图书纸张)
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void purchaseStorage(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap)throws Exception;
	/**
	 * 产成品入库
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void finishedProductStorage(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap) throws Exception;
	/**
	 * 存货(材料)
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void inventoryMaterial(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap) throws Exception;
	/**
	 * 稿费结算(编录经费)应付单
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void paymentSettlement(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap) throws Exception;
	/**
	 * 稿费预支应付单
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void advanceRoyaltiesPayableList(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap) throws Exception;
	/**
	 * 稿费暂估应付单
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void estimatedRoyaltiesPayableList(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap) throws Exception;
	/**
	 * 客商(发行)
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void customerIssue(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap)throws Exception;
	/**
	 * 客商(印纸厂)
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void customerPrintingFactory(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap)throws Exception;
	/**
	 * 其他出库
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void otherOutLibrary(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap)throws Exception;
	/**
	 * 其他入库
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void otherInLibrary(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap) throws Exception;
	/**
	 * 其他应付
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void otherPayable(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap)throws Exception;
	/**
	 * 其他应收单
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void otherReceivable(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap) throws Exception;
	/**
	 * 生产费用结算应付单
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void productionCostSettlementPayable(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap) throws Exception;
	/**
	 * 生产费用暂估应付单
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void productionCostEstimatePayable(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap) throws Exception;
	/**
	 * 项目信息登记单
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void projectInformationRegistration (Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap) throws Exception;
	/**
	 * 销售成本结转单
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void salesSingleCost(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap) throws Exception;
	/**
	 * 销售应付单
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void salePayable(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap) throws Exception;
	/**
	 * 销售应收单
	 * @param headMap
	 * @param bodyMap
	 * @param rootAttrMap
	 * @throws Exception
	 */
	public void salesReceivable(Map<String,String> headMap,Map<String,String> bodyMap, Map<String,String> rootAttrMap) throws Exception;
}

package cn.digitalpublishing.ufinterface;

import java.util.HashMap;
import java.util.Map;

public class UfTest {
	public static void main(String[] args) {
		IUFService ufService = new UFServiceImpl();
		Map<String,String> headMap = new HashMap<String,String>();
		headMap.put("pk_invcl", "0101");
		headMap.put("pk_corp", "0101");
		headMap.put("invcode", "chtest001");
		headMap.put("invname", "chtest001");
//		headMap.put("forinvname", "");
		headMap.put("pk_taxitems", "509");
		headMap.put("pk_measdoc", "001");
		headMap.put("discountflag", "N");
		headMap.put("setpartsflag", "N");
		headMap.put("laborflag", "N");
		headMap.put("assistunit", "N");
		headMap.put("ismngstockbygrswt", "N");
		headMap.put("autobalancemeas", "N");
		headMap.put("isstorebyconvert", "N");
		headMap.put("isretail", "N");
		headMap.put("asset", "N");
		Map<String,String> bodyMap = new HashMap<String,String>();
		Map<String,String> rootAttrMap = new HashMap<String,String>();
		rootAttrMap.put("id", "rmchtest001");
		rootAttrMap.put("subdoctype", "invbas");
		try {
			ufService.inventoryMaterial(headMap, bodyMap, rootAttrMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

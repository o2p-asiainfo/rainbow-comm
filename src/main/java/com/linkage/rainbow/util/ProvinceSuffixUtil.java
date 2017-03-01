package com.linkage.rainbow.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 省份后缀工具类
 * @author liudf
 *
 */
public class ProvinceSuffixUtil {
	
	private static Map<String, String> mapSufix = new HashMap<String, String>();
	
	public static String getProvinceSuffix(String provinceCode){
		return mapSufix.get(provinceCode);
	}
	
	static{
		mapSufix.put("600101", "gd");
		mapSufix.put("600102", "sh");
		mapSufix.put("600103", "js");
		mapSufix.put("600104", "zj");
		mapSufix.put("600105", "fj");
		mapSufix.put("600201", "sc");
		mapSufix.put("600202", "hb");
		mapSufix.put("600203", "hn");
		mapSufix.put("600204", "sn");
		mapSufix.put("600205", "yn");
		mapSufix.put("600301", "ah");
		mapSufix.put("600302", "gx");
		mapSufix.put("600303", "xj");
		mapSufix.put("600304", "cq");
		mapSufix.put("600305", "jx");
		mapSufix.put("600401", "gs");
		mapSufix.put("600402", "gz");
		mapSufix.put("600403", "hi");
		mapSufix.put("600404", "nx");
		mapSufix.put("600405", "qh");
		mapSufix.put("600406", "xz");
		mapSufix.put("609001", "bj");
		mapSufix.put("609801", "mo");
		mapSufix.put("609902", "tj");
		mapSufix.put("609903", "sd");
		mapSufix.put("609904", "ha");
		mapSufix.put("609905", "ln");
		mapSufix.put("609906", "he");
		mapSufix.put("609907", "sx");
		mapSufix.put("609908", "nm");
		mapSufix.put("609909", "jl");
		mapSufix.put("609910", "hl");
	}
	

}

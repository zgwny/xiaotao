package com.xiaotao.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xiaotao.common.pojo.XiaotaoResult;
import com.xiaotao.pojo.TbItem;
import com.xiaotao.pojo.TbItemDesc;
import com.xiaotao.pojo.TbItemParamItem;
import com.xiaotao.portal.pojo.PortIbItem;
import com.xiaotao.portal.service.ItemService;
import com.xiaotao.utils.HttpClientUtil;
import com.xiaotao.utils.JsonUtils;

@Service
public class ItemServiceImpl implements ItemService {
	
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_ITEM_BASE_URL}")
	private String REST_ITEM_BASE_URL;
	@Value("${REST_ITEM_DESC_URL}")
	private String REST_ITEM_DESC_URL;
	@Value("${REST_ITEM_PARAM_URL}")
	private String REST_ITEM_PARAM_URL;
	

	@Override
	public PortIbItem getItemById(Long itemId) {
		// 根据商品id查询商品基本信息
		String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_BASE_URL + itemId);
		//转换成java对象
		XiaotaoResult xiaotaoResult = XiaotaoResult.formatToPojo(json, PortIbItem.class);
		//取商品对象
		PortIbItem item = (PortIbItem) xiaotaoResult.getData();
		return item;
	}

	
	@Override
	public String getItemDescById(Long itemId) {
		//根据商品id调用taotao-rest的服务获得数据
		//http://localhost:8081/rest/item/desc/144766336139977
		String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_DESC_URL + itemId);
		//转换成java对象
		XiaotaoResult taotaoResult = XiaotaoResult.formatToPojo(json, TbItemDesc.class);
		//取商品描述
		TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
		String desc = itemDesc.getItemDesc();
		return desc;
	}


	@Override
	public String getItemParamById(Long itemId) {
		// 根据商品id获得对应的规格参数
				String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_PARAM_URL + itemId);
				// 转换成java对象
				XiaotaoResult taotaoResult = XiaotaoResult.formatToPojo(json, TbItemParamItem.class);
				// 取规格参数
				TbItemParamItem itemParamItem = (TbItemParamItem) taotaoResult.getData();
				String paramJson = itemParamItem.getParamData();
				// 把规格参数的json数据转换成java对象
				// 转换成java对象
				List<Map> mapList = JsonUtils.jsonToList(paramJson, Map.class);
				// 遍历list生成html
				StringBuffer sb = new StringBuffer();

				sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
				sb.append("	<tbody>\n");
				for (Map map : mapList) {
					sb.append("		<tr>\n");
					sb.append("			<th class=\"tdTitle\" colspan=\"2\">" + map.get("group") + "</th>\n");
					sb.append("		</tr>\n");
					// 取规格项
					List<Map> mapList2 = (List<Map>) map.get("params");
					for (Map map2 : mapList2) {
						sb.append("		<tr>\n");
						sb.append("			<td class=\"tdTitle\">" + map2.get("k") + "</td>\n");
						sb.append("			<td>" + map2.get("v") + "</td>\n");
						sb.append("		</tr>\n");
					}
				}
				sb.append("	</tbody>\n");
				sb.append("</table>");

				return sb.toString();

	}
	
	


}

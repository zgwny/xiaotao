package com.xiaotao.order.pojo;

import java.util.List;

import com.xiaotao.pojo.TbOrder;
import com.xiaotao.pojo.TbOrderItem;
import com.xiaotao.pojo.TbOrderShipping;

public class OrderInfo extends TbOrder {

	
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}

}

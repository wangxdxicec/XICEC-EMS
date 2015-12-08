package com.zhenhappy.dto;

import java.util.List;



/**
 * 
 * @author rocsky
 * 
 */
public class ExhibitItemResponse extends BaseResponse {
	private List<Item> items ;
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public static class Item{
		private int itemId;
		private String nameEn;
		private String nameZh;
		private String nameTw;

		public String getNameTw() {
			return nameTw;
		}

		public void setNameTw(String nameTw) {
			this.nameTw = nameTw;
		}

		public Item(){
			
		}

		public Item(int itemId, String nameEn, String nameZh, String nameTw) {
			this.itemId = itemId;
			this.nameEn = nameEn;
			this.nameZh = nameZh;
			this.nameTw = nameTw;
		}

		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}
		public String getNameEn() {
			return nameEn;
		}

		public void setNameEn(String nameEn) {
			this.nameEn = nameEn;
		}
		public String getNameZh() {
			return nameZh;
		}

		public void setNameZh(String nameZh) {
			this.nameZh = nameZh;
		}

	}
}

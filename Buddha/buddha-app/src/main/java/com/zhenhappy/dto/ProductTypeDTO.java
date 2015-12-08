package com.zhenhappy.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeDTO {
	
	private String typeName;
	
	private String typeNameE;
	
	private String typeCode;
	
	private List<ProductChildType> childs = new ArrayList<ProductTypeDTO.ProductChildType>();
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeNameE() {
		return typeNameE;
	}

	public void setTypeNameE(String typeNameE) {
		this.typeNameE = typeNameE;
	}


	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}


	public static class ProductChildType extends ProductTypeDTO{
	
		private String fatherCode;
		
		public String getFatherCode() {
			return fatherCode;
		}

		public void setFatherCode(String fatherCode) {
			this.fatherCode = fatherCode;
		}

		private boolean selected;
		
		private String otherRemark;
		
		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}

		public String getOtherRemark() {
			return otherRemark;
		}

		public void setOtherRemark(String otherRemark) {
			this.otherRemark = otherRemark;
		}

	}

	public List<ProductChildType> getChilds() {
		return childs;
	}

	public void setChilds(List<ProductChildType> childs) {
		this.childs = childs;
	}
	
	public void addChild(String name,String ename,String typeCode,boolean selected,String remark,String fatherCode){
		ProductChildType childType = new ProductChildType();
		childType.setTypeName(name);
		childType.setTypeNameE(ename);
		childType.setSelected(selected);
		childType.setOtherRemark(remark);
		childType.setTypeCode(typeCode);
		childType.setFatherCode(fatherCode);
		childs.add(childType);
	}
	
}

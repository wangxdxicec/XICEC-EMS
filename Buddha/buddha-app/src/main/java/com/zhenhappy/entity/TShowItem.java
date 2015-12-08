package com.zhenhappy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author rocsky
 * 
 */
@Entity
@Table(name = "t_show_item", schema = "dbo")
public class TShowItem implements java.io.Serializable {
	private Integer showItemId;
	private Integer showId;
	private Integer itemId;

	public TShowItem() {

	}

	public TShowItem(Integer showId, Integer itemId) {
		this.showId = showId;
		this.itemId = itemId;
	}

	@Id
	@Column(name = "show_item_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getShowItemId() {
		return showItemId;
	}

	public void setShowItemId(Integer showItemId) {
		this.showItemId = showItemId;
	}

	@Column(name = "show_id")
	public Integer getShowId() {
		return showId;
	}

	public void setShowId(Integer showId) {
		this.showId = showId;
	}

	@Column(name = "item_id")
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

}

package com.github.hualuomoli.core.entity;

import java.util.List;

/**
 * paging message
 * @author hualuomoli
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Page {

	private Integer pageNo;
	private Integer pageSize;

	private Integer count;
	private List list; // data

	public Page() {
	}

	public Page(Integer pageNo, Integer pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public <T> List<T> getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	// 获取第一条数据位置，从0开始
	public Integer getFirstResult() {
		Integer first = (pageNo - 1) * pageSize;
		if (first < 0) {
			first = 0;
		}
		return first;
	}

	// 获取页码
	public Integer getPageNumber() {
		Integer number = (count + pageSize - 1) / pageSize;
		if (number < 0) {
			return 0;
		}
		return number;
	}

}

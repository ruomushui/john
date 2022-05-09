/*
 *
 *      Copyright (c) 2018-2025, ris All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the cloud.ris.com.cn developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: ris
 *
 */

package com.epro.springboot.util;

import lombok.Data;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ris
 * @date 2017年11月9日23:33:45
 */
@Data
public class TreeNode<T extends TreeNode<T>> {

	protected String id;

	protected String parentId;

	protected Boolean isLeaf = Boolean.FALSE;

	protected Integer level;

	protected List<T> children = new ArrayList<>();

	public void add(T node) {
		Assert.isTrue(!isLeaf, "叶子节点，禁止添加下级");

		if(null == children){
			children = new ArrayList<>();
		}
		children.add(node);
	}

	public void setId(Object id){
		this.id = String.valueOf(id);
	}

	public void setParentId(Object parentId){
		this.parentId = String.valueOf(parentId);
	}

	public Boolean getHasChildren(){
		return null == children || children.isEmpty() ? Boolean.FALSE : Boolean.TRUE;
	}
}

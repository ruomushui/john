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

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author ris
 * @date 2020-02-09
 */
@UtilityClass
public class TreeUtil {

	/**
	 * 两层循环实现建树
	 * @param treeNodes 传入的树节点列表
	 * @return
	 */
	public <T extends TreeNode> List<T> build(List<T> treeNodes, Object root) {
		return build(treeNodes, root, null);
	}
	public <T extends TreeNode> List<T> build(List<T> treeNodes, Object root, Predicate<T> filter) {

		List<T> trees = new ArrayList<>();

		Set<String> set = new HashSet<>();
		for (T treeNode : treeNodes) {
			set.add(treeNode.getId());
			if (null!=root && String.valueOf(root).equals(treeNode.getParentId())) {
				trees.add(treeNode);
			}

			for (T it : treeNodes) {
				if (StrUtil.equals(it.getParentId(),treeNode.getId())) {
					if (treeNode.getChildren() == null) {
						treeNode.setChildren(new ArrayList<>());
					}
					treeNode.add(it);
				}
			}
		}
		if(null==root){
			for (T it : treeNodes) {
				if(!set.contains(it.getParentId())){
					// 无上级
					trees.add(it);
				}
			}
		}
		setLevel(trees, 1);
		if(null!=filter){
			filter(trees, filter);
		}
		return trees;
	}

	/**
	 * 使用递归方法建树
	 * @param treeNodes
	 * @return
	 */
	public <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Object root) {
		return buildByRecursive(treeNodes, root);
	}
	public <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Object root, Predicate<T> filter) {
		List<T> trees = new ArrayList<T>();
		Set<String> set = new HashSet<>();
		for (T treeNode : treeNodes) {
			set.add(treeNode.getId());
			if (null!=root && String.valueOf(root).equals(treeNode.getParentId())) {
				trees.add(findChildren(treeNode, treeNodes));
			}
		}
		if(null==root){
			for (T it : treeNodes) {
				if(!set.contains(it.getParentId())){
					// 无上级
					trees.add(it);
				}
			}
		}
		setLevel(trees, 1);
		if(null!=filter){
			filter(trees, filter);
		}
		return trees;
	}

	/**
	 * 递归查找子节点
	 * @param treeNodes
	 * @return
	 */
	public <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
		for (T it : treeNodes) {
			if (StrUtil.equals(it.getParentId(),treeNode.getId())) {
				if (treeNode.getChildren() == null) {
					treeNode.setChildren(new ArrayList<>());
				}
				treeNode.add(findChildren(it, treeNodes));
			}
		}
		return treeNode;
	}
	public <T extends TreeNode> T findFirst(List<T> treeNodes, Predicate<T> check) {
		for (T it : treeNodes) {
			if (check.test(it)) {
				return it;
			}
			if(it.getHasChildren()){
				List<T> children = it.getChildren();
				T node = findFirst(children, check);
				if(null!=node){
					return node;
				}
			}
		}
		return null;
	}
	/**
	 * 递归查找节点路径（有序）
	 * @param treeNodes
	 * @return
	 */
	public <T extends TreeNode> List<T> getPath(T treeNode, List<T> treeNodes) {
		List<T> list = new ArrayList<>();
		for (T it : treeNodes) {
			if (StrUtil.equals(it.getId(), treeNode.getParentId())) {
				list.addAll(getPath(it, treeNodes));
				break;
			}
		}
		list.add(treeNode);
		return list;
	}
	/**
	 * 是否包含符合条件的下级（递归）
	 * @param treeNodes
	 * @return
	 */
	public <T extends TreeNode> boolean containsChild(T treeNode, List<T> treeNodes, Predicate<T> predicate){
		for (T it : treeNodes) {
			if (StrUtil.equals(treeNode.getId(), it.getParentId())) {
				if(predicate.test(it)){
					return true;
				}else if(containsChild(it, treeNodes, predicate)){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @description 取最近的符合条件的上级节点
	 * @author wangjun
	 * @date 2021/7/1 19:26
	 * @param id
	 * @param treeNodes
	 * @param predicate 断言函数
	 * @return T
	 **/
	public <T extends TreeNode> T getParent(String id, List<T> treeNodes, Predicate<T> predicate){
		T treeNode = treeNodes.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
		if(null==treeNode){
			return null;
		}
		return getParent(treeNode, treeNodes, predicate);
	}
	public <T extends TreeNode> T getParent(T treeNode, List<T> treeNodes, Predicate<T> predicate){
		for (T it : treeNodes) {
			if (StrUtil.equals(it.getId(), treeNode.getParentId())) {
				if(predicate.test(it)){
					return it;
				}else{
					return getParent(it, treeNodes, predicate);
				}
			}
		}
		return null;
	}

	public <T extends TreeNode> List<T> build(List<Object> nodes, Object root, Function<? super Object, ? extends T> mapper) {
		List<T> trees = nodes.stream().map(mapper).collect(Collectors.toList());
		return TreeUtil.build(trees, root);
	}

	/**
	 * @description 设置级别
	 * @author wangjun
	 * @date 2021/6/30 15:15
	 * @param treeList
 	 * @param level
	 * @return void
	 **/
	public <T extends TreeNode<T>> void setLevel(List<T> treeList, int level){
		if(CollUtil.isEmpty(treeList)){
			return;
		}
		int childLevel = level + 1;
		for(T node : treeList){
			node.setLevel(level);
			if(node.getHasChildren()){
				setLevel(node.getChildren(), childLevel);
			}
		}
	}

	/**
	 * @description 末级开始递归过滤数据
	 * @author wangjun
	 * @date 2021/11/10 10:51
	 * @param trees
 	 * @param filter
	 * @return void
	 **/
	public <T extends TreeNode<T>> void filter(List<T> trees, Predicate<T> filter){
		if(CollUtil.isNotEmpty(trees) && null!=filter) {
			for (Iterator<T> it = trees.iterator(); it.hasNext(); ) {
				T node = it.next();
				if (node.getHasChildren()) {
					filter(node.getChildren(), filter);
				}
				if (!filter.test(node)) {
					it.remove();
				}
			}
		}
	}
}

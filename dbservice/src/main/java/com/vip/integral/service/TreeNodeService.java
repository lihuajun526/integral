package com.vip.integral.service;

import com.vip.integral.model.TreeNode;

/**
 * Created by lihuajun on 16-7-25.
 */
public interface TreeNodeService {

    int save(TreeNode treeNode);

    int update(TreeNode treeNode);

    TreeNode get(Integer id);
}

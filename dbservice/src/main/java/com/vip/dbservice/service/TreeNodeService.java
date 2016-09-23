package com.vip.dbservice.service;

import com.vip.dbservice.model.TreeNode;

import java.util.List;

/**
 * Created by lihuajun on 16-7-25.
 */
public interface TreeNodeService {

    int save(TreeNode treeNode);

    int update(TreeNode treeNode);

    TreeNode get(Integer id);

    List<TreeNode> listByParent(Integer parentid);
}

package com.vip.dbservice.dao;

import com.vip.dbservice.model.TreeNode;

import java.util.List;

public interface TreeNodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TreeNode record);

    int insertSelective(TreeNode record);

    TreeNode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TreeNode record);

    int updateByPrimaryKey(TreeNode record);

    List<TreeNode> listByParent(Integer parentid);
}
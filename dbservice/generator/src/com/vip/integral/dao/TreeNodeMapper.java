package com.vip.integral.dao;

import com.vip.integral.model.TreeNode;

public interface TreeNodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TreeNode record);

    int insertSelective(TreeNode record);

    TreeNode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TreeNode record);

    int updateByPrimaryKey(TreeNode record);
}
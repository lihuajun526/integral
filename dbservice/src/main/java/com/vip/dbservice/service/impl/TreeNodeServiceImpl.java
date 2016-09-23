package com.vip.dbservice.service.impl;

import com.vip.dbservice.dao.TreeNodeMapper;
import com.vip.dbservice.model.TreeNode;
import com.vip.dbservice.service.TreeNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 16-7-25.
 */
@Service("treeNodeService")
public class TreeNodeServiceImpl implements TreeNodeService {

    @Autowired
    private TreeNodeMapper treeNodeMapper;

    @Override public int save(TreeNode treeNode) {
        return treeNodeMapper.insert(treeNode);
    }

    @Override public int update(TreeNode treeNode) {
        return treeNodeMapper.updateByPrimaryKey(treeNode);
    }

    @Override public TreeNode get(Integer id) {
        return treeNodeMapper.selectByPrimaryKey(id);
    }

    @Override public List<TreeNode> listByParent(Integer parentid) {
        return treeNodeMapper.listByParent(parentid);
    }
}

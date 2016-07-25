package com.vip.integral.controller;

import com.vip.integral.base.Result;
import com.vip.integral.model.TreeNode;
import com.vip.integral.service.TreeNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/tree")
public class TreeNodeController {

    @Autowired
    private TreeNodeService treeNodeService;

    @RequestMapping("/add")
    @ResponseBody
    public String save(TreeNode treeNode) {

        Result<Boolean> result = new Result<>();

        treeNodeService.save(treeNode);

        result.set("添加成功", true);
        return result.toString();
    }

    @RequestMapping("/modify")
    @ResponseBody
    public String update(TreeNode treeNode) {

        Result<Boolean> result = new Result<>();

        treeNodeService.update(treeNode);

        result.set("更新成功", true);
        return result.toString();
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(Integer id) {

        Result<TreeNode> result = new Result<>();

        TreeNode treeNode = treeNodeService.get(id);

        result.setData(treeNode);
        return result.toString();
    }

}

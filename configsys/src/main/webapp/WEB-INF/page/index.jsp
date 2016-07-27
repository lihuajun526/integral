<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>配置管理系统</title>
    <link rel="stylesheet" type="text/css" href="/statics/jquery-easyui-1.4.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/statics/jquery-easyui-1.4.5/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/statics/jquery-easyui-1.4.5/demo/demo.css">
    <script type="text/javascript" src="/statics/jquery-easyui-1.4.5/jquery.min.js"></script>
    <script type="text/javascript" src="/statics/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">爬虫规则管理系统</div>
<div data-options="region:'west',split:true,title:'导航'" style="width:200px;padding:10px;">
    <button onclick="loadPage('rule/crawl_point.html')">添加</button>
    <div class="easyui-panel" style="padding:5px" title="采集点">
        <ul id="tree" class="easyui-tree"></ul>
    </div>
    <br/>
</div>
<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region
</div>
<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">银联钱包杭州事业部</div>
<div data-options="region:'center',title:'工作区'">
    <div id="p" class="easyui-panel" style="width:900px;height:480px;padding:10px;margin: 10px;"
         data-options="
				tools:[{
					iconCls:'icon-reload',
					handler:loadPage
				}]
			">
    </div>
</div>
<div id="mm" class="easyui-menu" style="width:120px;">
    <div onclick="edit()" data-options="iconCls:'icon-add'">编辑</div>
    <div onclick="append()" data-options="iconCls:'icon-add'">添加子节点</div>
</div>

<script type="text/javascript">
    //选中的节点
    var checkNode = null;
    //父节点
    var pNode = null;
    //当前节点
    var curNode = null;
    //加载页面
    function loadPage(path) {
        $('#p').panel('refresh', 'statics/' + path);
    }
    //添加节点
    function append() {
        pNode = checkNode;
        curNode = null;
        loadPage('rule/node_add_edit.html');
    }
    //编辑节点
    function edit() {
        pNode = null;
        curNode = checkNode;
        loadPage('rule/node_add_edit.html');
    }
    $('#tree').tree({
        onClick: function (node) {
            curNode = node;
            if (node.attributes == null)
                return;
            if (node.attributes.pageType == "crawlPoint") {
                loadPage('rule/crawl_point.html');
            } else if (node.attributes.pageType == "attacker") {
                loadPage('rule/attacker.html');
            }
        },
        onContextMenu: function (e, node) {
            e.preventDefault();
            // 查找节点
            $('#tree').tree('select', node.target);
            checkNode = node;
            // 显示快捷菜单
            $('#mm').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        },
        onExpand: function (node) {
            $.get("./tree/tree?id=" + node.id,
                    function (data) {
                        var childrenNodes = $('#tree').tree('getChildren', node.target);
                        for (var i = 0; i < childrenNodes.length; i++) {
                            var childrenNode = childrenNodes[i];
                            $('#tree').treegrid('remove', childrenNode.id);//移除
                        }
                        $('#tree').tree('append', {
                            parent: node.target,
                            data: data
                        });
                    }, "json");
        }
    });

    $(function () {
        $(document).ready(function () {
            $.get("./tree/tree?id=0", function (data) {
                $("#tree").tree({
                    data: data
                });
            }, "json");
        });
    });

</script>
</body>
</html>

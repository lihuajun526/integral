<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>规则管理系统</title>
    <link rel="stylesheet" type="text/css" href="/statics/jquery-easyui-1.4.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/statics/jquery-easyui-1.4.5/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/statics/jquery-easyui-1.4.5/demo/demo.css">
    <script type="text/javascript" src="/statics/jquery-easyui-1.4.5/jquery.min.js"></script>
    <script type="text/javascript" src="/statics/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">爬虫规则管理系统</div>
<div data-options="region:'west',split:true,title:'导航'" style="width:200px;padding:10px;">
    <button onclick="loadPage('rule/crawlPoint.html')">添加</button>
    <div class="easyui-panel" style="padding:5px" title="采集点">
        <!--<ul class="easyui-tree" data-options="url:'statics/tree_data1.json',method:'get',animate:true,lines:true"></ul>-->
        <ul id="tt"></ul>
    </div>
    <br/>
    <div class="easyui-panel" title="系统设置" style="width:150px;">
        <div id="mm" data-options="inline:true" style="width:100%">
            <!--<div class="easyui-panel" onclick="loadPage('rule/crawlPoint.html')">
                <ul class="easyui-tree" data-options="url:'statics/tree_data1.json',method:'get',animate:true,lines:true"></ul>

            </div>-->

            <div onclick="loadPage('sys/setting.html')">代理设置</div>
        </div>
    </div>
</div>
<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region
</div>
<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">银联钱包杭州事业部</div>
<div data-options="region:'center',title:'工作区'">
    <div id="p" class="easyui-panel" style="width:900px;height:1000px;padding:10px;margin: 10px;"
         data-options="
				tools:[{
					iconCls:'icon-reload',
					handler:loadPage
				}]
			">
    </div>
</div>
<script type="text/javascript">
    var crawlId = 0;
    $(function () {
        $('#mm').menu().menu('enableNav');
        $(document).keydown(function (e) {
            if (e.altKey && e.keyCode == 87) {
                $('#mm').focus();
            }
        })
    });

    function loadPage(path) {
        $('#p').panel('refresh', 'statics/' + path);
    }

    $('#tt').tree({
        url: 'tree/load',
        method: 'get',
        dataType: 'json',
        state: "closed",
        onClick: function (node) {
            crawlId = node.id;
            if (crawlId.toString().indexOf("tree") < 0) {
                loadPage('rule/crawlPoint.html');
            }
        }
    });
</script>
</body>
</html>

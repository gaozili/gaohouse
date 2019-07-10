<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript">
        $(function(){
            $('#dg').datagrid({
                toolbar: '#tb'
            });
            //使用datagrid显示区域
            $('#dg').datagrid({
                title:"房屋信息",
                url:'getHouse',  //服务器地址
                pagination:true,  //启用分页
                pageList:[3,6,9,15,20], //设置每页大小
                pageSize:3, //每页三条
                columns:[[
                    {field:'id',title:'编号',width:100,align:'left'},
                    {field:'usersname',title:'用户编号',width:100,align:'left'},
                    {field:'typename',title:'类型编号',width:100,align:'left'},
                    {field:'title',title:'标题',width:100,align:'left'},
                    {field:'description',title:'描述',width:100,align:'left'},
                    {field:'price',title:'出租价',width:100,align:'left'},
                    {field:'pubdate',title:'发布日期',width:100,align:'left',
                        formatter: function(value,row,index){
                            // return "<input type='button' value='点死你'>";
                            var date=new Date(value);
                            var year=date.getFullYear();
                            var month=date.getMonth()+1;
                            var day=date.getDay();
                            return year+"年"+month+"月"+day+"日";
                        }
                        },
                    {field:'floorage',title:'面积',width:100,align:'left'},
                    {field:'contact',title:'联系人',width:100,align:'left'},
                    {field:'streetname',title:'街道编号',width:100,align:'left'},
                    {field:'ispass',title:'是否审核通过',width:100,align:'left'},
                    {field:'isdel',title:'是否删除',width:100,align:'left'},
                    {field:'path',title:'图片路径',width:100,align:'left'}
                ]]
            });


        });

    </script>
</head>
<body>


</div>
<!--显示区域-->
<table id="dg" pagination="true"
       fitColumns="true"  rownumbers="true" nowrap="false">
    <div id="tb">
        <a href="javascript:Add()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:ModifyBySelect()" class="easyui-linkbutton"
                iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:DeleteByFruitName()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">删除</a>
    </div>
</table>
</body>
</html>



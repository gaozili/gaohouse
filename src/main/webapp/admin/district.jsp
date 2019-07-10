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
                title:"区域信息",
                url:'getDistrict',  //服务器地址
                pagination:true,  //启用分页
                pageList:[3,6,9,15,20], //设置每页大小
                pageSize:3, //每页三条
                columns:[[
                    {field:'cb',checkbox:"ture",align:'left'},
                    {field:'id',title:'编号',width:100,align:'left'},
                    {field:'name',title:'区域名称',width:100,align:'left'},
                    {field:'opt',title:'操作',width:100,align:'left',
                        formatter:function (value,row,index) {
                            return "<a href=\"javascript:DelDirstrict("+row.id+")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";
                        }
                    }
                ]]
            });
        });
        function Add(){
            $("#AddDialog").dialog("open").dialog("setTitle",">>>>添加区域");}
        function SaveDialog(){
            //传统使用ajax技术实现添加
            //1.获取表单对象的数据  2.使用ajax方法|post方法发送异步请求 3.处理回调用函数

            //使用easyui提交表单
            $('#ModiyDialogForm').form('submit', {
                url:"addDistrict",
                success:function(data){  //注意:返回的是json字符串
                    //将json字符串转化为json对象
                    data=$.parseJSON(data);
                    if(data.result==1){
                        //关闭对话框
                        $("#AddDialog").dialog("close");
                        $.messager.alert('提示框','添加成功！^_^','info');
                    }else{
                        $.messager.alert('提示框','添加失败！^_^','info');
                    }
                }
            });

        }
        function  DelDirstrict(obj) {  //传的是编号
            //发送异步请求实现删除
            $.messager.confirm('提示框', '你真的想把我删除掉吗？我不能离开你', function(r){
                if (r){
                    $.post("delDistrict",{"id":obj},function(data){
                        if(data.result==1){
                            //实现datagrid的刷新
                            $('#dg').datagrid('reload');
                        }else{
                            $.messager.alert('提示框','删除失败！^_^','info');
                        }
                    },"json");
                }else{
                    $.messager.alert('提示框','想好再点，可以吗！^_^','info');
                }
            });
        }
        function DeleteMoreById(){
            //获取选中行
            var SelectRows = $("#dg").datagrid('getSelections');
            if(SelectRows.length==0){
                $.messager.alert('提示框','你还没有选中行^_^','info');
                return;
            }

            //确认删除
            $.messager.confirm('提示框', '你真的想把我删除掉吗？我不能离开你',function(flag){
                if(flag){  //为true实现删除
                    // 调用服务器接口进行删除
                    //获取选中项的值
                    var value="";
                    for(var i=0;i<SelectRows.length;i++){
                        value=value+SelectRows[i].id+",";
                    }
                                $.post("delMoreDistrict",{"id":value},function(data){
                        if(data.result>0){
                            //实现datagrid的刷新
                            $('#dg').datagrid('reload');
                        }else{
                            $.messager.alert('提示框','删除失败！^_^','info');
                        }
                    },"json");
                }
            });

        }
        function ModifyBySelect(){
            //获取datagrid选中行  返回的数组
            var SelectRows = $("#dg").datagrid('getSelections');
            if(SelectRows.length!=1){
                $.messager.alert('提示框','你还没有选中行，或者选择了多行.','info');
                return;
            }
            //打开窗口
            $("#upDialog").dialog("open").dialog("setTitle",">>>>修改区域");
            var row=SelectRows[0];
            $.post("getSingleDistrict",{"id":row.id},function(data){
                //回显
                $('#upDialogForm').form('load',data);
            },"json");
        }
        function upDaveDialog(){
            //使用easyui提交表单
            $('#upDialogForm').form('submit', {
                url:"updateDistrict",
                success:function(data){  //注意:返回的是json字符串
                    //将json字符串转化为json对象
                    data=$.parseJSON(data);
                    if(data.result==1){
                        //关闭对话框
                        $("#upDialog").dialog('close');
                        //实现datagrid的刷新
                        $.messager.alert('提示框','修改成功！^_^','info');
                        $('#dg').datagrid('reload');


                    }else{
                        $.messager.alert('提示框','修改失败！^_^','info');
                    }
                }
            });
        }
        function addCloseDialog(){
            $("#AddDialog").dialog('close');
            $('#dg').datagrid('reload');}
        function CloseDialog(){

            $("#upDialog").dialog('close');
            $('#dg').datagrid('reload');
        }

    </script>
</head>
<body>





<!--显示区域-->
<table id="dg" pagination="true"
       fitColumns="true"  rownumbers="true">
    <div id="tb">
        <a href="javascript:Add()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:ModifyBySelect()" class="easyui-linkbutton"
           iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:DeleteMoreById()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">删除</a>
    </div>
</table>

<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="ModiyDialogForm" method="post">
        <table>
            <tr>
                <td>区域名称:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="name" id="bname" /></td>
            </tr>

        </table>
    </form>
</div>
<div id="upDialog" class="easyui-dialog" buttons="#UpdateDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="upDialogForm" method="post">
        <table>
            <tr>
                <td>区域名称:</td>
                <td><input type="text" class="easyui-validatebox"

                           name="name" id="name" /></td>
            </tr>
            <tr>
                <td>编号</td>
                <td><input type="text" class="easyui-validatebox" readonly

                           name="id" id="id" /></td>
            </tr>

        </table>
    </form>
</div>

<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:addCloseDialog()"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<div id="UpdateDialogButtons">
    <a href="javascript:upDaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog()"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
</html>
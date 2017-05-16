<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>hello world</title>
    <link rel="stylesheet" href="../resources/css/ext-all.css">
    <script src="../js/bootstrap.js"></script>
    <script src="../js/ext-lang-zh_CN.js"></script>
    <style>
        /* 在此插入样式代码 */
    </style>
</head>
<body>
<!-- 在此插入Html代码 -->
<script>
    Ext.onReady(function(){
        if(Ext.BLANK_IMAGE_URL.substr(0,4) != 'data') {
            Ext.BLANK_IMAGE_URL = './images/s.gif';
        }
        //在此添加Ext JS代码
        var store = Ext.create("Ext.data.Store",{
            model: 'Products',
            autoLoad: true,
            pageSize: 20,
            proxy: {
                type: 'ajax',
                //url: '/Restful/Products',
                url: "<%=path%>inter/testInter.jsp",
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            storeId: 'ProductStore'
        });

        var panel = Ext.create('Ext.grid.Panel',{
            renderTo: Ext.getBody(),
            title: '产品列表',
            height: 600,
            width: 800,
            store: store,
            columns: [
                {
                    text: '序号',
                    dataIndex: 'id'
                },
                {
                    text: 'ProductName',
                    dataIndex: 'ProductName'
                },
                {
                    text: 'SupplierId',
                    dataIndex: 'SupplierId'
                },
                {
                    text: 'CategoryId',
                    dataIndex: 'CategoryId'
                },
                {
                    text: 'QuantityPerUnit',
                    dataIndex: 'QuantityPerUnit'
                },
                {
                    text: 'UnitPrice',
                    dataIndex: 'UnitPrice'
                },
                {
                    text: 'UnitsInStock',
                    dataIndex: 'UnitsInStock'
                },
                {
                    text: 'UnitsOnOrder',
                    dataIndex: 'UnitsOnOrder'
                },
                {
                    text: 'RecorderLevel',
                    dataIndex: 'RecorderLevel'
                },
                {
                    text: 'discontinued',
                    dataIndex: 'Discontinued'
                }
            ]
        });
    });

    //定义模型，类似于实体类？
    Ext.define('Products',{
        extend: 'Ext.data.Model',
        fields: [
            {
                name: 'id',
                type: 'int'
            },
            'ProductName',
            {
                name: 'SupplierId',
                type: 'int'
            },
            {
                name: 'CategoryId',
                type: 'int'
            },
            'QuantityPerUnit',
            {
                name: 'UnitPrice',
                type: 'float'
            },
            {
                name: 'UnitsInStock',
                type: 'int'
            },
            {
                name: 'UnitsOnOrder',
                type: 'int'
            },
            {
                name: 'RecorderLevel',
                type: 'int'
            },
            {
                name: 'Discontinued',
                type: 'bool'
            }
        ]
    });
</script>
</body>
</html>
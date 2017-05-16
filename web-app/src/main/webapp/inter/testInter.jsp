<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/common.jsp"%>
<%
    response.setContentType("application/json");
%>
{
    "total": 1,
    "success": false,
    "data": [
        {
            "id": 1,
            "ProductName": "chai",
            "QuantityPerUnit": "10 boxes x 20 bags",
            "RecorderLevel": 10,
            "SupplierId" : 1,
            "UnitPrice": 18.0,
            "UnitsInStock": 39,
            "UnitsOnOrder": 0,
            "CategoryID": 1,
            "Discontinued": false
        },
		{
            "id": 2,
            "ProductName": "hao",
            "QuantityPerUnit": "20 boxes x 30 bags",
            "RecorderLevel": 120,
            "SupplierId" : 2,
            "UnitPrice": 12.0,
            "UnitsInStock": 34,
            "UnitsOnOrder": 1,
            "CategoryID": 2,
            "Discontinued": true
        }
    ]
}
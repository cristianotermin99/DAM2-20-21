<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xxiva
  Date: 29/10/2020
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Productos</title>
</head>
<body>
<div>
    <div>
        <h5>PRODUCTOS</h5>
        <form action="cesta" method="get">
            <table class="table">
                <c:forEach var="item" items="${numList}">
                    <tr>
                        <c:forEach var="i" begin="0" end="2" step="1" varStatus="status">
                            <td>
                                <c:choose>
                                    <c:when test="${i ==0 }">${item}</c:when>
                                    <c:when test="${i ==2 }"><input type="checkbox" name="producto"
                                                                    value=${item}></c:when>
                                </c:choose>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" value="Añadir al carrito" name="op">
        </form>
    </div>
</div>
</body>
</html>
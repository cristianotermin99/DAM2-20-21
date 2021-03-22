<%--
  Created by IntelliJ IDEA.
  User: oscar
  Date: 9/28/2020
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>


<c:out value="${test}"/>

<c:if test="${titulo!=null}">
    <h1><c:out value="${titulo}"/></h1>
</c:if>

<h1><c:out value="${numList.get(0).getBirth()}"/></h1>





<c:set scope="request" var="par" value="true"/>

<table border="1">
    <tr>
        <c:forEach var="i" begin="0" end="${columnas-1}" step="1" varStatus="status">
            <th>
                <c:out value="${cabeceras[i]}"/>
            </th>
        </c:forEach>
    </tr>
    <c:forEach var="item" items="${numList}">
        <tr>

            <c:forEach var="i" begin="0" end="${veces-1}" step="1" varStatus="status">
                <td
                        <c:if test="${par}">bgcolor="${color}" </c:if> >
                    <c:choose>
                        <c:when test="${i ==0 }">${item.getNombre()}</c:when>
                        <c:when test="${i ==1 }">${item.getEdad()}</c:when>
                        <c:when test="${i ==2 }">${item.getBirth()}</c:when>
                    </c:choose>

                </td>

            </c:forEach>
        </tr>
        <c:set var="par" value="${!par}"/>
    </c:forEach>

</table>
</body>
</html>


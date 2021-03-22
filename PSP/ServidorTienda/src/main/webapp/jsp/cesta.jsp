<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cesta</title>
</head>
<body>
<div>
    <div>
        <form action="cesta" method="post">
            <table>
                <tr>
                    <th>
                        <c:out value="Cesta"/>
                    </th>
                </tr>
                <c:forEach var="item" items="${listaProductos}">
                    <tr>
                        <td>
                            -<c:out value="${item}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <button type="submit" value="Comprar" name="button">Comprar</button>
            <button type="submit" value="Atras" name="button">Atras</button>
            <button type="submit"  value="Logout" name="button">Salir</button>
        </form>
    </div>
</div>
</body>
</html>
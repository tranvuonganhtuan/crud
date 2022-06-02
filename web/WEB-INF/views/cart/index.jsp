<%-- 
    Document   : index
    Created on : Mar 7, 2022, 2:43:26 PM
    Author     : SE151470  Tran Vuong Anh Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table class="table table-striped">
    <thead>
        <tr>
            <th class="text-right">No.</th>
            <th class="text-right">Id</th>
            <th>Image</th>
            <th>Description</th>
            <th class="text-right">Old Price</th>
            <th class="text-right">Discount</th>
            <th class="text-right">New Price</th>
            <th class="text-right">Quantity</th>
            <th class="text-right">Cost</th>
            <th>Operations</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${cart.items}" varStatus="loop">
        <form>
            <tr>
                <td class="text-right">${loop.count}</td>
                <td class="text-right">${item.id}</td>
                <td><img src="<c:url value="/products/${item.id}.jpg"/>" height="60px"/></td>
                <td>${item.description}</td>
                <%--<td><fmt:formatNumber value="${item.price}" pattern="$#,##0.00"/></td>--%>
                <td class="text-right"><fmt:formatNumber value="${item.price}" type="currency"/></td>
                <%--<td><fmt:formatNumber value="${item.discount}" pattern="#%"/></td>--%>
                <td class="text-right"><fmt:formatNumber value="${item.discount}" type="percent"/></td>
                <td class="text-right"><fmt:formatNumber value="${item.newPrice}" type="currency"/></td>
                <td class="text-right"><input type="number" value="${item.quantity}" name="quantity" style="width: 60px"/></td>
                <td class="text-right"><fmt:formatNumber value="${item.cost}" type="currency"/></td>
                <td>
                    <input type="hidden" value="${item.id}" name="id"/>
                    <button type="submit" class="btn btn-link" formaction="<c:url value="/cart/update.do"/> ">Update</button> |
                    <button type="submit" class="btn btn-link" formaction="<c:url value="/cart/delete.do"/> ">Delete</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</tbody>
<tfoot>
    <tr>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th>Total</th>
        <th class="text-right"><fmt:formatNumber value="${cart.total}" type="currency"/></th>
        <th><a href="<c:url value="/cart/empty.do" />">Empty your cart</a></th>
    </tr>
</tfoot>
</table>

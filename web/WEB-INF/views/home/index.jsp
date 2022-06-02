<%-- 
    Document   : index
    Created on : Mar 6, 2022, 3:46:52 PM
    Author     : SE151470  Tran Vuong Anh Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <c:forEach var="product" items="${list}">
        <div class="col-sm-4 p-2">
            <!--<form action="/new-cart-management/cart/add.do"/>-->
            <!--<form action="${pageContext.request.contextPath}/cart/add.do"/>-->
            <!--<form action="${root}/cart/add.do"/>-->
            <form action="<c:url value="/cart/add.do"/>"/>
            <form action="<c:url value="/cart/add.do"/>"/>
            <img src="<c:url value="/products/${product.id}.jpg"/>" width="100%"/>
            <input type="hidden" value="${product.id}" name="id"/>
            Id: ${product.id}<br/>
            Description: ${product.description}<br/>
            Price: <strike> <fmt:formatNumber value=" ${product.price}" type="currency"/></strike>
            <span style="color: red; font-size: 30px"> <fmt:formatNumber value=" ${product.price*(1-product.discount)}" type="currency"/></span><br/>
            Discount: <fmt:formatNumber value="${product.discount}" type="percent"/><br/>
            Quantity: <input type="number" class="" name="quantity" style="width: 80px" value="1"/> <br/>
            <button type="submit" class="btn btn-sm btn-info"><i class="bi bi-cart-plus"></i> Add to cart</button>
            </form>
        </div>
    </c:forEach>
</div>
<div class="row">
    <div class="col" style="text-align: right;">
        <br/>
        <form action="<c:url value="/home/index.do" />">
            <button type="submit" class="btn btn-sm btn-info" name="op" value="FirstPage" title="First Page" <c:if test="${page==1}">disabled</c:if>><i class="bi bi-chevron-bar-left"></i></button>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="PreviousPage" title="Previous Page" <c:if test="${page==1}">disabled</c:if>><i class="bi bi-chevron-left"></i></button>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="NextPage" title="Next Page" <c:if test="${page==totalPage}">disabled</c:if>><i class="bi bi-chevron-right"></i></button>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="LastPage" title="Last Page" <c:if test="${page==totalPage}">disabled</c:if>><i class="bi bi-chevron-bar-right"></i></button>
            <input type="text" name="gotoPage" value="${page}" class="btn btn-sm btn-outline-default" style="text-align: right;width: 32px;" title="Enter page number"/>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="GotoPage" title="Goto Page"><i class="bi bi-arrow-up-right-circle"></i></button>
        </form>
        Page ${page}/${totalPage}
    </div>
</div>
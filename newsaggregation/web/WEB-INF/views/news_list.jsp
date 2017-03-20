<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<h1>Список новостей:</h1>

<br>

<form:form  method="get" modelAttribute="search" action="news-list" autocomplete="true" class="form-inline">
    Поиск: <input name="search" class="form-control" placeholder="Search"/>
    <button type="submit" class="btn btn-success">Найти</button>
</form:form>

<br>

<%--<!-- Nav tabs -->--%>
<ul class="nav nav-tabs pagination">
    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <li class="${currentPage == i.count ? 'active' : 'default'}"><a href="<s:url value="/news-list?page=${i.count}"/>" target="_self"><c:out value="${i.count}" /></a></li>
    </c:forEach>
</ul>

<table class="table table-bordered table-hover table-striped">
    <thead>
    <tr>
        <th>Publication date</th>
        <th>News</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${items}" var="item">
        <tr>
            <td>
                <c:out value="${item.publishedDate}" />
                <br />
                <c:out value="${item.site.name}" />
            </td>
            <td>
                <strong>
                    <a href="<c:out value="${item.link}" />" target="_blank">
                        <c:out value="${item.title}" />
                    </a>
                </strong>
                <br />
                    ${item.description}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%--<!-- Nav tabs -->--%>
<ul class="nav nav-tabs">
    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <li class="${currentPage == i.count ? 'active' : 'default'}"><a href="<s:url value="/news-list?page=${i.count}"/>" target="_self"><c:out value="${i.count}" /></a></li>
    </c:forEach>
</ul>
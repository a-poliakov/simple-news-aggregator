<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>

    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>

    <script type="text/javascript"
            src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>

    <script
            src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:getAsString name="title" /></title>
</head>
<body>

<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>

<tilesx:useAttribute name="current"/>

<div class="container">

    <!-- Static navbar -->
    <div class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand active" href="<spring:url value="/" />">Simple News Aggregator</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="${current == 'index' ? 'active' : ''}"><a href='<spring:url value="/" />'>Источники новостей</a></li>
                    <li class="${current == 'news_list' ? 'active' : ''}"><a href="<spring:url value="/news-list" />">Список новостей</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </div>


    <tiles:insertAttribute name="body" />

    <br>
    <br>
    <div style="text-align: center;">
        <tiles:insertAttribute name="footer" />
    </div>

</div>

</body>
</html>


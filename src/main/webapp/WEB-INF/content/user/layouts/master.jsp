<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><tiles:getAsString name="title" /></title>
    <%@ include file="header.html" %>
</head>

<body class="hold-transition sidebar-mini layout-fixed">
    <div class="wrapper">
        <%@ include file="sidebar.jsp" %>
        <%@ include file="navbar.jsp" %>
        <div class="content-wrapper px-4 py-2">
            <tiles:insertAttribute name="body" />
        </div>
    </div>
</body>

<%@ include file="scripts.html" %>
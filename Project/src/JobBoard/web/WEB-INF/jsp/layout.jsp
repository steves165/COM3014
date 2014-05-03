<%--
    Document   : layout
    Created on : 13-Mar-2013, 16:47:00
    Author     : Daniel Searle <ds00148@surrey.ac.uk>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:insertAttribute name="title" ignore="true" /></title>

        <!-- Blueprint Framework CSS -->
        <link rel="stylesheet" href="<c:url value='/static/blueprint/screen.css'/>" media="screen, projection">
        <link rel="stylesheet" href="<c:url value='/static/blueprint/print.css'/>" type="text/css" media="print">
        <!--[if lt IE 8]>
            <link rel="stylesheet" href="<c:url value='/static/blueprint/ie.css'/>" type="text/css" media="screen, projection">
        <![endif]-->
        <link rel="stylesheet" href="<c:url value='/static/blueprint/plugins/fancy-type/screen.css'/>" type="text/css" media="screen, projection">

        <!-- Load in jQuery -->
        <script type="text/javascript" src="<c:url value='/static/jquery/jquery-1.9.1.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/static/jquery/plugins/jquery.validate.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/static/jquery/plugins/jquery.tmpl.min.js'/>"></script>


        <!-- jQuery UI -->
        <script type="text/javascript" src="<c:url value='/static/jquery-ui/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
        <link rel="stylesheet" href="<c:url value='/static/jquery-ui/css/start/jquery-ui-1.10.3.custom.min.css'/>" type="text/css" media="screen, projection">


        <!-- Custon jQuery plugin for input lists -->
        <script type="text/javascript" src="<c:url value='/static/jquery.inputList.js'/>"></script>

        <!-- Local Javascript -->
        <!--<script type="text/JavaScript" src="<c:url value='/static/JavaScriptJobs.js'/>"></script>-->
        <script type="text/javascript" src="<c:url value='/static/JobEntryJquery.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/static/CompEntryJquery.js'/>"></script>
        <!-- Local css -->
        <link rel="stylesheet" href="<c:url value='/static/screen.css'/>" media="screen, projection">
        <link rel="stylesheet" href="<c:url value='/static/print.css'/>" type="text/css" media="print">

        <!-- Validation Javascript -->
        <script type="text/javascript" src="<c:url value='/static/validation.js'/>"></script>

        <!-- Google maps API javascript -->
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBd1dr1SUTQdx52sBq68w_3B082ABaL8kg&amp;sensor=false">
        </script>
        <!-- Job Map Javascript -->
        <script type="text/javascript" src="<c:url value='/static/jquery.jobMap.js'/>"></script>

        <!-- Search Javascript -->
        <script type="text/javascript" src="<c:url value='/static/jquery.search.js'/>"></script>

    </head>
    <body>
        <div class="container">
            <tiles:insertAttribute name="header" />
            <hr class="nomargin"/>
            <div class="span-5 leftmenu">
                <div class="prepend-top append-bottom ">
                    <tiles:insertAttribute name="menu" />
                </div>
            </div>
            <div class="span-19 last">
                <div class="prepend-top append-bottom">
                     <tiles:insertAttribute name="body" />
                </div>
            </div>
            <hr/>
            <tiles:insertAttribute name="footer" />
        </div>
    </body>
</html>

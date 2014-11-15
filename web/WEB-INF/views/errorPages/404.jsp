<%-- 
    Document   : addedItem
    Created on : 11.11.2014, 15:51:15
    Author     : Touche
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="m"%>

<m:Base title="Nemocnice - správa">
    <m:RootBase/>
    
    <div class="alert alert-danger mySuccess" role="alert">
        <p><strong>Chyba 404!</strong> Omlouváme se, ale požadovaná stránka nebyla bohužel nalezena.</p>
        <a class="btn btn-lg btn-danger my-btn-success" href='<c:url value="/RootController"/>' role="button">Pokračovat</a>
    </div>
    <img src='res/virtualni-nemocnice-logo.png' alt='logo' class='footer_logo'/>
</m:Base>


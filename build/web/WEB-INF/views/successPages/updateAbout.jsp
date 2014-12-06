<%-- 
    Document   : showDepartment
    Created on : 20.11.2014, 11:29:30
    Author     : Touche
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="m"%>

<m:Base title="Nemocnice">
    <c:if test="${param.root}">
        <m:RootBase/>
    </c:if>
    <c:if test="${!param.root}">
        <m:UserBase/>
    </c:if>
    <div class="alert alert-success mySuccess" role="alert">
        <p><strong>Údaje aktualizovány!</strong> Vaše osobní údaje byly úspěšně změněny.</p>
        <c:if test="${param.root}">
            <a class="btn btn-lg btn-success my-btn-success" href='<c:url value="/RootController"/>' role="button">Pokračovat</a>
        </c:if>
        <c:if test="${!param.root}">
            <a class="btn btn-lg btn-success my-btn-success" href='<c:url value="/DocController"/>' role="button">Pokračovat</a>
        </c:if>   
    </div>
    <img src='res/virtualni-nemocnice-logo.png' alt='logo' class='footer_logo'/>    
</m:Base>

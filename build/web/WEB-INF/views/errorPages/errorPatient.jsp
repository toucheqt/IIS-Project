<%-- 
    Document   : showDepartment
    Created on : 20.11.2014, 11:29:30
    Author     : Touche
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="m"%>

<m:Base title="Nemocnice">
    <m:UserBase/>
    <div class="alert alert-danger mySuccess" role="alert">
        <p><strong>Něco se pokazilo!</strong> Omlouváme se, ale data související s pacientem nemohla býz aktualizována.
         Všechny povinné položky nebyly vyplněny.</p>
        <a class="btn btn-lg btn-danger my-btn-success" href='<c:url value="/DocController"/>' role="button">Pokračovat</a>  
    </div>
    <img src='res/virtualni-nemocnice-logo.png' alt='logo' class='footer_logo'/>    
</m:Base>

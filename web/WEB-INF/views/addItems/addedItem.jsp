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
    <!-- After 5s redirect to main page -->
    <script type="text/javascript">
    function redirect() {
        location.href = '<c:url value="/RootController"/>';
    }
    window.setTimeout("redirect()", 5000);
    </script>
    
    <div class="alert alert-success mySuccess" role="alert">
        <c:if test="${param.doc}">
            <p><strong>Úspěch!</strong> Podařilo se úspěšně přidat lékaře
                do databáze nemocnice Na Veleslavíně. Za moment dojde k přesměrování na úvodní stránku.</p>
        </c:if>
        <c:if test="${param.nurse}">
            <p><strong>Úspěch!</strong> Podařilo se úspěšně přidat sestru
                do databáze nemocnice Na Veleslavíně. Za moment dojde k přesměrování na úvodní stránku.</p>
        </c:if>
        <c:if test="${param.staff}">
            <p><strong>Úspěch!</strong> Podařilo se úspěšně přiřadit doktora k oddělení 
                nemocnice Na Veleslavíně. Za moment dojde k přesměrování na úvodní stránku.</p>
        </c:if>
        <a class="btn btn-lg btn-success my-btn-success" href='<c:url value="/RootController"/>' role="button">Pokračovat</a>
    </div>
    <img src='res/virtualni-nemocnice-logo.png' alt='logo' class='footer_logo'/>
</m:Base>

<%-- 
    Document   : Base
    Created on : 4.11.2014, 10:54:37
    Authors    : Ondřej Krpec, xkrpec01@stud.fit.vutbr.cz
               : Jiří Kulda, xkulda00@stud.fit.vutbr.cz
--%>

<%@tag description="Base template for welcome screen" pageEncoding="UTF-8"%>
<%@ attribute name="title" rtexprvalue="true" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>${title}</title>
        <link rel='stylesheet' href='<c:url value="/bootstrap/css/bootstrap.css"/>' />
        <link rel='stylesheet' href='<c:url value="/css/stickyfooter.css"/>' />
        <link rel='stylesheet' href='<c:url value="/css/signin.css"/>' />   
        <link rel='stylesheet' href='<c:url value="/css/style.css"/>' />
    </head>
    <body>
        <jsp:doBody/>
        <div class="footer">
            <div class="container">
                <p class="text-muted myFooter">© Informační systém Nemocnice na Veleslavíně, Veleslavínova 5, 612 00 Brno<br>
                Připomínky zasílejte na info@naveleslavine.cz</p>
            </div>
        </div>
    </body>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script type="text/javascript" src='<c:url value="/bootstrap/js/bootstrap.js"/>' ></script>
</html>
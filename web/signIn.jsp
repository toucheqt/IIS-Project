<%-- 
    Document   : signIn
    Created on : 4.11.2014, 10:53:33
    Authors    : Ondřej Krpec, xkrpec01@stud.fit.vutbr.cz
               : Jiří Kulda, xkulda00@stud.fit.vutbr.cz 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="m"%>

<m:Base title="Nemocnice - přihlášení">
    
    <div class="container">
      <form class="form-signin" role="form" method='POST' action='j_security_check'>
        <fieldset>
        <h2 class="form-signin-heading">Přihlášení do systému</h2>
        <c:if test="${param.warning}">
            <div class='form-group has-error' id='myAlert'>
                <input type="text" class="form-control" id='name' name='j_username' placeholder="Login" required autofocus>
                <input type="password" class="form-control" id='passwd' name='j_password' placeholder="Heslo" required>
            </div>
        </c:if>
        <c:if test="${!param.warning}">
            <div class='form-group'>
                <input type="text" class="form-control" id='name' name='j_username' placeholder="Login" required autofocus>
                <input type="password" class="form-control" id='passwd' name='j_password' placeholder="Heslo" required>
            </div>
        </c:if>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Přihlásit se</button>
        </fieldset>
      </form>
    </div> <!-- end container -->
    
    <c:if test="${param.warning}">
        <div class="alert alert-dismissable alert-danger myAlert">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong class='warn-strong'>Špatně zadané přihlašovací jméno nebo heslo. </strong>
        </div>          
    </c:if>
    <img src='res/virtualni-nemocnice-logo.png' alt='logo' class='footer_logo'/>
        
</m:Base>

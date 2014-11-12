<%-- 
    Document   : RootBase
    Created on : 10.11.2014, 17:47:36
    Authors    : Ondřej Krpec, xkrpec01@stud.fit.vutbr.cz
               : Jiří Kulda, xkulda00@stud.fit.vutbr.cz
--%>

<%@tag description="Base template root view" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href='<c:url value="/RootController"/>'>Nemocnice na Veleslavíně</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href='<c:url value="/RootController"/>'>Domů</a></li>
            <li class='dropdown'>
                <a href='#' class='dropdown-toggle' data-toggle='dropdown'>Personál<span class='caret'></span></a>
                <ul class='dropdown-menu' role='menu'>
                    <li><a href='<c:url value="/addDocDel"/>'>Přidat lékaře</a></li>
                    <li><a href='<c:url value="/addNurse"/>'>Přidat sestru</a></li>
                    <li><a href='<c:url value="/addStaff"/>'>Přiřadit personál</a></li>
                    <li class='divider'></li>
                    <li><a href='<c:url value="/removeDoc"/>'>Odebrat lékaře</a></li>
                    <li><a href='<c:url value="/removeNurse"/>'>Odebrat sestru</a></li>
                    <li><a href='<c:url value="/removeStaff"/>'>Odebrat personál</a></li>
                </ul>
            </li>
            <li class='dropdown'>
                <a href='#' class='dropdown-toggle' data-toggle='dropdown'>Zobrazit<span class='caret'></span></a>
                <ul class='dropdown-menu' role='menu'>
                    <li><a href='#'>Zobrazit lékáře</a></li>
                    <li><a href='#'>Zobrazit sestru</a></li>
                    <li><a href='#'>Zobrazit oddělení</a></li>
                </ul>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
              <li class='dropdown'>
                  <a href='#' class='dropdown-toggle' data-toggle='dropdown'>Nastavení<span class='caret'></span></a>
                  <ul class='dropdown-menu' role='menu'>
                      <li><a href='#'>Změnit heslo</a></li>
                      <li><a href='#'>Změnit osobní údaje</a></li>
                  </ul>
              </li>
            <li class="active"><a href="./">Logout</a></li>
          </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
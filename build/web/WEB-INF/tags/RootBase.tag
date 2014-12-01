<%-- 
    Document   : RootBase
    Created on : 10.11.2014, 17:47:36
    Authors    : Ondřej Krpec, xkrpec01@stud.fit.vutbr.cz
               : Jiří Kulda, xkulda00@stud.fit.vutbr.cz
--%>

<%@tag description="Base template root view" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- TODO prejmenovat tagy nejak rozumne aby slo poznat co kurva delaji -->
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
                    <li><a href='<c:url value="/addNurseDel"/>'>Přidat sestru</a></li>
                    <li class='divider'></li>
                    <li><a href='<c:url value="/assignStaff"/>'>Přiřadit personál</a></li>
                </ul>
            </li>
            <li class='dropdown'>
                <a href='#' class='dropdown-toggle' data-toggle='dropdown'>Zobrazit<span class='caret'></span></a>
                <ul class='dropdown-menu' role='menu'>
                    <li><a href='<c:url value="/showDoctor"/>'>Zobrazit lékáře</a></li>
                    <li><a href='<c:url value="/showNurse"/>'>Zobrazit sestry</a></li>
                    <li><a href="<c:url value="/showDepartment"/>">Zobrazit oddělení</a></li>
                </ul>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
              <li class='dropdown'>
                  <a href='#' class='dropdown-toggle' data-toggle='dropdown'>Nastavení<span class='caret'></span></a>
                  <ul class='dropdown-menu' role='menu'>
                      <li><a href='#' data-toggle="modal" data-target="#updatePasswd" >Změnit heslo</a></li>
                      <li><a href='#' data-toggle="modal" data-target="#updateAbout">Změnit osobní údaje</a></li>
                  </ul>
              </li>
              <li class="active"><a href="<c:url value="/logout"/>">Logout</a></li>
          </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
          
<div class="modal fade bs-example-modal-sm" id="updatePasswd" tabindex="-1" role="dialog"
        aria-labelledby="modalPasswd" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-passwd">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Změna hesla</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" method="POST" action="<c:url value="/updatePasswd"/>">
                    <div class="form-group">
                        <label for="oldPasswd" class="control-label change-pass-label">Staré heslo</label>
                        <div class="col-sm-10 doc-form">
                            <input type="password" class="form-control change-pass" name="oldPasswd"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="newPasswd1" class="control-label change-pass-label">Nové heslo</label>
                        <div class="col-sm-10 doc-form">
                            <input type="password" class="form-control change-pass" name="newPasswd1"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="newPasswd2" class="control-label change-pass-label">Nové heslo</label>
                        <div class="col-sm-10 doc-form">
                            <input type="password" class="form-control change-pass" name="newPasswd2"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Storno</button>
                        <input type="submit" value="Změnit" class="btn btn-primary" data-dissmiss="modal"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
                    
<div class="modal fade bs-example-modal-sm" id="updateAbout" tabindex="-1" role="dialog"
        aria-labelledby="modalAbout" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-passwd">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Změna osobních údajů</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" method="POST" action="<c:url value="/updateAbout"/>">
                    <div class="form-group">
                        <label for="inputName" class="control-label change-pass-label">Jméno</label>
                        <div class="col-sm-10 doc-form">
                            <input type="text" class="form-control change-pass" name="inputName"
                                   value="${user.username}" placeholder="Jméno (povinné)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputSurname" class="control-label change-pass-label">Příjmení</label>
                        <div class="col-sm-10 doc-form">
                            <input type="text" class="form-control change-pass" name="inputSurname"
                                   value="${user.surname}" placeholder="Příjmení (povinné)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputBirthNum" class="control-label change-pass-label">Rodné číslo</label>
                        <div class="col-sm-10 doc-form">
                            <input type="text" class="form-control change-pass" name="inputBirthNum"
                                   value="${user.birthNum}" placeholder="Rodné číslo"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputAddr" class="control-label change-pass-label">Adresa</label>
                        <div class="col-sm-10 doc-form">
                            <input type="text" class="form-control change-pass" name="inputAddr"
                                   value="${user.address}" placeholder="Adresa"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputCity" class="control-label change-pass-label">Město</label>
                        <div class="col-sm-10 doc-form">
                            <input type="text" class="form-control change-pass" name="inputCity"
                                   value="${user.city}" placeholder="Město"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail" class="control-label change-pass-label">E-mail</label>
                        <div class="col-sm-10 doc-form">
                            <input type="text" class="form-control change-pass" name="inputEmail"
                                   value="${user.email}" placeholder="E-mail (povinné)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputTel" class="control-label change-pass-label">Telefon</label>
                        <div class="col-sm-10 doc-form">
                            <input type="text" class="form-control change-pass" name="inputTel"
                                   value="${user.tel}" placeholder="Telefonní číslo"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Storno</button>
                        <input type="submit" value="Změnit" class="btn btn-primary" data-dissmiss="modal"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
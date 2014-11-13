<%-- 
    Document   : addNurse
    Created on : 13.11.2014, 13:47:11
    Author     : Touche

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="m"%>

<m:Base title="Nemocnice - Přidání sestry">
    <m:RootBase/>
    <div class='container doc-container'>
        <form class='form-horizontal' role='form' method='POST' action='<c:url value="/actionAddNurse"/>'>
            <fieldset>
                <c:if test="${!param.username}">
                    <div class='form-group'>
                        <label for='inputName' class='col-sm-2 control-label'>Jméno</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' value="${nurse.username}" name='inputName' placeHolder='Jméno'/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${param.username}">
                    <div class='form-group has-error'>
                        <label for='inputName' class='col-sm-2 control-label'>Jméno</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' name='inputName' placeHolder='Jméno'/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${!param.surname}">
                    <div class='form-group'>
                        <label for='inputSurame' class='col-sm-2 control-label'>Příjmení</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' value="${nurse.surname}" name='inputSurname' placeHolder='Příjmení'/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${param.surname}">
                    <div class='form-group has-error'>
                        <label for='inputSurame' class='col-sm-2 control-label'>Příjmení</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' name='inputSurname' placeHolder='Příjmení'/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${!param.birthNum}">
                    <div class='form-group'>
                        <label for='inputBirthNum' class='col-sm-2 control-label'>Rodné číslo</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' value="${nurse.birthNum}" name='inputBirthNum' placeHolder='Rodné číslo'>
                        </div>
                    </div>
                </c:if>
                <c:if test="${param.birthNum}">
                    <div class='form-group has-error'>
                        <label for='inputBirthNum' class='col-sm-2 control-label'>Rodné číslo</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' name='inputBirthNum' placeHolder='Rodné číslo'>
                        </div>
                    </div>
                </c:if>
                <c:if test="${!param.address}">
                    <div class='form-group'>
                        <label for='inputAddr' class='col-sm-2 control-label'>Adresa</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' value="${nurse.address}" name='inputAddr' placeholder="Adresa">
                        </div>
                    </div>
                </c:if>
                <c:if test="${param.address}">
                    <div class='form-group has-error'>
                        <label for='inputAddr' class='col-sm-2 control-label'>Adresa</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' name='inputAddr' placeholder="Adresa">
                        </div>
                    </div>
                </c:if>
                <c:if test="${!param.city}">
                    <div class='form-group'>
                        <label for='inputCity' class='col-sm-2 control-label'>Město</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' value="${nurse.city}" name='inputCity' placeholder="Město">
                        </div>
                    </div>
                </c:if>
                <c:if test="${param.city}">
                    <div class='form-group has-error'>
                        <label for='inputCity' class='col-sm-2 control-label'>Město</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' name='inputCity' placeholder="Město">
                        </div>
                    </div>
                </c:if>
                <div class="form-group">
                    <label for="inputDepNum" class="col-sm-2 control-label">Oddělení</label>
                    <select name="inputDepNum" class="form-control doc-form select">
                        <option></option>
                        <option>ORL</option>
                        <option>Ortopedie</option>
                        <option>Urologie</option>
                        <option>Chirurgie</option>
                        <option>Traumatologie</option>
                        <!-- todo mel bych tohle pridavat dynamicky z databaze -->
                    </select>
                </div>
                <button type='submit' class='btn btn-primary doc-button'>Přidat</button>
            </fieldset>
        </form>
    </div>
        <c:if test="${param.username or param.surname or param.birthNum or param.address or param.city}">
        <div class="alert alert-dismissable alert-danger myAlert docAlert">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <c:if test="${param.username}">
                <strong class='doc-warn'>Prosím, zadejte korektně jméno.</strong>
            </c:if>
            <c:if test="${param.surname}">
                <strong class='doc-warn'>Prosím, zadejte korektně příjmení.</strong>
            </c:if>
            <c:if test="${param.birthNum}">
                <strong class='doc-warn'>Prosím, zadejte korektně rodné číslo.</strong>
            </c:if>
            <c:if test="${param.address}">
                <strong class='doc-warn'>Prosím, zadejte korektně adresu.</strong>
            </c:if>    
            <c:if test="${param.city}">
                <strong class='doc-warn'>Prosím, zadejte korektně město.</strong>
            </c:if>
            <c:if test="${param.dep}">
                <strong class="doc-warn">Prosím, zařaďte sestru na oddělení.</strong>
            </c:if>
        </div>          
    </c:if>
    <img src='res/virtualni-nemocnice-logo.png' alt='logo' class='footer_logo'/>
</m:Base>

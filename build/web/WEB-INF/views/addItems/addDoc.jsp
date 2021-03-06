<%-- 
    Document   : addDoc
    Created on : 10.11.2014, 17:56:29
    Author     : Ondřej Krpec, xkrpec01@stud.fit.vutbr.cz
               : Jiří Kulda, xkulda00@stud.fit.vutbr.cz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="m"%>

<m:Base title="Nemocnice - Přidání lékaře">
    <m:RootBase/>
    <div class='container doc-container'>
        <form class='form-horizontal' role='form' method='POST' action='<c:url value="/actionAddDoc"/>'>
            <fieldset>
                <c:if test="${!param.username}">
                    <div class='form-group'>
                        <label for='inputName' class='col-sm-2 control-label'>Jméno</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' value="${doctor.username}" 
                                   name='inputName' placeHolder='Jméno (povinné)'/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${param.username}">
                    <div class='form-group has-error'>
                        <label for='inputName' class='col-sm-2 control-label'>Jméno</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' name='inputName' placeHolder='Jméno (povinné)'/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${!param.surname}">
                    <div class='form-group'>
                        <label for='inputSurame' class='col-sm-2 control-label'>Příjmení</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' value="${doctor.surname}" 
                                   name='inputSurname' placeHolder='Příjmení'/>
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
                <div class='form-group'>
                    <label for='inputBirthNum' class='col-sm-2 control-label'>Rodné číslo</label>
                    <div class='col-sm-10 doc-form'>
                        <input type='text' class='form-control' value="${doctor.birthNum}" name='inputBirthNum' placeHolder='Rodné číslo'>
                    </div>
                </div>
                <c:if test="${!param.email && !param.used}">
                    <div class='form-group'>
                        <label for='inputMail' class='col-sm-2 control-label'>E-mail</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' value="${doctor.email}" name='inputMail' 
                                   placeholder="E-mailová adresa (povinné)">
                        </div>
                    </div>
                </c:if>
                <c:if test="${param.email || param.used}">
                    <div class='form-group has-error'>
                        <label for='inputMail' class='col-sm-2 control-label'>E-mail</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' name='inputMail' 
                                   placeholder="E-mailová adresa (povinné)">
                        </div>
                    </div>
                </c:if>
                <c:if test="${!param.tel}">
                    <div class='form-group'>
                        <label for='inputTel' class='col-sm-2 control-label myTooltip' data-toggle='tooltip' 
                               data-placement='left' title="Telefonní číslo">Telefon</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' value="${doctor.tel}" name='inputTel' 
                                   placeholder="Telefonní číslo">
                        </div>
                    </div>
                </c:if>
                <c:if test="${param.tel}">
                    <div class='form-group has-error'>
                        <label for='inputTel' class='col-sm-2 control-label'>Telefon</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' name='inputTel' 
                                   placeholder="Telefonní číslo">
                        </div>
                    </div>
                </c:if>
                <div class='form-group'>
                    <label for='inputAddr' class='col-sm-2 control-label'>Adresa</label>
                    <div class='col-sm-10 doc-form'>
                        <input type='text' class='form-control' value="${doctor.address}" name='inputAddr' 
                               placeholder="Adresa">
                    </div>
                </div>
                <div class='form-group'>
                    <label for='inputCity' class='col-sm-2 control-label'>Město</label>
                    <div class='col-sm-10 doc-form'>
                        <input type='text' class='form-control' value="${doctor.city}" name='inputCity' placeholder="Město">
                    </div>
                </div>
                <button type='submit' class='btn btn-primary doc-button'>Přidat</button>
            </fieldset>
        </form>
    </div>
        <c:if test="${param.username or param.surname or param.email or param.tel or param.used}">
        <div class="alert alert-dismissable alert-danger myAlert docAlert">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <c:if test="${param.username}">
                <strong class='doc-warn'>Prosím, zadejte korektně jméno.</strong>
            </c:if>
            <c:if test="${param.surname}">
                <strong class='doc-warn'>Prosím, zadejte korektně příjmení.</strong>
            </c:if>
            <c:if test="${param.email}">
                <strong class='doc-warn'>Prosím, zadejte korektně e-mail.</strong>
            </c:if>
            <c:if test="${param.used}">
                <strong class="doc-warn">E-mailová adresa je již používána.</strong>
            </c:if>
            <c:if test="${param.tel}">
                <strong class='doc-warn'>Prosím, zadejte korektně telefon.</strong>
            </c:if>   
        </div>          
    </c:if>
    <img src='res/virtualni-nemocnice-logo.png' alt='logo' class='footer_logo'/>  
</m:Base>

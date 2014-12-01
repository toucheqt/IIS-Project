<%-- 
    Document   : addDoc
    Created on : 10.11.2014, 17:56:29
    Author     : Ondřej Krpec, xkrpec01@stud.fit.vutbr.cz
               : Jiří Kulda, xkulda00@stud.fit.vutbr.cz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="m"%>

<m:Base title="Nemocnice - Přidání pacienta">
    <m:UserBase/>
    <div class='container doc-container'>
        <form class='form-horizontal' role='form' method='POST' action='<c:url value="/actionAddPatient"/>'>
            <fieldset>
                <c:if test="${!param.name}">
                    <div class='form-group'>
                        <label for='inputName' class='col-sm-2 control-label'>Jméno</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' value="${patient.name}" 
                                   name='inputName' placeHolder='Jméno (povinné)'/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${param.name}">
                    <div class='form-group has-error'>
                        <label for='inputName' class='col-sm-2 control-label'>Jméno</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' name='inputName' placeHolder='Jméno (povinné)'/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${!param.surname}">
                    <div class='form-group'>
                        <label for='inputSurname' class='col-sm-2 control-label'>Příjmení</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' value="${patient.surname}" 
                                   name='inputSurname' placeHolder='Příjmení (povinné)'/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${param.surname}">
                    <div class='form-group has-error'>
                        <label for='inputSurname' class='col-sm-2 control-label'>Příjmení</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' name='inputSurname' placeHolder='Příjmení (povinné)'/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${!param.birthNum}">
                    <div class='form-group'>
                        <label for='inputBirthNum' class='col-sm-2 control-label'>Rodné číslo</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' value="${patient.birthNum}" name='inputBirthNum'
                                   placeHolder='Rodné číslo'>
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
                <div class='form-group'>
                    <label for='inputAddr' class='col-sm-2 control-label'>Adresa</label>
                    <div class='col-sm-10 doc-form'>
                        <input type='text' class='form-control' value="${patient.address}" name='inputAddr' 
                               placeholder="Adresa">
                    </div>
                </div>
                <c:if test="${!param.city}">
                    <div class='form-group'>
                        <label for='inputCity' class='col-sm-2 control-label'>Město</label>
                        <div class='col-sm-10 doc-form'>
                            <input type='text' class='form-control' value="${patient.city}" name='inputCity' 
                                   placeholder="Město">
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
                <button type='submit' class='btn btn-primary doc-button'>Přidat</button>
            </fieldset>
        </form>
    </div>
        <c:if test="${param.name or param.surname or param.birthNum or param.city}">
        <div class="alert alert-dismissable alert-danger myAlert docAlert">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <c:if test="${param.name}">
                <strong class='doc-warn'>Prosím, zadejte korektně jméno.</strong>
            </c:if>
            <c:if test="${param.surname}">
                <strong class='doc-warn'>Prosím, zadejte korektně příjmení.</strong>
            </c:if>
            <c:if test="${param.birthNum}">
                <strong class='doc-warn'>Prosím, zadejte korektně rodné číslo.</strong>
            </c:if>   
            <c:if test="${param.city}">
                <strong class='doc-warn'>Prosím, zadejte korektně město.</strong>
            </c:if>
        </div>          
    </c:if>
    <img src='res/virtualni-nemocnice-logo.png' alt='logo' class='footer_logo'/>
    
</m:Base>

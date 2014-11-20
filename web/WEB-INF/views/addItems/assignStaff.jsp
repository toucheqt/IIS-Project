<%-- 
    Document   : addNurse
    Created on : 13.11.2014, 13:47:11
    Author     : Touche

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="m"%>

<m:Base title="Nemocnice - Přiřazení personálu">
    <m:RootBase/>
    <div class='container doc-container'>
        <form class='form-horizontal' role='form' method='POST' action='<c:url value="/actionAssignStaff"/>'>
            <fieldset>
                <div class="form-group">
                    <label for="staff" class="col-sm-2 control-label">Lékaři</label>
                    <select name="inputDoctor" class="form-control doc-form select">
                        <option disabled selected>Vyberte lékaře</option>
                        <c:forEach var="doctorInfo" items="${doctor}" varStatus="status">
                            <option>${doctor[status.index].username} ${doctor[status.index].surname}, 
                                ${doctor[status.index].email}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="inputDepNum" class="col-sm-2 control-label">Oddělení</label>
                    <select name="inputDepNum" class="form-control doc-form select">
                        <option disabled selected>Vyberte oddělení</option>
                        <c:forEach var="depName" items="${department}" varStatus="status">
                            <option>${department[status.index]}</option>
                        </c:forEach>
                    </select>
                </div>
                <c:if test="${!param.tel}">
                    <div class="form-group">
                        <label for="telDep" class="col-sm-2 control-label">Telefon</label>
                        <div class="col-sm-10 doc-form">
                            <input type="text" class="form-control" name="inputTelDep" value="${telNum}" placeholder="Telefon na oddělení">
                        </div>
                    </div>
                </c:if>
                <c:if test="${param.tel}">
                    <div class="form-group has-error">
                        <label for="telDep" class="col-sm-2 control-label">Telefon</label>
                        <div class="col-sm-10 doc-form">
                            <input type="text" class="form-control" name="inputTelDep" placeholder="Telefon na oddělení">
                        </div>
                    </div>
                </c:if>
                <div class="form-group">
                    <label for="workingTime" class="col-sm-2 control-label">Úvazek</label>
                    <select name="inputWorkingTime" class="form-control doc-form select">
                        <option disabled selected>Vyberte typ úvazku</option>
                        <option>Plný úvazek</option>
                        <option>Částečný úvazek</option>
                        <option>Internship</option>
                    </select>
                </div>
                <button type='submit' class='btn btn-primary doc-button'>Přidat</button>
            </fieldset>
        </form>
    </div>
    <c:if test="${param.tel}">
        <div class="alert alert-dismissable alert-danger myAlert docAlert">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong class='doc-warn'>Prosím, zadejte korektně telefon.</strong>
        </div>             
    </c:if>
    <c:if test="${param.other}">
        <div class="alert alert-dismissable alert-danger myAlert docAlert">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong class='doc-warn'>Prosím, vyplňte koretkně údaje.</strong>
        </div>             
    </c:if>
    <c:if test="${param.mail}">
        <div class="alert alert-dismissable alert-danger myAlert docAlert">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong class='doc-warn'>Doktor již na tomto oddělení pracuje.</strong>
        </div>             
    </c:if>        
    <img src='res/virtualni-nemocnice-logo.png' alt='logo' class='footer_logo'/>
</m:Base>

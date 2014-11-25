<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : showDepartment
    Created on : 20.11.2014, 11:29:30
    Author     : Touche
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="m"%>

<m:Base title="Nemocnice - Oddělení">
    <m:RootBase/>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <c:choose>
                    <c:when test="${empty param.dep}">
                        <li class="active">
                            <a href="<c:url value="/showDepartment"/>">Oddělení</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="<c:url value="/showDepartment"/>">Oddělení</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="depname" items="${department}" varStatus="status">
                    <c:choose>
                        <c:when test="${param.dep == department[status.index]}">
                            <li class="active">
                                <a href="<c:url value="/showDepartment?dep=${department[status.index]}"/>">
                                    ${department[status.index]}
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="<c:url value="/showDepartment?dep=${department[status.index]}"/>">
                                    ${department[status.index]}
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>         
        </div> <!-- TODO radit pomoci js tablesorter 2.0 -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">Oddělení ${param.dep} | doktoři</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Jméno</th>
                            <th>Příjmení</th>
                            <th>Email</th>
                            <th>Název oddělení</th>
                            <th>Úvazek</th>
                            <th>Telefon</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty param.dep}">
                                <c:forEach var="docInfo" items="${doctor}" varStatus="status">
                                    <tr>
                                        <td>${doctor[status.index].username}</td>
                                        <td>${doctor[status.index].surname}</td>
                                        <td>${doctor[status.index].email}</td>
                                        <td>${doctor[status.index].departmentName}</td>
                                        <td>${doctor[status.index].workingTime}</td>
                                        <c:if test="${doctor[status.index].tel != 0}">
                                            <td>${doctor[status.index].tel}</td>
                                        </c:if>
                                        <c:if test="${doctor[status.index].tel == 0}">
                                            <td></td>
                                        </c:if>                                
                                        <td class="col-sm-1 col-md-1">
                                            <button type="button" class="btn btn-sm btn-primary nurse-change"
                                                    data-toggle="modal" data-target="#${status.index}-change">Změnit
                                            </button>
                                        </td>
                                        <div class="modal fade bs-example-modal-sm" id="${status.index}-change" tabindex="-1" role="dialog"
                                             aria-labelledby="modalDelete" aria-hidden="true">
                                            <div class="modal-dialog modal-lg">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title">Úprava lékaře / lékařky</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form class="form-horizontal" role="form" method="POST" action="<c:url value="/updateDocWork"/>">
                                                            <div class="form-group">
                                                                <label for="inputName" class="control-label change-form-label">Jméno</label>
                                                                <div class="col-sm-10 doc-form">
                                                                    <input type="text" class="form-control change-form" value="${doctor[status.index].username}"
                                                                          name="inputName" disabled="true"/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="inputSurname" class="control-label change-form-label">Příjmení</label>
                                                                <div class="col-sm-10 doc-form">
                                                                    <input type="text" class="form-control change-form" value="${doctor[status.index].surname}"
                                                                           name="inputSurname" disabled="true"/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="inputEmail" class="control-label change-form-label">E-mail</label>
                                                                <div class="col-sm-10 doc-form">
                                                                    <input type="text" class="form-control change-form" value="${doctor[status.index].email}"
                                                                           name="inputEmail" disabled="true"/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="inputDepName" class="col-sm-2 control-label change-form-select-label">Oddělení</label>
                                                                <select name="inputDepName" class="form-control doc-form select change-form-select">
                                                                    <option disabled selected>Vyberte oddělení</option>
                                                                    <c:forEach var="depName" items="${department}" varStatus="iterator">
                                                                        <option>${department[iterator.index]}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="inputTel" class="control-label change-form-label">Telefon</label>
                                                                <div class="col-sm-10 doc-form">
                                                                    <input type="text" class="form-control change-form" value="${doctor[status.index].tel}"
                                                                           name="inputTel"/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="inputWorkingTime" class="col-sm-2  control-label change-form-select-label">Úvazek</label>
                                                                <select name="inputWorkingTime" class="form-control doc-form select change-form-select">
                                                                    <option disabled selected>Vyberte typ úvazku</option>
                                                                    <option>Plný úvazek</option>
                                                                    <option>Částečný úvazek</option>
                                                                    <option>Internship</option>
                                                                </select>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <input type="hidden" value="${doctor[status.index].departmentName}" name="defaultDepName"/>
                                                                <input type="hidden" value="${doctor[status.index].email}" name="defaultEmail"/>
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Storno</button>
                                                                <input type="submit" value="Potvrdit" class="btn btn-primary" data-dissmiss="modal"/>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <td class="col-sm-1 col-md-1">
                                            <button type="button" class="btn btn-sm btn-primary nurse-remove"
                                                    data-toggle="modal" data-target="#${status.index}-remove">Odebrat
                                            </button>
                                        </td>
                                        <div class="modal fade bs-example-modal-sm" id="${status.index}-remove" tabindex="-1" role="dialog"
                                             aria-labelledby="modalDelete" aria-hidden="true">
                                            <div class="modal-dialog modal-sm">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title">Opravdu chcete lékaře odebrat z oddělení?</h4>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <form method="POST" action="<c:url value="/delDocWork"/>">
                                                            <input type="hidden" value="${doctor[status.index].email}" name="email"/>
                                                            <input type="hidden" value="${doctor[status.index].departmentName}" name="depName"/>
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Storno</button>
                                                            <input type="submit" value="Potvrdit" class="btn btn-primary" data-dissmiss="modal"/>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>     
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="docInfo" items="${doctor}" varStatus="status">
                                    <c:if test="${param.dep == doctor[status.index].departmentName}">
                                        <tr>
                                            <td>${doctor[status.index].username}</td>
                                            <td>${doctor[status.index].surname}</td>
                                            <td>${doctor[status.index].email}</td>
                                            <td>${doctor[status.index].departmentName}</td>
                                            <td>${doctor[status.index].workingTime}</td>
                                            <c:if test="${doctor[status.index].tel != 0}">
                                                <td>${doctor[status.index].tel}</td>
                                            </c:if>
                                            <c:if test="${doctor[status.index].tel == 0}">
                                                <td></td>
                                            </c:if>                                
                                            <td class="col-sm-1 col-md-1">
                                                <button type="button" class="btn btn-sm btn-primary nurse-change"
                                                        data-toggle="modal" data-target="#${status.index}-change">Změnit
                                                </button>
                                            </td>
                                            <div class="modal fade bs-example-modal-sm" id="${status.index}-change" tabindex="-1" role="dialog"
                                                 aria-labelledby="modalDelete" aria-hidden="true">
                                                <div class="modal-dialog modal-lg">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title">Úprava lékaře / lékařky</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form class="form-horizontal" role="form" method="POST" action="<c:url value="/updateDocWork"/>">
                                                                <div class="form-group">
                                                                    <label for="inputName" class="control-label change-form-label">Jméno</label>
                                                                    <div class="col-sm-10 doc-form">
                                                                        <input type="text" class="form-control change-form" value="${doctor[status.index].username}"
                                                                              name="inputName" disabled="true"/>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="inputSurname" class="control-label change-form-label">Příjmení</label>
                                                                    <div class="col-sm-10 doc-form">
                                                                        <input type="text" class="form-control change-form" value="${doctor[status.index].surname}"
                                                                               name="inputSurname" disabled="true"/>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="inputEmail" class="control-label change-form-label">E-mail</label>
                                                                    <div class="col-sm-10 doc-form">
                                                                        <input type="text" class="form-control change-form" value="${doctor[status.index].email}"
                                                                               name="inputEmail" disabled="true"/>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="inputDepName" class="col-sm-2 control-label change-form-select-label">Oddělení</label>
                                                                    <select name="inputDepName" class="form-control doc-form select change-form-select">
                                                                        <option disabled selected>Vyberte oddělení</option>
                                                                        <c:forEach var="depName" items="${department}" varStatus="iterator">
                                                                            <option>${department[iterator.index]}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="inputTel" class="control-label change-form-label">Telefon</label>
                                                                    <div class="col-sm-10 doc-form">
                                                                        <input type="text" class="form-control change-form" value="${doctor[status.index].tel}"
                                                                               name="inputTel"/>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="inputWorkingTime" class="col-sm-2  control-label change-form-select-label">Úvazek</label>
                                                                    <select name="inputWorkingTime" class="form-control doc-form select change-form-select">
                                                                        <option disabled selected>Vyberte typ úvazku</option>
                                                                        <option>Plný úvazek</option>
                                                                        <option>Částečný úvazek</option>
                                                                        <option>Internship</option>
                                                                    </select>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <input type="hidden" value="${doctor[status.index].departmentName}" name="defaultDepName"/>
                                                                    <input type="hidden" value="${doctor[status.index].email}" name="defaultEmail"/>
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Storno</button>
                                                                    <input type="submit" value="Potvrdit" class="btn btn-primary" data-dissmiss="modal"/>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <td class="col-sm-1 col-md-1">
                                                <button type="button" class="btn btn-sm btn-primary nurse-remove"
                                                        data-toggle="modal" data-target="#${status.index}-remove">Odebrat
                                                </button>
                                            </td>
                                            <div class="modal fade bs-example-modal-sm" id="${status.index}-remove" tabindex="-1" role="dialog"
                                                 aria-labelledby="modalDelete" aria-hidden="true">
                                                <div class="modal-dialog modal-sm">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title">Opravdu chcete lékaře odebrat z oddělení?</h4>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <form method="POST" action="<c:url value="/delDocWork"/>">
                                                                <input type="hidden" value="${doctor[status.index].email}" name="email"/>
                                                                <input type="hidden" value="${doctor[status.index].departmentName}" name="depName"/>
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Storno</button>
                                                                <input type="submit" value="Potvrdit" class="btn btn-primary" data-dissmiss="modal"/>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>     
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
            <c:if test="${param.err}">
                <div class="alert alert-dismissable alert-danger alert-update">
                    <strong class='warn-strong'>Nepovedlo se aktualizovat tabulku.</strong>
                </div>
            </c:if>
        </div>
        <div class="col-sm-2 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">Oddělení ${param.dep} | sestry</h2>
          <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Identifikační číslo</th>
                        <th>Jméno</th>
                        <th>Příjmení</th>
                        <th>Název oddělení</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty param.dep}">
                            <c:forEach var="nurseInfo" items="${nurse}" varStatus="status">
                                <tr>
                                    <td class="col-sm-1 col-md-2">${nurse[status.index].id}</td>
                                    <td>${nurse[status.index].username}</td>
                                    <td>${nurse[status.index].surname}</td>
                                    <td>${nurse[status.index].departmentName}</td>
                                    <td class="col-sm-1 col-md-1">
                                        <button type='submit' class='btn btn-sm btn-primary nurse-update'
                                                data-toggle="modal" data-target="#${status.index}-nurse-update">Změnit
                                        </button>
                                    </td>
                                    <div class="modal fade bs-example-modal-sm" id="${status.index}-nurse-update" tabindex="-1" role="dialog"
                                            aria-labelledby="modalDelete" aria-hidden="true">
                                        <div class="modal-dialog modal-lg">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h4 class="modal-title">Úprava sestry</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <form class="form-horizontal" role="form" method="POST" action="<c:url value="/updateNurseWork"/>">
                                                        <div class="form-group">
                                                            <label for="inputId" class="control-label change-form-label">Id</label>
                                                            <div class="col-sm-10 doc-form">
                                                                <input type="text" class="form-control change-form" value="${nurse[status.index].id}"
                                                                        name="inputId" disabled="true"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="inputName" class="control-label change-form-label">Jméno</label>
                                                            <div class="col-sm-10 doc-form">
                                                                <input type="text" class="form-control change-form" value="${doctor[status.index].username}"
                                                                        name="inputName" disabled="true"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="inputSurname" class="control-label change-form-label">Příjmení</label>
                                                            <div class="col-sm-10 doc-form">
                                                                <input type="text" class="form-control change-form" value="${doctor[status.index].surname}"
                                                                        name="inputSurname" disabled="true"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="inputDepName" class="col-sm-2 control-label change-form-select-label">Oddělení</label>
                                                            <select name="inputDepName" class="form-control doc-form select change-form-select">
                                                                <option disabled selected>Vyberte oddělení</option>
                                                                <c:forEach var="depName" items="${department}" varStatus="iterator">
                                                                    <option>${department[iterator.index]}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <input type="hidden" value="${nurse[status.index].id}" name="defaultId"/>
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Storno</button>
                                                            <input type="submit" value="Potvrdit" class="btn btn-primary" data-dissmiss="modal"/>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="nurseInfo" items="${nurse}" varStatus="status">
                                <c:if test="${param.dep == nurse[status.index].departmentName}">
                                    <tr>
                                        <td class="col-sm-1 col-md-2">${nurse[status.index].id}</td>
                                        <td>${nurse[status.index].username}</td>
                                        <td>${nurse[status.index].surname}</td>
                                        <td>${nurse[status.index].departmentName}</td>
                                        <td class="col-sm-1 col-md-1">
                                            <button type='submit' class='btn btn-sm btn-primary nurse-update'
                                                    data-toggle="modal" data-target="#${status.index}-nurse-update">Změnit
                                            </button>
                                        </td>
                                        <div class="modal fade bs-example-modal-sm" id="${status.index}-nurse-update" tabindex="-1" role="dialog"
                                                aria-labelledby="modalDelete" aria-hidden="true">
                                            <div class="modal-dialog modal-lg">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title">Úprava sestry</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form class="form-horizontal" role="form" method="POST" action="<c:url value="/updateNurseWork"/>">
                                                            <div class="form-group">
                                                                <label for="inputId" class="control-label change-form-label">Id</label>
                                                                <div class="col-sm-10 doc-form">
                                                                    <input type="text" class="form-control change-form" value="${nurse[status.index].id}"
                                                                            name="inputId" disabled="true"/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="inputName" class="control-label change-form-label">Jméno</label>
                                                                <div class="col-sm-10 doc-form">
                                                                    <input type="text" class="form-control change-form" value="${doctor[status.index].username}"
                                                                            name="inputName" disabled="true"/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="inputSurname" class="control-label change-form-label">Příjmení</label>
                                                                <div class="col-sm-10 doc-form">
                                                                    <input type="text" class="form-control change-form" value="${doctor[status.index].surname}"
                                                                            name="inputSurname" disabled="true"/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="inputDepName" class="col-sm-2 control-label change-form-select-label">Oddělení</label>
                                                                <select name="inputDepName" class="form-control doc-form select change-form-select">
                                                                    <option disabled selected>Vyberte oddělení</option>
                                                                    <c:forEach var="depName" items="${department}" varStatus="iterator">
                                                                        <option>${department[iterator.index]}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <input type="hidden" value="${nurse[status.index].id}" name="defaultId"/>
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Storno</button>
                                                                <input type="submit" value="Potvrdit" class="btn btn-primary" data-dissmiss="modal"/>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </tr>
                                </c:if>    
                            </c:forEach>
                        </c:otherwise>        
                    </c:choose>
                </tbody>
            </table>
          </div>
        </div>
        
        
        
        
        
        
        
        
        
      </div>
    </div>
</m:Base>

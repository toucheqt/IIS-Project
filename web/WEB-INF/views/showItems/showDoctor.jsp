<%-- 
    Document   : showDepartment
    Created on : 20.11.2014, 11:29:30
    Author     : Touche
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="m"%>

<m:Base title="Nemocnice - Doktoři">
    <m:RootBase/>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <c:choose>
                        <c:when test="${empty param.dep}">
                            <li class="active">
                                <a href="<c:url value="/showDoctor"/>">Oddělení</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="<c:url value="/showDoctor"/>">Oddělení</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="depname" items="${department}" varStatus="status">
                        <c:choose>
                            <c:when test="${param.dep == department[status.index]}">
                                <li class="active">
                                    <a href="<c:url value="/showDoctor?dep=${department[status.index]}"/>">
                                        ${department[status.index]}
                                    </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="<c:url value="/showDoctor?dep=${department[status.index]}"/>">
                                        ${department[status.index]}
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>      
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <c:choose>
                    <c:when test="${empty param.dep}">
                        <h2 class="sub-header">Oddělení</h2>
                    </c:when>
                    <c:otherwise>
                        <h2 class="sub-header">${param.dep}</h2>
                    </c:otherwise>
                </c:choose>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Jméno</th>
                                <th>Příjmení</th>
                                <th>Rodné číslo</th>
                                <th>Adresa</th>
                                <th>Město</th>
                                <th>E-mail</th>
                                <th>Telefon</th>
                                <th>Oddělení</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${empty param.dep}">
                                    <c:forEach var="docInfo" items="${doctor}" varStatus="status">
                                        <tr>
                                            <td>${doctor[status.index].username}</td>
                                            <td>${doctor[status.index].surname}</td>
                                            <td>${doctor[status.index].birthNum}</td>
                                            <td>${doctor[status.index].address}</td>
                                            <td>${doctor[status.index].city}</td>
                                            <td>${doctor[status.index].email}</td>
                                            <c:if test="${doctor[status.index].tel == 0}">
                                                <td></td>
                                            </c:if>
                                            <c:if test="${doctor[status.index].tel != 0}">
                                                <td>${doctor[status.index].tel}</td>
                                            </c:if>
                                            <td>${doctor[status.index].departmentName}</td>
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
                                                            <h4 class="modal-title">Úprava lékaře</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form class="form-horizontal" role="form" method="POST" action="<c:url value="/updateDoctor"/>">
                                                                <div class="form-group">
                                                                    <label for="inputName" class="control-label change-form-label">Jméno</label>
                                                                    <div class="col-sm-10 doc-form">
                                                                        <input type="text" class="form-control change-form" value="${doctor[status.index].username}"
                                                                               name="inputName"/>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="inputSurname" class="control-label change-form-label">Příjmení</label>
                                                                    <div class="col-sm-10 doc-form">
                                                                        <input type="text" class="form-control change-form" value="${doctor[status.index].surname}"
                                                                               name="inputSurname"/>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="inputBirthNum" class="control-label change-form-label">Rodné číslo</label>
                                                                    <div class="col-sm-10 doc-form">
                                                                        <input type="text" class="form-control change-form" value="${doctor[status.index].birthNum}"
                                                                               name="inputBirthNum"/>
                                                                    </div>
                                                                </div>
                                                               <div class="form-group">
                                                                    <label for="inputAddr" class="control-label change-form-label">Adresa</label>
                                                                    <div class="col-sm-10 doc-form">
                                                                        <input type="text" class="form-control change-form" value="${doctor[status.index].address}"
                                                                               name="inputAddr"/>
                                                                    </div>
                                                                </div>               
                                                                <div class="form-group">
                                                                    <label for="inputCity" class="control-label change-form-label">Město</label>
                                                                    <div class="col-sm-10 doc-form">
                                                                        <input type="text" class="form-control change-form" value="${doctor[status.index].city}"
                                                                               name="inputCity"/>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="inputEmail" class="control-label change-form-label">E-mail</label>
                                                                    <div class="col-sm-10 doc-form">
                                                                        <input type="text" class="form-control change-form" value="${doctor[status.index].email}"
                                                                               name="inputEmail"/>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="inputTel" class="control-label change-form-label">Telefon</label>
                                                                    <div class="col-sm-10 doc-form">
                                                                        <input type="text" class="form-control change-form" value="${doctor[status.index].tel}"
                                                                               name="inputTel"/>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
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
                                                            <h4 class="modal-title">Opravdu chcete lékaře propustit?</h4>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <form method="POST" action="<c:url value="/delDoctor"/>">
                                                                <input type="hidden" value="${doctor[status.index].email}" name="defaultEmail"/>
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
                                        <c:if test="${doctor[status.index].departmentName == param.dep}">
                                            <tr>
                                                <td>${doctor[status.index].username}</td>
                                                <td>${doctor[status.index].surname}</td>
                                                <td>${doctor[status.index].birthNum}</td>
                                                <td>${doctor[status.index].address}</td>
                                                <td>${doctor[status.index].city}</td>
                                                <td>${doctor[status.index].email}</td>
                                                <c:if test="${doctor[status.index].tel == 0}">
                                                    <td></td>
                                                </c:if>
                                                <c:if test="${doctor[status.index].tel != 0}">
                                                    <td>${doctor[status.index].tel}</td>
                                                </c:if>
                                                <td>${doctor[status.index].departmentName}</td>
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
                                                                <h4 class="modal-title">Úprava lékaře</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <form class="form-horizontal" role="form" method="POST" action="<c:url value="/updateDoctor"/>">
                                                                    <div class="form-group">
                                                                        <label for="inputName" class="control-label change-form-label">Jméno</label>
                                                                        <div class="col-sm-10 doc-form">
                                                                            <input type="text" class="form-control change-form" value="${doctor[status.index].username}"
                                                                                   name="inputName"/>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="inputSurname" class="control-label change-form-label">Příjmení</label>
                                                                        <div class="col-sm-10 doc-form">
                                                                            <input type="text" class="form-control change-form" value="${doctor[status.index].surname}"
                                                                                   name="inputSurname"/>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="inputBirthNum" class="control-label change-form-label">Rodné číslo</label>
                                                                        <div class="col-sm-10 doc-form">
                                                                            <input type="text" class="form-control change-form" value="${doctor[status.index].birthNum}"
                                                                                   name="inputBirthNum"/>
                                                                        </div>
                                                                    </div>
                                                                   <div class="form-group">
                                                                        <label for="inputAddr" class="control-label change-form-label">Adresa</label>
                                                                        <div class="col-sm-10 doc-form">
                                                                            <input type="text" class="form-control change-form" value="${doctor[status.index].address}"
                                                                                   name="inputAddr"/>
                                                                        </div>
                                                                    </div>               
                                                                    <div class="form-group">
                                                                        <label for="inputCity" class="control-label change-form-label">Město</label>
                                                                        <div class="col-sm-10 doc-form">
                                                                            <input type="text" class="form-control change-form" value="${doctor[status.index].city}"
                                                                                   name="inputCity"/>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="inputEmail" class="control-label change-form-label">E-mail</label>
                                                                        <div class="col-sm-10 doc-form">
                                                                            <input type="text" class="form-control change-form" value="${doctor[status.index].email}"
                                                                                   name="inputEmail"/>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="inputTel" class="control-label change-form-label">Telefon</label>
                                                                        <div class="col-sm-10 doc-form">
                                                                            <input type="text" class="form-control change-form" value="${doctor[status.index].tel}"
                                                                                   name="inputTel"/>
                                                                        </div>
                                                                    </div>
                                                                    <div class="modal-footer">
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
                                                                <h4 class="modal-title">Opravdu chcete lékaře propustit?</h4>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <form method="POST" action="<c:url value="/delDoctor"/>">
                                                                    <input type="hidden" value="${doctor[status.index].email}" name="defaultEmail"/>
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
            </div> 
        </div>
    </div>    
</m:Base>

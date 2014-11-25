<%-- 
    Document   : showDepartment
    Created on : 20.11.2014, 11:29:30
    Author     : Touche
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="m"%>

<m:Base title="Nemocnice - Sestry">
    <m:RootBase/>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <c:choose>
                        <c:when test="${empty param.dep}">
                            <li class="active">
                                <a href="<c:url value="/showNurse"/>">Oddělení</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="<c:url value="/showNurse"/>">Oddělení</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="depname" items="${department}" varStatus="status">
                        <c:choose>
                            <c:when test="${param.dep == department[status.index]}">
                                <li class="active">
                                    <a href="<c:url value="/showNurse?dep=${department[status.index]}"/>">
                                        ${department[status.index]}
                                    </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="<c:url value="/showNurse?dep=${department[status.index]}"/>">
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
                                <th>Id</th>
                                <th>Jméno</th>
                                <th>Příjmení</th>
                                <th>Rodné číslo</th>
                                <th>Adresa</th>
                                <th>Město</th>
                                <th>Oddělení</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${empty param.dep}">
                                    <c:forEach var="nurseInfo" items="${nurse}" varStatus="status">
                                        <tr>
                                            <td>${nurse[status.index].id}</td>
                                            <td>${nurse[status.index].username}</td>
                                            <td>${nurse[status.index].surname}</td>
                                            <td>${nurse[status.index].birthNum}</td>
                                            <td>${nurse[status.index].address}</td>
                                            <td>${nurse[status.index].city}</td>
                                            <td>${nurse[status.index].departmentName}</td>
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
                                                            <h4 class="modal-title">Úprava sestry</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form class="form-horizontal" role="form" method="POST" action="<c:url value="/updateNurse"/>">
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
                                                                        <input type="text" class="form-control change-form" value="${nurse[status.index].username}"
                                                                               name="inputName"/>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="inputSurname" class="control-label change-form-label">Příjmení</label>
                                                                    <div class="col-sm-10 doc-form">
                                                                        <input type="text" class="form-control change-form" value="${nurse[status.index].surname}"
                                                                               name="inputSurname"/>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="inputBirthNum" class="control-label change-form-label">Rodné číslo</label>
                                                                    <div class="col-sm-10 doc-form">
                                                                        <input type="text" class="form-control change-form" value="${nurse[status.index].birthNum}"
                                                                               name="inputBirthNum"/>
                                                                    </div>
                                                                </div>
                                                               <div class="form-group">
                                                                    <label for="inputAddr" class="control-label change-form-label">Adresa</label>
                                                                    <div class="col-sm-10 doc-form">
                                                                        <input type="text" class="form-control change-form" value="${nurse[status.index].address}"
                                                                               name="inputAddr"/>
                                                                    </div>
                                                                </div>               
                                                                <div class="form-group">
                                                                    <label for="inputCity" class="control-label change-form-label">Město</label>
                                                                    <div class="col-sm-10 doc-form">
                                                                        <input type="text" class="form-control change-form" value="${nurse[status.index].city}"
                                                                               name="inputCity"/>
                                                                    </div>
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
                                                            <h4 class="modal-title">Opravdu chcete sestru propustit?</h4>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <form method="POST" action="<c:url value="/delNurse"/>">
                                                                <input type="hidden" value="${nurse[status.index].id}" name="id"/>
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
                                    <c:forEach var="nurseInfo" items="${nurse}" varStatus="status">
                                        <c:if test="${param.dep == nurse[status.index].departmentName}">
                                            <tr>
                                                <td>${nurse[status.index].id}</td>
                                                <td>${nurse[status.index].username}</td>
                                                <td>${nurse[status.index].surname}</td>
                                                <td>${nurse[status.index].birthNum}</td>
                                                <td>${nurse[status.index].address}</td>
                                                <td>${nurse[status.index].city}</td>
                                                <td>${nurse[status.index].departmentName}</td>
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
                                                                <h4 class="modal-title">Úprava sestry</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <form class="form-horizontal" role="form" method="POST" action="<c:url value="/updateNurse"/>">
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
                                                                            <input type="text" class="form-control change-form" value="${nurse[status.index].username}"
                                                                                   name="inputName"/>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="inputSurname" class="control-label change-form-label">Příjmení</label>
                                                                        <div class="col-sm-10 doc-form">
                                                                            <input type="text" class="form-control change-form" value="${nurse[status.index].surname}"
                                                                                   name="inputSurname"/>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="inputBirthNum" class="control-label change-form-label">Rodné číslo</label>
                                                                        <div class="col-sm-10 doc-form">
                                                                            <input type="text" class="form-control change-form" value="${nurse[status.index].birthNum}"
                                                                                   name="inputBirthNum"/>
                                                                        </div>
                                                                    </div>
                                                                   <div class="form-group">
                                                                        <label for="inputAddr" class="control-label change-form-label">Adresa</label>
                                                                        <div class="col-sm-10 doc-form">
                                                                            <input type="text" class="form-control change-form" value="${nurse[status.index].address}"
                                                                                   name="inputAddr"/>
                                                                        </div>
                                                                    </div>               
                                                                    <div class="form-group">
                                                                        <label for="inputCity" class="control-label change-form-label">Město</label>
                                                                        <div class="col-sm-10 doc-form">
                                                                            <input type="text" class="form-control change-form" value="${nurse[status.index].city}"
                                                                                   name="inputCity"/>
                                                                        </div>
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
                                                                <h4 class="modal-title">Opravdu chcete sestru propustit?</h4>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <form method="POST" action="<c:url value="/delNurse"/>">
                                                                    <input type="hidden" value="${nurse[status.index].id}" name="id"/>
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

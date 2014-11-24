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
            <li class="active"><a href="#">Oddělení<span class="sr-only">(current)</span></a></li>
            <c:forEach var="depName" items="${department}" varStatus="status">
                <li><a href="<c:url value="/showDepartment${department[status.index]}"/>">
                        ${department[status.index]}
                    </a>
                </li> 
            </c:forEach>
          </ul>
        </div> <!-- TODO radit pomoci js tablesorter 2.0 -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">Oddělení - doktoři</h2>
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
                                    <div class="modal-dialog modal-sm">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title">Úprava lékaře / lékařky</h4>
                                            </div>
                                            <div class="modal-body">
                                                <p>dasdasdasdasgjwiojhsopjiophsdfhnidasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd</p>
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
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-sm-2 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">Oddělení - sestry</h2>
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
                    <c:forEach var="nurseInfo" items="${nurse}" varStatus="status">
                        <tr>
                            <td class="col-sm-1 col-md-2">${nurse[status.index].id}</td>
                            <td>${nurse[status.index].username}</td>
                            <td>${nurse[status.index].surname}</td>
                            <td>${nurse[status.index].departmentName}</td>
                            <td class="col-sm-1 col-md-1">
                                <button type='submit' class='btn btn-sm btn-primary nurse-change'>Změnit</button>
                            </td>
                            <td class="col-sm-1 col-md-1">
                                <button type='submit' class='btn btn-sm btn-primary nurse-remove'>Odebrat</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
</m:Base>

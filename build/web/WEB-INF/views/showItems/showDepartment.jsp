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
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">Oddělení - doktoři</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>Název oddělení</th>
                  <th>Jméno</th>
                  <th>Příjmení</th>
                  <th>Email</th>
                  <th>Úvazek</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1,001</td>
                  <td>Lorem</td>
                  <td>ipsum</td>
                  <td>dolor</td>
                  <td>sit</td>
                </tr>
                <tr>
                  <td>1,001</td>
                  <td>Lorem</td>
                  <td>ipsum</td>
                  <td>dolor</td>
                  <td>sit</td>
                </tr>
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

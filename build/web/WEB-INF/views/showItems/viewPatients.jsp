<%-- 
    Document   : showDepartment
    Created on : 20.11.2014, 11:29:30
    Author     : Touche
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="m"%>

<m:Base title="Nemocnice - Pacienti">
    <m:UserBase/>
    <div class="container-fluid">
        <div class="row"> <!-- TODO synchronizovat vsechny show na view -->
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <c:choose>
                        <c:when test="${empty param.dep}">
                            <li class="active">
                                <a href="<c:url value="/viewPatients"/>">Vyšetření</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="<c:url value="/viewPatients"/>">Vyšetření</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="depname" items="${department}" varStatus="status">
                        <c:choose>
                            <c:when test="${param.dep == department[status.index]}">
                                <li class="active">
                                    <a href="<c:url value="/viewPatients?dep=${department[status.index]}"/>">
                                        ${department[status.index]}
                                    </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="<c:url value="/viewPatients?dep=${department[status.index]}"/>">
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
                        <h2 class="sub-header">Vyšetření</h2>
                    </c:when>
                    <c:otherwise>
                        <h2 class="sub-header">${param.dep} | hospitalizace</h2>
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
                                <th>Ošetřující lékář</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${empty param.dep}">
                                    <c:forEach var="nurseInfo" items="${patient}" varStatus="status">
                                        <tr>
                                            <td>${patient[status.index].name}</td>
                                            <td>${patient[status.index].surname}</td>
                                            <td>${patient[status.index].birthNum}</td>
                                            <td>${patient[status.index].address}</td>
                                            <td>${patient[status.index].city}</td>
                                            <td>${patient[status.index].doctorName} ${patient[status.index].doctorSurname}</td>
                                            <td class="col-sm-1 col-md-1">
                                                <button type="button" class="btn btn-sm btn-primary">
                                                    Více informací &raquo;
                                                </button>
                                            </td>  
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="nurseInfo" items="${patient}" varStatus="status">
                                        <c:if test="${param.dep == patient[status.index].departmentName}">
                                            <tr>
                                                <td>${patient[status.index].name}</td>
                                                <td>${patient[status.index].surname}</td>
                                                <td>${patient[status.index].birthNum}</td>
                                                <td>${patient[status.index].address}</td>
                                                <td>${patient[status.index].city}</td>
                                                <td>${patient[status.index].doctorName} ${patient[status.index].doctorSurname}</td>
                                                <td class="col-sm-1 col-md-1">
                                                    <button type="button" class="btn btn-sm btn-primary">
                                                        Více informací &raquo;
                                                    </button>
                                                </td>  
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

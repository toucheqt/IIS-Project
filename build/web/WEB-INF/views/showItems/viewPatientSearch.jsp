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
    <div class="container-fluid"> <!-- TODO synchronizovat vsechny show na view -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-1">
            <h2 class="sub-header">Nalezení pacienti</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Jméno</th>
                            <th>Příjmení</th>
                            <th>Rodné číslo</th>
                            <th>Adresa</th>
                            <th>Město</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="patientInfo" items="${patient}" varStatus="status">
                            <tr>
                                <td>${patient[status.index].name}</td>
                                <td>${patient[status.index].surname}</td>
                                <td>${patient[status.index].birthNum}</td>
                                <td>${patient[status.index].address}</td>
                                <td>${patient[status.index].city}</td>
                                <td class="col-sm-1 col-md-1">
                                    <form method="GET" action="<c:url value="/patient"/>">
                                        <input type="hidden" value="${patient[status.index].id}" name="patientId"/>
                                        <button type="submit" class="btn btn-sm btn-primary">
                                            Více informací &raquo;
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</m:Base>

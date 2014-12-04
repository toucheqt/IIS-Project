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
    <br>
    <div class="container">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">${patient.name} ${patient.surname}</h3>
            </div>
            <div class="panel-body">
                <div class="container">
                    
                    <div class="col-xs-12 col-sm-9 jumbotron-shift">
                        
                        <h2 class="sub-header">Užívané léky</h2>
                        <div class="table-responsive">
                          <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Název</th>
                                    <th>Kontraindikace</th>
                                    <th>Začátek užívání</th>
                                    <th>Konec užívání</th>
                                    <th>Dávkování</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="drugsInfo" items="${patient.drugs}" varStatus="status">
                                    <tr>
                                        <td>${patient.drugs[status.index].name}</td>
                                        <td>${patient.drugs[status.index].contraindication}</td>
                                        <td>${patient.drugs[status.index].startUsage}</td>
                                        <td>${patient.drugs[status.index].endUsage}</td>
                                        <td>${patient.drugs[status.index].dosage}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                          </table>
                        </div>
                        
                        <h2 class="sub-header">Provedená vyšetření</h2>
                        <div class="table-responsive">
                          <table class="table table-striped">
                            <thead>
                              <tr>
                                <th>Popis</th>
                                <th>Čas vyšetření</th>
                                <th>Ošetřující lékař</th>
                                <th>Čas výsledků</th>
                                <th>Výsledky</th>
                              </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="examInfo" items="${patient.exams}" varStatus="status">
                                    <tr>
                                        <td>${patient.exams[status.index].description}</td>
                                        <td>${patient.exams[status.index].examTime}</td>
                                        <td>${patient.exams[status.index].doctorName} ${patient.exams[status.index].doctorSurname}</td>
                                        <td>${patient.exams[status.index].resultDate}</td>
                                        <td>${patient.exams[status.index].result}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                          </table>
                        </div>
                        
                        <h2 class="sub-header">Hospitalizace</h2>
                        <div class="table-responsive">
                          <table class="table table-striped">
                            <thead>
                              <tr>
                                <th>Den hospitalizace</th>
                                <th>Den propuštění</th>
                                <th>Oddělení</th>
                                <th>Ošetřující lékař</th>
                              </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="hospInfo" items="${patient.hospitalization}" varStatus="status">
                                    <tr>
                                        <td>${patient.hospitalization[status.index].hospitalized}</td>
                                        <td>${patient.hospitalization[status.index].released}</td>
                                        <td>${patient.hospitalization[status.index].departmentName}</td>
                                        <td>${patient.hospitalization[status.index].doctorName} 
                                            ${patient.exams[status.index].doctorSurname}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                          </table>
                        </div>
                        
                    </div>
                    
                    <div class="row row-offcanvas row-offcanvas-left">
                        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
                            <div class="list-group">
                                <p class="list-group-item active"><strong>Rodné číslo:&nbsp;</strong>${patient.birthNum}</p>
                                <p class="list-group-item active"><strong>Adresa:&nbsp;</strong>${patient.address}</p>
                                <p class="list-group-item active"><strong>Město:&nbsp;</strong>${patient.city}</p>
                                <a href="#" class="list-group-item">Aktualizovat údaje</a>
                                <a href="#" class="list-group-item">Přiřadit léky</a>
                                <a href="#" class="list-group-item">Zadat vyšetření</a>
                                <a href="#" class="list-group-item">Zadat výsledky vyšetření</a>
                                <a href="#" class="list-group-item">Hospitalizovat</a>
                            </div>
                        </div><!--/.sidebar-offcanvas-->                          
                    </div>
                                
                </div>     
            </div>
        </div>
    </div>
</m:Base>
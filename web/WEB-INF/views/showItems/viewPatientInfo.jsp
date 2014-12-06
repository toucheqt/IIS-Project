<%-- 
    Document   : showDepartment
    Created on : 20.11.2014, 11:29:30
    Author     : Touche
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="m"%>

<m:Base title="Nemocnice - Pacient ${patient.name} ${patient.surname}">
    <m:UserBase/>
    <br>
    <div class="container" style="margin-top: -0.1%;">
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
                                <a href="#" class="list-group-item" data-toggle="modal" data-target="#update">Aktualizovat údaje</a>
                                <a href="#" class="list-group-item" data-toggle="modal" data-target="#prescriptions">Přiřadit léky</a>
                                <a href="#" class="list-group-item" data-toggle="modal" data-target="#examination">Zadat vyšetření</a>
                                <a href="#" class="list-group-item" data-toggle="modal" data-target="#result">Zadat výsledky vyšetření</a>
                                <a href="#" class="list-group-item" data-toggle="modal" data-target="#hospitalization">Hospitalizovat</a>
                                <a href="#" class="list-group-item" data-toggle="modal" data-target="#delete">Odstranit pacienta</a>
                            </div>
                        </div><!--/.sidebar-offcanvas-->                          
                    </div>
                                
                </div>     
            </div>
        </div>
    </div>
    
    <!-- Update patient modal window -->    
    <div class="modal fade bs-example-modal-sm" id="update" tabindex="-1" role="dialog" aria-labelledby="modalUpdate" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Aktualizace údajů</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form" method="POST" action="<c:url value="/updatePatient"/>">
                        <div class="form-group">
                            <label for="inputName" class="control-label change-form-label">Jméno</label>
                            <div class="col-sm-10 doc-form">
                                <input type="text" class="form-control change-form" value="${patient.name}" name="inputName"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputSurname" class="control-label change-form-label">Příjmení</label>
                            <div class="col-sm-10 doc-form">
                                <input type="text" class="form-control change-form" value="${patient.surname}" name="inputSurname"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputBirthNum" class="control-label change-form-label">Rodné číslo</label>
                            <div class="col-sm-10 doc-form">
                                <input type="text" class="form-control change-form" value="${patient.birthNum}" name="inputBirthNum"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputAddr" class="control-label change-form-label">Adresa</label>
                            <div class="col-sm-10 doc-form">
                                <input type="text" class="form-control change-form" value="${patient.address}" name="inputAddr"/>
                            </div>
                        </div>               
                        <div class="form-group">
                            <label for="inputCity" class="control-label change-form-label">Město</label>
                            <div class="col-sm-10 doc-form">
                                <input type="text" class="form-control change-form" value="${patient.city}" name="inputCity"/>
                            </div>
                        </div>               
                        <div class="modal-footer">
                            <input type="hidden" value="${patient.id}" name="defaultId"/>
                            <input type="hidden" value="${patient.id}" name="patientId"/> <!-- todo ehm 2x rly ?? -->
                            <button type="button" class="btn btn-default" data-dismiss="modal">Storno</button>
                            <input type="submit" value="Potvrdit" class="btn btn-primary" data-dissmiss="modal"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
                            
    <!-- Add drugs to patient modal window -->    
    <div class="modal fade bs-example-modal-sm" id="prescriptions" tabindex="-1" role="dialog" aria-labelledby="modalDrugs" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Přiřadit léky</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form" method="POST" action="<c:url value="/addDrugs"/>">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 drug-select-label">Název</label>
                            <select name="inputName" class="form-control select drug-select">
                                <option disabled selected>Vyberte lék</option>
                                <c:forEach var="drugInfo" items="${drugs}" varStatus="status">
                                    <option>${drugs[status.index]}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="inputStartUsage" class="control-label change-form-label">Užívat od</label>
                            <div class="col-sm-10 doc-form">
                                <input type="text" class="form-control change-form" placeholder="Povinná kolonka (YYYY-MM-DD)" 
                                       name="inputStartUsage"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputStopUsage" class="control-label change-form-label">Užívat do</label>
                            <div class="col-sm-10 doc-form">
                                <input type="text" class="form-control change-form" placeholder="Povinná kolonka (YYYY-MM-DD)" 
                                       name="inputStopUsage"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 drug-select-label">Dávkování</label>
                            <select name="inputDosage" class="form-control select drug-select">
                                <option disabled selected>Vyberte dávkování</option>
                                <option>ráno</option>
                                <option>ráno-poledne</option>
                                <option>ráno-večer</option>
                                <option>ráno-poledne-večer</option>
                                <option>poledne</option>
                                <option>poledne-večer</option>
                                <option>večer</option>
                            </select>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" value="${patient.id}" name="patientId"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Storno</button>
                            <input type="submit" value="Potvrdit" class="btn btn-primary" data-dissmiss="modal"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
                            
    <!-- Add examination to patient modal window -->    
    <div class="modal fade bs-example-modal-sm" id="examination" tabindex="-1" role="dialog" aria-labelledby="modalExam" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Zapsání vyšetření</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form" method="POST" action="<c:url value="/addExamination"/>">
                        <div class="form-group">
                            <label for="description" class="control-label exam-form-label">Popis</label>
                            <div class="col-sm-10 doc-form">
                                <input type="text" class="form-control change-form" placeholder="Povinná kolonka" name="description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="examTime" class="control-label exam-form-label">Čas vyšetření</label>
                            <div class="col-sm-10 doc-form">
                                <input type="text" class="form-control change-form" placeholder="Povinná kolonka" name="examTime"/>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" value="${patient.id}" name="patientId"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Storno</button>
                            <input type="submit" value="Potvrdit" class="btn btn-primary" data-dissmiss="modal"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
                            
    <!-- Add results to patients examination modal window -->    
    <div class="modal fade bs-example-modal-sm" id="result" tabindex="-1" role="dialog" aria-labelledby="modalResult" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Zapsání výsledků vyšetření</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form" method="POST" action="<c:url value="/addResult"/>">
                        <div class="form-group">
                            <label for="resultDate" class="control-label exam-form-label">Doba vystavení</label>
                            <div class="col-sm-10 doc-form">
                                <input type="text" class="form-control change-form" placeholder="Povinná kolonka" name="resultDate"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="resultDsc" class="control-label exam-form-label">Popis</label>
                            <div class="col-sm-10 doc-form">
                                <input type="text" class="form-control change-form" placeholder="Povinná kolonka" name="resultDsc"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="examId" class="col-sm-2 result-select-label">Vyšetření</label>
                            <select name="examId" class="form-control select drug-select">
                                <option disabled selected>Vyberte vyšetření</option>
                                <c:forEach var="examInfo" items="${patient.exams}" varStatus="status">
                                    <option value="${patient.exams[status.index].examId}">
                                        ${patient.exams[status.index].examTime}, 
                                        ${patient.exams[status.index].description}, 
                                        ${patient.exams[status.index].doctorName} 
                                        ${patient.exams[status.index].doctorSurname}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Storno</button>
                            <input type="submit" value="Potvrdit" class="btn btn-primary" data-dissmiss="modal"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
                        
    <!-- Add hospitalization to patient modal window -->    
    <div class="modal fade bs-example-modal-sm" id="hospitalization" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Hospitalizace</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form" method="POST" action="<c:url value="/addHospitalization"/>">
                        <div class="form-group">
                            <label for="enterDate" class="control-label exam-form-label">Čas nástupu</label>
                            <div class="col-sm-10 doc-form">
                                <input type="text" class="form-control change-form" placeholder="Povinná kolonka" name="enterDate"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="releaseDate" class="control-label exam-form-label">Propuštění</label>
                            <div class="col-sm-10 doc-form">
                                <input type="text" class="form-control change-form" name="releaseDate"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="depId" class="col-sm-2 result-select-label">Oddělení</label>
                            <select name="depId" class="form-control select drug-select">
                                <option disabled selected>Vyberte oddělení</option>
                                <c:forEach var="examInfo" items="${department}" varStatus="status">
                                    <option value="${status.index}">${department[status.index]}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" value="${patient.id}" name="patientId"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Storno</button>
                            <input type="submit" value="Potvrdit" class="btn btn-primary" data-dissmiss="modal"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Delete patient modal window -->
    <div class="modal fade bs-example-modal-sm" id="delete" tabindex="-1" role="dialog" aria-labelledby="modalDelete" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Opravdu chcete pacienta odstranit z databáze?</h4>
                </div>
                <div class="modal-footer">
                    <form method="POST" action="<c:url value="/deletePatient"/>">
                        <input type="hidden" value="${patient.id}" name="defaultId"/>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Storno</button>
                        <input type="submit" value="Potvrdit" class="btn btn-primary" data-dissmiss="modal"/>
                    </form>
                </div>
            </div>
        </div>
    </div> 
</m:Base>
    


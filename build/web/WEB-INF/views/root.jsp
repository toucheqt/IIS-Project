<%-- 
    Document   : root
    Created on : 6.11.2014, 13:17:02
    Author     : Ondřej Krpec, xkrpec01@stud.fit.vutbr.cz
               : Jiří Kulda, xkulda00@stud.fit.vutbr.cz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="m"%>

<m:Base title="Nemocnice - správa">
    <m:RootBase/>
    <div class="container">
        <div class="jumbotron">
            <h1>Zpráva dne</h1>
            <p>Upozornění pro veškerý personál! Experimentální lék na ebolu C8H12N4O5 prozatím došel. Dodávky by měli být opět v dostupné v brzké době. Mezitím je třeba přijmout patřičné kroky, aby se nákaza nešířila i mezi personálem.</p>
            <p>Prosím, řiďte se instrukcemi zaslanými v soukromých zprávách.</p>
        
            <button type="button" class="btn btn-lg btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">Více informací &raquo;</button>
            <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title" id="myModalLabel">Postup při ebole</h4>
                        </div>
                        <div class="modal-body">
                            <b>1. </b>Maecenas lorem. Sed ac dolor sit amet purus malesuada congue. Mauris dictum facilisis augue. Pellentesque arcu. Proin mattis lacinia justo. Nulla non lectus sed nisl molestie malesuada. Phasellus rhoncus. Fusce consectetuer risus a nunc.<br>
                            <b>2. </b>Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo. Nullam rhoncus aliquam metus.<br>
                            <b>3. </b>In laoreet, magna id viverra tincidunt, sem odio bibendum justo, vel imperdiet sapien wisi sed libero. Maecenas aliquet accumsan leo. Aliquam in lorem sit amet leo accumsan lacinia.<br>
                            <b>4. </b>Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo. Nullam rhoncus aliquam metus.<br>
                            <b>5. </b>Maecenas aliquet accumsan leo. Aliquam in lorem sit amet leo accumsan lacinia.<br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Zavřít</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- /container -->
</m:Base>

<%-- 
    Document   : home
    Created on : Oct 16, 2015, 10:19:31 AM
    Author     : Suzanne Ludwig
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DVD Library</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Ek+Mukta:400,600' rel='stylesheet' type='text/css'>        <link href="${pageContext.request.contextPath}/css/mainstyles.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>DVD Library</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/search">Search</a>
                    </li>
                </ul>

            </div>

            <div class="row">
                <div class="col-md-8" id="contentDiv">

                </div>
                <div class="col-md-4">
                    <h2>Add A DVD</h2>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="add-title" class="col-md-4 control-label">
                                Title:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       id="add-title" placeholder="Title"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-releaseDate" class="col-md-4 control-label">
                                Release Date:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       id="add-releaseDate" placeholder="Release Date"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-mpaaRating" class="col-md-4 control-label">
                                MPAA Rating:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       id="add-mpaaRating" placeholder="MPAA Rating"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-director" class="col-md-4 control-label">
                                Director:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       id="add-director" placeholder="Director"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-studio" class="col-md-4 control-label">
                                Studio:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       id="add-studio" placeholder="Studio"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-note" class="col-md-4 control-label">
                                Note:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       id="add-note" placeholder="Note"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-imgSrc" class="col-md-4 control-label">
                                Image Source:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       id="add-imgSrc" placeholder="Image Source"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit" id="add-button" class="btn btn-default">
                                    Add DVD
                                </button>
                            </div>
                        </div>
                    </form>
                    <div id="validationErrors" style="color:red;"></div>

                </div>
            </div>
                    <div class="row">
                        <footer>
                            <hr>
                            <p>&copy; 2015 - Suzanne Ludwig</p>
                        </footer>
                    </div>

        </div>

        <div class="modal fade" id="detailsModal" tabIndex="-1" role="dialog"
             aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="detailsModalLabel">
                            DVD Details
                        </h4>
                    </div>

                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-4">
                                <h3 id="dvd-id"></h3>
                                <img id="modal-image" />
                            </div>
                            <div class="col-md-8">
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label for="edit-title" class="col-md-4 control-label">
                                            Title:
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" disabled="true"
                                                   id="edit-title" placeholder="Title"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit-releaseDate" class="col-md-4 control-label">
                                            Release Date:
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" disabled="true"
                                                   id="edit-releaseDate" placeholder="Release Date"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit-mpaaRating" class="col-md-4 control-label">
                                            MPAA Rating:
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" disabled="true"
                                                   id="edit-mpaaRating" placeholder="MPAA Rating"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit-director" class="col-md-4 control-label">
                                            Director:
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" disabled="true"
                                                   id="edit-director" placeholder="Director"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit-studio" class="col-md-4 control-label">
                                            Studio:
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" disabled="true"
                                                   id="edit-studio" placeholder="Studio"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit-note" class="col-md-4 control-label">
                                            Note:
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" disabled="true"
                                                   id="edit-note" placeholder="Note"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit-imgSrc" class="col-md-4 control-label">
                                            Image Source:
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" disabled="true"
                                                   id="edit-imgSrc" placeholder="Image Source"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-md-offset-4 col-md-8">
                                            <button type="button" id="edit-button"
                                                    class="btn btn-default">Edit</button>
                                            <button type="submit" id="save-button" 
                                                    class="btn btn-default">
                                                Save
                                            </button>
                                            <button type="button" id="delete-button"
                                                    class="btn btn-default"
                                                    data-dismiss="modal">
                                                Delete
                                            </button>
                                            <input type="hidden" id="edit-dvd-id">
                                        </div>
                                    </div>
                                </form>
                                <div id="editValidationErrors" style="color:red;"></div>

                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="close-button"
                                class="btn btn-default" >
                            Close
                        </button>
                    </div>
                </div>
            </div>
        </div>             

        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dvds.js"></script>
    </body>
</html>

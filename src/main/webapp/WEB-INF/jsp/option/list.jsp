<%@ include file="/webresources/header.jspf"%>
    <h1>Option list</h1>
    <table class="table table-striped table-dark">
        <thead>
            <tr>
                <th>Option</th>
                <c:if test="${role.contains('ADMIN') || role.contains('MODER')}">
                    <th width="101"></th>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach var = "option" items="${optionList}">
                <tr>
                    <td>${option.text}</td>
                    <c:if test="${role.contains('ADMIN') || role.contains('MODER')}">
                        <td>
                            <a href="${pageContext.request.contextPath}/option/update/${option.id}" class="btn btn-warning btn-sm" title="Edit"><i class="fa fa-pencil-square fa-fw"></i></a>
                            <a data-toggle="modal" data-target="#confirm_delete_${option.id}" href="#" class="btn btn-danger btn-sm" title="Delete"><i class="fa fa-trash fa-fw"></i></a>
                            <div class="modal" id="confirm_delete_${option.id}" tabindex="-1" role="dialog">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title">Confirmation</h4>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
<%--                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
                                        </div>
                                        <div class="modal-body">
                                            <p>Are you sure to delete option?</p>
                                        </div>
                                        <div class="modal-footer">
<%--                                            <button type="button" class="btn btn-primary">Save changes</button>--%>
<%--                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>--%>
                                            <form action="${pageContext.request.contextPath}/option/remove/${option.id}" method="DELETE">
                                                <a href="#" class="btn" data-dismiss="modal">Cancel</a><button type="submit" class="btn btn-primary">Confirm</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${role.contains('ADMIN') || role.contains('MODER')}">
        <a href="${pageContext.request.contextPath}/option/create" class="btn btn-success"><i class="fa fa-plus fa-fw"></i>Add</a>
    </c:if>
<%@ include file="/webresources/footer.jspf"%>

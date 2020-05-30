<%@ include file="/webresources/header.jspf"%>
    <h1>Accounts</h1>
    <table class="table table-striped table-dark">
        <thead>
            <tr>
                <th>Login</th>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Phone</th>
                <th>Rating</th>
                <th>Status</th>
                <th>Role</th>
                <th width="100"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var = "account" items="${accountList}">
                <tr>
                    <td>${account.login}</td>
                    <td>${account.firstname}</td>
                    <td>${account.lastname}</td>
                    <td>${account.phone}</td>
                    <td>${account.rating}</td>
                    <td>${account.statusUser}</td>
                    <td>${account.role}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/account/${account.id}" class="btn btn-primary btn-sm"><i class="fa fa-info-circle fa-fw"></i></a>
                        <a href="${pageContext.request.contextPath}/account/update/${account.id}" class="btn btn-warning btn-sm"><i class="fa fa-pencil-square fa-fw"></i></a>
<%--                        <a data-toggle="modal" data-target="#confirm_delete_${account.id}" href="#" class="btn btn-danger btn-sm"><i class="fa fa-trash fa-fw"></i></a>--%>
<%--                        <div class="modal fade" id="confirm_delete_${account.id}" tabindex="-1" role="dialog" aria-hidden="true">--%>
<%--                            <div class="modal-dialog">--%>
<%--                                <div class="modal-content">--%>
<%--                                    <div class="modal-header">--%>
<%--                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
<%--                                        <h4 class="modal-title">Confirmation</h4>--%>
<%--                                    </div>--%>
<%--                                    <div class="modal-body">--%>
<%--                                        <p>Are you sure to delete Account?</p>--%>
<%--                                    </div>--%>
<%--                                    <div class="modal-footer">--%>
<%--                                        <form action="${pageContext.request.contextPath}/account/remove/${account.id}" method="DELETE">--%>
<%--                                            <a href="#" class="btn" data-dismiss="modal">Cancel</a><button type="submit" class="btn btn-primary">Confirm</button>--%>
<%--                                        </form>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<%@ include file="/webresources/footer.jspf"%>

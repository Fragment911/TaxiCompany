<%@ include file="/webresources/header.jspf"%>
    <h1>Option</h1>
    <table class="table table-striped table-bordered">
        <tbody>
            <tr>
                <td>Id</td>
                <td>${option.id}</td>
            </tr>
            <tr>
                <td>Text</td>
                <td>${option.text}</td>
            </tr>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/option/update/${option.id}" class="btn btn-warning"><i class="fa fa-pencil-square fa-fw"></i>Update</a>
    <a href="${pageContext.request.contextPath}/option" class="btn btn-danger"><i class="fa fa-times fa-fw"></i>Back</a>
<%@ include file="/webresources/footer.jspf"%>

<%@ include file="/webresources/header.jspf"%>
    <h1>Update account ${account.firstname} ${account.lastname}</h1>
    <form role="form" action="${contextPath}/account/update" method="POST">
        <div class="form-group">
            <label for="id">Id</label>
            <input class="form-control" type="number" name="id" readonly value="${account.id}" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="login">Login</label>
            <input class="form-control" type="text" name="login" readonly value="${account.login}" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input class="form-control" type="text" name="password" readonly value="${account.password}" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="firstname">Firstname</label>
            <input class="form-control" type="text" name="firstname" readonly value="${account.firstname}" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="lastname">Lastname</label>
            <input class="form-control" type="text" name="lastname" readonly value="${account.lastname}" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="phone">Phone</label>
            <input class="form-control" type="text" name="phone" readonly value="${account.phone}" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="rating">Rating</label>
            <input class="form-control" type="number" name="rating" readonly value="${account.rating}" autocomplete="off" />
        </div>
        <div class="form-group">
            <label for="role">Example select</label>
            <select path="role" name="role" class="form-control" id="role">
                <c:forEach var="role" items="${roleList}">
                    <c:if test="${not (role.name().equals(account.role))}">
                        <option>${role.name()}</option>
                    </c:if>
                    <c:if test="${role.name().equals(account.role)}">
                        <option selected>${role.name()}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="statusUser">Example select</label>
            <select path="statusUser" name="statusUser" class="form-control" id="statusUser">
                <c:forEach var="statusUser" items="${statusList}">
                    <c:if test="${not (statusUser.name().equals(account.statusUser))}">
                        <option>${statusUser.name()}</option>
                    </c:if>
                    <c:if test="${statusUser.name().equals(account.statusUser)}">
                        <option selected>${statusUser.name()}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-success"><i class="fa fa-check fa-fw"></i>Save</button>
        <a href="${pageContext.request.contextPath}/account" class="btn btn-danger"><i class="fa fa-times fa-fw"></i>Cancel</a>
    </form>
<%@ include file="/webresources/footer.jspf"%>
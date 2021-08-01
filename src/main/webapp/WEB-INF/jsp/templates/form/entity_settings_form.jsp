<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${CSS_SETTINGS_FORM}">
<form class="form-horizontal" role="form" method="post">
    <fieldset>
        <legend>${TEXT[USER_LABEL_TEXT]} ${entity.id} </legend>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="entity-id">${TEXT[ID_TEXT]}: </label>
            <div class="col-sm-9">
                <label type="text" class="form-control" name="entity-id" id="entity-id"> ${entity.id} </label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="entity-email">${TEXT[EMAIL_TEXT]}: </label>
            <div class="col-sm-9">
                <label type="text" class="form-control" name="entity-email"
                       id="entity-email">${entity.email} </label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="entity-name">${TEXT[NAME_TEXT]}: </label>
            <div class="col-sm-9">
                <label type="text" class="form-control" name="entity-name" id="entity-name">${entity.name}</label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="entity-surname">${TEXT[SURNAME_TEXT]}: </label>
            <div class="col-sm-9">
                <label type="text" class="form-control" name="entity-surname"
                       id="entity-surname">${entity.surname}</label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="entity-balance">${TEXT[BALANCE_TEXT]}: </label>
            <div class="col-sm-9">
                <label type="text" class="form-control" name="entity-balance"
                       id="entity-balance">${entity.balance}</label>
            </div>
        </div>
        <form class="all_drops" method="post">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button"
                        id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                        aria-expanded="false" value="${entity.userRole}">
                    ${TEXT[USER_ROLE_LABEL_TEXT]}
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">

                    <li>
                        <form method="post">
                            <button type="submit" class="dropdown-item"
                                    formaction="${CONTROLLER_PATH}?${COMMAND}=${CHANGE_ENTITY_INFO}&${ENTITY_ID}=${entity.id}&${USER_ROLE_TO_CHANGE}=${admin}">
                                ${TEXT[ADMIN_USER_LABEL_TEXT]}</button>
                        </form>
                    </li>


                    <li>
                        <form method="post">
                            <button type="submit" class="dropdown-item"
                                    formaction="${CONTROLLER_PATH}?${COMMAND}=${CHANGE_ENTITY_INFO}&${ENTITY_ID}=${entity.id}&${USER_ROLE_TO_CHANGE}=${employee}">
                                ${TEXT[EMPLOYEE_USER_LABEL_TEXT]}</button>
                        </form>
                    </li>


                    <li>
                        <form method="post">
                            <button type="submit" class="dropdown-item"
                                    formaction="${CONTROLLER_PATH}?${COMMAND}=${CHANGE_ENTITY_INFO}&${ENTITY_ID}=${entity.id}&${USER_ROLE_TO_CHANGE}=${user}">
                                ${TEXT[USER_USER_LABEL_TEXT]}</button>
                        </form>
                    </li>

                </div>
            </div>
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button"
                        id="dropdownMenuButton2" data-toggle="dropdown" aria-haspopup="true"
                        aria-expanded="false" value="${entity.userStatus}">
                    ${TEXT[USER_STATUS_LABEL_TEXT]}
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">

                    <li>
                        <form method="post">
                            <button type="submit" class="dropdown-item"
                                    formaction="${CONTROLLER_PATH}?${COMMAND}=${CHANGE_ENTITY_INFO}&${ENTITY_ID}=${entity.id}&${USER_STATUS_TO_CHANGE}=${active}">
                                ${TEXT[ACTIVE_USER_LABEL_TEXT]}</button>
                        </form>
                    </li>


                    <li>
                        <form method="post">
                            <button type="submit" class="dropdown-item"
                                    formaction="${CONTROLLER_PATH}?${COMMAND}=${CHANGE_ENTITY_INFO}&${ENTITY_ID}=${entity.id}&${USER_STATUS_TO_CHANGE}=${blocked}">
                                ${TEXT[BANNED_USER_LABEL_TEXT]}</button>
                        </form>
                    </li>

                </div>
            </div>
            <button type="submit"
                    formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_HOME_USER_PAGE}">${TEXT[PAGE_HOME]}</button>
        </form>
    </fieldset>

</form>

</div>

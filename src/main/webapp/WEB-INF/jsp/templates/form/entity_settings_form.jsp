<link rel="stylesheet" href="${CSS_SETTINGS_FORM}">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<div class="container light-style flex-grow-1 container-p-y">
    <h4 class="font-weight-bold py-3 mb-4">
        ${TEXT[ACCOUNT_SETTINGS_TEXT]}
    </h4>
    <form class="text-right mt-3" method="post">
        <div class="card overflow-hidden">
            <div class="row no-gutters row-bordered row-border-light">
                <div class="col-md-3 pt-0">
                    <div class="list-group list-group-flush account-settings-links">
                        <a class="list-group-item list-group-item-action active" data-toggle="list"
                           href="#account-general">${TEXT[GENERAL_SETTINGS_FIELD_TEXT]}</a>
                    </div>
                </div>

                <div class="col-md-9">
                    <div class="tab-content">
                        <div class="tab-pane fade active show" id="account-general">
                            <div class="card-body">
                                <div class="form-group">
                                    <label class="form-label">${TEXT[EMAIL_TEXT]}</label>
                                    <label type="text" class="form-control mb-1">${entity.email}</label>
                                </div>
                                <div class="form-group">
                                    <label class="form-label">${TEXT[NAME_TEXT]}</label>
                                    <label type="text" class="form-control mb-1">${entity.name}</label>
                                </div>
                                <div class="form-group">
                                    <label class="form-label">${TEXT[SURNAME_TEXT]}</label>
                                    <label type="text" class="form-control mb-1">${entity.surname}</label>
                                </div>
                                <div class="dropdown">
                                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" value="${entity.userRole}">
                                        ${TEXT[USER_ROLE_LABEL_TEXT]}
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <a class="dropdown-item" href="${CONTROLLER_PATH}?${COMMAND}=${CHANGE_ENTITY_INFO}&${USER_TO_CHANGE}=${entity.id}&${USER_ROLE_TO_CHANGE}=${admin}">
                                            ${TEXT[ADMIN_USER_LABEL_TEXT]}</a>
                                        <a class="dropdown-item" href="${CONTROLLER_PATH}?${COMMAND}=${CHANGE_ENTITY_INFO}&${USER_TO_CHANGE}=${entity.id}&${USER_ROLE_TO_CHANGE}=${employee}">
                                            ${TEXT[EMPLOYEE_USER_LABEL_TEXT]}</a>
                                        <a class="dropdown-item" href="${CONTROLLER_PATH}?${COMMAND}=${CHANGE_ENTITY_INFO}&${USER_TO_CHANGE}=${entity.id}&${USER_ROLE_TO_CHANGE}=${user}">
                                            ${TEXT[USER_USER_LABEL_TEXT]}</a>
                                    </div>
                                </div>
                                <div class="dropdown">
                                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" value="${entity.userStatus}">
                                        ${TEXT[USER_STATUS_LABEL_TEXT]}
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <a class="dropdown-item" href="${CONTROLLER_PATH}?${COMMAND}=${CHANGE_ENTITY_INFO}&${USER_TO_CHANGE}=${entity.id}&${USER_STATUS_TO_CHANGE}=${active}">
                                            ${TEXT[ACTIVE_USER_LABEL_TEXT]}</a>
                                        <a class="dropdown-item" href="${CONTROLLER_PATH}?${COMMAND}=${CHANGE_ENTITY_INFO}&${USER_TO_CHANGE}=${entity.id}&${USER_STATUS_TO_CHANGE}=${blocked}">
                                            ${TEXT[BANNED_USER_LABEL_TEXT]}</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
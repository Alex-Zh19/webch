<link rel="stylesheet" href="${CSS_SETTINGS_FORM}">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<div class="container light-style flex-grow-1 container-p-y">
    <h4 class="font-weight-bold py-3 mb-4">
        ${TEXT[PRODUCT_SETTINGS_LABEL_TEXT]}
    </h4>
    <form class="text-right mt-3" method="post">
        <div class="card overflow-hidden">
            <div class="row no-gutters row-bordered row-border-light">
                <div class="col-md-3 pt-0">
                    <div class="list-group list-group-flush account-settings-links">
                        <a class="list-group-item list-group-item-action active" data-toggle="list"
                           href="#account-general">${TEXT[PRODUCT_GENERAL_SETTINGS_TEXT]}</a>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="tab-content">
                        <div class="tab-pane fade active show" id="account-general">
                            <div class="card-body">
                                <div class="form-group">
                                    <label class="form-label">${TEXT[USER_ROLE_LABEL_TEXT]}</label>
                                    <input type="text" name="${USER_ROLE_TO_CHANGE}" class="form-control mb-1" value="${entity.role}">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">${TEXT[USER_STATUS_LABEL_TEXT]}</label>
                                    <input type="text" name="${USER_STATUS_TO_CHANGE}" class="form-control" value="${entity.status}">
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${CHANGE_ENTITY_INFO}&${ENTITY_ID}=${entity.id}"
                class="btn btn-primary">
            ${TEXT[SAVE_CHANGES_TEXT]}</button>
        <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_HOME_ADMIN_PAGE}" class="btn btn-default">
            ${TEXT[CANCEL_TEXT]}</button>
    </form>
</div>
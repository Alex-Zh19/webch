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
                    <a class="list-group-item list-group-item-action" data-toggle="list"
                       href="#account-change-password">${TEXT[PASSWORD_SETTINGS_FIELD_TEXT]}</a>
                </div>
            </div>
            <div class="col-md-9">
                <div class="tab-content">
                    <div class="tab-pane fade active show" id="account-general">
                        <div class="card-body">
                            <div class="form-group">
                                <label class="form-label">${TEXT[NAME_TEXT]}</label>
                                <input type="text" name="${NAME}" class="form-control mb-1" value="${sessionScope.CURRENT_USER.name}">
                            </div>
                            <div class="form-group">
                                <label class="form-label">${TEXT[SURNAME_TEXT]}</label>
                                <input type="text" name="${SURNAME}" class="form-control" value="${sessionScope.CURRENT_USER.surname}">
                            </div>
                            <div class="form-group">
                                <label class="form-label">${TEXT[EMAIL_TEXT]}</label>
                                <input type="text" name="${EMAIL}" class="form-control mb-1" value="${sessionScope.CURRENT_USER.email}">
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="account-change-password">
                        <div class="card-body pb-2">
                            <div class="form-group">
                                <label class="form-label">${TEXT[CURRENT_PASSWORD_TEXT]}</label>
                                <input type="password" id="current password" name="${CURRENT_PASSWORD}" class="form-control">
                            </div>

                            <div class="form-group">
                                <label class="form-label">${TEXT[NEW_PASSWORD_TEXT]}</label>
                                <input type="password" id="new password" name="${NEW_PASSWORD}" class="form-control">
                            </div>

                            <div class="form-group">
                                <label class="form-label">${TEXT[CONFIRM_NEW_PASSWORD_TEXT]}</label>
                                <input type="password" id="confirm new password" name="${CONFIRM_NEW_PASSWORD}"
                                       class="form-control">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </div>
        <button type="submit" formaction="controller?command=change-user-info" class="btn btn-primary">
            ${TEXT[SAVE_CHANGES_TEXT]}</button>
        <button type="submit" formaction="controller?command=open-home-user-page" class="btn btn-default">
            ${TEXT[CANCEL_TEXT]}</button>
    </form>
</div>
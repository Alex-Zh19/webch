<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<div class="container">
    <form class="form-horizontal" role="form" method="post">
        <fieldset>
            <legend>${TEXT[USER_LABEL_TEXT]}</legend>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="entity-id">${TEXT[ID_TEXT]}: </label>
                <div class="col-sm-9">
                    <label type="text" class="form-control" name="entity-id" id="entity-id"> ${entity.id} </label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="entity-email">${TEXT[EMAIL_TEXT]}: </label>
                <div class="col-sm-9">
                    <label type="text" class="form-control" name="entity-email" id="entity-email">${entity.emaild} </label>
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
                    <label type="text" class="form-control" name="entity-surname" id="entity-surname">${entity.surname}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="entity-balance">${TEXT[BALANCE_TEXT]}: </label>
                <div class="col-sm-9">
                    <label type="text" class="form-control" name="entity-balance" id="entity-balance">${entity.balance}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="entity-role">${TEXT[ROLE_TEXT]}: </label>
                <div class="col-sm-9">
                    <label type="text" class="form-control" name="entity-role" id="entity-role">${entity.role}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="entity-status">${TEXT[STATUS_TEXT]}: </label>
                <div class="col-sm-9">
                    <label type="text" class="form-control" name="entity-status" id="entity-status">${entity.status}</label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9">
                    <button type="submit" class="btn btn-success"
                            formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_CHANGE_ENTITY_INFO_PAGE}&${ENTITY_ID}=${entity.id}"
                            class="change-user-button">${TEXT[CHANGE_USER_BUTTON_TEXT]}</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
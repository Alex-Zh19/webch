<link rel="stylesheet" href="${CSS_SIGN_UP_FORM}">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script var pass=""></script>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <form action="${CONTROLLER_PATH}?${COMMAND}=${SIGN_UP_USER}" method="post" id="form-sign-up">
            <input type="text" id="email" class="fadeIn first" name="${EMAIL}" placeholder="${TEXT[EMAIL_TEXT]}" required
                   pattern="^\w{2,}[@]\w{2,8}[\.]?(\w{2,6})?$">
            <input type="password" id="password" class="fadeIn second" name="${PASSWORD}"
                   placeholder="${TEXT[PASSWORD_TEXT]}" required>
            <input type="password" id="confirm password" class="fadeIn third" name="${CONFIRM_PASSWORD}"
                   placeholder="${TEXT[CONFIRM_PASSWORD_TEXT]}" required>
            <input type="text" id="name" class="fadeIn fourth" name="${NAME}" placeholder="${TEXT[NAME_TEXT]}" required
            pattern="^\w{2,28}$">
            <input type="text" id="surname" class="fadeIn fifth" name="${SURNAME}" placeholder="${TEXT[SURNAME_TEXT]}"
            pattern="^\w{2,28}$">
            <input type="submit" id="sign-up-button" class="fadeIn six" value="${TEXT[SIGN_UP_BUTTON]}">
        </form>

    </div>
</div>
<script src="js/password_validation.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<link rel="stylesheet" href="${CSS_SIGN_IN_FORM}">
<div class="wrapper fadeInDown">
    <div id="formContent">
        <form action="controller?command=sign-in-user" method="post" id="form-sign-in" >
            <input type="text" id="login" class="fadeIn first" name="${EMAIL}" placeholder="${TEXT[EMAIL_TEXT]}" required
                   pattern="^\w{2,}[@]\w{2,8}[\.]?(\w{2,6})?$">
            <input type="password" id="password" class="fadeIn second" name="${PASSWORD}" placeholder="${TEXT[PASSWORD_TEXT]}" required>
            <input type="submit" class="fadeIn third" value="${TEXT[SIGN_IN_BUTTON]}">
        </form>

        <div id="formFooter">
            <a class="underlineHover" href="#">${TEXT[FORGOT_PASSWORD]}</a>
        </div>

    </div>
</div>

compare(password, passwordConfirm)
{
    if (password == passwordConfirm) {
        password.style.borderColor = "green";
        passwordConfirm.style.borderColor = "green";
        passwordConfirm.setCustomValidity("password and confirm password arent equal")
    } else {
        password.style.borderColor = "red";
        passwordConfirm.style.borderColor = "red";
    }
}






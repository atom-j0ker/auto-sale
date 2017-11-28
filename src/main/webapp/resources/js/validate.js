function validateUsername() {
    var username = document.forms["registration-form"]["username"].value;

    if (username == null || username == "") {
        document.getElementById("invalid-username").innerHTML = "username is required";
        return false;
    } else if (/^[a-zA-Z1-9]+$/.test(username) === false) {
        document.getElementById("invalid-username").innerHTML = "username must contain latin letters only";
        return false;
    } else if (username.length < 3 || username.length > 20) {
        document.getElementById("invalid-username").innerHTML = "username must contain 3-20 symbols";
        return false;
    } else if (parseInt(username.substr(0, 1))) {
        document.getElementById("invalid-username").innerHTML = "first symbol must be a letter";
        return false;
    } else {
        document.getElementById("invalid-username").innerHTML = "";
        return true;
    }
}

function validatePassword() {
    var password = document.forms["registration-form"]["password"].value;

    if (password == null || password == "") {
        document.getElementById("invalid-password").innerHTML = "password is required";
        return false;
    } else if (/^[a-zA-Z1-9]+$/.test(password) === false) {
        document.getElementById("invalid-password").innerHTML = "password must contain latin letters only";
        return false;
    } else if (password.length < 6 || password.length > 50) {
        document.getElementById("invalid-password").innerHTML = "username must contain 6-50 symbols";
        return false;
    } else if (/^[A-Z]+$/.test(password.substr(0, 1)) === false) {
        document.getElementById("invalid-password").innerHTML = "first letter must be in upper case";
        return false;
    } else {
        document.getElementById("invalid-password").innerHTML = "";
        return true;
    }
}

function validateEmail() {
    var email = document.forms["registration-form"]["email"].value;

    if (email == null || email == "") {
        document.getElementById("invalid-email").innerHTML = "email is required";
        return false;
    } else if (email.length < 5 || email.length > 63 || email.indexOf("@") < 0 || email.indexOf(".") < 0) {
        document.getElementById("invalid-email").innerHTML = "invalid email";
        return false;
    } else {
        document.getElementById("invalid-email").innerHTML = "";
        return true;
    }
}

function validateForm() {
    if (validateUsername())
        return true;
    else
        return false;
}
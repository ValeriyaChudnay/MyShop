function checkPassword(str) {
    var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
    return re.test(str);
}

function checkEmail(str) {
    var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    return re.test(str);
}

function checkForm(form) {
let val=true;
    if (form.email.value == "") {
        alert("Error: Username cannot be blank!");
        form.email.focus();
        val= false;
    }
    if (!checkEmail(form.email.value)) {
        alert("Error: Email must contain only letters, numbers and underscores!");
        form.email.focus();
        val= false;
    }
    if (form.pwd1.value != "" && form.pwd1.value == form.pwd2.value) {
        if (!checkPassword(form.pwd1.value)) {
            alert("The password you have entered is not valid!" +
                " Password must contain at least 1 lowercase 1 uppercase alphabetical character, 1 numeric character and have length more than 6 characters"
            );
            form.pwd1.focus();
             val= false;
        }
    } else {
        alert("Error: Please check that you've entered and confirmed your password!");
        form.pwd1.focus();
        val= false;
    }
    return val;
}
$(document).ready(function () {
    $('#submit').on('click', function () {
       let valid = true;
        if ( $('#email').length < 1) {
            alert("Enter email");
            valid = false;
        }
        if (valid) {
            var regEx = /^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/;
            valid = regEx.test($('#email').val());
            console.log(valid);
            if (!valid) {
                alert('Enter a valid email');
            }
        }

        if ($('#password1').val() != "" && $('#password1').val() == $('#password2').val()) {
            var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
            var validPassword = re.test($('#password1').val());
            if (!validPassword) {
                alert("The password you have entered is not valid!" +
                    " Password must contain at least 1 lowercase 1 uppercase alphabetical character, 1 numeric character and have length more than 6 characters"
                );
                valid = false;
            }
        } else {
            alert("Please check that you've entered same password!");
            valid = false;
        }
        if (valid) {
            $('#submit').submit;
            alert("All information Right:)");
        }
        return valid;
    });
});
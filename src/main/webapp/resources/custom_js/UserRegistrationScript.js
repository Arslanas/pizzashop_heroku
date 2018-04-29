function UserRegistrationInit() {
    maskFields();
    validateForm();
}
function maskFields() {
    $("#phoneNumID").mask('+7 (000) 000-00-00');
}
function validateForm() {
    $("#UserRegistrationFormID").validate({
        rules: {
            username: "required",
            password: "required",
            'contact.email': "required",
            'contact.phoneNum': {required:true, minlength:18},
            'address.streetHome': "required"
        },
        messages: {
            username: "Заполните поле",
            password: "Заполните поле",
            'contact.email': "Заполните поле",
            'contact.phoneNum': {required: "Заполните поле", minlength: "Заполните поле до конца"},
            'address.streetHome': "Заполните поле"
        },
        errorClass: "alert-danger",
        highlight: function (element) {
            $(element).fadeOut(function () {
                $(element).fadeIn();
            });
        }
    })
}

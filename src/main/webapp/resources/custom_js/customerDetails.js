function validateForm() {
    $("#phoneNumID").mask('+7 (000) 000-00-00');
    $("#formID").validate({
            rules: {
                username: "required",
                password: "required",
                'contact.phoneNum': {required: true, minlength: 18},
                'address.streetHome': "required"
            },
            messages: {
                username: "Заполните поле",
                password: "Заполните поле",
                'contact.phoneNum': {required: "Заполните поле", minlength: "Заполните поле до конца"},
                'address.streetHome': "Заполните поле"
            },

            errorClass: "alert-danger",

            highlight: function (element) {
                $(element).fadeOut(function () {
                    $(element).fadeIn();
                });
            }
        }
    )
}
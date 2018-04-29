function AddProductInit() {
    $('#fileInputID').fileselect();
    validateForm();
}
function selectCategory() {
    $('#optionID_Pizza').attr('selected', true);
}
function validateForm() {
    $("#addProductForm").validate({
        rules: {
            name: "required",
            categorizedItems: "required",
            'price.amount': "required"
        },
        messages: {
            name: "Введите название",
            categorizedItems: "Выберите категорию",
            'price.amount': "Укажите цену"
        },
        errorClass: "alert-danger",

        highlight: function (element) {
            $(element).fadeOut(function () {
                $(element).fadeIn();
            });
        }
    })
}
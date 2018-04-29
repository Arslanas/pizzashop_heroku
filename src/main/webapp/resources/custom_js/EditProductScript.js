function EditProductInit() {
    $('#fileInputID').fileselect();
    parseCategory();
    validateForm();
}
function editCategory(name, list) {
//    $('#optionID_Pizza').attr('selected', true);

}
function validateForm() {
    $("#editProductForm").validate({
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
function parseCategory() {
    var categories = [];
    var catItems = [];
    $("[id^='optionID_']").each(function () {
        categories.push($(this).text());
    });
    $("[id^='catItemsNameID_']").each(function () {
        catItems.push($(this).attr('value'));
    });
    catItems.forEach(function (item) {
        categories.forEach(function (category) {
            if (item === category) {
                $('#optionID_' + item).attr('selected', true);
            }
        })
    })
}
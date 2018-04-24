function EditProductInit() {
    $('#fileInputID').fileselect();
    parseCategory();
}
function editCategory(name, list) {
//    $('#optionID_Pizza').attr('selected', true);

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
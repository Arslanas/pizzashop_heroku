function EditProductInit() {
    $('#fileInputID').fileselect();
    parseCategory();
}
function editCategory(name, list) {
//    $('#optionID_Pizza').attr('selected', true);

}
function parseCategory() {
    var categories = [];
    $( "[id^='optionID_']" ).each(function () {
        categories.push($(this).text());
    });
}
function AddProductInit() {
    $('#fileInputID').fileselect();
    selectCategory();
}
function selectCategory() {
    $('#optionID_Pizza').attr('selected', true);
}
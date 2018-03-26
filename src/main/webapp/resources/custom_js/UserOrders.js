function onloadUserOrders() {
    $('#tableID').dataTable({
        "lengthChange":false,
        "info":false,
        "ordering":false,
        "pagingType": "simple",
        "pageLength":3,
        "searching" : false
    });
};
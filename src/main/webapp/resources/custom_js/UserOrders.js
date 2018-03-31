function onloadUserOrders(context) {
    initProductsPage(context);
    $('#tableID').dataTable({
        "lengthChange":false,
        "info":false,
        "ordering":false,
        "pagingType": "simple",
        "pageLength":3,
        "searching" : false
    });
};
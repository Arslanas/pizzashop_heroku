$().ready(function () {
    $("#addProductForm").validate({
        rules:{
            // name:"required",
            // setOfCategorizedItems:"required",
            // description:"required",
            // price:"required",
        },
        messages:{
            name:"Введите название",
            setOfCategorizedItems:"Выберите категорию",
            description:"Опишите продукт",
            price:"Укажите цену"
        },
        errorClass: "alert-danger",

        highlight: function(element) {
            $(element).fadeOut(function() {
                $(element).fadeIn();
            });
        }


    })
})
/*
 $(document).ready(function(){              // по окончанию загрузки страницы
 $('#example-1').click(function(){      // вешаем на клик по элементу с id = example-1
 $(this).load('Ajax_tempHTML.html'); // загрузку HTML кода из файла example.html
 })
 });
 */

// function myFunction(){
//     $("#example-1").load('Ajax_tempHTML.html');
// }
function disableUser(url, username) {
    $.getJSON(url + username, {}, function (user) {
        var button = $('#' + username + '_enabled');
        if (user.enabled == true) {
            button.html("enabled");
        } else {
            button.html("disabled");
        }
    })
}
function findUsers(url, sort, direction) {
    direction == "asc" ? direction = "desc" : direction = "asc";
    $("button[id~='" + sort + "Button']").attr("onclick", "findUsers('" + url + "','" + sort + "','" + direction + "')");

    $.getJSON("" + url + "?page=0&sort=" + sort + "," + direction, {}, function (json) {
        var usersBody = $("#usersBody");
        usersBody.html("");
        for (var i = 0; i < json.length; i++) {
            var user = json[i];
            usersBody.append("<tr> <td>" + user.username + "</td> <td>" + user.contact.email + "</td> <td>" + user.contact.phoneNum + "</td> <td>" + user.password + "</td> <td>" + user.enabled + "</td><td>" + user.date + "</td> </tr>")
        }
    });
}

function addToCart(itemID) {
    $.getJSON("http://localhost:8080/products/add/" + itemID, {}, function (json) {
        $("#itemID-" + itemID).html("");
        $("#itemID-" + itemID).append("This ID is " + json.id);
        $("#cartPrice").html(json.totalPrice);
    })
}
function addToCartFromCart(cartID) {
    $.getJSON("http://localhost:8080/products/addCart/" + cartID, {}, function (json) {
        $("#cartPrice").html(json.totalPrice);
    })
}
function removeFromCart(itemID) {
    $.getJSON("http://localhost:8080/products/shoppingCart/remove/" + itemID, {}, function (json) {
        $("#cartPrice").html(json.totalPrice);
        $("#cartTotalPrice").html(json.totalPrice);
        $("#productRow_" + itemID).remove();
    })
}
function increaseCart(itemID) {
    $.getJSON("http://localhost:8080/products/shoppingCart/increase/" + itemID, {}, function (json) {
        modifyCart(json, itemID);

    })
}
function decreaseCart(itemID) {
    $.getJSON("http://localhost:8080/products/shoppingCart/decrease/" + itemID, {}, function (json) {
        modifyCart(json, itemID);
    })
}
function modifyCart(dataJson, itemID) {
    $("#cartPrice").html(dataJson.totalPrice);
    $("#cartTotalPrice").html(dataJson.totalPrice);
    var quantityTag = $("#productQuantity_" + itemID);
    var priceTag = $("#productPrice_" + itemID);
    var products = dataJson.cart;
    var price;
    var quantity;
    for (var i = 0; i < products.length; i++) {
        var productID = products[i].item.id;
        if (productID == itemID) {
            price = products[i].totalPrice;
            quantity = products[i].quantity;
            quantityTag.html(quantity);
            priceTag.html(price);
            return;
        }
    }
    ;

}

/*
 $(document).ready(function(){                            // по завершению загрузки страницы
 $('#example-4').click(function(){                    // вешаем на клик по элементу с id = example-4
 $.getJSON('ajax/example.json', {}, function(json){  // загрузку JSON данных из файла example.json
 $('#example-4').html('');
 // заполняем DOM элемент данными из JSON объекта
 $('#example-4').append('To: '   + json.note.to + '<br/>')
 .append('From: ' + json.note.from + '<br/>')
 .append('<b>'    + json.note.heading + '</b><br/>')
 .append(           json.note.body + '<br/>');
 });
 })
 });

 */
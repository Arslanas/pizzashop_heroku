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
}
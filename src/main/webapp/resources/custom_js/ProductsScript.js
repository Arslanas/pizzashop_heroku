var gUrlAddItemID;
var gUrlAddCartID;
var gUrlCartRemoveByItemID;
var gUrlCartIncreaseByItemID;
var gUrlCartDecreaseByItemID;

function initProductsPage(context) {
    var contextUrl = "http://localhost:8080"+context;
    gUrlAddItemID = contextUrl + '/products/add/';
    gUrlAddCartID = contextUrl + '/products/addCart/';
    gUrlCartRemoveByItemID = contextUrl + '/products/shoppingCart/remove/';
    gUrlCartIncreaseByItemID = contextUrl + '/products/shoppingCart/increase/';
    gUrlCartDecreaseByItemID = contextUrl + '/products/shoppingCart/decrease/';
}


function addToCart(itemID) {
    $.getJSON(gUrlAddItemID + itemID, {}, function (json) {
        $("#cartPrice").html(json.totalPrice.amount + " " + json.totalPrice.currency);
    })
}
function addToCartFromCart(cartID) {
    $.getJSON(gUrlAddCartID + cartID, {}, function (json) {
        $("#cartPrice").html(json.totalPrice.amount + " " + json.totalPrice.currency);
    })
}
function removeFromCart(itemID) {
    $.getJSON(gUrlCartRemoveByItemID + itemID, {}, function (json) {
        $("#cartPrice").html(json.totalPrice.amount + " " + json.totalPrice.currency);
        $("#cartTotalPrice").html(json.totalPrice.amount + " " + json.totalPrice.currency);
        $("#productRow_" + itemID).remove();
    })
}
function increaseCart(itemID) {
    $.getJSON(gUrlCartIncreaseByItemID + itemID, {}, function (json) {
        modifyCart(json, itemID);

    })
}
function decreaseCart(itemID) {
    $.getJSON(gUrlCartDecreaseByItemID + itemID, {}, function (json) {
        modifyCart(json, itemID);
    })
}
function modifyCart(dataJson, itemID) {
    $("#cartPrice").html(dataJson.totalPrice.amount + " " + dataJson.totalPrice.currency);
    $("#cartTotalPrice").html(dataJson.totalPrice.amount + " " + dataJson.totalPrice.currency);
    var quantityTag = $("#productQuantity_" + itemID);
    var priceTag = $("#productPrice_" + itemID);
    var products = dataJson.cart;
    var price;
    var quantity;
    for (var i = 0; i < products.length; i++) {
        var productID = products[i].item.id;
        if (productID == itemID) {
            price = products[i].totalPrice.amount + " " + products[i].totalPrice.currency;
            quantity = products[i].quantity;
            quantityTag.html(quantity);
            priceTag.html(price);
            return;
        }
    }
}
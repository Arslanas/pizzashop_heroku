var gSort;
var gDirection;
var gPage;
var gPageSize;
var gTotalPages;
var gTotalElements;
var gDisableUserUrl;
var gFindUsersUrl;

function getUsersPage(pageNum, size, sort, direction) {
    $.getJSON("" + gFindUsersUrl + "?page=" + pageNum + "&size=" + size + "&sort=" + sort + "," + direction, {}, function (json) {
        var content = json.content;
        var usersBody = $("#usersBody");
        usersBody.html("");
        for (var i = 0; i < content.length; i++) {
            var user = content[i];
            usersBody.append("<tr> <td>" + user.username + "</td> <td>" + user.contact.email + "</td> <td>" + user.contact.phoneNum + "</td> <td>" + user.password + "</td> <td><button id='" + user.username + "_enabled' onclick=\"disableUser('" + user.username + "')\">" + user.enabled + "</button></td><td>" + user.date + "</td> </tr>")
        }
        updateGData(json);
        enabledButtonInit();
        updatePageState();
    });
}
function findUsers(sort, direction) {
    direction == "asc" ? direction = "desc" : direction = "asc";
    $("button[id~='" + sort + "Button']").attr("onclick", "findUsers('" + sort + "','" + direction + "')");
    getUsersPage(0, gPageSize, sort, direction);
}

function disableUser(username) {
    $.getJSON(gDisableUserUrl + username, {}, function (user) {
        var button = $('#' + username + '_enabled');
        if (user.enabled == true) {
            button.html("enabled");
            button.removeClass("btn-danger").addClass("btn-success");
        } else {
            button.html("disabled");
            button.removeClass("btn-success").addClass("btn-danger");

        }
    })
}
function showBy(id) {
    var value = $("#" + id).text();
    if (value.trim() == "Все"){
        gPageSize = -1;
        $("#showByButtons").text("Показать все");
    }else {
        gPageSize = Number(value);
        $("#showByButtons").text("Показать по "+gPageSize);
    }
    getUsersPage(0, gPageSize, gSort, gDirection);
}
function getFirstUsersPage() {
    getUsersPage(0, gPageSize, gSort, gDirection);
}
function getPrevUsersPage() {
    getUsersPage(gPage - 1, gPageSize, gSort, gDirection);
}
function getNextUsersPage() {
    getUsersPage(gPage + 1, gPageSize, gSort, gDirection);
}
function getLastUsersPage() {
    getUsersPage(gTotalPages - 1, gPageSize, gSort, gDirection);
}
function updatePageState() {
    if (gTotalPages > 1) {
        switch (gPage) {
            case 0 :
                stateStart();
                break;

            case gTotalPages - 1 :
                stateEnd();
                break;

            default:
                stateMiddle();
        }
    } else {
        stateSingle();
    }
}

function onloadUsersManagement(totalPages, totalElements, disableUserUrl, findUsersUrl) {
    gSort = 'username';
    gDirection = 'asc';
    gPage = 0;
    gPageSize = 2;
    gTotalPages = totalPages;
    gTotalElements = totalElements;
    gDisableUserUrl = disableUserUrl;
    gFindUsersUrl = findUsersUrl;
    enabledButtonInit();
    updatePageState();
}
function updateGData(json) {
    gPage = json.number;
    gSort = json.sort[0].property;
    gDirection = json.sort[0].direction;
    gTotalPages = json.totalPages;
    gPageSize = json.size;
    $("#pageNumID").text(gPage + 1);
    $("#pageTotalID").text(gTotalPages);
}
function enabledButtonInit() {
    $("[id$=_enabled]").each(function () {
        if ($(this).text() == 'true') {
            $(this).text("enabled");
            $(this).addClass("btn btn-sm btn-success");
        } else {
            $(this).text("disabled");
            $(this).addClass("btn btn-sm btn-danger");
        }
    });
}

function stateStart() {
    lock("first");
    lock("prev");
    unlock("next");
    unlock("last");
}
function stateMiddle() {
    unlock("first");
    unlock("prev");
    unlock("next");
    unlock("last");
}
function stateEnd() {
    unlock("first");
    unlock("prev");
    lock("next");
    lock("last");
}
function stateSingle() {
    lock("first");
    lock("prev");
    lock("next");
    lock("last");
}
function unlock(id) {
    $("#" + id + "PageID").removeClass('disabled');
}
function lock(id) {
    $("#" + id + "PageID").addClass('disabled');
}
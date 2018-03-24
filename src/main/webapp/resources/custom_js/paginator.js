var gSort;
var gDirection;
var gPage;
var gPageSize;
var gTotalPages;
var gTotalElements;
var gDisableUserUrl;

function getUsersPage(pageNum, size, sort, direction, url) {
    $.getJSON("" + url + "?page=" + pageNum + "&size=" + size + "&sort=" + sort + "," + direction, {}, function (json) {
        var content = json.content;
        var usersBody = $("#usersBody");
        usersBody.html("");
        for (var i = 0; i < content.length; i++) {
            var user = content[i];
            usersBody.append("<tr> <td>" + user.username + "</td> <td>" + user.contact.email + "</td> <td>" + user.contact.phoneNum + "</td> <td>" + user.password + "</td> <td><button id='" + user.username + "_enabled' onclick=\"disableUser('" + gDisableUserUrl + "', '" + user.username + "')\">" + user.enabled + "</button></td><td>" + user.date + "</td> </tr>")
        }
        updateGData(json);
        renameBooleanToEnabled();
        updatePageState();
    });
}
function getUsersPageBySize(size, url) {
    getUsersPage(0, size, gSort, gDirection, url);
}
function getFirstUsersPage(url) {
    getUsersPage(0, gPageSize, gSort, gDirection, url);
}
function getPrevUsersPage(url) {
    getUsersPage(gPage - 1, gPageSize, gSort, gDirection, url);
}
function getNextUsersPage(url) {
    getUsersPage(gPage + 1, gPageSize, gSort, gDirection, url);
}
function getLastUsersPage(url) {
    getUsersPage(gTotalPages - 1, gPageSize, gSort, gDirection, url);
}
function updatePageState() {
    if (gTotalPages > 1) {
        if (gPage == 0) {
            stateStart();
        }
        if (gPage == gTotalPages - 1) {
            stateEnd();
        }
        if (gPage > 0 && gPage < gTotalPages - 1) {
            stateMiddle();
        }
    } else {
        stateSingle();
    }
}

function onloadUsersManagement(totalPages, totalElements, disableUserUrl) {
    gSort = 'username';
    gDirection = 'asc';
    gPage = 0;
    gPageSize = 1;
    gTotalPages = totalPages;
    gTotalElements = totalElements;
    gDisableUserUrl = disableUserUrl;
    renameBooleanToEnabled();
    if (gTotalPages > 1) {
        stateStart();
    } else {
        stateSingle();
    }

}
function updateGData(json) {
    gPage = json.number;
    gSort = json.sort[0].property;
    gDirection = json.sort[0].direction;
    gTotalPages = json.totalPages;
    gPageSize = json.size;
    $("#pageNumID").text(gPage+1);
    $("#pageTotalID").text(gTotalPages);
}
function renameBooleanToEnabled() {
    $("[id$=_enabled]").each(function () {
        if ($(this).text() == 'true') {
            $(this).text("enabled");
        } else {
            $(this).text("disabled");
        }
    });
}

function stateStart() {
    lockFirst();
    lockPrev();
    unlockNext();
    unlockLast();
}
function stateMiddle() {
    unlockFirst();
    unlockPrev();
    unlockNext();
    unlockLast();
}
function stateEnd() {
    unlockFirst();
    unlockPrev();
    lockNext();
    lockLast();
}
function stateSingle() {
    lockFirst();
    lockPrev();
    lockNext();
    lockLast();
}
function unlockPrev() {
    $("#prevPageID").removeClass('disabled');
}
function lockPrev() {
    $("#prevPageID").addClass('disabled');
}
function unlockNext() {
    $("#nextPageID").removeClass('disabled');
}
function lockNext() {
    $("#nextPageID").addClass('disabled');
}
function unlockFirst() {
    $("#firstPageID").removeClass('disabled');
}
function lockFirst() {
    $("#firstPageID").addClass('disabled');
}
function unlockLast() {
    $("#lastPageID").removeClass('disabled');
}
function lockLast() {
    $("#lastPageID").addClass('disabled');
}

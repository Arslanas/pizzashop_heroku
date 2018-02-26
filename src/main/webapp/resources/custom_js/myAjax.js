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
function myAjaxFunction(itemID){
    $.getJSON("http://localhost:8080/products/add/"+itemID,{},function (json) {
        $("#itemID-"+itemID).html("");
        $("#itemID-"+itemID).append("This ID is " +json.id);
        $("#cartPrice").html(json.totalPrice);
    })
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
function addMask(){
    $("#Mobil").mask('+7 (000) 000-00-00');
    $("#formID").validate({
            rules: {
                'contact.phoneNum':{
                    required:true,
                    minlength: 18
                }
            },
            messages:{
                'contact.phoneNum':{
                    minlength: "Пожалуйста, заполните поле до конца"
                }
            }
        }
    )
}
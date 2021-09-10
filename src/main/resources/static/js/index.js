const LINK="http://14.233.18.201:3000/qltv/0/";
$(document).ready(function() {

    var key = document.getElementById('seach')
    key.addEventListener('keydown', (event) => {
        if (event.keyCode == 13) event.preventDefault()
    })
    key.addEventListener('keyup', (event) => {
        if (key.value == '') {
            $.get(LINK+'sachs', function(sachs, status) {
                rendertable(sachs)
            })
        } else {
            var key1 = (key.value).trim();
            $.get(LINK+'sachs/' + key1, function(sachs, status) {
                rendertable(sachs)
            })
        }
    })
    var select = document.getElementById('select')
    $.get(LINK+'theloais', function(genres, status) {
        var option = document.createElement('option')
        option.value = 0
        option.text = 'Tên Thể Loại'
        select.appendChild(option)
        genres.forEach(e => {
            let option = document.createElement('option')
            option.value = e.id
            option.text = e.name
            select.appendChild(option)
        })
    })
    select.addEventListener('change', (event) => {
        event.preventDefault()
        if (select.value == 0) {
            $.get(LINK+'sachs', function(sachs, status) {
                rendertable(sachs)
            })
        }
        $.get(LINK+'sachs/idGenre=/' + select.value, function(sachs, status) {
            rendertable(sachs)
        })
    })
})

function rendertable(sachs) {
    var t = document.getElementById('table')
    t.innerHTML = ''
    var i = 1
    sachs.forEach(e => {
        var tr = document.createElement('tr')
        var td1 = document.createElement('td')
        td1.innerHTML = i++
            var td2 = document.createElement('td')
        td2.innerHTML = e.name
        var td3 = document.createElement('td')
        td3.innerHTML = e.author
        var td4 = document.createElement('td')
        $.get(LINK+'theloais/' + e.idgenre, function(theloai, status) {
            var ty = theloai.name
                //console.log(ty)
            td4.innerHTML = ty
        })
        var td5 = document.createElement('td')
        var check = document.createElement('i')
        td5.appendChild(check)
        if (e.status) {
            check.setAttribute('class', 'far fa-check-circle')
            check.style.color = "green"
        } else {
            check.setAttribute('class', 'fas fa-window-close')
            check.style.color = "red"
        }
        td5.appendChild(check)
        var td6 = document.createElement('td')
        td6.innerHTML = e.sale
        var td7 = document.createElement('td')
        var btndetail = document.createElement('i')
        btndetail.setAttribute('class', 'fas fa-eye')
        btndetail.style.color = 'mediumblue'
        btndetail.setAttribute('data-target', '#exampleModal')
        btndetail.setAttribute('data-toggle', 'modal')
        btndetail.setAttribute('data-whatever', '@mdo')
        td7.appendChild(btndetail)
        tr.appendChild(td1)
        tr.appendChild(td2)
        tr.appendChild(td3)
        tr.appendChild(td4)
        tr.appendChild(td5)
        tr.appendChild(td6)
        tr.appendChild(td7)
        t.appendChild(tr)

        btndetail.addEventListener('click', (event) => {
            $.get(LINK+'sachs/id=/' + e.id, function(sach, status) {
                var i = document.getElementById('message-text')
                i.innerHTML = sach.description
                var i1 = document.getElementById('exampleModalLabel')
                i1.innerHTML = sach.name
            })

        })
    })

    var iconMess = document.getElementById('iconMess')
    iconMess.setAttribute('data-target', '#exampleModal1')
    iconMess.setAttribute('data-toggle', 'modal')
    iconMess.setAttribute('data-whatever', '@mdo')

    var id = document.getElementById('id')
    var pass = document.getElementById('pass')

    var btnsecurity = document.getElementById('btnsecurity')
    var btnsend = document.getElementById('btnsend')
    btnsecurity.addEventListener('click', (event) => {
        event.preventDefault()
        $.get(LINK+'sinhviens/' + id.value + '/' + pass.value, function(check, status) {
            if (check) mess.disabled = false
            else {
                alert('Mã Sinh Viên hoặc mật khẩu không đúng')
                mess.disabled = true
            }
            return false;
        })
    })

    var mess = document.getElementById('mess')
    mess.addEventListener('change', (event) => {
        if (mess.value.length >= 20) btnsend.disabled = false
        else btnsend.disabled = true
    })


    btnsend.addEventListener('click', (event) => {
        event.preventDefault()
        var mess = document.getElementById('mess')
        var m = mess.value
        var mess = { midStudent: id.value, message: m }
        $.ajax({
            url: LINK+'message',
            type: 'POST',
            data: JSON.stringify(mess),
            contentType: "application/json",
            timeout: 6000,
            success: function(data) {
                if (data != null) {
                    var mess = document.getElementById('mess')
                    var id = document.getElementById('id')
                    var pass = document.getElementById('pass')
                    id.value = ''
                    pass.value = ''
                    mess.value = ''
                    mess.disabled = true
                    alert("Gửi Thành Công")
                } else {
                    alert("Gửi Thất bại")
                }
            }
        })
        return
    })
}
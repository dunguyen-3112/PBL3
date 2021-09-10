//render table sinh vien
const LINK = 'http://14.233.18.201:9000/qltv/1/'
const LINK_I = 'http://14.233.18.201:9000/'

function renderTableSV(href) {
    $.get(href, function(sinhviens, status) {
        const L = [{ id: 0, name: 'Lọc' }]
        $.get(LINK + 'lops', function(lops, status) {
            lops.forEach(e => {
                L.push({ id: e.id, name: e.name })
            })
        })
        var cols = [{ name: 'image', text: 'Ảnh', type: 'img' },
            { name: 'id', text: 'Mã Sinh Viên', type: 'number' },
            { name: 'idClass', text: 'Lớp', type: 'select', option: L, col: [{ name: 'id', text: 'Mã Lớp' }, { name: 'name', text: 'Tên Lớp' }] },
            { name: 'name', text: 'Tên Sinh Viên', type: 'text' },
            { name: 'code', text: 'Căn Cước Công Dân', type: 'number' },
            { name: 'gender', text: 'Nam', type: 'checkbox' },
            { name: 'birthdate', text: 'Ngày Sinh', type: 'date' },
            { name: 'address', text: 'Địa Chỉ', type: 'text' },
            { name: 'emailNumber', text: 'email', type: 'email' },
            { name: 'phoneNumber', text: 'Số Điện Thoại', type: 'number' },
            { name: 'password', text: 'Mật Khẩu', type: 'password' },
        ]
        renderTable(cols, sinhviens, href, true, 'sv')
    })
}
// function table Chuc vụ
function renderTableCV(href) {
    $.get(href, function(chucvus, status) {
        var cols = [{ name: 'id', text: 'Mã Vai Trò', type: 'number' },
            { name: 'name', text: 'Tên Vai Trò', type: 'text' },
            { name: 'number', text: 'Số Lượng', type: 'number' }
        ]
        renderTable(cols, chucvus, href, false, '')
    })
}

function renderTableNV(href) {
    $.get(href, function(nhanviens, status) {
        $.get(LINK + 'chucvus', function(chucvus, status) {
            const N = []
            N.push({ id: 0, name: 'Lọc' })
            chucvus.forEach(e => {
                N.push({ id: e.id, name: e.name })
            })
            var cols = [{ name: 'image', text: 'Ảnh', type: 'img' },
                { name: 'id', text: 'Mã Nhân Viên', type: 'number' },
                { name: 'idRole', text: 'Vai Trò', type: 'select', 'option': N, col: [{ name: 'id', text: 'Mã Vai Trò' }, { name: 'name', text: 'Tên Vai Trò' }] },
                { name: 'name', text: 'Tên Nhân Viên', type: 'text' },
                { name: 'code', text: 'Căn Cước Công Dân', type: 'number' },
                { name: 'gender', text: 'Nam', type: 'checkbox' },
                { name: 'birthdate', text: 'Ngày Sinh', type: 'date' },
                { name: 'address', text: 'Địa Chỉ', type: 'text' },
                { name: 'emailNumber', text: 'email', type: 'email' },
                { name: 'phoneNumber', text: 'Số Điện Thoại', type: 'number' },
                { name: 'password', text: 'Mật Khẩu', type: 'password' }
            ]
            renderTable(cols, nhanviens, href, true, 'nv')
        })
    })
}

function renderTableK(href) {
    $.get(href, function(khoas, status) {
        var cols = [{ name: 'id', text: 'Mã Khoa', type: 'number' },
            { name: 'name', text: 'Tên Khoa', type: 'text' }
        ]
        renderTable(cols, khoas, href, false, '')
    })
}

function renderTableM(href) {
    $.get(href, function(messages, status) {
        var cols = [{ name: 'id', text: 'Mã Message', type: 'number' },
            { name: 'midStudent', text: 'Mã Sinh Viên', type: 'number' },
            { name: 'message', text: 'Message', type: 'textarea' },
            { name: 'time', text: 'Thời Gian', type: 'date' },
            { name: 'mcheck', text: 'Xác Nhận', type: 'checkbox' }
        ]
        renderTable(cols, messages, href, false, '')
    })
}

function renderTableTL(href) {
    $.get(href, function(theloais, status) {
        var cols = [{ name: 'id', text: 'Mã Thể Loại', type: 'number' },
            { name: 'name', text: 'Tên Thể Loại', type: 'text' },
        ]
        renderTable(cols, theloais, href, false)
    })
}

function renderTableL(href) {
    const K = []
    $.get(LINK + 'khoas', function(khoas, status) {
        K.push({ id: 0, name: 'Lọc' })
        khoas.forEach(element => {
            K.push({ id: element.id, name: element.name })
        })
    })
    $.get(href, function(lops, status) {

        var cols = [{ name: 'id', text: 'Mã Lớp', type: 'number' },
            { name: 'name', text: 'Tên Lớp', type: 'text' },
            { name: 'idFaculty', text: 'Khoa', type: 'select', option: K, col: [{ name: 'id', text: 'Mã Khoa' }, { name: 'name', text: 'Tên Khoa' }] },
        ]
        renderTable(cols, lops, href, false, 'nv')
    })
}

function renderTableDK(href) {
    var options = [{ id: 0, name: 'Lọc' },
        { id: 1, name: '1 Tháng' },
        { id: 2, name: '2 Tháng' },
        { id: 3, name: '3 Tháng' },
        { id: 4, name: '4 Tháng' },
        { id: 5, name: '5 Tháng' },
        { id: 6, name: '6 Tháng' },
        { id: 9, name: '9 Tháng' },
        { id: 12, name: '1 năm' },
    ]
    var cols = [{ name: 'id', text: 'Mã Đăng Ký', type: 'number' },
        { name: 'idBook', text: 'Mã Sách', type: 'number' },
        { name: 'idStudent', text: 'Mã Sinh Viên', type: 'number' },
        { name: 'time', text: 'Thời hạn (Tháng)', type: 'select', option: options },
        { name: 'registrationDate', text: 'Ngày Mượn', type: 'date' },
        { name: 'status', text: 'Trạng Thái', type: 'checkbox' },
    ]
    $.get(href, function(nhanviens, status) {
        renderTable(cols, nhanviens, href, false, '')
    })
}

function renderTableS(href) {
    const T = [{ id: 0, name: 'Lọc' }]
    $.get(LINK + 'theloais', function(theloais, status) {
        console.log(status)
        theloais.forEach(e => {
            T.push({ id: e.id, name: e.name })
        })

    })
    console.log(T)

    $.get(href, function(sachs, status) {
        var cols = [{ name: 'image', text: 'Ảnh', type: 'img' },
            { name: 'id', text: 'Mã Sách', type: 'number' },
            { name: 'idgenre', text: 'Thể Loại', type: 'select', option: T, col: [{ name: 'id', text: 'Mã Thể Loại' }, { name: 'name', text: 'Tên Thể Loại' }] },
            { name: 'name', text: 'Tên Sách', type: 'text' },
            { name: 'sale', text: 'Giá Sách', type: 'number' },
            { name: 'releaDate', text: 'Ngày Xuất Bản', type: 'date' },
            { name: 'publisher', text: 'Nhà Xuất Bản', type: 'text' },
            { name: 'pages', text: 'Số Trang', type: 'number' },
            { name: 'author', text: 'Tác Giả', type: 'text' },
            { name: 'status', text: 'Trạng Thái', type: 'checkbox' },
            { name: 'description', text: 'Mô Tả', type: 'textarea' }
        ]
        renderTable(cols, sachs, href, true, 's')
    })
}

$(document).ready(function() {

    $('.btnSinhVien').on('click', (event) => {
        event.preventDefault()
        var href = event.target.href
        renderTableSV(href)
    })

    $('.btnchucvu').on('click', (event) => {
        event.preventDefault();
        var href = event.target.href
        renderTableCV(href);
    })

    $('.btnNhanVien').on('click', (event) => {
        event.preventDefault()
        var href = event.target.href
            //console.log(href)
        renderTableNV(href)
    })
    $('.btnLop').on('click', (event) => {
        event.preventDefault()
        var href = event.target.href
        renderTableL(href)
    })
    $('.btnKhoa').on('click', (event) => {
        event.preventDefault()
        var href = event.target.href
        renderTableK(href)
    })
    $('.btnTheLoai').on('click', (event) => {
        event.preventDefault()
        var href = event.target.href
        renderTableTL(href)
    })
    $('.btnSach').on('click', (event) => {
        event.preventDefault()
        var href = event.target.href
        renderTableS(href)
    })
    $('.btnDkmuontra').on('click', (event) => {
        event.preventDefault()
        var href = event.target.href
        renderTableDK(href)
    })
    $('.IconMessage').on('click', (event) => {
        event.preventDefault()
        var div = document.getElementById('root')
        div.style.display = 'none'
        var div1 = document.getElementById('btnadd')
        div1.style.display = 'none'
        var div2 = document.getElementById('form-seach')
        div2.style.display = 'none'
        var div3 = document.getElementById('contai')
        div3.style.display = 'none'

        renderTableM(LINK + 'messages')
    })
});

function renderSeach(cols, objects, href, Img, ImgLink) {
    var formseach = document.getElementById('form-seach')
    formseach.innerHTML = '';
    formseach.style.display = 'block'
    formseach.style.marginLeft = '20px'
    var divseach = document.createElement('div')
    divseach.setAttribute('class', 'form-row')
    var divseachinput = document.createElement('div')
    divseachinput.setAttribute('class', 'form-group col-md-4')
    var inputseach = document.createElement('input')
    inputseach.setAttribute('class', 'form-control')
    inputseach.type = 'search'
    divseachinput.appendChild(inputseach)
    divseach.appendChild(divseachinput)

    var divseachselect1 = document.createElement('div')
    divseachselect1.setAttribute('class', 'form-group col-md-4')
    var selectseach1 = document.createElement('select')
    selectseach1.setAttribute('class', 'form-control')
    divseachselect1.appendChild(selectseach1)
    divseach.appendChild(divseachselect1)

    var divseachselect2 = document.createElement('div')
    divseachselect2.setAttribute('class', 'form-group col-md-3')
    var selectseach2 = document.createElement('select')
    selectseach2.setAttribute('class', 'form-control')
    divseachselect2.appendChild(selectseach2)
    divseach.appendChild(divseachselect2)

    var divcheck = document.createElement('div')
    divcheck.setAttribute('class', 'form-group col-md-1')
    var inputcheck = document.createElement('input')
    inputcheck.type = 'checkbox'
    inputcheck.setAttribute('class', 'form-control')
    divcheck.appendChild(inputcheck)
    divseach.appendChild(divcheck)

    inputcheck.addEventListener('change', (event) => {
            event.preventDefault()
            if (href == LINK + 'sachs') {
                $.get(href + '/check/' + inputcheck.checked, function(data, status) {
                    renderTbody(cols, data, href, Img, ImgLink)
                    return
                })
            }
            if (href == LINK + 'dkmuontras') {
                if (inputcheck.checked) {
                    $.get(href + '/check', function(data, status) {
                        renderTbody(cols, data, href, Img, ImgLink)
                    })
                    return
                } else renderTbody(cols, objects, href, Img, ImgLink)
            }
            if (href == LINK + 'messages') {
                $.get(href + '/check/' + inputcheck.checked, function(data, status) {
                    renderTbody(cols, data, href, Img, ImgLink)
                })
                return
            }
            if (href == LINK + 'nhanviens' || href == LINK + 'sinhviens') {
                $.get(href + '/check/' + inputcheck.checked, function(data, status) {
                    renderTbody(cols, data, href, Img, ImgLink)
                })
                return
            } else return
        })
        /////

    inputseach.addEventListener('keydown', (event) => {
        if (event.keyCode == 13) event.preventDefault()
    })
    inputseach.addEventListener('keyup', (event) => {
        if (inputseach.value == '') {
            renderTable(cols, objects, href, Img, ImgLink)
            return true;
        } else {
            $.get(href + '/keyword=/' + inputseach.value, function(O, status) {
                renderTbody(cols, O, href, Img, ImgLink)
                return true;
            })
        }
    })
    for (let i = 0; i < cols.length; i++) {
        if (cols[i].option != null) {
            cols[i].option.forEach(e => {
                var option = document.createElement('option')
                option.value = e.id
                option.text = e.name
                selectseach1.appendChild(option)
            })
        }

    }

    selectseach1.addEventListener('change', (event) => {
        event.preventDefault()
        $.get(href + '/idsort=/' + selectseach1.value, function(O, status) {
            renderTbody(cols, O, href, Img, ImgLink)
        })
    })

    var option0 = document.createElement('option')
    option0.value = 0
    option0.text = 'sắp xếp'
    selectseach2.appendChild(option0)
    var option = document.createElement('option')
    option.value = 1
    option.text = 'id'
    selectseach2.appendChild(option)
    var option1 = document.createElement('option')
    option1.value = 2
    option1.text = 'name/time'
    selectseach2.appendChild(option1)
    formseach.appendChild(divseach)

    selectseach2.addEventListener('change', (event) => {
        if (selectseach2.value === 0) return
        if (selectseach2.value === 1) {
            $.get(href + '/sortid/' + inputcheck.checked, function(data, status) {
                renderTbody(cols, data, href, Img, ImgLink)
                return true;
            })
        } else {
            if (href === LINK + 'dkmuontras') {
                $.get(href + '/sortregistrationDate/' + inputcheck.checked, function(data, status) {
                    renderTable(cols, data, href, Img, ImgLink)
                    return true;
                })
            } else {
                $.get(href + '/sortname/' + inputcheck.checked, function(data, status) {
                    renderTable(cols, data, href, Img, ImgLink)
                    return true;
                })
            }
        }
    })
}

function renderTHeader(cols, objects, href, Img, ImgLink) {
    var divadd = document.getElementById('btnadd')
    divadd.style.class = 'form-row'
    divadd.innerHTML = ''
    divadd.style.display = 'block'
    var btnAdd = document.createElement('button')
    var iconadd = document.createElement('i')
    iconadd.setAttribute('class', 'fas fa-plus-square')
    btnAdd.appendChild(iconadd)
    btnAdd.setAttribute('class', 'btn btn-link')

    btnAdd.addEventListener('click', (event) => {
        event.preventDefault()
        renderForm(href, cols, null, Img, false)
    })

    var thead = document.getElementById('thead')
    thead.innerHTML = ''
    var tr = document.createElement("tr")
    var th = document.createElement("th")
    th.setAttribute("align", "20px")
    th.innerHTML = 'STT'
    tr.appendChild(th)
    cols.forEach(name => {
        if ((name.name != 'emailNumber') &&
            (name.name != 'phoneNumber') &&
            (name.name != 'address') &&
            (name.name != 'code') &&
            (name.name != 'password') &&
            (name.name != 'birthdate') &&
            (name.name != 'id') &&
            // (name.name != 'status') &&
            (name.name != 'releaDate') &&
            (name.name != 'publisher') &&
            (name.name != 'pages') &&
            (name.name != 'description')) {
            var th = document.createElement("th")
            if ((name.name == 'gender'))
                th.innerHTML = 'Giới Tính'
            else if (name.name == 'idStudent') {
                console.log('name')
                th.innerHTML = 'Tên Sinh Viên'
            } else if (name.name == 'idBook')
                th.innerHTML = 'Tên Sách'
            else
                th.innerHTML = name.text
            tr.appendChild(th)
        }

    });
    var th2 = document.createElement("th")
    th2.innerHTML = 'Lựa Chọn'
    tr.appendChild(th2)
    tr.appendChild(btnAdd)
    tr.style.textAlign = 'center'
    thead.appendChild(tr)
    var title = document.createElement('h5')
    title.setAttribute('margin-top', '0px')
    title.setAttribute('align', 'center')

    if (href == LINK + 'chucvus')
        title.innerHTML = 'Danh Sách Vai Trò'
    if (href == LINK + 'nhanviens')
        title.innerHTML = 'Danh Sách Nhân Viên'
    if (href == LINK + 'khoas')
        title.innerHTML = 'Danh Sách Khoa'
    if (href == LINK + 'lops')
        title.innerHTML = 'Danh Sách Lớp'
    if (href == LINK + 'sinhviens')
        title.innerHTML = 'Danh Sách Sinh Viên'
    if (href == LINK + 'sachs')
        title.innerHTML = 'Danh Sách Sách'
    if (href == LINK + 'theloais')
        title.innerHTML = 'Danh Sách Thể Loại'
    if (href == LINK + 'dkmuontras')
        title.innerHTML = 'Danh Sách Đăng Ký'

    divadd.appendChild(title);
}

function renderTbody(cols, objects, href, Img, ImgLink) {
    var g = 1;
    var tbody = document.getElementById("tbody")
    tbody.innerHTML = ''
    var d = 1
    objects.forEach(Item => {
        var tr = document.createElement("tr")
        var td = document.createElement("td")
        td.innerHTML = d++
            tr.appendChild(td)
        cols.forEach(name => {
            if ((name.name == 'image')) {
                var btnIma = document.createElement('button')
                btnIma.setAttribute('class', 'btn btn-link')
                btnIma.setAttribute('href', LINK_I + ImgLink + '/' + Item.id + '.jpg')

                btnIma.addEventListener('click', (event) => {
                    open(LINK_I + ImgLink + '/' + Item.id + '.jpg')
                })
                var tdImg = document.createElement('img')
                tdImg.setAttribute('src', LINK_I + ImgLink + '/' + Item.id + '.jpg')
                tdImg.setAttribute('width', "30px");
                btnIma.appendChild(tdImg)
                tr.appendChild(btnIma)
            } else {
                if ((name.name != 'emailNumber') &&
                    (name.name != 'phoneNumber') &&
                    (name.name != 'address') &&
                    (name.name != 'code') &&
                    (name.name != 'password') &&
                    (name.name != 'birthdate') &&
                    (name.name != 'id') &&
                    // (name.name != 'status') &&
                    (name.name != 'publisher') &&
                    (name.name != 'releaDate') &&
                    (name.name != 'publisher') &&
                    (name.name != 'pages') &&
                    (name.name != 'description')) {
                    var td1 = document.createElement("td")
                    if (name.name == 'idgenre') g = 2;
                    if (name.name == 'registrationDate') g = 3;

                    var urlTable = ''
                    if (((name.name == 'idgenre') && (urlTable = 'theloais')) ||
                        ((name.name == 'idBook') && (urlTable = 'sachs')) ||
                        ((name.name == 'idStudent') && (urlTable = 'sinhviens')) ||
                        ((name.name == 'idClass') && (urlTable = 'lops')) ||
                        ((name.name == 'idFaculty') && (urlTable = 'khoas')) ||
                        ((name.name == 'idRole') && (urlTable = 'chucvus'))) {
                        var btnid = document.createElement('button')
                        btnid.setAttribute('class', 'btn btn-link')
                        btnid.style.color = 'black'
                        btnid.style.textDecorationLine = 'none'
                        $.get(LINK + urlTable + '/' + Item[name.name], function(data, status) {
                            btnid.innerHTML = data.name
                            td1.appendChild(btnid)
                            btnid.addEventListener('click', (event) => {
                                event.preventDefault();
                                if ((name.name != 'idStudent') && (name.name != 'idBook')) {
                                    console.log(name.name)
                                    renderForm(href, cols[2].col, data, false, true)
                                } else {
                                    if (name.name == 'idStudent') {
                                        $.get(LINK + 'lops', function(lops, status) {
                                            const L = []
                                            L.push({ id: 0, name: 'select' })
                                            lops.forEach(e => {
                                                L.push({ id: e.id, name: e.name })
                                            })
                                            let col = [{ name: 'image', text: 'Ảnh', type: 'img' },
                                                { name: 'id', text: 'Mã Sinh Viên', type: 'number' },
                                                { name: 'idClass', text: 'Lớp', type: 'select', option: L, col: [{ name: 'id', text: 'Mã Lớp' }, { name: 'name', text: 'Tên Lớp' }] },
                                                { name: 'name', text: 'Tên Sinh Viên', type: 'text' },
                                                { name: 'code', text: 'Căn Cước Công Dân', type: 'number' },
                                                { name: 'gender', text: 'Nam', type: 'checkbox' },
                                                { name: 'birthdate', text: 'Ngày Sinh', type: 'date' },
                                                { name: 'address', text: 'Địa Chỉ', type: 'text' },
                                                { name: 'emailNumber', text: 'email', type: 'email' },
                                                { name: 'phoneNumber', text: 'Số Điện Thoại', type: 'number' },
                                                { name: 'password', text: 'Mật Khẩu', type: 'password' },
                                            ]
                                            renderForm(href, col, data, true, true)
                                        })
                                    } else {
                                        const T = []
                                        T.push({ id: 0, name: 'select' })
                                        $.get(LINK + 'theloais', function(theloais, status) {
                                            theloais.forEach(e => {
                                                T.push({ id: e.id, name: e.name })
                                            })
                                        })
                                        let col = [{ name: 'image', text: 'Ảnh', type: 'img' },
                                            { name: 'id', text: 'Mã Sách', type: 'number' },
                                            { name: 'idgenre', text: 'Thể Loại', type: 'select', option: T, col: [{ name: 'id', text: 'Mã Thể Loại' }, { name: 'name', text: 'Tên Thể Loại' }] },
                                            { name: 'name', text: 'Tên Sách', type: 'text' },
                                            { name: 'sale', text: 'Giá Sách', type: 'number' },
                                            { name: 'releaDate', text: 'Ngày Xuất Bản', type: 'date' },
                                            { name: 'publisher', text: 'Nhà Xuất Bản', type: 'text' },
                                            { name: 'pages', text: 'Số Trang', type: 'number' },
                                            { name: 'author', text: 'Tác Giả', type: 'text' },
                                            { name: 'status', text: 'Trạng Thái', type: 'checkbox' },
                                            { name: 'description', text: 'Mô Tả', type: 'textarea' }
                                        ]
                                        renderForm(href, col, data, true, true)
                                    }
                                }
                                return
                            })
                        })
                    } else if (name.name == 'mcheck' || name.name == 'gender' || name.name == 'status') {
                        var check = document.createElement('i')
                        if (Item[name.name]) {
                            check.setAttribute('class', 'far fa-check-circle')
                            check.style.color = "green"
                        } else {
                            check.setAttribute('class', 'fas fa-window-close')
                            check.style.color = "red"
                        }
                        td1.appendChild(check)
                    } else
                        td1.innerHTML = Item[name.name]
                    tr.appendChild(td1)
                }
            }

        });
        var td2 = document.createElement("td")

        var btndel = document.createElement("button")
        var icondel = document.createElement('i')
            //btndel.innerHTML = 'Xóa'
        if (g === 2) {
            $.get(href + '/check/' + Item.id + '/' + Item.idUser, function(data, status) {
                if (!data) btndel.disabled = true
            })
        }
        if (g === 3) {
            $.get(href + '/check/' + Item.id + '/' + Item.idUser, function(data, status) {
                if (!data) btndel.disabled = true
            })
        }
        icondel.setAttribute('class', 'far fa-trash-alt')
        btndel.appendChild(icondel)
        btndel.setAttribute("href", href + "/" + Item.id)
        btndel.setAttribute("class", "btn btn-link")

        btndel.addEventListener('click', (event) => {
            event.preventDefault()
            Remove(href, Item.id)
        })
        td2.appendChild(btndel)

        var btnUpda = document.createElement("button")
        var iconedit = document.createElement('i')
        iconedit.setAttribute('class', 'fas fa-edit')
        btnUpda.appendChild(iconedit)
        btnUpda.setAttribute("href", href + '/' + Item.id)
        btnUpda.setAttribute("class", "btn btn-link")
        td2.appendChild(btnUpda)

        // btnUpda.addEventListener('mouseover',(event)=>{
        //     event.preventDefault()
        //     btnUpda.innerHTML=''
        //     var lblupda = document.createElement('label')
        //     lblupda.innerHTML='sửa'
        //     btnUpda.appendChild(lblupda)
        // })

        // btnUpda.addEventListener('mouseout',(event)=>{
        //     btnUpda.innerHTML=''
        //     var iconupda = document.createElement('i')
        //     //btndel.innerHTML = 'Xóa'
        //     iconupda.setAttribute('class', 'fas fa-edit')
        //     btnUpda.appendChild(iconupda)
        // })

        // btnAdd.addEventListener('mouseover',(event)=>{
        //     event.preventDefault()
        //     btnAdd.innerHTML=''
        //     var lbladd = document.createElement('label')
        //     lbladd.innerHTML='Thêm'
        //     btnAdd.appendChild(lbladd)
        // })

        // btnAdd.addEventListener('mouseout',(event)=>{
        //     event.preventDefault()
        //     btnAdd.innerHTML=''
        //     var iconadd = document.createElement('i')
        //     //btndel.innerHTML = 'Xóa'
        //     iconadd.setAttribute('class', 'fas fa-plus-square')
        //     btnAdd.appendChild(iconadd)
        // })

        // btndel.addEventListener('mouseover',(event)=>{
        //     event.preventDefault()
        //     btndel.innerHTML=''
        //     // var lbldel = document.createElement('label')
        //     lbldel.innerHTML='Xóa'
        //     //btndel.appendChild(lbldel)
        // })
        // btndel.addEventListener('mouseout',(event)=>{
        //     btndel.innerHTML=''
        //     var icondel = document.createElement('i')
        //     //btndel.innerHTML = 'Xóa'
        //     // icondel.setAttribute('class', 'far fa-trash-alt')
        //     // btndel.appendChild(icondel)
        //     lbldel.innerHTML='   '
        // })
        btnUpda.addEventListener('click', (event) => {
            event.preventDefault()
            $.get(href + '/' + Item.id, function(data, status) {
                    renderForm(href, cols, data, Img, true)
                })
                //renderForm(href, cols, Item, Img, true)
        })
        tr.appendChild(td2)
        tbody.appendChild(tr)
    });
}
//function render Table
function renderTable(cols, objects, href, Img, ImgLink) {


    var divinfo = document.getElementById('info')
    divinfo.style.display = 'none'

    renderSeach(cols, objects, href, Img, ImgLink)

    var root = document.getElementById('root')
    root.style.marginLeft = '20px'
    root.style.marginRight = '20px'
    root.style.height = '800px';
    root.style.overflow = 'auto'
    root.style.border = '10px solid rgb(140, 165, 177)'
    root.style.borderRadius = '5px'
    root.style.paddingTop = '10px'
    root.innerHTML = ''
    root.style.display = 'block'

    var TableComponent = document.createElement("table")
    TableComponent.setAttribute("class", "table table-striped table-bordered text-center")
    root.appendChild(TableComponent)
    var thead = document.createElement('thead')
    thead.setAttribute('id', 'thead')
    TableComponent.appendChild(thead)
    var tbody = document.createElement('tbody')
    tbody.setAttribute('id', 'tbody')
    TableComponent.appendChild(tbody)


    renderTHeader(cols, objects, href, Img, ImgLink)

    renderTbody(cols, objects, href, Img, ImgLink)

}

function render(href) {
    if (href == LINK + 'chucvus')
        renderTableCV(href)
    if (href == LINK + 'nhanviens')
        renderTableNV(href)
    if (href == LINK + 'khoas')
        renderTableK(href)
    if (href == LINK + 'lops')
        renderTableL(href)
    if (href == LINK + 'sinhviens')
        renderTableSV(href)
    if (href == LINK + 'sachs')
        renderTableS(href)
    if (href == LINK + 'theloais')
        renderTableTL(href)
    if (href == LINK + 'dkmuontras')
        renderTableDK(href)
    if (href == LINK + 'messages')
        renderTableM(href)
}

// Xóa Object
function Remove(href, id) {
    var ch = confirm("Bạn Muốn Xóa");
    if (ch == true) {
        $.ajax({
            type: "DELETE",
            url: href + "/" + id,
            success: function(data, status) {
                console.log(status)
                alertUsing("Xóa Thành Công", true)
                render(href)
            },
            error: function(err) {
                alertUsing(err, false);
            }
        });
    }
    return
}

//update object
function Update(href, O, cols, img) {
    var ch = true
    cols.forEach(item => {
        if ((item.name != 'id') &&
            (item.name != 'image') &&
            (item.name != 'gender') &&
            (item.name != 'status') &&
            (O[item.name] == '' || O[item.name] == null)) {
            alertUsing('Không được bỏ trống ==>' + item.text, false)
            ch = false;
        }
    });
    if (!ch) return
    $.ajax({
        url: href,
        type: "PUT",
        data: JSON.stringify(O),
        contentType: "application/json",
        timeout: 6000,
        success: function(data) {
            console.log(data)
            if (img) createImage(href, O.id)
            render(href)
            alertUsing("Chỉnh Sửa Thành Công", true)
        },
        error: function(error) {
            console.log(error)
            alertUsing("Chỉnh Sửa Thất Bại", false)
        }
    })
}

//save object

function Create(href, object, cols, Img) {
    console.log(object)
    var ch = true
    cols.forEach(item => {
        if ((item.name != 'id') &&
            (item.name != 'gender') &&
            (item.name != 'image') &&
            (item.name != 'registrationDate') &&
            (item.name != 'number') &&
            (object[item.name] == '' || object[item.name] == null)) {
            alertUsing('Không được bỏ trống ==>' + item.text, false)
            ch = false;
        }
    });
    if (!ch) return
    $.ajax({
        url: href,
        type: 'POST',
        data: JSON.stringify(object),
        contentType: "application/json",
        timeout: 6000,
        success: function(data) {
            if (data != '') {
                if (Img)
                    createImage(href, data.id)
                render(href)
                alertUsing("Lưu Thành Công", true)

            } else alertUsing("Lưu Thất Bại", false)

        },
        error: function(error) {
            console.log(error)
            alertUsing("Lưu Thất Bại", false)
        }
    })

}

function renderForm(href, cols, object, img, CorU) {

    var g = 0;
    var divseach = document.getElementById('form-seach')
    divseach.style.display = "none"

    var divadd = document.getElementById('btnadd')
    divadd.innerHTML = ''
        //div id = root
    var divroot = document.getElementById('root')
    divroot.innerHTML = ''
        // form 1
    var form = document.createElement('form')
    const ins = []
    for (var i = 0; i < cols.length; i++) {
        if (cols[i].name == 'pages') g = 2;
        if (cols[i].name == 'registrationDate') g = 3;
        //if form add then inpu id continue
        if (!CorU && cols[i].name == 'id')
            continue
            //not add input image
        if (cols[i].name != 'image' && cols[i].name != 'number') {
            //add div combo
            var _div = document.createElement('div')
            _div.style.marginLeft = '200px'
            _div.setAttribute('class', 'form-row')
                //add div item1
            let div = document.createElement('div')
            div.setAttribute('class', 'form-group col-md-4')
                //add div lable1
            let lbl = document.createElement('label')
            lbl.innerHTML = cols[i].text
            div.appendChild(lbl)
                //if input is select
            if (cols[i].option != null) {
                //add input select
                ins[i] = document.createElement('select')
                ins[i].id = cols[i].name
                ins[i].name = cols[i].name
                ins[i].setAttribute('class', 'form-control')
                    //add option in select
                cols[i].option.forEach(item => {
                    if (item.id != 0) {
                        var option = document.createElement('option')
                        option.text = item.name
                        option.value = item.id
                        ins[i].appendChild(option)
                    }
                });
                if (CorU) {
                    ins[i].value = object[cols[i].name]
                }
                //add input div item1 and i++
                div.appendChild(ins[i++])
                    //if input isn't select
            } else {
                ins[i] = document.createElement('input')
                ins[i].id = cols[i].name
                ins[i].name = cols[i].name
                ins[i].setAttribute('class', 'form-control')
                ins[i].type = cols[i].type
                if (CorU) {
                    ins[i].value = object[cols[i].name]
                    if (cols[i].name == 'id') ins[i].readOnly = 'true'
                    if ((cols[i].name == 'gender' && object.gender) ||
                        (cols[i].name == 'status' && object.status)) ins[i].checked = true
                    if ((cols[i].name == 'mcheck' && object.mcheck)) {
                        ins[i].checked = true
                        ins[i].disabled = true
                    }
                    if ((cols[i].name == 'status')) {
                        ins[i].disabled = true
                    }
                }
                if (cols[i].name == 'mcheck' || cols[i].name == 'message' || cols[i].name == 'midStudent') {
                    if (object[cols[i].name]) ins[i].readOnly = true
                }
                //add input div item1 and i++
                div.appendChild(ins[i++])
            }
            //add div item in div combo
            _div.appendChild(div)
                //if attribute next
            if (i < cols.length) {
                //add div item 2
                let div = document.createElement('div')
                div.setAttribute('class', 'form-group col-md-4')
                    //add lable 2
                let lbl = document.createElement('label')
                lbl.innerHTML = cols[i].text
                if ((!CorU && cols[i].name != 'registrationDate' && cols[i].name != 'number') || CorU)
                    div.appendChild(lbl)
                    //input is select
                if (cols[i].option != null) {
                    //add input select in div item2
                    ins[i] = document.createElement('select')
                    ins[i].id = cols[i].name
                    ins[i].name = cols[i].name
                    ins[i].setAttribute('class', 'form-control')
                        //add option in select
                    cols[i].option.forEach(item => {
                        if (item.id != 0) {
                            var option = document.createElement('option')
                            option.text = item.name
                            option.value = item.id
                            ins[i].appendChild(option)
                        }
                    });
                    //add input2 in div item2
                    div.appendChild(ins[i])
                        //input isn's select
                } else {
                    //add input in div item2
                    ins[i] = document.createElement('input')
                    ins[i].id = cols[i].name
                    ins[i].name = cols[i].name
                    ins[i].setAttribute('class', 'form-control')
                    ins[i].type = cols[i].type
                    if (CorU) {
                        ins[i].value = object[cols[i].name]
                        if (cols[i].name == 'id') ins[i].readOnly = 'true'
                        if ((cols[i].name == 'gender' && object.gender) ||
                            (cols[i].name == 'status' && object.status)) ins[i].checked = true
                        if (cols[i].name == 'time' || cols[i].name == 'midStudent') ins[i].readOnly = true
                        if ((cols[i].name == 'mcheck' && object.mcheck)) {
                            console.log(cols[i].name)
                            ins[i].checked = true
                            ins[i].readOnly = 'true'
                        }
                    }
                    // if ((!CorU && cols[i].name != 'registrationDate') || CorU )
                    //     div.appendChild(ins[i])
                    if ((!CorU && cols[i].name != 'registrationDate' && cols[i].name != 'number') || CorU)
                        div.appendChild(ins[i])
                }
                if (CorU) {
                    ins[i].value = object[cols[i].name]
                }
                //add div item2 in div combo
                _div.appendChild(div)
            }
            //add div combo item form
            form.appendChild(_div)
        } else {
            continue
        }

    }
    var btnQL = document.createElement('button')
    var iconQL = document.createElement('i')
    iconQL.setAttribute('class', 'fas fa-arrow-left')
    btnQL.appendChild(iconQL)
    btnQL.setAttribute('class', 'btn btn-outline-primary')

    btnQL.addEventListener('click', (event) => {
        event.preventDefault()
        render(href)
    })


    // btnsave
    var btnsave = document.createElement('button')
    var iconsave = document.createElement('i')
    iconsave.setAttribute('class', 'fas fa-save')
    btnsave.appendChild(iconsave)
    btnsave.setAttribute('class', 'btn btn-outline-primary')
    btnsave.type = 'submit'
    if (g === 2) {

        $.get(href + '/check/' + object.id + '/' + object.idUser, function(data, status) {
            if (!data) btnsave.disabled = true
        })
    }
    if (g === 3) {
        $.get(href + '/check/' + object.id + '/' + object.idUser, function(data, status) {
            if (!data) btnsave.disabled = true
        })
    }

    //bnTT
    var btnTT = document.createElement('button')
    var iconTT = document.createElement('i')
    iconTT.setAttribute('class', 'fas fa-arrow-right')
    btnTT.appendChild(iconTT)
    btnTT.setAttribute('class', 'btn btn-outline-primary')
        //div include (btnsave || btnTT)
    var div1 = document.createElement('div')
    div1.setAttribute('class', 'form-row')
    div1.appendChild(btnQL)
        //object set image
    if (img) {
        div1.appendChild(btnTT)
        _div.appendChild(div1)
        form.appendChild(_div)
        divroot.appendChild(form)

        var form2 = document.createElement('form')
        form2.setAttribute('method', 'POST')
        form2.setAttribute('enctype', 'multipart/form-data')
        form2.setAttribute('id', 'formdata')

        var divImage = document.createElement('div')
        divImage.setAttribute('class', 'form-group')
        var lblImage = document.createElement('label')
        lblImage.innerHTML = 'Ảnh'
        divImage.appendChild(lblImage)
        var inImge = document.createElement('input')
        inImge.setAttribute('type', 'file')
        inImge.setAttribute('name', 'uploadfile')
        inImge.setAttribute('id', 'uploadfile')
        inImge.setAttribute('class', 'form-control col-md-6')
        divImage.appendChild(inImge)

        var btnQL = document.createElement('button')
        var iconBack = document.createElement('i')
        iconBack.setAttribute('class', 'fas fa-arrow-left')
        btnQL.appendChild(iconBack)
        btnQL.setAttribute('class', 'btn btn-outline-danger')
        divImage.appendChild(btnQL)

        divImage.appendChild(btnsave)
        form2.appendChild(divImage)
        form2.style.display = 'none'
        divroot.appendChild(form2)

        btnTT.addEventListener('click', (event) => {
            event.preventDefault()
            form.style.display = 'none'
            form2.style.display = 'block'
        })

        btnQL.addEventListener('click', (event) => {
            event.preventDefault()
            form2.style.display = 'none'
            form.style.display = 'block'
        })
    } else {
        div1.appendChild(btnsave)
        form.appendChild(div1)
        divroot.appendChild(form)
    }
    btnsave.addEventListener('click', (event) => {
        event.preventDefault()
        var O = {}
        for (var i = 0; i < cols.length; i++) {
            if (!CorU && cols[i].name == 'registrationDate')
                continue
            if ((!CorU && cols[i].name == 'id') || (cols[i].name == 'image')) continue
            O[cols[i].name] = ins[i].value
            if ((cols[i].name == 'gender') || (cols[i].name == 'status') || (cols[i].name == 'mcheck')) {
                if (ins[i].checked)
                    O[cols[i].name] = true;
                else O[cols[i].name] = false;
            }
            if ((cols[i].name == 'birthdate') ||
                (cols[i].name == 'releaDate')) {
                var t = new Date(O[cols[i].name])
                t.setDate(t.getDate() + 1)
                O[cols[i].name] = t
            }
        }
        if (CorU) Update(href, O, cols, img)
        else Create(href, O, cols, img)
    })
}

function createImage(href, id, CorU) {
    var formData = $('#formdata')[0];
    var data = new FormData(formData)
    var method = ''
    $.ajax({
        url: href + '/' + id + '/uploadfile',
        type: 'PUT',
        enctype: 'multipart/form-data',
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function(data) {
            return true;
        },
        error: function(error) {
            console.log(error)
            return false;
        }
    })

}

function string(href, id) {
    const s = []
    $.get(href, function(data, status) {
        console.log(data)
        return data;
    })
}

function alertUsing(text, flag) {

    var alert = $(".alert");

    if (flag) {
        alert.removeClass("alert-danger").addClass("alert-success");
    } else {
        alert.removeClass("alert-success").addClass("alert-danger");

    }

    alert.fadeIn(400);
    alert.css("display", "block");
    alert.text(text);
    setTimeout(function() {
        alert.fadeOut();
    }, 2000);

}
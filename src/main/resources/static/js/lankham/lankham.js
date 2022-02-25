// danh sach thuoc
var addThuoc = $('.add')
var dsThuoc = $('.dsthuoc')
addThuoc.onclick = function(e){
    var thuoc = $('.thuoc')
    var optionThuoc = thuoc.options[thuoc.selectedIndex]
    var value = optionThuoc.value
    var text = optionThuoc.text
    var valueSoLuong = $('.so_luong').value
    // Tao the div
    var divThuoc = document.createElement('div')
    // Tao the input id thuoc
    var inputThuoc = document.createElement('input')
    inputThuoc.type = 'text'
    inputThuoc.name = 'idthuocs'
    inputThuoc.setAttribute("value",value)
    inputThuoc.hidden = true
    // Tao th input so luong
    var inputSoLuong = document.createElement('input')
    inputSoLuong.type = 'text'
    inputSoLuong.name = "soluongs"
    inputSoLuong.setAttribute("value",valueSoLuong)
    inputSoLuong.hidden = true
    // Tao span ten thuoc
    var spanThuoc = document.createElement('span')
    spanThuoc.className = 'value_thuoc_name'
    spanThuoc.innerText = text
    // Tao span so luong thuoc
    var spanSoLuong = document.createElement('span')
    spanSoLuong.className = 'value_thuoc'
    spanSoLuong.innerText = valueSoLuong
    // Tao delete
    spanDelete = document.createElement('span')
    spanDelete.classList = 'button'
    spanDelete.innerText = 'Delete'
    spanDelete.onclick = function(){
        divThuoc.remove();
    }
    divThuoc.appendChild(inputThuoc)
    divThuoc.appendChild(inputSoLuong)
    divThuoc.appendChild(spanThuoc)
    divThuoc.appendChild(spanSoLuong)
    divThuoc.appendChild(spanDelete)
    dsThuoc.appendChild(divThuoc)

}
// sua thuoc
var deleteThuocs = $$('.delete')
deleteThuocs.forEach((element) => {
    element.onclick = function(e){
        var divParent = e.target.parentNode
        divParent.remove();
    }
})
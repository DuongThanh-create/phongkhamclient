$ = document.querySelector.bind(document)
$$ = document.querySelectorAll.bind(document)
var list = $$('.category-item')
list.forEach((item,index) => {
    var nameItem = item.querySelector('.name-item')
    nameItem.onclick = function (){
        this.classList.toggle('category-item--active')
    }
});
// nav click
// var itemList = $$('.category-item-list-item')
// itemList.forEach((item,index) => {
//     item.addEventListener('click',function(e){
//         var active= $('.category-item-list-item--active')
//         if(active!==null){
//             active.classList.remove('category-item-list-item--active');
//         }
//         this.classList.add('category-item-list-item--active')
//     });
// });
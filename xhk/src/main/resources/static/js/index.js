window.addEventListener('load', function(){
    //切换三个页面
    var btnFirst = document.querySelector('.footer .iFirst');
    var btnNews = document.querySelector('.footer .news');
    var btnMy = document.querySelector('.footer .my');


    btnFirst.addEventListener('click',function () {
        location.href = '/xhk/first.html';
    })

    btnNews.addEventListener('click',function () {
        location.href = '/xhk/message.html';
    })

    btnMy.addEventListener('click',function () {
        location.href = '/xhk/my.html';
    })

})
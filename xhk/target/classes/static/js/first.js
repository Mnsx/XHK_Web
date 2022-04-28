window.addEventListener('load',function () {
    //展示优惠券
    let cookie = getCookie('curId');
    let xhr = new XMLHttpRequest();
    // xhr.open('GET', 'http://localhost/xhk/user_discount/' + cookie);
    xhr.open('GET', 'http://mnsx.top/xhk/user_discount/' + cookie);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
            let json = JSON.parse(xhr.responseText);
            let discounts = document.getElementsByClassName('ticket');
            for (let i = 0; i < json.length; ++i) {
                let div = document.createElement('div');
                div.className = 'content';
                discounts.item(0).appendChild(div);
                div.innerHTML = '<div class="id" hidden="hidden"></div>' + ' <div class="discounts_ticket">\n' +
                    ' <div class="fullminus"> \n' +
                    '                            <div class="dollas">\n' +
                    '                                <h1 id="money' + i + '"></h1>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '\n' +
                    '                        <div class="name">\n' +
                    '                            <div class="boosname" id="storeName' + i + '"><h1></h1></div>\n' +
                    '                            <div class="elt">\n' +
                    '                                <div class="first-elt"><h1>味道好</h1></div>\n' +
                    '                                <div class="second-elt"><h1>分量足</h1></div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '\n' +
                    '                        <div class="usediscount"  data-index="' + i + '">\n' +
                    '                            <h1>去使用</h1>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '</div>';
                let storeName = document.getElementById('storeName' + i);
                let money = document.querySelector('#money' + i);
                let id = document.getElementsByClassName('id');
                id[i].innerHTML = json[i].id;
                storeName.innerText = json[i].storeName;
                money.innerHTML = '￥' + json[i].money;
            }
        }

        //删除优惠券的逻辑
        var modalBox = document.querySelector('.modal-box');
        var useModal = document.getElementsByClassName('usediscount');
        var cover = document.querySelector('.cover');
        var closeCover = document.querySelector('.modal-box i');
        // 展示二维码
        for (let i = 0; i < useModal.length; ++i) {
            useModal.item(i).addEventListener('click', function () {
                // let index = this.getAttribute('data-index');
                let id = document.getElementsByClassName('id');
                let xhr = new XMLHttpRequest();
                xhr.open('GET', 'http://mnsx.top/xhk/qrcode/' + cookie + '/' + id[i].innerHTML); // 安全性不够高后期改进
                // xhr.open('GET', 'http://localhost/xhk/qrcode/' + cookie + '/' + id[i].innerHTML); // 安全性不够高后期改进
                xhr.send();
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
                        let qrCode = document.getElementsByClassName('qrCode');
                        qrCode.item(0).src = xhr.responseURL;
                    }
                }

                modalBox.style.display = 'block';
                cover.style.display = 'block';
            })
        }


        //二维码模态框显示
        closeCover.addEventListener('click', function () {
            modalBox.style.display = 'none';
            cover.style.display = 'none';
            location.reload();
        })



        //展示用户余额
        //从cookie中获取当前用户id
/*        let xhr1 = new XMLHttpRequest();
        xhr1.open('GET', 'http://mnsx.top/xhk/user/' + cookie);
        xhr1.open('GET', 'http://localhost/xhk/user/' + cookie);
        xhr1.send();
        xhr1.onreadystatechange = function () {
            if (xhr1.readyState === 4 && xhr1.status >= 200 && xhr1.status < 300) {
                let balance = document.getElementById('balance');
                let json = JSON.parse(xhr1.responseText);
                balance.innerText = json.balance;
            }
        }*/


        //搜索功能
        let search = document.getElementsByClassName('search');
        let search_text = document.getElementById('search_text');
        let input = document.getElementsByClassName('input')
        let dropDown = document.getElementsByClassName('dropDown');
        let text = document.getElementById('text');
            search.item(1).addEventListener('click', function () {
                let xhr = new XMLHttpRequest();
                let json = JSON.stringify(text.value);
                // xhr.open("post", "http://localhost/xhk/user_discount/search", true);
                xhr.open("post", "http://mnsx.top/xhk/user_discount/search", true);
                xhr.send(json);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        let json = JSON.parse(xhr.responseText);
                        reset();
                        showDiscount(json);
                        input.item(0).style.display = 'block';
                        dropDown.item(0).style.display = 'none';
                        text.value = '';
                        resetList();
                    }
                }
            })

        //下拉框显示
        // ajax不用刷新跟新界面
        let timer = null;
        text.addEventListener('input', function(){
            clearTimeout(timer);
            if(text.value !== '') {
                timer = setTimeout(function () {
                    let xhr = new XMLHttpRequest();
                    let json = JSON.stringify(text.value);
                    // xhr.open("post", "http://localhost/xhk/user_discount/search_down", true);
                    xhr.open("post", "http://mnsx.top/xhk/user_discount/search_down", true);
                    xhr.send(json);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            resetList();
                            let json = JSON.parse(xhr.responseText);
                            if(json.length === 0){
                                let list = document.getElementsByClassName('list');
                                let li = document.createElement('li');
                                list.item(0).appendChild(li);
                                li.innerHTML = '<li class="list_class">未查找到数据</li>'
                            }
                            for (let i = 0; i < json.length; ++i) {
                                let list = document.getElementsByClassName('list');
                                let li = document.createElement('li');
                                list.item(0).appendChild(li);
                                li.innerHTML = '<li class="list_class"></li>'
                                let list_class = document.getElementsByClassName('list_class');
                                list_class.item(i).innerHTML = json[i];
                                list_class.item(i).addEventListener('click', function(){

                                        let xhr = new XMLHttpRequest();
                                        let json = JSON.stringify(list_class.item(i).innerHTML);
                                        // xhr.open("post", "http://localhost/xhk/user_discount/search", true);
                                        xhr.open("post", "http://mnsx.top/xhk/user_discount/search", true);
                                        xhr.send(json);
                                        xhr.onreadystatechange = function () {
                                            if (xhr.readyState === 4 && xhr.status === 200) {
                                                let json = JSON.parse(xhr.responseText);
                                                reset();
                                                showDiscount(json);
                                                input.item(0).style.display = 'block';
                                                dropDown.item(0).style.display = 'none';
                                                text.value = '';
                                                resetList();
                                            }
                                        }
                                })
                            }
                        }
                    }
                }, 500)
            }else{
                resetList();
                let list = document.getElementsByClassName('list');
                let li = document.createElement('li');
                list.item(0).appendChild(li);
                li.innerHTML = '<li class="list_class">请输入店名关键字</li>'
            }
        })

        //显示下拉框
        search_text.addEventListener('click', function(){
                if(text.value !== null) {
                    input.item(0).style.display = 'none';
                    dropDown.item(0).style.display = 'block';
                    text.focus();
                }
        })

        //禁止拖动


        //轮播图
        let first = document.querySelector('.rotation-map');
        let ul = first.children[0];
        let width = screen.width > 780 ? 780 : screen.width;
        let index = 0;
        let ol = first.children[1];
        let flag = false;
        // alert(width);
        let timer_focus = setInterval(function(){
           index++;
           let translateX = -index * width;
           ul.style.transition = 'all .3s';
           ul.style.transform ='translateX(' + translateX + 'px)';
        }, 2000);
        ul.addEventListener('transitionend', function(){
            if(index >= 3){
                index = 0;
                ul.style.transition = 'none';
                let translateX = -index * width;
                ul.style.transform ='translateX(' + translateX + 'px)';
            } else if(index < 0){
                index = 2;
                ul.style.transition = 'none';
                let translateX = -index * width;
                ul.style.transform ='translateX(' + translateX + 'px)';
            }
            ol.querySelector('.current').classList.remove('current');
            ol.children[index].classList.add('current');
        });
        let startX = 0;
        let moveX = 0;
        ul.addEventListener('touchstart', function(e){
            startX = e.targetTouches[0].pageX;
            clearInterval(timer_focus);
        });
        ul.addEventListener('touchmove', function(e){
            moveX = e.targetTouches[0].pageX - startX;
            let translateX = -index * width + moveX;
            ul.style.transition = 'none';
            ul.style.transform ='translateX(' + translateX + 'px)';
            e.preventDefault();
            flag = true;
        });
        ul.addEventListener('touchend', function(e){
            if(flag) {
                if (Math.abs(moveX) > 50) {
                    if (moveX > 0) {
                        index--;
                    } else {
                        index++;
                    }
                    let translateX = -index * width;
                    ul.style.transition = 'all .3s';
                    ul.style.transform = 'translateX(' + translateX + 'px)';
                } else {
                    let translateX = -index * width;
                    ul.style.transition = 'all .3s';
                    ul.style.transform = 'translateX(' + translateX + 'px)';
                }
                clearInterval(timer_focus);
                timer_focus = setInterval(function () {
                    index++;
                    let translateX = -index * width;
                    ul.style.transition = 'all .3s';
                    ul.style.transform = 'translateX(' + translateX + 'px)';
                }, 2000);
            }
        });

        //关闭下拉框
        let cha = document.getElementsByClassName('iconfont icon-cha');
        cha.item(0).addEventListener('click', function(){
            input.item(0).style.display = 'block';
            dropDown.item(0).style.display = 'none';
            text.value = '';
            resetList();
            reset();
            let xhr = new XMLHttpRequest();
            let json = JSON.stringify('');
            // xhr.open("post", "http://localhost/xhk/user_discount/search", true);
            xhr.open("post", "http://mnsx.top/xhk/user_discount/search", true);
            xhr.send(json);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    let json = JSON.parse(xhr.responseText);
                    reset();
                    showDiscount(json);
                    resetList();
                    let list = document.getElementsByClassName('list');
                    let li = document.createElement('li');
                    list.item(0).appendChild(li);
                    li.innerHTML = '<li class="list_class">请输入店名关键字</li>'
                }
            }
        })
    }
})

//通过名字获取cookie
function getCookie(cname)
{
    let cookies = document.cookie.split(";");
    let cookName = []
    let cook = [];
    for(let i = 0; i < cookies.length; ++i){
        let cookie = cookies[i].split("=");
        cookName[i] = cookie[0] ;
        cook[i] = cookie[1];
    }
    for(let i = 0; i < cookName.length; ++i){
        if(cookName[i] === cname){
            return cook[i];
        }
    }
    return '';
}

//优惠券信息展示
function showDiscount(json){
    let cookie1 = getCookie('curId');
    let discounts = document.getElementsByClassName('ticket');
    for(let i = 0; i < json.length; ++i) {
        let div = document.createElement('div');
        div.className = 'content';
        discounts.item(0).appendChild(div);
        div.innerHTML = '<div class="id" hidden="hidden"></div>' + ' <div class="discounts_ticket">\n' +
            ' <div class="fullminus"> \n' +
            '                            <div class="dollas">\n' +
            '                                <h1 id="money' + i + '"></h1>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '\n' +
            '                        <div class="name">\n' +
            '                            <div class="boosname" id="storeName' + i + '"><h1></h1></div>\n' +
            '                            <div class="elt">\n' +
            '                                <div class="first-elt"><h1>味道好</h1></div>\n' +
            '                                <div class="second-elt"><h1>分量足</h1></div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '\n' +
            '                        <div class="usediscount"  data-index="' + i + '">\n' +
            '                            <h1>去使用</h1>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '</div>';
        let storeName = document.getElementById('storeName' + i);
        let money = document.querySelector('#money' + i);
        let id = document.getElementsByClassName('id');
        id[i].innerHTML = json[i].id;
        storeName.innerText = json[i].storeName;
        money.innerHTML = '￥' + json[i].money;
    }
        var modalBox = document.querySelector('.modal-box');
        var useModal = document.getElementsByClassName('usediscount');
        var cover = document.querySelector('.cover');
        var closeCover = document.querySelector('.modal-box i');
        // 展示二维码
        for (let i = 0; i < useModal.length; ++i) {
            useModal.item(i).addEventListener('click', function () {
                // let index = this.getAttribute('data-index');
                let id = document.getElementsByClassName('id');
                let xhr = new XMLHttpRequest();
                xhr.open('GET', 'http://mnsx.top/xhk/qrcode/' + cookie1 + '/' + id[i].innerHTML); // 安全性不够高后期改进
                // xhr.open('GET', 'http://localhost/xhk/qrcode/' + cookie + '/' + id[i].innerHTML); // 安全性不够高后期改进
                xhr.send();
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
                        let qrCode = document.getElementsByClassName('qrCode');
                        qrCode.item(0).src = xhr.responseURL;
                    }
                }

                modalBox.style.display = 'block';
                cover.style.display = 'block';
            })
        }
        closeCover.addEventListener('click', function () {
            modalBox.style.display = 'none';
            cover.style.display = 'none';
            location.reload();
        })
}

// 刷新展示
function reset() {
    let elem = document.getElementsByClassName('ticket')[0];
    while (elem.hasChildNodes()) //当elem下还存在子节点时 循环继续
    {
        // elem.item(i).innerHTML = "";
        elem.removeChild(elem.firstChild);
    }
}

function resetList(){
    let elem = document.getElementsByClassName('list')[0];
    while (elem.hasChildNodes()) //当elem下还存在子节点时 循环继续
    {
        // elem.item(i).innerHTML = "";
        elem.removeChild(elem.firstChild);
    }
}

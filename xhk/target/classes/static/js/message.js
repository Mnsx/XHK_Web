window.addEventListener('load', function(){
    let xhr = new XMLHttpRequest();
    // xhr.open('Get', "http://localhost/xhk/user_discount/message");
    xhr.open('Get', "http://mnsx.top/xhk/user_discount/message");
    xhr.send();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let json = JSON.parse(xhr.responseText);
            let discounts = document.getElementsByClassName('messages');
            for (let i = 0; i < json.length; ++i) {
                let div = document.createElement('div');
                div.className = 'content2';
                discounts.item(0).appendChild(div);
                div.innerHTML = '<div class="message">\n' +
                    '                    <div class="top">\n' +
                    '                        <img src="img/tuoyuan.png">\n' +
                    '                        <a class="s"></a>\n' +
                    '                        <a class="time">14:30</a>\n' +
                    '                    </div>\n' +
                    '                    <div class="content">\n' +
                    ' 你使用了一张价值<span class="m"></>的优惠券 \n' +
                    '                    </div>\n' +
                    '                </div>';
                let t = document.getElementsByClassName('time');
                let s = document.getElementsByClassName('s');
                let m = document.getElementsByClassName('m');
                t[i].innerHTML = json[i].createTime;
                s[i].innerHTML = json[i].storeName;
                m[i].innerHTML = json[i].money;
            }
        }
    }
})
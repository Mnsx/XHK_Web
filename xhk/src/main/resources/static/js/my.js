window.addEventListener('load',function () {
    let cookie = getCookie('curId');
    //充值模态框
    // var Modal = document.querySelector('.modal-box');
    var recharge = document.getElementsByClassName('first');
    // var cover = document.querySelector('.cover');

    recharge.item(0).addEventListener('click', function(){
        alert('功能还未开发，请等待版本迭代！😭');
    })

    recharge.item(1).addEventListener('click', function(){
        alert('功能还未开发，请等待版本迭代！😭');
    })

    recharge.item(2).addEventListener('click', function(){
        alert('功能还未开发，请等待版本迭代！😭');
    })

    //点充值打开模态框
    recharge.item(3).addEventListener('click', function () {
        location.href='/xhk/add_money.html'
    })

    //个人信息功能
    recharge.item(4).addEventListener('click', function(){
        location.href='/xhk/personalInfo.html'
    })

    //退出登录功能
    recharge.item(5).addEventListener('click', function () {
        let b = confirm('确定退出登录吗？');
        if (b) {
            let xhr = new XMLHttpRequest();
            // xhr.open("Get", "http://localhost/xhk/exit");
            xhr.open("Get", "http://mnsx.top/xhk/exit");
            xhr.send();
            xhr.onreadystatechange = () => {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    location.reload();
                }
            }
        }
    })

    //联系客服
    recharge.item(6).addEventListener('click', function(){
        location.href="tel:13648061898";
    })

    //展示用户余额
    //从cookie中获取当前用户id
    let xhr1 = new XMLHttpRequest();
    xhr1.open('GET', 'http://mnsx.top/xhk/user/' + cookie);
    // xhr1.open('GET', 'http://localhost/xhk/user/' + cookie);
    xhr1.send();
    xhr1.onreadystatechange = function () {
        if (xhr1.readyState === 4 && xhr1.status >= 200 && xhr1.status < 300) {
            let balance = document.getElementById('balance');
            let json = JSON.parse(xhr1.responseText);
            balance.innerText = json.balance;
        }
    }

    //获取用户名称
    let name = document.getElementsByClassName('user');
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "http://mnsx.top/xhk/user/name")
    // xhr.open("GET", "http://localhost/xhk/user/name")
    xhr.send();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let p2 = document.createElement('p');
            p2.innerText = "用户名：";
            let p = document.createElement('p');
            p.innerText = xhr.responseText;
            name.item(0).appendChild(p2);
            name.item(0).appendChild(p);
        }
    }

    //点击叉关闭模态框
    /*let close = document.getElementsByClassName("iconfont icon-guanbi");
    close.item(0).addEventListener('click', function () {
        Modal.style.display = 'none';
        cover.style.display = 'none';
    })*/



    //注册码输入验证功能
/*    let registerBnt = document.getElementById("registerBnt");
    registerBnt.addEventListener('click', function(){
        let rCode = document.getElementById("registerCode");
        let xhr = new XMLHttpRequest();
        let value = rCode.value;
        let json = JSON.stringify(value);
        xhr.open("delete", "http://localhost/xhk/key/", true);
        // xhr.open("delete", "http://mnsx.top/xhk/key/", true);
        xhr.send(json);
        xhr.onreadystatechange = function(){
            if(xhr.readyState === 4 && xhr.status === 200){
                if(xhr.responseText === "true"){
                    let mbox = document.getElementsByClassName("modal-box").item(0);
                    let cover = document.getElementsByClassName("cover").item(0);
                    mbox.style.display = 'none';
                    cover.style.display = 'none';
                    rCode.value = "";
                    alert("充值成功😁，请前往首页查看！");
                    location.reload();
                }else{
                    alert("使用失败！");
                    // rCode.value = "";
                }
            }
        }
    })*/


})

//通过名字获取cookie
function getCookie(cname) {
    let cookies = document.cookie.split(";");
    let cookName = []
    let cook = [];
    for (let i = 0; i < cookies.length; ++i) {
        let cookie = cookies[i].split("=");
        cookName[i] = cookie[0];
        cook[i] = cookie[1];
    }
    for (let i = 0; i < cookName.length; ++i) {
        if (cookName[i] === cname) {
            return cook[i];
        }
    }
    return '';
}

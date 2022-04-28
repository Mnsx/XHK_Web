window.addEventListener('load',function () {
    //cookie记住密码
    let cookies = document.cookie.split('=');
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://mnsx.top/xhk/login/' + cookies[1]);
    // xhr.open('GET', 'http://localhost/xhk/login/' + cookies[1]);
    xhr.send();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300){
            let json = JSON.parse(xhr.responseText);
            let username = document.querySelector('#username');
            let password = document.querySelector('#password');
            let remember = document.querySelector('#remember');
            let noChecked = document.querySelector('#noChecked');
            let checked = document.querySelector('#checked');
            username.value = json.username;
            password.value = "123456";
            remember.checked = 'checked';
            checked.style.display='block';
            noChecked.style.display='none';
            username.addEventListener('change', function(){
                remember.checked = '';
                password.value = '';
                checked.style.display='none';
                noChecked.style.display='block';
            })
            password.addEventListener('change', function(){
                remember.checked = '';
                checked.style.display='none';
                noChecked.style.display='block';
            })
        }
    }

    //登录操作
    let login = document.querySelector('#login');
    login.addEventListener('click', function(){
        let username = document.querySelector('#username');
        let password = document.querySelector('#password');
        let remember = document.querySelector('#remember');
        let json = [];
        let user = {};
        user.username = username.value;
        user.password = password.value;
        json.push(user);
        if(remember.checked === "checked"){
            json.push(remember.value);
        }
        json.push("0");
        let str = JSON.stringify(json);
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "http://mnsx.top/xhk/user/login", true)
        // xhr.open("POST", "http://localhost/xhk/user/login", true)
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(str);
        xhr.onreadystatechange=function(){
            if(xhr.readyState === 4 && xhr.status === 200){
                if(xhr.responseText === "true"){
                    //查询当前用户id
                    let test;
                    let xhr1 = new XMLHttpRequest();
                    xhr1.open('GET', 'http://mnsx.top/xhk/user/id');
                    // xhr1.open('GET', 'http://localhost/xhk/user/id');
                    xhr1.send();
                    xhr1.onreadystatechange = function(){
                        if(xhr1.readyState === 4 && xhr1.status >= 200 && xhr1.status < 300){
                            test = xhr1.responseText;
                            document.cookie="curId=" + test;
                            location.href="/xhk/first.html";
                        }
                    }
                }else if(xhr.responseText === "false"){
                    alert("账号不存在或者密码错误，请重新输入!");
                }
                reset();
            }
        }
    })


    // 记住密码
    var iNoselected = document.querySelector('.login .login-content .mg  .mg-child .rember  .icon-fuxuankuang');
    var iSelected = document.querySelector('.login .login-content .mg  .mg-child .rember .icon-duoxuankuangxuanzhong ');

    iNoselected.addEventListener('click',function () {
        this.style.display = 'none';
        iSelected.style.display = 'block';
    })
    iSelected.addEventListener('click',function () {
        this.style.display = 'none';
        iNoselected.style.display = 'block';
    })

    // 复选框处理
    let noChecked = document.querySelector('#noChecked');
    let checked = document.querySelector('#checked');
    let remember = document.querySelector('#remember');
    noChecked.addEventListener('click', function(){
        remember.checked = 'checked';
    })
    checked.addEventListener('click', function(){
        remember.checked = '';
    })
})
window.addEventListener('load', function () {
    var iNoSelect = document.querySelector('.content .agent-Negotiate .NoSelected');
    var iSelect = document.querySelector('.content .agent-Negotiate .Selected');
    let register = document.getElementById('register');
    let passwordCheck = document.getElementsByClassName('password-check');

    iNoSelect.addEventListener('click', function () {
        this.style.display = 'none';
        iSelect.style.display = 'block';
        if((!(passwordCheck.item(0).style.display === 'block')) && (!(passwordCheck.item(1).style.display === 'block')) && (allCheck.style.display === 'none')){
            register.style.backgroundColor = '#6fb8e5';
            register.disabled="";
        }
    })
    iSelect.addEventListener('click', function () {
            this.style.display = 'none';
            iNoSelect.style.display = 'block';
            register.style.backgroundColor = '#cecece';
            register.disabled="disabled";
    })

    var rePassword = document.querySelector('#re-password');
    var password = document.querySelector('#password');
    let allCheck = document.getElementById('all_check');
    let username = document.getElementById('username');
    let phoneNum = document.getElementById('phoneNum');
    let ipt = document.getElementsByClassName('ipt');
    for(let i = 0; i < ipt.length; ++i){
        let timer3 = null;
        ipt.item(i).addEventListener('input', function(){
            clearTimeout(timer3);
            timer3 = setTimeout(function(){
                if(username.value === "" || password.value === '' || phoneNum.value === ''){
                    allCheck.style.display = 'block';
                    register.style.backgroundColor = '#cecece';
                    register.disabled="disabled";
                }else {
                    allCheck.style.display = 'none';
                    if(passwordCheck.item(0).style.display === 'none' && passwordCheck.item(1).style.display === 'none' && iSelect.style.display === 'block'){
                        register.style.backgroundColor = '#6fb8e5';
                        register.disabled = "";
                    }
                }
            }, 100)
        })
    }

    var changeIMG = document.getElementById('changeImg');
    changeIMG.addEventListener('click',function () {
    })

    var loginButton = document.querySelector('.btn1');
    loginButton.addEventListener('click',function () {
    })

    let timer1 = null;
    let timer2 = null;
    rePassword.addEventListener("input", function(){
        clearTimeout(timer1);
        timer1 = setTimeout(function(){
            timerFun(password, rePassword, passwordCheck, register, iSelect, allCheck);
        }, 100);
    })
    password.addEventListener("input", function(){
        timer2 = setTimeout(function(){
            timerFun(password, rePassword, passwordCheck, register, iSelect, allCheck);
        }, 100);
    })


    //校惠卡协议
    let registerBody = document.querySelector('.registerBody');
    let xhkBook = document.querySelector('.xhkBook');
    let xhkAgreeBook = document.querySelector('.agent-Negotiate .second-h1');
    let closeAgressBook =document.querySelector('.xhkBook div');

    xhkAgreeBook.addEventListener('click',function () {
        registerBody.style.display = 'none';
        xhkBook.style.display = 'block';
    })

    closeAgressBook.addEventListener('click',function () {
        xhkBook.style.display = 'none';
        registerBody.style.display = 'block';
    })

})

function timerFun(password, rePassword, passwordCheck, register, iSelect, allCheck){
    if(password.value !== rePassword.value){
        passwordCheck.item(0).style.display = 'block';
        passwordCheck.item(1).style.display = 'block';
        register.style.backgroundColor = '#cecece';
        register.disabled="disabled";
    }else {
        passwordCheck.item(0).style.display = 'none';
        passwordCheck.item(1).style.display = 'none';
        if(iSelect.style.display === 'block' && allCheck.style.display === 'none') {
            register.style.backgroundColor = '#6fb8e5';
            register.disabled = "";
        }
    }
}
window.addEventListener('load', function(){
    //注册码输入验证功能
    let registerBnt = document.getElementById("registerBnt");
    registerBnt.addEventListener('click', function(){
        let rCode = document.getElementById("registerCode");
        let xhr = new XMLHttpRequest();
        let value = rCode.value;
        let json = JSON.stringify(value);
        // xhr.open("delete", "http://localhost/xhk/key/", true);
        xhr.open("delete", "http://mnsx.top/xhk/key/", true);
        xhr.send(json);
        xhr.onreadystatechange = function(){
            if(xhr.readyState === 4 && xhr.status === 200){
                if(xhr.responseText === "true"){
                    // let mbox = document.getElementsByClassName("modal-box").item(0);
                    // let cover = document.getElementsByClassName("cover").item(0);
                    // mbox.style.display = 'none';
                    // cover.style.display = 'none';
                    rCode.value = "";
                    alert("充值成功😁，请前往首页查看！");
                    location.reload();
                }else{
                    alert("使用失败！");
                    // rCode.value = "";
                }
            }
        }
    })
})
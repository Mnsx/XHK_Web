<template>
  <div class="page">
  <el-container>
      <el-aside width="300px" style="margin-right: 50px;">
        <div>
          <ul>
            <li @click="chooseUser(item)" v-for="(item, index) in userList" :key="item.uid" class="userList">
              <el-avatar :src="item.avatar" shape="square" :size="50"></el-avatar>
              <div style="font-width: border;font-size: 18px;margin-left: 5px;">{{item.username}}</div>
              <div style="font-size: 12px;color: limegreen; margin-left: 5px" v-if="item.username === chatUser">聊天中...</div>
              <el-divider v-if="index + 1 !== userList.length"></el-divider>
            </li>
          </ul>
        </div>
      </el-aside>
      <el-main>
        <div class="main" style="height: 80vh;width: 80vh">
          <div class="text" style="height: 60vh;width: 80vh" v-html="content">
          </div>
          <div class="input" style="height: 20vh;width: 80vh">
            <textarea class="area" v-model="text" rows="7"></textarea>
            <el-button plain class="button" @click="send()">发送</el-button>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
let socket;
export default {
  name: "Message",
  data() {
    return {
      userList: [],
      user: {},
      users: [],
      chatUser: '',
      text: "",
      messages: [],
      content: '',
      avatar: window.localStorage.getItem("avatar") == null ? 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png' : window.localStorage.getItem("avatar")
    }
  },
  created() {
    this.init()
    this.showData()
  },
  methods: {
    chooseUser(item) {
      this.chatUser = item.username
      this.content = '';
      this.$axios.get('http://localhost:8081/messages/get_message/' + window.localStorage.getItem("username") + "/" + item.username, {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        },
      }).then((response) => {
        console.log(response);
        if (response.data.status === 200) {
          for (let i = 0; i < response.data.data.length; ++i){
            this.createContent(item.username, null, response.data.data[i]);
          }
        } else {
          this.$message({
            showClose: true,
            message: response.data.message,
            type: 'error'
          });
        }
      })
    },
    showData() {
      this.$axios.get('http://localhost:8081/users/get_user_list', {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        },
      }).then((response) => {
        console.log(response);
        if (response.data.state === 200) {
          for (let i = 0; i < response.data.data.length; ++i){
            if (response.data.data[i].username !== window.localStorage.getItem("username")) {
              this.userList.push(response.data.data[i]);
            }
          }
        } else {
          this.$message({
            showClose: true,
            message: response.data.message,
            type: 'error'
          });
        }
      })
    },
    send() {
      if (!this.chatUser) {
        this.$message({type: 'warning', message: "请选择聊天对象"})
        return;
      }
      if (!this.text) {
        this.$message({type: 'warning', message: "请输入内容"})
      } else {
        if (typeof (WebSocket) == "undefined") {
          console.log("您的浏览器不支持WebSocket");
        } else {
          console.log("您的浏览器支持WebSocket");

          let message = {from: this.user.username, to: this.chatUser, text: this.text}
          socket.send(JSON.stringify(message));
          this.messages.push({user: this.user.username, text: this.text})
          this.createContent(null, this.user.username, this.text)
          this.text = '';
        }
      }
    },
    createContent(remoteUser, nowUser, text) {  // 这个方法是用来将 json的聊天消息数据转换成 html的。
      let html
      // 当前用户消息
      if (nowUser) { // nowUser 表示是否显示当前用户发送的聊天消息，绿色气泡
        html = "<div class=\"el-row\" style=\"padding: 5px 0\">\n" +
          "  <div class=\"el-col el-col-22\" style=\"text-align: right; padding-right: 10px\">\n" +
          "    <div class=\"tip left\">" + text + "</div>\n" +
          "  </div>\n" +
          "  <div class=\"el-col el-col-2\">\n" +
          "  <span class=\"el-avatar el-avatar--circle\" style=\"height: 40px; width: 40px; line-height: 40px;\">\n" +
          "    <img src=" + this.avatar + " style=\"object-fit: cover;\">\n" +
          "  </span>\n" +
          "  </div>\n" +
          "</div>";
      } else if (remoteUser) {   // remoteUser表示远程用户聊天消息，蓝色的气泡
        html = "<div class=\"el-row\" style=\"padding: 5px 0\">\n" +
          "  <div class=\"el-col el-col-2\" style=\"text-align: right\">\n" +
          "  <span class=\"el-avatar el-avatar--circle\" style=\"height: 40px; width: 40px; line-height: 40px;\">\n" +
          "    <img src=\"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png\" style=\"object-fit: cover;\">\n" +
          "  </span>\n" +
          "  </div>\n" +
          "  <div class=\"el-col el-col-22\" style=\"text-align: left; padding-left: 10px\">\n" +
          "    <div class=\"tip right\">" + text + "</div>\n" +
          "  </div>\n" +
          "</div>";
      }
      console.log(html)
      this.content += html;
    },
    init() {
      this.user.username = window.localStorage.getItem("username");
      let username = this.user.username;
      let _this = this;
      if (typeof (WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
      } else {
        console.log("您的浏览器支持WebSocket");
        let socketUrl = "ws://localhost:8081/messages/" + username;
        if (socket != null) {
          socket.close();
          socket = null;
        }

        socket = new WebSocket(socketUrl);

        socket.onopen = function () {
          console.log("websocket 已打开")
        }

        socket.onmessage = function (msg) {
          console.log("收到数据====" + msg.data);
          let data = JSON.parse(msg.data)
          if (data.users) {
            _this.users = data.users.filter(user => user.username !== username)
          } else {
            if (data.from === _this.chatUser) {
              _this.messages.push(data)
              _this.createContent(data.from, null, data.text)
            }
          }
        };

        socket.onclose = function () {
          console.log("websocket已关闭")
        };

        socket.onerror = function () {
          console.log("websocket发生了错误");
        };
      }
    }
  }
}
</script>

<style scoped>


  .page{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
  }


  ::-webkit-scrollbar-track
  {
    background: rgba(0,0,0,.1);
    border-radius: 0;
  }

  ::-webkit-scrollbar
  {
    -webkit-appearance: none;
    width: 8px;
    height: 10px;
  }

  ::-webkit-scrollbar-thumb
  {
    cursor: pointer;
    border-radius: 5px;
    background: #99a9bf;
    transition: color .2s ease;
    /*background-image: linear-gradient(aquamarine,#fd7286,lightgreen);*/
  }

  li, ul {
    list-style: none;
  }

  ul {
    /*border-right: 1px solid #99a9bf;*/
    box-sizing: border-box;
    height: 80vh;
  }

  li {
    padding: 0;
  }

  .userList {
    display: flex;
    /*justify-content: center;*/
    flex-wrap: wrap;
  }

  .text {
    font-size: 14px;
  }

  .item {
    padding: 18px 0;
  }

  .box-card {
    width: 480px;
  }

  .main {
    /*border: 1px solid #99a9bf;*/
  }

  .input {
    border: 1px solid #bbc5d3;
  }

  .inputDeep>>>.el-textarea__inner {
    border: 0;
    resize: none;
  }

  .area {
    margin-bottom: 10px;
    width: 95%;
    margin-left: 2%;
    border: none;
    resize: none;
    cursor: pointer;
    font-size: 15px;
    outline: none;
  }

  .button {
    float: right;
    margin-right: 20px;
    font-weight: bolder;
    padding-left: 50px;
    padding-right: 50px;
  }

  .button:hover {
    color: #7889a2;
    border-color: #7889a2;
  }

  .tip {
    color: white;
    text-align: center;
    border-radius: 10px;
    font-family: sans-serif;
    padding: 10px;
    width:auto;
    display:inline-block !important;
    display:inline;
  }
  .right {
    background-color: deepskyblue;
  }
  .left {
    background-color: forestgreen;
  }
</style>

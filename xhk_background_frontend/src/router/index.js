import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login'
import Home from '../views/Home'
import User from '../views/home/User'
import Main from '../views/home/Main'
import Store from "../views/home/Store";
import Product from "../views/home/Product";
import Package from "../views/home/Package";
import Ticket from "../views/home/Ticket";
import Code from "../views/home/Code";
import Advertising from "../views/Advertising";
import First from "../views/simulation/First";
import Message from "../views/simulation/Message"
import My from "../views/simulation/My";
import Simulation from "../views/Simulation";
import Info from "../views/simulation/my/Info";
import ModifyInfo from "../views/simulation/my/ModifyInfo";
import ModifyPasswd from "../views/simulation/my/ModifyPasswd";
import Recharge from "../views/simulation/my/Recharge";
import ModifyAvatar from "../views/simulation/my/ModifyAvatar";

Vue.use(Router)

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      children: [
        {
          path: '/',
          name: 'Main',
          component: Main,
        },
        {
          path: '/user',
          name: 'User',
          component: User,
        },
        {
          path: '/store',
          name: 'Store',
          component: Store
        },
        {
          path: '/product',
          name: 'Product',
          component: Product
        },
        {
          path: '/package',
          name: 'Package',
          component: Package
        },
        {
          path: '/ticket',
          name: 'Ticket',
          component: Ticket
        },
        {
          path: '/code',
          name: 'Code',
          component: Code
        },
        {
          path: '/advertising',
          name: 'Advertising',
          component: Advertising
        },
      ]
    },
    {
      path: '/simulation',
      name: 'Simulation',
      component: Simulation,
      children: [
        {
          path: '/first',
          name: 'First',
          component: First
        },
        {
          path: '/message',
          name: 'Message',
          component: Message
        },
        {
          path: '/my',
          name: 'My',
          component: My,
          children: [
            {
              path: '/info',
              name: '/info',
              component: Info
            },
            {
              path: '/modifyInfo',
              name: '/modifyInfo',
              component: ModifyInfo
            },
            {
              path: '/modifyPasswd',
              name: '/modifyPasswd',
              component: ModifyPasswd
            },
            {
              path: '/recharge',
              name: '/recharge',
              component: Recharge
            },
            {
              path: '/modifyAvatar',
              name: '/modifyAvatar',
              component: ModifyAvatar
            }
          ]
        }
      ]
    },
  ]
})

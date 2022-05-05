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
        }
      ]
    },
  ]
})

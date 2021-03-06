## 项目简介

这个项目是一个变种的电商项目，主要是通过向用户售卖兑换码，用户通过兑换码在指定未知兑换优惠券和钱包余额，核心功能支持二维码扫码使用优惠券，在线聊天功能，各类用户信息修改功能。这个项目兼顾了前台展示的同时，也搭建了一个后台管理系统，能够使管理员实时监控项目的情况变化。并且能够通过非常简便的操作，完成前台展示数据的更改。项目通过对于登录者的角色权限进行验证判断用户是否拥有进入后台的权力。并且为了适应后期的分布式扩展，本项目使用redis，这种NoSQL技术，加上JWT来实现用户授权和认证的功能。模拟用户量加大的情况，使用RabbitMQ对项目的重要逻辑环节进行队列化，减小服务器宕机的情况，项目采用前后端分离的架构思想，减少项目的耦合，并且使用swaggerAPI技术，减小前后端交互难度，使得项目开发难度大幅度降低。使用了WebSocket加redis实现在线聊天的功能，并且当用户未上线的情况下也能够为用户保存聊天数据。

## 技术选型

* 前端

  HTML、CSS、JavaScript、Ajax、Axios、Vue2、ElementUI等

* 后端

  Spring、SpringMVC、MyBatis、MyBatisPlus（略用）、JWT、Redis、RabbitMQ、SpringBoot、Swagger、MySQL等

* 开发环境

  开发和测试环境——Win11

  中间件运行环境——CentOS7.6

## 项目运行环境

* JDK 8

* Win11

* IDEA 2021

* CentOS 7.6

* RabbitMQ 3.8.29

* Redis 7.0.0

  ...

## 创意介绍

* 为锦江学院学生及周围群众提供更大的优惠力度，花更少的钱，得到更加优质的产品与服务

* 能够在后台通过建议的操作进行数据修改，主要针对广告轮播图设计，可以直接通过拖图片，来删除和替换轮播图中的广告
* 直接通过二维码使用优惠券，能够通过二维码扫描软件进行使用
* 提供聊天功能，能够很方便的和客服沟通所遇到的问题，或者能够和自己的朋友分享美食体验

## 设计思路

我们采用前后端分离的开发模式，通过http传送json来进行前后端沟通。后端采用经典的MVC架构进行开发。前后端通过swaggerAPI进行协作，通过Git进行代码汇总

前后端分离，前端界面通过router跳转，通过Axios发送请求到后端，后端通过SpringMVC接受请求，获取请求参数，调用Service方法，通过操作Dao去修改数据获取数据等功能。当用户登录后，后端会生成一个tocken发送给前端，前端每次发送请求时需要在请求头中携带tocken数据，后端会通过拦截器拦截请求，判断请求头中的tocken数据是否正确，正确则放行，不正确则返回到登录界面

## 项目功能介绍

* 登录功能

  设计账号密码验证码验证功能，验证码能够刷新，登录成功后会返回一个tocken，作为身份判定

* 注册功能

  常规注册功能

* 主页

  组件+自改配置-》轮播图，能够轮播广告，并且能够通过后台管理系统进行修改

  展示用户所拥有的优惠券，并且可以展示店家的信息，使用优惠券能够返回一个二维码，店家通过扫描二维码能够是消费这张优惠券

* 消息

  聊天功能的实现，展示所有的用户，点击用户头像能够与用户进行聊天，将聊天的消息存入redis中，对方上线时点击用户头像就能显示消息记录，聊天功能通过WebSocket实现

* 我的

  由五个功能模块组成：

  1. 用户信息的展示，能够直观的查看用户的信息

  2. 用户信息的修改，方便用户随时修改自己的个人资料

  3. 密码修改，提供给用户一个修改自己密码的机会
  4. 头像修改，通过传送multipartFile类型的文件去修改用户的头像，用户能够从自己的本地资源中选择想要修改
  5. 注册码使用，充值功能，当数据库中的注册码功能用完，会自动生成100个放回数据库中

* 后台

  对于用户、商家、优惠券、用户券包、轮播图图片进行管理的程序

## 项目主要难点

1. 使用multipartyFile去作为SpringMVC图片文件的载体时，需要将form改为multipart/form-data
2. 在使用WebSocket和Redis配合时，因为WebSocket加载时间是在SpringContext之前，所以无法通过@Autowired去自动装配，于是手写了一个获取Bean的工具类
3. 实时聊天功能如果只是WebSocket的话，那么只能在双方都点击对方的情况下进行通话，明显没有达到我们的预期要求，所以我选择当用户发送消息后，将其发送的消息存储到redis中，当另一个用户上线时点击该用户信息就能够获取到消息内容
4. 图片路径无法匹配本地资源，需要编写图片映射的配置类，进行路径的转换
5. 新版的Swagger和SpringBoot冲突，需要修改配置文件进行修改

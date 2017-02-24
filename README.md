   RxJava+Retrofit+okhttp+glide+mvp+butterknife实现的简易开源项目
====

####首先介绍下该项目用到的技术点和亮点
1. Rxjava+Retrofit+okhttp搭建的网络框架
2. mvp设计模式
3. butterknife注解方式查找控件，减少findViewById冗余代码
4. Glide图片加载框架
5. Recyclerview结合SwipeRefreshLayout实现列表和下拉刷新
6. 封装了ListView和GridView，RecycleView的通用数据适配器工具类

####需要添加的库文件
    compile 'com.jakewharton:butterknife:5.1.1'
    compile 'com.android.support:recyclerview-v7:23.1.1'
    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'jp.wasabeef:glide-transformations:2.0.1'
    compile 'com.android.support:design:23.3.0'

    compile 'io.reactivex:rxjava:+'
    compile 'io.reactivex:rxandroid:1.2.0'
    compile 'com.squareup.retrofit2:retrofit:+'
    compile 'com.squareup.retrofit2:converter-gson:+'
    compile 'com.squareup.retrofit2:adapter-rxjava:+'
    compile 'com.android.support:cardview-v7:23.3.0'
    
###Rxjava+Retrofit+okhttp搭建的网络框架
    项目里面有一个HttpUtils工具类 调用requestNetByPost(Observable observable, final OnResultListener resultListener)方法即可
    参数分别是Observable观察者对象和创建一个回调监听接口，实现网络数据加载成功和失败的方法
    
###mvp设计模式 
    1.Model:业务逻辑和实体模型
    2.View:View通常来说是由Activity实现的，它会包含一个Presenter的引用，View要做的就只是在每次有接口
    调用的时候（比如按钮点击后）调用Presenter方法。
    3.主要作为沟通View和Model的桥梁，它从Model层检索数据后，返回给View层，但是不像MVC结构，因为它也
    可以决定与View层的交互操作。
    
###ListView和GridView，RecycleView的通用数据适配器工具类封装 （具体请看源代码）

###glide实现的图片加载框架(具体请看源代码)

####[点击进入我的博客](http://blog.csdn.net/rjgcszlc "尽人事看天意")

###效果图展示
![](https://github.com/zlc921022/LookMvpProject/raw/master/image/beauty.jpg)
###联系方式
    QQ:1509815887
    Email:zlc921022@163.com
###感谢
    如果觉得好就给我右上角star点一下吧, 如果觉得不好 欢迎批评指点 Thank you very much!

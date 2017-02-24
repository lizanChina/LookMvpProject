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
<tab>compile 'com.jakewharton:butterknife:5.1.1'<br>
<tab>compile 'com.android.support:recyclerview-v7:23.1.1'<br>
<tab>compile 'com.github.bumptech.glide:glide:3.7.0'<br>
<tab>compile 'jp.wasabeef:glide-transformations:2.0.1'<br>
<tab>compile 'com.android.support:design:23.3.0'<br>

<tab>compile 'io.reactivex:rxjava:+'<br>
<tab>compile 'io.reactivex:rxandroid:1.2.0'<br>
<tab>compile 'com.squareup.retrofit2:retrofit:+'<br>
<tab>compile 'com.squareup.retrofit2:converter-gson:+'<br>
<tab>compile 'com.squareup.retrofit2:adapter-rxjava:+'<br>
<tab>compile 'com.android.support:cardview-v7:23.3.0'<br>

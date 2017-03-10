# EasyMvpBuilder
     这是一个基于MVP模式开发的简单的App，MVP也是目前主流的框架设计的方式之一。

接下来我会大致介绍一下工程的结构：

## easy_mvp

![](https://github.com/AlexMagic/SimpleResources/blob/master/EasyMvpBuilder/easy_mvp.png)

这个Module下的包是框架的核心部分:

```
- base : View和Presenter的基本接口
- interactor : 交互器集合，用来处理网络交互的过程
- model : 基本模型
- net : 处理网络请求
- utils :工具包 
```

以下是该框架用到的第三方的库

```
compile 'com.google.code.gson:gson:2.3.1'
compile 'com.squareup.okhttp:okhttp:2.5.0'
compile 'io.reactivex:rxjava:1.0.14'
compile 'io.reactivex:rxandroid:1.0.1'
```

## 详情
如果想了解更多，可以[点击这里](https://alexmagic.github.io/)来阅读在博客中的详细介绍。

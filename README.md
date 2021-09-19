
这个 Demo 主要演示插桩式实现 Android 插件化

```
java.lang.NullPointerException: Attempt to invoke virtual method 'void android.app.Application.dispatchActivityStarted(android.app.Activity)' on a null object reference
        at android.app.Activity.dispatchActivityStarted(Activity.java:1347)
        at android.app.Activity.onStart(Activity.java:1832)
        at com.dev.taopiaopiao.BaseActivity.onStart(BaseActivity.kt:21)
        at com.dev.ProxyActivity.onStart(ProxyActivity.kt:33)
```

#### Activity 插件化

- 宿主 App 可以加载插件 App 中类

- 宿主 App 可以加载插件 App 中的资源

- 宿主 App 可以加载插件中的 Activity 

#### 动态加载技术

在应用程序运行时，动态加载一些程序中原本不存在的可执行文件，可执行文件总的来说分为两个

- 动态链接库 so 文件

- dex 相关文件（jar/apk）

## proguard base file ##
-basedirectory proguard-pro

############### framework ###############
-include proguard-normal.pro
-include proguard-fastjson.pro
-include proguard-glide.pro
-include proguard-okhttp3.pro
-include proguard-okio.pro
-include proguard-retrofit2.pro
-include proguard-rxjava.pro
-include proguard-baidumtj.pro
############### baidumap ###############
-include proguard-baidumap.pro

#################################### other ####################################
#不要混淆<NetworkModel>的所有属性与方法
-keepclasseswithmembers class com.fast.framework.network.NetworkModel {
    <fields>;
    <methods>;
}
#不要混淆<BaseBean>的所有属性与方法
-keepclasseswithmembers class com.fast.framework.base.BaseBean{
    <fields>;
    <methods>;
}
#不要混淆<BaseBean>所有子类的属性与方法
-keepclasseswithmembers class * extends com.fast.framework.base.BaseBean{
    <fields>;
    <methods>;
}
#不混淆<com.ff.example.data.bean>包下的类
-keep class com.ff.example.data.bean.** {*;}
###############################################################################
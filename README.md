### 启动顺序
先启动eureka-service，再启动picture-service和user-service
### 各模块功能
1. common 共用的组件
2. eureka-service 微服务注册中心，所有微服务启动后都要在此处注册自己
3. picture-service 图片管理模块
4. user-service 用户模块
5. parent 仅用于聚合maven模块
### 注意
1. picture-service和user-service中仅配置好了数据源，没有其他东西
2. 本项目构建参考https://github.com/quhailong/NetworkDisk_Storage 预计模块内部分包结构模仿该项目
    
 
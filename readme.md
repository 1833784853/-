# 房屋租赁系统（后端）

## 1. 系统架构

> 后端：采用SSM框架（spring、springmvc、mybatis）通过Maven进行构建，如果没有基础看起来会比较费力。

> 前端：采用Vue、Element-ui、axios架构进行页面的构建、实现了前后端分离，耦合性更低。

- Spring：管理整个项目所需要的类
- SpringMVC：开放对外提供功能接口
- Mybatis：数据库框架

## 2. 项目目录

```
java
│  └─com
│      └─imcode
│          ├─common
│          │  ├─controller
│          │  ├─exception
│          │  ├─model
│          │  └─service
│          └─rls
│              ├─collect
│              │  ├─controller
│              │  ├─mapper
│              │  ├─model
│              │  └─service
│              ├─orders
│              │  ├─controller
│              │  ├─mapper
│              │  ├─model
│              │  └─service
│              │      └─impl
│              ├─roomlease
│              │  ├─controller
│              │  ├─mapper
│              │  ├─model
│              │  └─service
│              ├─roomsource
│              │  ├─controller
│              │  ├─mapper
│              │  ├─model
│              │  └─service
│              │      └─impl
│              ├─user
│              │  ├─controller
│              │  ├─mapper
│              │  ├─model
│              │  └─service
│              │      └─impl
│              └─userinfo
│                  ├─controller
│                  ├─mapper
│                  ├─model
│                  └─service
├─resources
│  └─mappers
│      └─rls
└─webapp
    ├─css
    ├─fonts
    ├─img
    ├─js
    ├─static
    │  ├─css
    │  └─js
    └─WEB-INF
```

- common：这个是一个公共的模块，用来提供项目需要的类（model）和接口（controller）和业务方法（service）。

- resources：资源目录，主要放置spring和springmvc、mybatis的配置文件。
  - mappers：每个模块的mapper文件
- rls：放置项目各个模块的代码，如果有新模块追加即可
  - user：用户的登录、注册、权限分配模块
  - roomsource：房源信息模块
  - roomlease：租赁及合同信息模块
  - orders：其他模块（轮播图管理、房源信息新闻发布、房源新闻管理）
  - collect：收租模块
- webapp：放置前端编译好后的web资源页面

## 3. API接口目录

### 3.1 用户模块

#### 3.1.1 用户普通登录




# 毕业设计:吸烟检测项目 Java后端

## 技术栈:
- Vue.js
- FastApi
- SpringBoot/AutumnFramework(可以试试)
- Redis
- Mysql
- 
## 说明:
- 要是有时间会迁移到AutumnFramework
## 更新日志
2024/3/12
- 增加了部分鉴权组件,一开始想用拦截器鉴权后通过Session像Controller传参,但是前端不传递Cookie,开启之后跨域又不能用*得指定跨域范围,而且每次的JsessionId都不一样导致spring给的sessionid也不一样,依然拿不到数据,于是想着自建一个StringPair类在拦截器中手动像Controller注入参数但是貌似无法实现,剩下只有Aop一条路但是改代码太麻烦最终选择写一个工具类二次解析Jwt

2024/3/14
- 辖区相关的接口补全,swagger可以正常用了

```mermaid
sequenceDiagram
    participant 管理员 as 管理员
    participant 控制器 as 控制层
    participant 服务 as 业务层
    participant JWT工具 as 鉴权组件
    participant 辖区映射器 as 映射层

    管理员->>控制器: GET /updateTerritoryChange
    控制器->>JWT工具: parseJwt(request)
    JWT工具-->>控制器: Claims
    控制器->>服务: updateTerritoryChange(territoryReviewResultDto)
    alt 审批结果为同意
        服务->>辖区映射器: updateTerritoryChange(territoryReviewResultDto)
        辖区映射器-->>服务: 更新结果
        服务->>辖区映射器: addUserTerritory(territoryReviewResultDto)
        辖区映射器-->>服务: 添加结果
        alt 更新和添加均成功
            服务-->>控制器: 返回1
        else 更新或添加失败
            服务-->>控制器: 返回0
        end
    else 审批结果为拒绝
        服务->>辖区映射器: updateTerritoryChange(territoryReviewResultDto)
        辖区映射器-->>服务: 更新结果
    end
    控制器-->>管理员: Result(操作结果)
```

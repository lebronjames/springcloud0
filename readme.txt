springboot项目

1、 测试demo接口
http://localhost:8080/hello
http://localhost:8080/hello/info?name=123
http://localhost:8080/hello/list
http://localhost:8080/hello/listmap

2、 在 SpringBootApplication 上使用@ServletComponentScan 注解后，
Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 
注解自动注册，无需其他代码。

3、 监听器的小示例：利用HttpSessionListener和HttpServletContextListener实现定时销毁HttpSession
http://www.cnblogs.com/youwillsee/p/6659079.html

4、 静态资源配置，放在resources/static的路径下，如：路径下test.xlsx文件，
映射地址：http://localhost:8080/test.xlsx

5、 SLF日志集成
配置logback.xml文件；Logger调用
日志多参数配置：logger.info("初始化信息:{},时间:{}",sce.getServletContext().getServerInfo(),new Date());

6、 swagger配置
pom.xml配置依赖；添加Swagger配置类SwaggerConfiguration
http://localhost:8080/swagger-ui.html#/

7、 热部署配置
pom.xml配置依赖

8、 @RequestBody接受参数，Json返回封装
curl -X POST --header 'Content-Type: application/json' --header 'Accept: text/plain' -d '{ \ 
   "header": { \ 
     "companyId": "111", \ 
     "platform": "111", \ 
     "portalType": "1", \ 
     "productLine": "1", \ 
     "transactionId": "11", \ 
     "version": "1" \ 
   }, \ 
   "list": ["1","2","3","4"] \ 
 }' 'http://localhost:8080/hello/listByIds'
 
swagger参数：
 {
  "header": {
    "companyId": "111",
    "platform": "111",
    "portalType": "1",
    "productLine": "1",
    "transactionId": "11",
    "version": "1"
  },
  "list": ["1","2","3","4"]
}
请求结果：{"result":{"resultCode":"1","resultMsg":"返回正确结果"},"list":[{"id":4,"name":"DemoDto4","createTime":1508310113445},{"id":4,"name":"DemoDto4","createTime":1508310113445},{"id":4,"name":"DemoDto4","createTime":1508310113445},{"id":4,"name":"DemoDto4","createTime":1508310113445}]}

9、 SpringJDBC集成
pom.xml配置；application.yml参数配置
调用的地方直接注入JdbcTemplate
http://localhost:8080/jdbc/list
调用objectMapper.writeValueAsString(object)，返回json格式的字符串（Content-Type:text/html;charset=UTF-8）
http://localhost:8080/jdbc/channels
返回json（Content-Type:application/json;charset=UTF-8）






Mybatis集成


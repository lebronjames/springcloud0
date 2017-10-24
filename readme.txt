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

10、 Mybatis集成配置
1)pom.xml配置:mybatis、通用Mapper、Pagehelper、Druid、Jpa；
2)SpringCloud0Application主类增加注解@MapperScan("com.example.demo.mapper")
3)创建Mapper接口，继承通用Mapper接口。extends Mapper<ContractChannel>
4)把ChannelMapper注入到Service中。
5)创建实体类映射。@Table(name="pd_channel")、@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)、@Column(name = "channel_code")
6)配置ContractChannelMapper.xml。
<mapper namespace="com.example.demo.mapper.ChannelMapper" >
  <resultMap id="BaseResultMap" type="com.example.demo.entity.Channel" >
7)列表：http://localhost:8080/channel/list
详情：http://localhost:8080/channel/getChannelById?id=5
插入：http://localhost:8080/channel/saveChannel
swagger参数：{
  "channelCode": "",
  "channelDesc": "自动化",
  "channelName": "自动化",
  "createTime": "",
  "isWholeChannel": "",
  "updateTime": ""
}
批量插入：

swagger参数：[{
  "channelCode": "",
  "channelDesc": "自动化1",
  "channelName": "自动化1",
  "createTime": "",
  "isWholeChannel": "",
  "updateTime": ""
},{
  "channelCode": "",
  "channelDesc": "自动化2",
  "channelName": "自动化2",
  "createTime": "",
  "isWholeChannel": "",
  "updateTime": ""
},{
  "channelCode": "",
  "channelDesc": "自动化3",
  "channelName": "自动化3",
  "createTime": "",
  "isWholeChannel": "",
  "updateTime": ""
}
]

11、 @Configuation使用示例
@Configuration标注在类上，相当于把该类作为spring的xml配置文件中的<beans>，作用为：配置spring容器(应用上下文)
@Bean标注在方法上(返回某个实例的方法)，等价于spring的xml配置文件中的<bean>，作用为：注册bean对象
(1)、@Bean注解在返回实例的方法上，如果未通过@Bean指定bean的名称，则默认与标注的方法名相同； 
(2)、@Bean注解默认作用域为单例singleton作用域，可通过@Scope(“prototype”)设置为原型作用域； 
(3)、既然@Bean的作用是注册bean对象，那么完全可以使用@Component、@Controller、@Service、@Ripository等注解注册bean，当然需要配置@ComponentScan注解进行自动扫描。

@Configuation总结
@Configuation等价于<Beans></Beans>
@Bean等价于<Bean></Bean>
@ComponentScan(basePackages="com.dxz.demo")等价于<context:component-scan base-package="com.dxz.demo"/>

12、 自定义属性配置读取
1)application.yml增加自定义属性配置myconfig.name
2)@Configuration、@ConfigurationProperties标注在配置类上。@Value("${myconfig.name}")标注在属性上
3)调取前先注入配置类：@Autowired
	private TestConfigurationProperties testConfigurationProperties;
http://localhost:8080/hello/config

13、 







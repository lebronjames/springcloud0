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
1) 配置logback.xml文件；Logger调用
日志多参数配置：logger.info("初始化信息:{},时间:{}",sce.getServletContext().getServerInfo(),new Date());
2) 项目执行Mybatis脚本看不到日志，解决方式
在application.properties中，配置logging.level.com.example.demo=DEBUG（名称以logging.level开头，后面跟要输入日志的包名）

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

13、 项目打包
1) cd F:\migu910\springCloud0\springcloud0
2) mvn package -Dmaven.test.skip=true

14、 项目启动通过Profile区分环境
1) 增加application-test.yml,application-prod.yml两个配置文件
2) java -jar springCloud0-1.0.0.jar,访问http://localhost:8080/hello/config,结果：Hello,name:yzx-dev,tech:Docker
3) java -jar springCloud0-1.0.0.jar --spring.profiles.active=test,访问http://localhost:8080/hello/config,结果：Hello,name:yzx-test,tech:Docker
4) java -jar springCloud0-1.0.0.jar --spring.profiles.active=prod,访问http://localhost:8080/hello/config,结果：Hello,name:yzx-prod,tech:Docker

15、 事务配置
1) 首先使用注解 @EnableTransactionManagement 开启事务支持后.// 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven />
2) 然后在访问数据库的Service方法上添加注解 @Transactional 便可
http://localhost:8080/channel/saveChannelTransaction?channelName=name1&channelDesc=desc2222

16、 通用Mapper插件集成
1) 分页
PageHelper.startPage(num, size);
return new PageInfo<Channel>(channelMapper.queryAllPdChannel())
http://localhost:8080/channel/getChannelPage?pageNum=1&pageSize=2

2) 通用接口：根据主键删除
http://localhost:8080/tk/tkDeleteByPrimaryKey?id=90182
返回值1

3) 通用接口：根据实体删除
http://localhost:8080/tk/tkDeleteBean?id=90183
返回值1

4) 通用接口：根据条件删除
http://localhost:8080/tk/tkDeleteByExample?fieldName=channelCode&fieldValue=15088381869745
返回值1
前提：pom.xml中热部署spring-boot-devtools必须注释掉，
问题解决：​restartClassLoader是spring-boot-devtools这个包里面的，只是为了开发时热部署使用，弃用该包后程序运行正常。

5) 通用接口：新增
http://localhost:8080/tk/tkInsert?channelName=test1&channelDesc=test1
返回值1

6) 通用接口：根据主键查询实体是否存在
http://localhost:8080/tk/tkExistWithPrimaryKey?id=90183
返回值:true/false

7) 通用接口：根据条件筛选列表
http://localhost:8080/tk/tkListByCondition?fieldName=channelName&fieldValue=test2
前提：pom.xml中热部署spring-boot-devtools必须注释掉，
问题解决：​restartClassLoader是spring-boot-devtools这个包里面的，只是为了开发时热部署使用，弃用该包后程序运行正常。
模糊查询的坑：example.createCriteria().andLike(fieldName, "%"+fieldValue+"%");

17、 单元测试Junit、MockMvc整合
1) 测试类上增加注解
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！ 
@SpringBootTest(classes = SpringCloud0Application.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration // 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。

2) post请求
String content = JsonUtil.toJsonString(channel);
MvcResult result = mvc
		.perform(MockMvcRequestBuilders.post("/channel/saveChannel")
				//.header(name, values) //设置请求报文头
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
		.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
int responseCode = result.getResponse().getStatus();
Assert.assertEquals(responseCode, 200);
String responseBody = result.getResponse().getContentAsString();

3)get请求
MvcResult result = mvc
		.perform(MockMvcRequestBuilders.get("/tk/tkListByCondition")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("fieldName", "channelName").param("fieldValue", "test"))
		.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
int responseCode = result.getResponse().getStatus();
Assert.assertEquals(responseCode, 200);
String responseBody = result.getResponse().getContentAsString();

18、 @PathVariable使用
1) @GetMapping("/getChannelBean/{id}")
2) public Channel getChannelBean(@PathVariable("id") String id) {..}
http://localhost:8080/channel/getChannelBean/90178

19、 定时任务配置
1) 定时配置类上增加注解@Configuration、@EnableScheduling
2) 方法上增加调度器@Scheduled(cron="0/20 * * * * ?")

20、 Redis单机集成
1) 单记录插入：StringRedisTemplate.opsForValue().set("v5", "ni hao diao");
http://localhost:8080/redis/operation/get
2) 单记录查询：stringRedisTemplate.opsForValue().get(SingleRedisConstants.REDIS_PREFIX+"v5");
http://localhost:8080/redis/operation/getOne

21、 性能测试
1) Redis 单条记录查询耗时：1105341ns,1ms
http://10.5.2.241:8082/redis/operation/getOne
2) Redis 批量记录查询10000次，耗时：5558ms
http://10.5.2.241:8082/redis/operation/batchGet
3) Redis 10个线程读10000数据，耗时：80000ms（80秒）
http://10.5.2.241:8082/redis/operation/threadPoolGet

4) Redis 单值插入耗时：2455670ns,2ms
http://10.5.2.241:8082/redis/operation/set
5) Redis 批量插入10000次，耗时：5462ms
http://10.5.2.241:8082/redis/operation/batchSet

22、 Jemter性能测试
调整tomcat 最大线程数 max-threads: 200
1) 1 线程     1000请求 平均时长  2ms  最大时间 62ms  错误率 0
2) 100线程 	  1000请求 平均时长 1ms  最大时间  27ms  错误率 0
3) 300线程    1000请求 平均时长 2ms 最大时间 47ms  错误率 0
4) 500线程    1000请求 平均时长 35ms 最大时间 783ms  错误率 0
5) 1000线程   1000请求 平均时长 10ms  最大时间 284ms  错误率 0
调整tomcat最大线程数 max-threads: 800
6) 300线程    1000请求 平均时长 5ms 最大时间 52ms  错误率 0
7) 500线程    1000请求 平均时长 5ms 最大时间  68ms 错误率 0
8) 1000线程   1000请求 平均时长 4ms 最大时间  102ms  错误率 0

23、 原生态注解
1) 定义注解类
@Documented //表示是否将注解信息添加在java文档中
@Retention(RetentionPolicy.RUNTIME)//始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息。我们自定义的注解通常使用这种方式。
@Target(ElementType.METHOD)//表示该注解用于方法
public @interface AopAspectAnnotation {String value() default "";}
2) 自定义注解修饰方法
@AopAspectAnnotation(value="annotationValue",name="annotationName")
	@GetMapping("/show")
	public void show() {...}
3) 获取注解信息
Class aopAspectController = AopAspectController.class;
for(Method method : aopAspectController.getMethods()) {
	AopAspectAnnotation aopAspectAnnotation = method.getAnnotation(AopAspectAnnotation.class);//返回注解信息
	if(aopAspectAnnotation != null) {
		logger.info("---Method Name : {}",method.getName());//show
		logger.info("---Annotation Name : {}",aopAspectAnnotation.name());//annotationName
		logger.info("---Annotation Value : {}",aopAspectAnnotation.value());//annotationValue
	}
}

字段注解
http://localhost:8080/fruit/info

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {String value() default "";}

Field[] fields = AppleModel.class.getDeclaredFields();
for(Field field : fields) {
	if(field.isAnnotationPresent(FruitName.class)) {//判断指定类型的注释是否存在于此元素上
		FruitName fruitName = (FruitName)field.getAnnotation(FruitName.class); 
		strFruitName = strFruitName + fruitName.value();

24、 Aop Aspect切面类处理自定义注解
1) pom.xml中添加依赖
spring-boot-starter-aop
2) 创建自定义注解
@Documented //表示是否将注解信息添加在java文档中
@Retention(RetentionPolicy.RUNTIME)//始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息。我们自定义的注解通常使用这种方式。
@Target(ElementType.METHOD)//表示该注解用于方法
public @interface AopAspectAnnotation {String value() default "";String name() default "";}
3) 创建Aspect切面类
@Aspect //aop切面类注解
@Order(-99) //控制多个Aspect的执行顺序，越小越先执行
@Component
public class AspectAop {
	
	private static final Logger logger = LoggerFactory.getLogger(AspectAop.class);

	//拦截被AopAspectAnnotation注解的方法；如果你需要拦截指定package指定规则名称的方法，可以使用表达式execution(...)
	@Before("@annotation(aaAnnotation)")
	public void beforeAop(JoinPoint joinPoint, AopAspectAnnotation aaAnnotation) throws Throwable {
		logger.info("-AspectAop##beforeAop##name:"+aaAnnotation.name()+",value:"+aaAnnotation.value());
	}
	
	@After("@annotation(aaAnnotation)")
	public void afterAop(JoinPoint joinPoint, AopAspectAnnotation aaAnnotation) throws Throwable {
		logger.info("-AspectAop##afterAop##name:"+aaAnnotation.name()+",value:"+aaAnnotation.value());
	}
}
4) 创建方法，使用自定义注解
@AopAspectAnnotation(value="annotationValue",name="annotationName")
@GetMapping("/show")
public void show() {
	logger.info("-----------AopAspectController##show");
}


25、 AOP记录Web请求日志
1) 使用@Aspect注解将一个java类定义为切面类 
2) 使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
@Aspect
@Component
public class WebLogAspect {

	private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
	
	//使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数
	//两个..代表所有子目录，最后括号里的两个..代表所有参数
	@Pointcut("execution(public * com.example.demo..controller.*.*(..))")
	public void logPointCut() {
	}
	
	@Before("logPointCut()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        // 记录下请求内容
        logger.info("请求地址 :"+request.getRequestURI().toString());
        logger.info("HTTP METHOD :"+request.getMethod());
        logger.info("IP :"+request.getRemoteAddr());
        logger.info("CLASS_METHOD :"+joinPoint.getSignature().getDeclaringTypeName()
        			+"."+joinPoint.getSignature().getName());
        logger.info("参数 :"+Arrays.toString(joinPoint.getArgs()));
	}
	
	// returning的值和doAfterReturning的参数名一致
	@AfterReturning(returning="ret", pointcut="logPointCut()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		logger.info("返回值 :"+ret);
	}
	
	@Around("logPointCut()")
	public Object doAroud(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object object = pjp.proceed();// object 为方法的返回值
		logger.info("方法请求耗时 : " + (System.currentTimeMillis() - startTime));
		return object;
	}
}
http://localhost:8080/aopAspect/hello

26、 







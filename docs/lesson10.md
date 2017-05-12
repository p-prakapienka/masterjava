# Онлайн проекта  <a href="https://github.com/JavaWebinar/masterjava">Masterjava</a>.

## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Разбор домашнего задания HW9
### ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) 1. [Реализация SOAP handlers](https://drive.google.com/open?id=0B9Ye2auQ_NsFTVhpbTNCTDZ4bTA)
### ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) 2. [Конфигурирование сервисов](https://drive.google.com/open?id=0B9Ye2auQ_NsFR1lybnQyRUJUUEU)
- <a href="https://github.com/typesafehub/config#how-to-handle-defaults">Handle defaults in config</a>

## ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) 3. [JavaEE](https://drive.google.com/open?id=0B9Ye2auQ_NsFUU92ZFBEZmJjb2c)
- <a href="https://ru.wikipedia.org/wiki/Java_Platform,_Enterprise_Edition">Java Platform, Enterprise Edition</a>
- <a href="https://habrahabr.ru/post/283290/">Холивары в комментах</a>
- <a href="http://www.ibm.com/developerworks/websphere/techjournal/1301_stephen/1301_stephen.html">CDI</a>
- <a href="https://ru.wikipedia.org/wiki/Enterprise_JavaBeans">Enterprise JavaBeans</a>
- <a href="http://tomee.apache.org/comparison.html">TomEE состав</a>
- <a href="https://zeroturnaround.com/rebellabs/java-tools-and-technologies-landscape-2016/">Application Server statistics</a>

## ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) 4. [JAX-RS](https://drive.google.com/file/d/0B9Ye2auQ_NsFeWQxTUVBSDFGMGM)
- <a href="https://jersey.java.net/">Jersey: RESTful Web Services in Java</a>
- <a href="https://jersey.java.net/documentation/latest/modules-and-dependencies.html">Modules and dependencies</a>
- <a href="http://howtodoinjava.com/jersey/jersey-2-hello-world-application-tutorial/">Example with web.xml</a>
- <a href="https://jersey.java.net/documentation/latest/deployment.html#deployment.servlet.3">Descriptor-less deployment</a>
  - [ServletContainerInitializer](http://stackoverflow.com/a/10784700/548473)
  - [Servlet 3.0 ServletContainerInitializer and Spring WebApplicationInitializer](http://www.java-allandsundry.com/2014/03/servlet-30-servletcontainerinitializer.html)
- <a href="http://howtodoinjava.com/jersey/jax-rs-jersey-moxy-json-example/">Jersey + MOXy JSON</a>
- <a href="https://jersey.java.net/documentation/latest/bean-validation.html#d0e11875">Validation</a>  
- <a href="https://jersey.java.net/documentation/latest/wadl.html#d0e13052">Web Application Description Language (WADL)</a>
  - <a href="http://localhost:8080/mail/rest/application.wadl">mail/rest/application.wadl</a>
  - <a href="https://wadl.java.net/">wadl2java client stub generation</a>

## ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) 5. <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFMUpGRGpSVXJLVGs">JMS</a>
- <a href="https://ru.wikipedia.org/wiki/Java_Message_Service">Java Message Service</a>
- <a href="http://queues.io/">Queues</a>
- <a href="https://www.linkedin.com/pulse/jms-vs-amqp-eran-shaham">JMS vs AMQP</a>, <a href="https://ru.wikipedia.org/wiki/AMQP">AMQP</a>
- <a href="http://blog.net21.cz/index.php?/archives/3-ActiveMQ,-HornetQ-and-RabbitMQ-Performance-Comparison.html">ActiveMQ, HornetQ and RabbitMQ Performance Comparison</a>
- <a href="http://activemq.apache.org/tomcat.html">ActiveMQ + Tomcat</a>
- <a href="http://www.tomcatexpert.com/blog/2010/12/16/integrating-activemq-tomcat-using-local-jndi">Embedded/StandAlone ActiveMQ</a>, <a href="http://activemq.apache.org/configuring-transports.html">ActiveMQ transport</a>, <a href="http://activemq.apache.org/uri-protocols.html">URI protocols</a>
- <a href="https://martinsdeveloperworld.wordpress.com/2013/03/03/apache-activemq-and-tomcat/">Apache ActiveMQ and Tomcat sample</a>
   - [When close JMS connection/sessions](http://stackoverflow.com/questions/19772082/when-should-i-close-a-jms-connection-that-was-created-in-a-stateless-session-bea)
   - [Cache JMS connections/sessions](https://developer.jboss.org/wiki/ShouldICacheJMSConnectionsAndJMSSessions)
   
## Домашнее задание
- Добавить аттачи в JAX-RS
  - <a href="http://www.mkyong.com/webservices/jax-rs/file-upload-example-in-jersey">File upload example in Jersey</a>

#### Optional
- Реализовать отсылку почты через JMS `ObjectMessage`

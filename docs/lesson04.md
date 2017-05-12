# Онлайн проекта  <a href="https://github.com/JavaWebinar/masterjava">Masterjava</a>.

## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Разбор домашнего задания HW3

### ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) 1. <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFOFFNYzB6VGdma2c">Структура многомодульного проекта</a>
### ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) 2. <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFSDJYUXNRb0Z6NzA">Реализация модуля export: Thymeleaf и Upload</a>

- <a href="http://www.thymeleaf.org/">Thymeleaf</a>:  natural templates
   - <a href="http://www.thymeleaf.org/doc/articles/thymeleaf3migration.html">Thymeleaf 3 migration guide</a>
   - <a href="http://www.thymeleaf.org/doc/articles/layouts.html">th:include и th:replace</a>
   - <a href="http://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#conditional-expressions">Tutorial</a>
   - <a href="http://www.thymeleaf.org/doc/articles/standardurlsyntax.html">URL Syntax</a>
   - <a href="http://www.concretepage.com/thymeleaf/java-thymeleaf-example-getting-started-with-thymeleaf">Java Thymeleaf Example</a>

----------------

## ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) 3. <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFYlBQOXh3U3JzVzg">Maven. Поиск и разрешение конфликтов зависимостей</a>
- Maven options: -pl export -am install, -h, -X
- <a href="https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html">Maven transitive dependencies</a>
- <a href="https://maven.apache.org/components/plugins/maven-project-info-reports-plugin/">Project Info Reports Plugin</a>
- <a href="http://stackoverflow.com/questions/28098566/maven-site-warning-the-repository-url-https-maven-repository-dev-java-net-no/40040093#40040093">Disable blacklisted repository warnings</a>
```
mvn dependency:tree
mvn project-info-reports:dependencies
```
- <a href="https://habrahabr.ru/company/jugru/blog/191246/">Разрешение конфликтов в транзитивных зависимостях</a>
- <a href="https://ru.wikipedia.org/wiki/Dependency_hell">Dependency hell</a>
- <a href="http://maven.apache.org/enforcer/maven-enforcer-plugin/">Maven Enforcer Plugin</a>
```
mvn project-info-reports:dependency-convergence
```
- <a href="https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/javaway/jar-hell.zip">jar-hell.zip</a>
- <a href="http://jhades.github.io/">jHades</a>
```
mvn clean install
mvn -DincludeScope=runtime dependency:copy-dependencies
java -jar jar-hell.jar . 
```
- <a href="https://maven.apache.org/plugins/maven-dependency-plugin/analyze-mojo.html">Analyze used and declared; used and undeclared; unused and declared</a>
```
mvn dependency:analyze
```

## ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) 4. <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFSTR0cTl4NjE1OEE">Логирование</a>

> Добавил в корень проекта `config_templates` с копией конфигурации.

- Общие ресурсы. <a href="http://www.slf4j.org/legacy.html">Добавление зависимостей логирования</a> в без дублирования.
- <a href="https://www.mkyong.com/logging/logback-xml-example">logback.xml Example</a>
- Справочник:
  - <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFaTdYUnpLNFFUeXM">Topjava: логирование.</a>
  - <a href="https://www.youtube.com/watch?v=j-i3NQiKbcc">Владимир Красильщик: Что надо знать о логировании прагматичному Java‑программисту</a>  
  - <a href="http://habrahabr.ru/post/113145/">Java Logging: история кошмара</a>
  - <a href="http://skipy.ru/useful/logging.html">Ведение лога приложения</a>
  - <a href="http://logging.apache.org/log4j/2.x/index.html">Log4j</a>, <a href="http://logback.qos.ch/">Logback</a>

## ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) 5.<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFd29NNVhjd2ZKaGs">JDBC helper library. Tomcat Class Loader. Memory Leeks.</a>

### Выбор lightweight JDBC helper library
- <a href="http://stackoverflow.com/questions/7137929/lightweight-jdbc-helper-library-alternative-to-apache-commons-dbutils">Lightweight JDBC helper library</a>
- <a href="https://habrahabr.ru/company/luxoft/blog/280784/#ii5">Библиотеки для работа с JDBC</a>
- <a href="http://www.mybatis.org/mybatis-3/">MyBatis</a>
- <a href="http://sivalabs.in/2012/10/mybatis-tutorial-part-2-crud-operations-using-annotations/">MyBatis tutorial (CRUD)</a>
- <a href="https://commons.apache.org/proper/commons-dbutils/">Commons DbUtils</a>, <a href="https://habrahabr.ru/post/183204/">доработка commons-dbutils</a>
- <a href="http://jdbi.org/">JDBI</a>, <a href="http://zetcode.com/db/jdbi/">JDBI tutorial</a> (<a href="http://www.dropwizard.io/0.7.1/docs/manual/jdbi.html">Dropwizard JDBI</a>),

### Tomcat Class Loader. Memory Leeks
- <a href="https://tomcat.apache.org/tomcat-8.0-doc/class-loader-howto.html">Class Loader HOW-TO</a>
- Библиотеки vs Frameworks и Tomcat Common Lib. <a href="https://habrahabr.ru/post/222443/">Memory Leaks</a>. 
- <a href="https://www.youtube.com/watch?v=sSmQ6W-ovZE">Никита Сальников-Тарновский — Утечки памяти</a>

## ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) 6.<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFVUNOeWQyOG1waFE">HW6. Вопрос: как разбивать приложение на модули</a>

### Домашнее задание
- Сделать maven модуль `persist` для сохранения пользователей в базу (база и фреймворк-библиотека на ваш выбор)
- Соханить всех импортированных пользователей в модуле export в базе (без синхронизации, одну таблицу users без ссылок на города и группы). 
   - Выводить только сообщение о результате импорта. 
- Сделать отображение первых 20 пользователей в модуле web
- Протестировать сохранение в базу 

#### Optional
- Сконфигурить DataSource через JNDI для Tomcat и без него для тестов.
  - Добавить работу с базой через <a href="https://github.com/JavaWebinar/topjava08/blob/doc/doc/lesson06.md#-7---Конфигурирование-tomcat-через-maven-plugin-jndi-lookup">Tomcat JNDI pool</a>

- Сделать реализацию в несколько потоков и с учетом того, что пользователей может быть ООчень много.
  - делать вставку группами (chunk) в транзакции и batch моде
  - количестово пользователей в chunk принимать с UI как параметр
  - не делать для каждой вставки `nextval` (но иметь возможность вставки с автогенерацией id)
  - результат импорта - количество успешных/неуспешных вставок и информация, по которой из исходного xml можно повторить импорт неуспешных пользователей.
  - <a href="http://stackoverflow.com/a/12207237/548473">Speed up insertion performance in PostgreSQL</a>

#### <a href="https://www.jetbrains.com/help/idea/2016.2/analyzing-dependencies-using-dsm.html">IDEA DSM plugin</a>

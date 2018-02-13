Инструкция по установке:

1) Разместить ROOT.war в папке webapps запущенного Tomcat (Context path приложения "/"), после чего оно будет доступно по адресу http://localhost:8080/
2) Таблицы создаются в БД "myshop" (url=jdbc:mysql://localhost:3306/myshop, user=root, password=root, driver=com.mysql.jdbc.Driver),
    которая должна быть подключена перед запуском приложения

Интерфейс:

RESTful

Используемые технологии:

1) DB: MySQL + Hibernate + Spring Data + Ehcache
2) Back-end: Spring web + webMVC с конфигурацией через аннотации в Java-классах
3) Front-end: JSP + JSTL + JQuery + AJAX + Bootstrap + Datatable

Тестирование:

Набор JUnit тестов

Не успел исправить:
- cache

Исходный код приложения доступен на https://github.com/Kowi77/shop
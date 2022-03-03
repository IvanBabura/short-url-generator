# Service for creating short links.

**Language**: 
[English](#English),
[Русский](#Русский)
____
# English

## 1 Short description
Service for creating short links _aka_ "Short URL Generator".  
RESTful Web services. But I've added an MVC controller with simple website to demonstrate how the services work.  
The project is under development and expansion.

## 2 Technology stack 
- Spring (Spring boot);
- Java 11+;
- Db: H2 memory Embedded;
- Оptional: Thymeleaf only for testing REST requests from under the website.

## 3 Project Features
Message exchange method: JSON.  
All data is stored in the database.

REST Endpoints:
- Formation of a short link;
- Creation and/or obtaining a full-link based on a short-one;
- Creation and/or obtaining a short-link based on the full-one;
- Implementation of the redirect by a short link in the browser;
- Deleting an entry;
- Getting a list of all matches.

MVC is able to implement all these Endpoints, but does not in all cases do the reverse transformation from JSON (I'm not very good at front-end, and this is not the task).

## 4 Tasks
! _I rarely check the README as I'm immersed in the process. In committees, I try to write everything according to the fads. Perhaps I have already done something from this list and forgot to put it here_!

- [ ] Add exception logging.
- [ ] Add caching.
- [ ] Add link lifetime (TTL).
- [ ] Rewrite short-link-generation algorithm. (I don’t like it very much, it’s made for a quick hand “if only there was.” Can be improved.)
- [ ] Implement horizontal scaling of the service.
- [ ] Rewrite the README and make a REST API document.

____
# Русский

## 1 Краткое описание
Сервис создания коротких ссылок.  
RESTfull сервис. Но я добавил MVC-контроллер с простым веб-сайтом, чтобы продемонстрировать, как работает сервис.  
Идёт доработка, расширение и шлифовка проекта.

## 2 Стэк используемых технологий
- Spring (Spring boot);
- Java 11+;
- БД: встроенная H2 memory;
- Не обязательно: Thymeleaf используется только для теста REST запросов в реальном сайте.

## 3 Особенности проекта
Способ обмена сообщений: JSON.  
Все данные хранятся в базе данных.

REST Endpoints: 
-	Формирование короткой ссылки; 
-	Создание и/или получение полной ссылки на основе краткой;
-	Создание и/или получение краткой ссылки на основе полной;
-	Осуществление перехода по короткой ссылке в браузере;
-	Удаление записи;
-	Получение списка всех соответствий.

MVC способен осуществить все эти Endpoints, но не во всех случаях делает обратное преобразование из JSON (я не очень умею во фронтэнд, да и задача не в этом состоит).

## 4 Задачи
! _Я редко заглядываю в README, тк я погрузился в процесс. В комитах стараюсь писать всё по пунктикам. Возможно, я уже что-то сделал из этого списка и забыл сюда внести_!

- [ ] Добавить логирование исключений. 
- [ ] Добавить кэширование. 
- [ ] Добавить время жизни ссылки. 
- [ ] Переписать алгоритм генерации короткой ссылки. (Мне он не очень нравится, ос сделан на быструю руку «лишь бы было». Можно улучшить.) 
- [ ] Реализовать горизонтальное масштабирование сервиса. 
- [ ] Переписать README и сделать документацию к REST API.

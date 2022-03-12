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
- Maven
- Spring (Spring boot);
- Java 11+ (But mb it can also run on version 8);
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
- [X] Moved all exception calls to the service.
- [X] Add exception logging.
- [ ] Add caching.
- [X] Add link lifetime (TTL = 10 min).
- [ ] Add the ability to change the TTL. Rewrite TTL end message.
- [ ] Rewrite short-link-generation algorithm. (I don’t like it very much, it’s made for a quick hand “if only there was.” Can be improved.)
- [ ] Implement horizontal scaling of the service.
- [ ] Rewrite the README and make a REST API document.
- [ ] You can implement Interceptors, but for now, the existing logging and functionality is enough.

P.s. ! _I try to write everything to the commits. I often forget to update the project version in pom.xml. Do not swear. This is a trifle for a project under development._!

## 5 "Not a bug, but a feature"
- HTML 204 (NO_CONTENT) returns an empty JSON body, so nothing changes on pages in MVC. In theory, this is how it should be, but this can be bypassed, but since I write not a frontend project, but a backend one, then I should think about such features last. In extreme cases, in those places you can throw the code 200, and explain in the body message. PROFIT ¯\_(ツ)_/¯.
____
# Русский

## 1 Краткое описание
Сервис создания коротких ссылок.  
RESTfull сервис. Но я добавил MVC-контроллер с простым веб-сайтом, чтобы продемонстрировать, как работает сервис.  
Идёт доработка, расширение и шлифовка проекта.

## 2 Стэк используемых технологий
- Maven
- Spring (Spring boot);
- Java 11+ (но по идее сможет запуститься и с 8);
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
- [X] Переместить все вызовы исключений из контроллера в сервис.
- [X] Добавить логирование исключений. 
- [ ] Добавить кэширование. 
- [X] Добавить время жизни ссылки (TTL = 10 мин). 
- [ ] Добавить возможность изменить TTL. Переписать сообщение об окончании TTL.
- [ ] Переписать алгоритм генерации короткой ссылки. (Мне он не очень нравится, он сделан на быструю руку «лишь бы было». Можно улучшить.) 
- [ ] Реализовать горизонтальное масштабирование сервиса. 
- [ ] Переписать README и сделать документацию к REST API.
- [ ] Можно внедрить Interceptors, но пока и имеющегося логирования и функционала хватает. 

P.s. ! _В комитах стараюсь писать всё по пунктикам. Я часто забываю обновлять версию проекта в pom.xml. Не ругайтесь. Это мелочь для проекта в стадии разработки._!

## 5 "Не баг, а фича"
- Код HTML 204 (NO_CONTENT) возвращает пустой JSON, поэтому на страничках в MVC ничего не меняется. По идее так и должно быть, но это можно обойти, но тк я пишу ~не frontend проект, а~ backend, то о таких мелочах я должен думать в последнюю очередь. На крайний случай в тех местах можно кинуть код 200, а в сообщении тела пояснить. PROFIT ¯\_(ツ)_/¯.

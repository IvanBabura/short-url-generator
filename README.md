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
- Maven;
- Spring boot;
- Java 11;
- JUnit 5 + SpringBootTest (including Mockito)
- H2 memory Embedded;

Optional:

- HTML + JavaScript;
- Thymeleaf;

It just only for testing REST requests from under the simple website.

## 3 Project Features
Message exchange method: JSON.  
All data is stored in the database.

REST Endpoints:
- Formation of a short link: _POST JSON /url_;
- Creation and/or obtaining a full-link based on a short-one: _GET /url?shortUrl=Value_;
- Creation and/or obtaining a short-link based on the full-one: _GET /url?originalUrl=Value_;
- Implementation of the redirect by a short link in the browser: _GET /{shortUrlEnding}_;
- Deleting an entry: _DELETE JSON /url_;
- Getting a list of all matches: _GET /all_urls_.

MVC is able to implement all these Endpoints, but does not in all cases do the reverse transformation from JSON (I'm not very good at front-end, and this is not the task).

## 4 Tasks
- [X] Moved all exception calls to the service.
- [X] Add exception logging.
- [X] Unit tests for a controller and service. 
- [ ] Add caching.
- [X] Add link lifetime (TTL = 10 min).
- [ ] Rewrite short-link-generation algorithm. (I don’t like it very much, it’s made for a quick hand “if only there was.” Can be improved.)
- [ ] Implement horizontal scaling of the service.
- [X] Rewrite the README and add changelog file.
- [ ] Make a REST API document.
- [ ] You can implement Interceptors, but for now, the existing logging and functionality is enough.
- [ ] Rewrite Controller Advice for Exceptions (any exceptions send uncorrect HTML code).

P.s. ! _I try to write everything to the commits. I often forget to update the project version in pom.xml. Do not swear. This is a trifle for a project under development._!

## 5 "Not a bug, but a feature"
- Empty response to findAll / no changes to this request:

HTML 204 (NO_CONTENT) returns an empty JSON body, so nothing changes on pages in MVC. In theory, this is how it should be, but this can be bypassed, but since I write not a frontend project, but a backend one, then I should think about such features last. In extreme cases, in those places you can throw the code 200, and explain in the body message. PROFIT ¯\_(ツ)_/¯.
Loading default program parameters (in case of failure) inside the service constructor.

- This is just a reinsurance against an out of time initialized database (doesn't affect project operation):

It is a problem in Embedded H2. If use a non-Embedded BD, this problem should go away. I did not do it very competently, but it is better this way and without possible errors. Spring Boot won't let me run the utility before the controllers, and I'm not up to this problem right now. Even though it works so well.

- Scary site? Disgusting Front End? Terrible HTML and invalid script?

Sorry, but I'm doing the server, not the front. The site is just for show, what my REST is works. A-a-and... I don't know CSS, just plain HTML and JS, and no deepening. Therefore, what is, is.
____
# Русский

## 1 Краткое описание
Сервис создания коротких ссылок.  
RESTfull сервис. Но я добавил MVC-контроллер с простым веб-сайтом, чтобы продемонстрировать, как работает сервис.  
Идёт доработка, расширение и шлифовка проекта.

## 2 Стэк используемых технологий
- Maven
- Spring boot;
- Java 11;
- JUnit 5 + SpringBootTest (including Mockito)
- H2 memory Embedded;

Дополнительно (не обязательно):
- HTML + JavaScript;
- Thymeleaf;

Они используются только для теста REST запросов в реальном простеньком сайте.

## 3 Особенности проекта
Способ обмена сообщений: JSON.  
Все данные хранятся в базе данных.

REST Endpoints: 
-	Формирование короткой ссылки: _POST JSON /url_; 
-	Создание и/или получение полной ссылки на основе краткой: _GET /url?shortUrl=Value_;
-	Создание и/или получение краткой ссылки на основе полной: _GET /url?originalUrl=Value_;
-	Осуществление перехода по короткой ссылке в браузере: _GET /{shortUrlEnding}_;
-	Удаление записи: _DELETE JSON /url_;
-	Получение списка всех соответствий: _GET /all_urls_.

MVC способен осуществить все эти Endpoints, но не во всех случаях делает обратное преобразование из JSON (я не очень умею во фронтэнд, да и задача не в этом состоит).

## 4 Задачи
- [X] Переместить все вызовы исключений из контроллера в сервис.
- [X] Добавить логирование исключений.
- [X] Юнит тесты контроллеру и сервису.
- [ ] Добавить кэширование. 
- [X] Добавить время жизни ссылки (TTL = 10 мин).
- [ ] Переписать алгоритм генерации короткой ссылки. (Мне он не очень нравится, он сделан на быструю руку «лишь бы было». Можно улучшить.) 
- [ ] Реализовать горизонтальное масштабирование сервиса. 
- [X] Переписать README, добавить файл ченджлога для воды.
- [ ] Сделать документацию к REST API.
- [ ] Можно внедрить Interceptors, но пока и имеющегося логирования и функционала хватает. 
- [ ] Переписать Controller Advice (некоторые исключения отправляют некорректный HTML-код).

P.s. В коммитах я стараюсь писать всё по пунктикам.

## 5 "Не баг, а фича"
- Пустой ответ на findAll / нет изменений по этому запросу:

Код HTML 204 (NO_CONTENT) возвращает пустой JSON, поэтому на страничках в MVC ничего не меняется. По идее так и должно быть, но это можно обойти, но тк я пишу ~не frontend проект, а~ backend, то о таких мелочах я должен думать в последнюю очередь. На крайний случай в тех местах можно кинуть код 200, а в сообщении тела пояснить. PROFIT ¯\_(ツ)_/¯.

- Загрузка параметров программы по умолчанию (в случае неудачи) внутри конструктора сервиса (не влияет на работу проекта):

Проблема во _встроенной_ H2. С НЕ встроенной БД не должно быть такой проблемы. Это просто перестраховка от невовремя инициализированной БД. Я не очень грамотно её сделал, но лучше так и без возможных ошибок. Spring Boot не даёт мне запустить утилиту раньше контроллеров и мне сейчас не до этой проблемы. Хотя оно и так хорошо работает.

- Страшный сайт? Отвратительный FrontEnd? Ужасный HTML и неверный скрипт?

Сарян, но я сервер делаю, а не фронт. Сайт просто для галочки, чтобы доказать работоспособность REST. Иии... я не знаю CSS, только голый HTML и JS, и без углублений. Поэтому что есть, то есть.
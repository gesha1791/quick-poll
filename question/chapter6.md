# <a href="http://git.foxminded.com.ua/gesha17/quick-poll">Chapter 6</a>

#### 1.    Что такое Swagger, из каких модулей состоит, какие проблемы решают.
Swagger является спецификацией и платформой для создания интерактивной документации Rest API.
Модули: Swagger Editor, Swagger UI, Swagger Codegen, Swagger Core, Swagger Parser
Создание клиентских библиотек, создания интерактивной документации API, которая позволяет вашим пользователям проверять вызовы API прямо в браузере. (edited)
#### 2.    Какие swagger core аннотации можете назвать и для чего они?
@Api - помечает класс как ресурс Swagger. Swagger сканирует классы, помеченные @Api, чтобы прочитать метаданные, необходимые для создания списка ресурсов и файлов объявлений API.
@ApiOperation – описывает endpoint и тип ответа.
@ApiResponses – позволяет переопределить сообщения в ответе на стандартные HTTP методы
@ApiResponse – переопределяет или изменяет сообщение в ответе метода
@ApiModelProperty для описания свойств модели.

#### 3. Как мы можем обойтись без написания yaml? Как динамически сгенерировать его?

Редактор Swagger позволяет редактировать спецификации Swagger API в YAML в вашем браузере и просматривать документы в режиме реального времени.

#### 4. SpringFox. Кратко, суть @EnableSwagger2 и чем отличается от @EnableSwagger.
До версии 1.2 ставили аннотацию  @EnableSwagger и для настройки использовали  bean SwaggerSpringMvcPlugin, с версии 2 используют аннотацию @EnableSwagger2 и настраивают bean Docket
#### 5. Что репрезентует Docket.
Билдер который содержит, удобные методы для настройки swagger
#### 6. Отличия .apis() от .paths(). Что устанавливают эти настройки:
          .apis(RequestHandlerSelectors.any()) – используется для фильтрации API, API строится  в данном случае со всех контроллеров, мы могли указать пакет например с которого бы сваггер нам построил API
          .paths(PathSelectors.regex("(?!/error).+")) – еще один фильтр который указывает путь, в нашем случае все конечные точки , которые не /error доступны
#### 7. Что выключает swaggerSpringMvcPlugin.useDefaultResponseMessages(false) ?
Дефолтные response сообщения не добавляются в глобальные response сообщения.
#### 8. Как бы вы использовали @ApiModel?
Использовал бы его для описания модели. К примеру у нас есть определнные интерфейс который принимает на вход объект. Мы не можем видеть напрямую поля этого объекта. Для этого я бы использовал @ApiModel, затем пометил бы поля @ApiModelProperty чтобы дать описание поля.

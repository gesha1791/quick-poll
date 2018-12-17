# <a href="http://git.foxminded.com.ua/gesha17/quick-poll">Chapter 5</a>

#### 1.	Преимущество хорошего еррор-месседжа? Для чего javax.validation.Valid?
Хороший еррор-меседжер показывает подробные и полезные сведения об ошибке, которые помогают устранять неполадки в API.
Нужен для корректного ввода данных пользователем.
#### 2. "Какие знаете аннотации Bean Validation 2.0 / Hibernate Validator?"
@Size, @NotEmpty, @NotBlank, @Email, @NotNull
#### 3. Как в Spring Boot создать хандлер эксепшинов во всем приложении с кастомным телом ответа? (принцип и анотации)
Для начала создаем модель тела ответа, к примеру ErrorDetai, там пишем набор полей, который будет у нас отображаться в ответе.
После создаем класс, который будет перехватывать все ошибки который будет одинаково обрабатывать все ошибки и по шаблону создавать тело ответа. На примере нашего приложения это класс RestExceptionHandler. Мы над ним ставим аннотацию @ControllerAdvice который служит нам для решения таких проблем и есктендим ResponseEntityExceptionHandler
Дальше переопределяем ошибки или создаем свои, которые аннотируем @ExceptionHandler(в него передаем класс ошибки, к примеру MethodArgumnetNotValidException.class) в Exception создаем ErrorDetail в котором его заполняем и возвращаем тело ответа в запросе (edited)
#### 4. Над полем какого типа мы ставим @Pattern? А @Size?
     @Pattern String.
     @Size – collections, array
#### 5. Привести пример использования @ModelAttribute("mouse").
 @RequestMapping(value=”/addMouse, method = RequestMethod.POST)
public String createMouse(@ModelAttribute(“mouse”) Mouse mouse){
model.addAttribute(“name”, mouse.getName());
return “mouseView”;
}
addMouse.html
<form:form method = “POST” action = “localhost:8080/addMouse”
modelAttribute =”mouse”
<form:label path=”name” >Name</form:label>
<form:input path=”name” />
<input type=”submit” value =”submit”/>
</form:form>
mouseView.html
Name : ${name}

#### 6.  Ради какого бенефита мы создали messages.properties?
Ради того что бы в тело ошибки писать свои сообщения.

#### 7.  *Формат ключа (идентификатора) и сообщения. Что означает {1} в тексте сообщения? Как происходит поиск сообщения в файле (то, что нужно для того чтобы знать, как называть ключи)? Что если сообщения нет в файле?
Все форматы ключа и сообщения является String.
Параметр должен быть больше чем переменная {1} (под этой переменной заданное число в аннотации @Size)
Size.poll.options , первое аннотация , второе класс (модель), и
идентификатор поля над которым стоит аннотация
<< Constraint_Name >>. Model_name.field_Name
Если поле месседж пустой, выдается пустое сообщение в теле ответа с ошибкой

#### 8.  Как связаны @ModelAttribute и ключ (идентификатор) сообщения в properties файле? Как будет определн идентификатор если мы не указали @ModelAttribute (привести пример на конкретном классе сущности, например Mouse)?

Имя модели предоставляется с помощью аннотации @ModelAttribute …
Будет по названию модели.. Если контролер принимает в качестве модели Mouse mouse, будет называться mouse. (edited)

#### 9.  Почему мы пронаследовались от ResponseEntityExceptionHandler (спринг 3.2), а не от DefaultHandlerExceptionResolver (спринг 3.0), в чем бенефит?

ResponseEntityExceptionHandler позволяет воврщать ResponseEntity, DefaultHandlerExceptionResolver ModelAndView.

#### 10. Как без ResponseEntityExceptionHandler?
         ResponseStatusException

#### 11. *Какие конкретно эксепшины хандлит ResponseEntityExceptionHandler? (можно скопировать код)

ResponseEntityExceptionHandler? (можно скопировать код)
HttpRequestMethodNotSupportedException.class,
HttpMediaTypeNotSupportedException.class,
HttpMediaTypeNotAcceptableException.class,
MissingPathVariableException.class,
MissingServletRequestParameterException.class,
ServletRequestBindingException.class,
ConversionNotSupportedException.class,
TypeMismatchException.class,
HttpMessageNotReadableException.class,
HttpMessageNotWritableException.class,
MethodArgumentNotValidException.class,
MissingServletRequestPartException.class,
BindException.class,
NoHandlerFoundException.class,
AsyncRequestTimeoutException.class


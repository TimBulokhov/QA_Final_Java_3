# 🍔 QA Final Java 3 - Автоматизация тестирования Stellar Burgers



Проект автоматизации тестирования веб-приложения **Stellar Burgers** - сервиса для заказа бургеров с конструктором ингредиентов.

## 🚀 Технологии

- **Java 11+**

### 🍔 ConstructorTest - Тесты конструктора бургеров

| Метод теста | Описание | Что проверяет |
|-------------|-----------|---------------|
| `bunsSectionTest()` | Тест раздела "Булки" | Переход в раздел булок и проверка активности секции |
| `saucesSectionTest()` | Тест раздела "Соусы" | Переход в раздел соусов и проверка активности секции |
| `fillingsSectionTest()` | Тест раздела "Начинки" | Переход в раздел начинок и проверка активности секции |

**🎯 Цель:** Проверить корректность навигации между разделами конструктора бургеров.

---

### 🔐 LoginFromDifferentPlacesTest - Тесты авторизации из разных мест

| Метод теста | Описание | Что проверяет |
|-------------|-----------|---------------|
| `loginFromMainPageButtonTest()` | Вход через кнопку «Войти в аккаунт» на главной | Авторизацию через главную страницу |
| `loginFromPersonalAccountButtonTest()` | Вход через кнопку «Личный кабинет» | Авторизацию через личный кабинет |
| `loginFromRegistrationFormButtonTest()` | Вход через кнопку в форме регистрации | Авторизацию со страницы регистрации |
| `loginFromPasswordRecoveryButtonTest()` | Вход через кнопку в форме восстановления пароля | Авторизацию со страницы восстановления пароля |

**🎯 Цель:** Проверить все возможные способы входа в систему.

---

### 👤 RegistrationTest - Тесты регистрации

| Метод теста | Описание | Что проверяет |
|-------------|-----------|---------------|
| `successfulRegistrationTest()` | Успешная регистрация | Корректность процесса регистрации и переход на страницу входа |
| `failedRegistrationTest()` | Регистрация с коротким паролем | Появление ошибки при некорректном пароле |

**🎯 Цель:** Проверить функциональность регистрации новых пользователей.

---

### 🛠️ Вспомогательные классы

| Класс | Назначение |
|-------|------------|
| `DataGenerator` | Генерация случайных тестовых данных |
| `UserManager` | Управление пользователями через API |
| `TestUtils` | Настройка и управление WebDriver |
| `BaseTest` | Базовый класс для всех UI тестов |

## 🚀 ИНСТРУКЦИИ ПО ЗАПУСКУ

### ⚙️ Предварительная настройка

Перед запуском тестов настройте браузер в файле (для запуска вручную внутри проекта):
`src/test/resources/application.properties`

```properties
base.url=https://stellarburgers.education-services.ru
#browser.type=chrome
browser.type=yandex
```

### Запуск тестов в Yandex Browser с генерацией Allure отчета
Allure отчеты генерируются в target/allure-results/
#### Вариант 1 - Через терминал проекта:
```cmd
cmd
mvn clean test allure:serve -Pyandex
```

#### Вариант 2 - Через командную строку Windows:
```cmd
cd "Путь к проекту" (например C:\Users\TimofeyBulokhov\QA_Java_Final)
mvn clean test allure:serve -Pyandex
```

### Запуск тестов в Google Chrome с генерацией Allure отчета
Allure отчеты генерируются в target/allure-results/
#### Вариант 1 - Через терминал проекта:
```
cmd
mvn clean test allure:serve -Pchrome
```

ИЛИ 
```
mvn clean test -Pchrome; mvn allure:serve
```
#### Вариант 2 - Через командную строку Windows:
```cmd
cd "Путь к проекту" (например C:\Users\TimofeyBulokhov\QA_Java_Final)
mvn clean test allure:serve -Pchrome
```

### Запуск отдельных тестов
#### 1. Запуск конкретного тестового класса:
```
mvn test -Dtest=RegistrationTest -Pyandex
mvn test -Dtest=ConstructorTest -Pchrome
```
#### 2. Запуск конкретного тестового метода:
```
mvn test -Dtest=RegistrationTest#successfulRegistrationTest -Pyandex
mvn test -Dtest=LoginFromDifferentPlacesTest#loginFromMainPageButtonTest -Pchrome
```
#### 3. Запуск нескольких тестов:
```
mvn test -Dtest=RegistrationTest,ConstructorTest -Pyandex
mvn test -Dtest="LoginFromDifferentPlacesTest#loginFromMainPageButtonTest,LoginFromDifferentPlacesTest#loginFromPersonalAccountButtonTest" -Pchrome
```

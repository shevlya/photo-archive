# Photo Archive Application

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue)

## Содержание
- [Описание](#описание)
- [Технологии](#технологии)
- [Демонстрация работы приложения](#демонстрация-работы-приложения)
  - [Авторизация](#авторизация-пользователя)
  - [Фотографы](#главная-страница-страница-со-списком-фотографов)
  - [Фотоработы](#страница-фотоработы)
  - [Выставки](#страница-выставки)
  - [Жанры](#страница-жанры)
  - [Профиль пользователя](#профиль-пользователя)
  - [Регистрация](#регистрация-пользователя)

## Описание
Веб-приложение для ведения архива авторских фотографий.  
Реализованы CRUD-операции для сущностей: фотографы, фотоработы, выставки, жанры.  
Пользователи регистрируются и авторизуются; в профиле можно менять имя и фамилию.

## Технологии
- Java 21
- Spring Boot 3.5.3
- PostgreSQL
- Thymeleaf
- Hibernate

### Настройка базы данных
- Создайте БД и пользователя
- Укажите параметры в `application.properties`

### Сборка и запуск
```bash
mvn clean install
java -jar target/photo-archive-0.0.1-SNAPSHOT.jar
```

## Возможности приложения
- **Управление фотографами** – добавление, редактирование, удаление, просмотр списка.
- **Управление фотоработами** – привязка к фотографу и жанру, дата создания.
- **Управление выставками** – создание выставок и добавление/удаление фоторабот.
- **Управление жанрами** – CRUD для справочника жанров.
- **Аутентификация и регистрация** – пользователи входят по email/паролю.
- **Редактирование профиля** – изменение имени и фамилии.

## Демонстрация работы приложения
Ниже приведены ключевые сценарии.
Разверните спойлер, чтобы увидеть детали.

### Авторизация пользователя
Кратко: форма входа, проверка существования пользователя, валидация email, проверка пароля.

![Форма с корректными данными перед отправкой](screenshots/auth/auth-form-filled-valid.png)

<details> <summary>Подробные скриншоты авторизации (ошибки, валидация)</summary>

  - Форма входа    

![Форма входа email/пароль](screenshots/auth/auth-dialog.png)

- Попытка войти с несуществующим email
![Форма входа email отсутствует в БД только заполнение полей](screenshots/auth/auth-form-filled-unsubmitted.png)
![Форма входа email отсутствует в БД](screenshots/auth/auth-email-validation-error.png)   

- Валидация формата email
![Форма входа email написан без @](screenshots/auth/auth-user-not-found-with-bad-email.png)   
![Форма входа email введён без домена](screenshots/auth/auth-email-error-missing-domain.png)   

- Неправильный пароль
![Форма входа неправильный пароль пользователя](screenshots/auth/auth-wrong-password.png)   

- Успешный вход (данные корректны)
![Форма с корректными данными перед отправкой](screenshots/auth/auth-form-filled-valid.png)  

</details>

### Управление фотографами (CRUD)
Кратко: список фотографов, добавление, редактирование, удаление.

![Список фотографов](screenshots/photographers/home-photographers-list.png)  

<details> <summary>Подробные скриншоты работы с фотографами</summary>
Добавление:

- Исходное состояние таблицы в БД (7 записей)
![Таблица `photographer` до добавления записей](screenshots/photographers/photographers-table-before-insert.png)   

- Форма добавления
![Форма для добавления нового фотографа](screenshots/photographers/photographers-form-create-new.png)   
![Форма для добавления нового фотографа с заполненными данными](screenshots/photographers/photographers-form-completed.png)   

- Таблица после добавления (8 записей)
![Таблица `photographer` после добавления записей](screenshots/photographers/photographers-table-after-insert.png)   

- Обновлённый список на главной
![Обновлённый список фотографов](screenshots/photographers/home-photographers-list-after-insert.png)   

Редактирование:

- Карточка фотографа с действиями
![Просмотр карточки фотографа Александра Абаза](screenshots/photographers/photographer-profile-with-actions.png)   

- Форма редактирования
![Форма для редактирования данных фотографа](screenshots/photographers/photographers-form-edit.png) 

- Меняем имя на «Саша»
![Форма с изменённым именем, без сохранения](screenshots/photographers/photographer-edit-name.png)

- Результат на странице
![Просмотр карточки фотографа Александра Абаза с изменённым именем](screenshots/photographers/photographer-after-edit-name.png) 

- Проверка в БД: временные таблицы
![Временные таблицы для демонстрации обновления данных](screenshots/photographers/photographers-tables-changing-name.png)   
![Обновленная таблица фотограф](screenshots/photographers/photographers-tables-changing-name-all.png)   

Удаление:

- Буферная карточка для удаления
![Буферная карточка для демонстрации удаления](screenshots/photographers/photographer-show-delete.png) 

- Нажатие «Удалить»
![Нажатие на кнопку удалить](screenshots/photographers/photographer-show-delete-click.png)   

- Подтверждение удаления
![Окно подтверждения удаления](screenshots/photographers/photographer-show-delete-click-proof.png)

</details>

### Управление фотоработами
Кратко: список работ, добавление с выбором фотографа и жанра.

![Фотоработы фотографов](screenshots/photowork/photowork-start-page.png)

<details> <summary>Подробные скриншоты работы с фотоработами</summary>
  
  - Страница фоторабот

![Фотоработы фотографов](screenshots/photowork/photowork-start-page.png)

- Форма добавления
![Форма для добавление фотоработы](screenshots/photowork/photowork-create-new.png)
![Заполненная форма с фотоработой](screenshots/photowork/photowork-create-full.png)   

- Добавленная работа в списке
![Добавленная работа Александра Абаза](screenshots/photowork/photowork-create-show.png)

- Проверка изменения имени фотографа (Саша)
![Фотоработы смотрим на изменение имени фотографа Саша Абаза](screenshots/photowork/photowork-sasha-abaza.png) 

</details>

### Управление выставками
Кратко: список выставок, просмотр, добавление/удаление фоторабот.

https://screenshots/exhibitions/exhibitions-main-page.png

<details> <summary>Подробные скриншоты работы с выставками</summary>
  
  - Список выставок

https://screenshots/exhibitions/exhibitions-main-page.png

- Просмотр выставки (кнопка «Подробнее»)
https://screenshots/exhibitions/exhibitions-info-click.png

- Добавление работы в выставку (выпадающий список)
https://screenshots/exhibitions/exhibitions-info-insert.png
https://screenshots/exhibitions/exhibitions-info-insert-done.png

- Удаление работы с подтверждением
https://screenshots/exhibitions/exhibitions-info-delete-proof.png
https://screenshots/exhibitions/exhibitions-info-delete-proof-yes.png

- Итоговый список после изменений
https://screenshots/exhibitions/exhibitions-info-delete-insert-insert-show.png

- Создание новой выставки
https://screenshots/exhibitions/exhibitions-create.png
https://screenshots/exhibitions/exhibitions-create-new-show.png

</details>

### Управление жанрами   
Кратко: список, редактирование, удаление, добавление с проверкой уникальности.

![Список жанров](screenshots/genres/genres-main-page.png) 

<details> <summary>Подробные скриншоты работы с жанрами</summary>
  
  - Список жанров
![Список жанров](screenshots/genres/genres-main-page.png) 

Редактирование: меняем «Натюрморт» на «Ноктюрн»
![Страница редактирования жанра](screenshots/genres/genres-edit-natur.png)   
![Слово с ошибкой написали](screenshots/genres/genres-edit-fail.png)   
![Результат изменения слова](screenshots/genres/genres-edit-fail-show.png)  

- Возврат к правильному названию
![Вернули обратно слово](screenshots/genres/genres-edit-fail-show-up.png)   

- Удаление жанра (подтверждение)
![Форма подтверждения удаления жанра](screenshots/genres/genres-delete-proof.png)   

- Добавление существующего жанра (ошибка)
![Создаём жанр, который уже есть в таблице](screenshots/genres/genres-create-warning-db.png)   

- Добавление нового жанра («Интерьер»)
![Создан новый жанр](screenshots/genres/genres-create-good.png)

</details>

### Профиль пользователя   
Кратко: изменение имени и фамилии с валидацией (только буквы).

![Редактировать профиль страница](screenshots/profile/profile-edit.png)   

<details> <summary>Подробные скриншоты профиля</summary>
  
  - Страница редактирования

![Редактировать профиль страница](screenshots/profile/profile-edit.png)   

- Ошибка при вводе цифр в имя/фамилию
![В имени пользователя есть цифры](screenshots/profile/profile-edit-name-surname-fail.png)  

- Данные в БД до изменения
![Изменение имени и фамилии пользователя](screenshots/profile/profile-table.png)   

- Данные в БД после изменения
![Показываем изменение имени и фамилии](screenshots/profile/profile-table-up.png)

</details>

### Регистрация пользователя
Кратко: форма регистрации, валидация, проверка уникальности email.

![Страница регистрации пользователя](screenshots/register/register.png)   

<details> <summary>Подробные скриншоты регистрации</summary>
  
  - Страница регистрации

![Страница регистрации пользователя](screenshots/register/register.png)   

- Ошибки валидации (некорректные данные)
![Ошибки в полях заполнения](screenshots/register/register-fail.png) 

- Попытка использовать существующий email
![Повторение почты](screenshots/register/register-fail-no-uniq.png)  

- Успешная регистрация
![Успешная регистрация](screenshots/register/register-good.png)

</details>

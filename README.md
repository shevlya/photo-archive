# Photo Archive Application

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue)

## Содержание
- [Описание](#описание)
- [Технологии](#технологии)
- [Возможности приложения](#возможности-приложения)
- [Демонстрация работы приложения](#демонстрация-работы-приложения)
  - [Авторизация пользователя](#авторизация-пользователя)
  - [Управление фотографами](#управление-фотографами)
  - [Управление фотоработами](#управление-фотоработами)
  - [Управление выставками](#управление-выставками)
  - [Управление жанрами](#управление-жанрами)
  - [Профиль пользователя](#профиль-пользователя)
  - [Регистрация пользователя](#регистрация-пользователя)

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
Ниже приведены ключевые сценарии. Разверните спойлер, чтобы увидеть детали.

---

### Авторизация пользователя
Кратко: форма входа, проверка существования пользователя, валидация email, проверка пароля.

<p align="center">
  <img src="screenshots/auth/auth-form-filled-valid.png" alt="Форма входа с корректными данными" width="500"/>
  <br/>
  <em>Рисунок 1. Форма с корректными данными перед отправкой</em>
</p>

<details>
<summary>Подробные скриншоты авторизации (ошибки, валидация)</summary>

<br/>

#### Форма входа
<p align="center">
  <img src="screenshots/auth/auth-dialog.png" alt="Форма входа email/пароль" width="400"/>
  <br/>
  <em>Рисунок 2. Форма входа email/пароль</em>
</p>

#### Попытка войти с несуществующим email
<p align="center">
  <img src="screenshots/auth/auth-form-filled-unsubmitted.png" alt="Форма входа до отправки" width="400"/>
  <br/>
  <em>Рисунок 3. Только заполнение полей</em>
</p>

<p align="center">
  <img src="screenshots/auth/auth-email-validation-error.png" alt="Email отсутствует в БД" width="400"/>
  <br/>
  <em>Рисунок 4. Email отсутствует в БД</em>
</p>

#### Валидация формата email
<p align="center">
  <img src="screenshots/auth/auth-user-not-found-with-bad-email.png" alt="Email без @" width="400"/>
  <br/>
  <em>Рисунок 5. Email написан без @</em>
</p>

<p align="center">
  <img src="screenshots/auth/auth-email-error-missing-domain.png" alt="Email без домена" width="400"/>
  <br/>
  <em>Рисунок 6. Email введён без домена</em>
</p>

#### Неправильный пароль
<p align="center">
  <img src="screenshots/auth/auth-wrong-password.png" alt="Неправильный пароль" width="400"/>
  <br/>
  <em>Рисунок 7. Неправильный пароль пользователя</em>
</p>

#### Успешный вход
<p align="center">
  <img src="screenshots/auth/auth-form-filled-valid.png" alt="Успешный вход" width="500"/>
  <br/>
  <em>Рисунок 8. Форма с корректными данными перед отправкой</em>
</p>

</details>

---

### Управление фотографами (CRUD)
Кратко: список фотографов, добавление, редактирование, удаление.

<p align="center">
  <img src="screenshots/photographers/home-photographers-list.png" alt="Список фотографов" width="700"/>
  <br/>
  <em>Рисунок 9. Список фотографов</em>
</p>

<details>
<summary>Подробные скриншоты работы с фотографами</summary>

<br/>

#### Добавление

<p align="center">
  <img src="screenshots/photographers/photographers-table-before-insert.png" alt="Таблица до добавления" width="700"/>
  <br/>
  <em>Рисунок 10. Таблица <code>photographer</code> до добавления записей (7 записей)</em>
</p>

<p align="center">
  <img src="screenshots/photographers/photographers-form-create-new.png" alt="Форма добавления" width="500"/>
  <br/>
  <em>Рисунок 11. Форма для добавления нового фотографа</em>
</p>

<p align="center">
  <img src="screenshots/photographers/photographers-form-completed.png" alt="Заполненная форма" width="500"/>
  <br/>
  <em>Рисунок 12. Форма с заполненными данными</em>
</p>

<p align="center">
  <img src="screenshots/photographers/photographers-table-after-insert.png" alt="Таблица после добавления" width="700"/>
  <br/>
  <em>Рисунок 13. Таблица <code>photographer</code> после добавления (8 записей)</em>
</p>

<p align="center">
  <img src="screenshots/photographers/home-photographers-list-after-insert.png" alt="Обновлённый список" width="700"/>
  <br/>
  <em>Рисунок 14. Обновлённый список фотографов</em>
</p>

#### Редактирование

<p align="center">
  <img src="screenshots/photographers/photographer-profile-with-actions.png" alt="Карточка фотографа" width="500"/>
  <br/>
  <em>Рисунок 15. Просмотр карточки фотографа Александра Абаза</em>
</p>

<p align="center">
  <img src="screenshots/photographers/photographers-form-edit.png" alt="Форма редактирования" width="500"/>
  <br/>
  <em>Рисунок 16. Форма для редактирования данных фотографа</em>
</p>

<p align="center">
  <img src="screenshots/photographers/photographer-edit-name.png" alt="Изменение имени" width="500"/>
  <br/>
  <em>Рисунок 17. Форма с изменённым именем, без сохранения</em>
</p>

<p align="center">
  <img src="screenshots/photographers/photographer-after-edit-name.png" alt="Результат редактирования" width="500"/>
  <br/>
  <em>Рисунок 18. Карточка фотографа с изменённым именем</em>
</p>

<p align="center">
  <img src="screenshots/photographers/photographers-tables-changing-name.png" alt="Временные таблицы" width="700"/>
  <br/>
  <em>Рисунок 19. Временные таблицы для демонстрации обновления данных</em>
</p>

<p align="center">
  <img src="screenshots/photographers/photographers-tables-changing-name-all.png" alt="Обновлённая таблица" width="700"/>
  <br/>
  <em>Рисунок 20. Обновлённая таблица photographer</em>
</p>

#### Удаление

<p align="center">
  <img src="screenshots/photographers/photographer-show-delete.png" alt="Буферная карточка" width="500"/>
  <br/>
  <em>Рисунок 21. Буферная карточка для демонстрации удаления</em>
</p>

<p align="center">
  <img src="screenshots/photographers/photographer-show-delete-click.png" alt="Нажатие Удалить" width="500"/>
  <br/>
  <em>Рисунок 22. Нажатие на кнопку «Удалить»</em>
</p>

<p align="center">
  <img src="screenshots/photographers/photographer-show-delete-click-proof.png" alt="Подтверждение удаления" width="500"/>
  <br/>
  <em>Рисунок 23. Окно подтверждения удаления</em>
</p>

</details>

---

### Управление фотоработами
Кратко: список работ, добавление с выбором фотографа и жанра.

<p align="center">
  <img src="screenshots/photowork/photowork-start-page.png" alt="Фотоработы" width="700"/>
  <br/>
  <em>Рисунок 24. Фотоработы фотографов</em>
</p>

<details>
<summary>Подробные скриншоты работы с фотоработами</summary>

<br/>

<p align="center">
  <img src="screenshots/photowork/photowork-start-page.png" alt="Страница фоторабот" width="700"/>
  <br/>
  <em>Рисунок 25. Страница фоторабот</em>
</p>

<p align="center">
  <img src="screenshots/photowork/photowork-create-new.png" alt="Форма добавления фотоработы" width="500"/>
  <br/>
  <em>Рисунок 26. Форма для добавления фотоработы</em>
</p>

<p align="center">
  <img src="screenshots/photowork/photowork-create-full.png" alt="Заполненная форма" width="500"/>
  <br/>
  <em>Рисунок 27. Заполненная форма с фотоработой</em>
</p>

<p align="center">
  <img src="screenshots/photowork/photowork-create-show.png" alt="Добавленная работа" width="700"/>
  <br/>
  <em>Рисунок 28. Добавленная работа Александра Абаза</em>
</p>

<p align="center">
  <img src="screenshots/photowork/photowork-sasha-abaza.png" alt="Проверка изменения имени" width="700"/>
  <br/>
  <em>Рисунок 29. Проверка изменения имени фотографа Саша Абаза</em>
</p>

</details>

---

### Управление выставками
Кратко: список выставок, просмотр, добавление/удаление фоторабот.

<p align="center">
  <img src="screenshots/exhibitions/exhibitions-main-page.png" alt="Список выставок" width="700"/>
  <br/>
  <em>Рисунок 30. Страница выставок. Список выставок</em>
</p>

<details>
<summary>Подробные скриншоты работы с выставками</summary>

<br/>

<p align="center">
  <img src="screenshots/exhibitions/exhibitions-main-page.png" alt="Список выставок" width="700"/>
  <br/>
  <em>Рисунок 31. Список выставок</em>
</p>

<p align="center">
  <img src="screenshots/exhibitions/exhibitions-info-click.png" alt="Просмотр выставки" width="500"/>
  <br/>
  <em>Рисунок 32. Страница с информацией о выставке (кнопка «Подробнее»)</em>
</p>

<p align="center">
  <img src="screenshots/exhibitions/exhibitions-info-insert.png" alt="Список добавления фотоработы" width="500"/>
  <br/>
  <em>Рисунок 33. Добавление работы в выставку (выпадающий список)</em>
</p>

<p align="center">
  <img src="screenshots/exhibitions/exhibitions-info-insert-done.png" alt="Добавлена фоторабота" width="500"/>
  <br/>
  <em>Рисунок 34. Добавлена фоторабота</em>
</p>

<p align="center">
  <img src="screenshots/exhibitions/exhibitions-info-delete-proof.png" alt="Удаление работы" width="500"/>
  <br/>
  <em>Рисунок 35. Удаление работы — окно проверки</em>
</p>

<p align="center">
  <img src="screenshots/exhibitions/exhibitions-info-delete-proof-yes.png" alt="Подтверждение удаления" width="500"/>
  <br/>
  <em>Рисунок 36. Удаление работы — согласие на удаление</em>
</p>

<p align="center">
  <img src="screenshots/exhibitions/exhibitions-info-delete-insert-insert-show.png" alt="Итоговый список" width="700"/>
  <br/>
  <em>Рисунок 37. Итоговый список после изменений</em>
</p>

<p align="center">
  <img src="screenshots/exhibitions/exhibitions-create.png" alt="Создание выставки" width="500"/>
  <br/>
  <em>Рисунок 38. Добавление выставки</em>
</p>

<p align="center">
  <img src="screenshots/exhibitions/exhibitions-create-new-show.png" alt="Создание выставки (результат)" width="700"/>
  <br/>
  <em>Рисунок 39. Добавление выставки — результат</em>
</p>

</details>

---

### Управление жанрами
Кратко: список, редактирование, удаление, добавление с проверкой уникальности.

<p align="center">
  <img src="screenshots/genres/genres-main-page.png" alt="Список жанров" width="700"/>
  <br/>
  <em>Рисунок 40. Список жанров</em>
</p>

<details>
<summary>Подробные скриншоты работы с жанрами</summary>

<br/>

<p align="center">
  <img src="screenshots/genres/genres-main-page.png" alt="Список жанров" width="700"/>
  <br/>
  <em>Рисунок 41. Список жанров</em>
</p>

#### Редактирование (меняем «Натюрморт» на «Ноктюрн»)

<p align="center">
  <img src="screenshots/genres/genres-edit-natur.png" alt="Страница редактирования" width="500"/>
  <br/>
  <em>Рисунок 42. Страница редактирования жанра</em>
</p>

<p align="center">
  <img src="screenshots/genres/genres-edit-fail.png" alt="Ошибка написания" width="500"/>
  <br/>
  <em>Рисунок 43. Слово с ошибкой</em>
</p>

<p align="center">
  <img src="screenshots/genres/genres-edit-fail-show.png" alt="Результат изменения" width="700"/>
  <br/>
  <em>Рисунок 44. Результат изменения слова</em>
</p>

<p align="center">
  <img src="screenshots/genres/genres-edit-fail-show-up.png" alt="Возврат к правильному названию" width="700"/>
  <br/>
  <em>Рисунок 45. Возврат к правильному названию</em>
</p>

#### Удаление жанра

<p align="center">
  <img src="screenshots/genres/genres-delete-proof.png" alt="Подтверждение удаления" width="500"/>
  <br/>
  <em>Рисунок 46. Форма подтверждения удаления жанра</em>
</p>

#### Добавление жанра

<p align="center">
  <img src="screenshots/genres/genres-create-warning-db.png" alt="Ошибка дубликата" width="500"/>
  <br/>
  <em>Рисунок 47. Добавление существующего жанра (ошибка)</em>
</p>

<p align="center">
  <img src="screenshots/genres/genres-create-good.png" alt="Создание нового жанра" width="500"/>
  <br/>
  <em>Рисунок 48. Добавление нового жанра «Интерьер»</em>
</p>

</details>

---

### Профиль пользователя
Кратко: изменение имени и фамилии с валидацией (только буквы).

<p align="center">
  <img src="screenshots/profile/profile-edit.png" alt="Редактирование профиля" width="500"/>
  <br/>
  <em>Рисунок 49. Страница редактирования профиля</em>
</p>

<details>
<summary>Подробные скриншоты профиля</summary>

<br/>

<p align="center">
  <img src="screenshots/profile/profile-edit.png" alt="Страница редактирования" width="500"/>
  <br/>
  <em>Рисунок 50. Страница редактирования профиля</em>
</p>

<p align="center">
  <img src="screenshots/profile/profile-edit-name-surname-fail.png" alt="Ошибка валидации" width="500"/>
  <br/>
  <em>Рисунок 51. Ошибка при вводе цифр в имя/фамилию</em>
</p>

<p align="center">
  <img src="screenshots/profile/profile-table.png" alt="Данные в БД до изменения" width="700"/>
  <br/>
  <em>Рисунок 52. Данные в БД до изменения</em>
</p>

<p align="center">
  <img src="screenshots/profile/profile-table-up.png" alt="Данные в БД после изменения" width="700"/>
  <br/>
  <em>Рисунок 53. Данные в БД после изменения</em>
</p>

</details>

---

### Регистрация пользователя
Кратко: форма регистрации, валидация, проверка уникальности email.

<p align="center">
  <img src="screenshots/register/register.png" alt="Страница регистрации" width="500"/>
  <br/>
  <em>Рисунок 54. Страница регистрации пользователя</em>
</p>

<details>
<summary>Подробные скриншоты регистрации</summary>

<br/>

<p align="center">
  <img src="screenshots/register/register.png" alt="Страница регистрации" width="500"/>
  <br/>
  <em>Рисунок 55. Страница регистрации пользователя</em>
</p>

<p align="center">
  <img src="screenshots/register/register-fail.png" alt="Ошибки валидации" width="500"/>
  <br/>
  <em>Рисунок 56. Ошибки валидации (некорректные данные)</em>
</p>

<p align="center">
  <img src="screenshots/register/register-fail-no-uniq.png" alt="Повторение email" width="500"/>
  <br/>
  <em>Рисунок 57. Попытка использовать существующий email</em>
</p>

<p align="center">
  <img src="screenshots/register/register-good.png" alt="Успешная регистрация" width="500"/>
  <br/>
  <em>Рисунок 58. Успешная регистрация</em>
</p>

</details>

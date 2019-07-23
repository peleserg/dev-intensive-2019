package ru.skillbranch.devintensive.viewmodels

// TODO 07
// Text Input Layout error
// Необходимо реализовать вадидацию вводимых пользователем данных в поле @id/et_repository на соответствие url
// валидному github аккаунту
// Реализуй валидацию вводимых пользователем данных в поле @id/et_repository на соответствие url валидному github
// аккаунту, вводимое значение должно содержать домен github.com (https://, www, https://www) и аккаунт пользователя
// (пути для исключения прикреплены в ресурсах урока). Если URL невалиден, выводить сообщение "невалидный адрес
// репозитория" в TextInputLayout (wr_repository.error(message)) и запрещать сохранение невалидного значения в
// SharedPreferences (при попытке сохранить невалидное поле очищать et_repository при нажатии @id/btn_edit)
// Пример:
// https://github.com/john_doe //валиден
// https://www.github.com/john_doe //валиден
// www.github.com/john_doe //валиден
// github.com/john_doe //валиден
// https://anyDomain.github.com/john_doe //невалиден
// https://github.com/ //невалиден
// https://github.com //невалиден
// https://github.com/john_doe/tree //невалиден
// https://github.com/john_doe/tree/something //невалиден
// https://github.com/enterprise //невалиден
// https://github.com/pricing //невалиден
// https://github.com/join //невалиден
// Список исключений:
// enterprise
// features
// topics
// collections
// trending
// events
// marketplace
// pricing
// nonprofit
// customer-stories
// security
// login
// join

// TODO 08
// **Преобразование Инициалов в Drawable
// Необходимо реализовать программное преобразование инициалов пользователя в Drawable с цветным фоном и буквами
// Реализуй программное преобразование инициалов пользователя (если доступны - заполнено хотя бы одно поле) в Drawable
// с фоном colorAccent (c учетом темы) и буквами инициалов (colorWhite) и установи полученное изображение как
// изображение по умолчанию для профиля пользователя

class ProfileViewModel {
}
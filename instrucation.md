# Инструкция по запуску приложения Music Player

## 1. Клонирование проекта 
С клонируйте проект на свой компьютер:
```sh

git clone https://github.com/Vikviita/Music-Player-Avito.git
```
## 2. Открытие проекта
- Откройте проект **Android Studio** 
- Выберите **“Open an Existing Project”** и укажите папку проекта.
- Убедитесь что у вас установлен JDK 17
## 3. Запуск приложения 
- Подключите эмулятор или реальное устройство с Android 7.0+(min sdk 24).
- Запустите приложение через **"Run"** в Android Studio




## Зависимости используемые в проекте
- Retrofit
- Dagger2
- Jetpack Media 3 ExoPlayer
- Jetpack Media 3 MediaSession
- Kotlin Serialization
- Json
- Jetpack Navigation(Compose)
- Jetpack Compose
- Coil 
- Material 3

## Используемы разрешения
- android.permission.READ_EXTERNAL_STORAGE
- android.permission.READ_MEDIA_AUDIO
- android.permission.INTERNET
- android.permission.FOREGROUND_SERVICE
- android.permission.FOREGROUND_SERVICE_MEDIA_PLAYBACK

## API
### Deezer api (https://api.deezer.com/)
- Начальный лист треков: https://api.deezer.com/chart
- Поиск треков по имени: https://api.deezer.com/search?q=name
- Получение трека по id: https://api.deezer.com/track/id
- Получение альбома по id: https://api.deezer.com/album/id
 

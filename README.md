# Passport Management System 📂🆔

Bu proyekt şəxslərin (`Person`) və onlara bağlı olan pasportların (`Passport`) idarə olunması üçün hazırlanmış **Spring Boot** tətbiqidir. Sistem vasitəsilə şəxs məlumatlarını əlavə etmək, onlara müxtəlif növ pasportlar təyin etmək və ətraflı axtarışlar aparmaq mümkündür.

## Xüsusiyyətlər
* **Person Management**: Şəxslərin yaradılması, oxunması, yenilənməsi və silinməsi (CRUD).
* **Passport Management**: Pasportların şəxslərə bağlanması (One-to-One əlaqəsi).
* **Advanced Search**: Pasport növü, seriya nömrəsi və s. parametrlər üzrə filtrasiya.
* **Pagination & Sorting**: Məlumatların səhifələnmiş şəkildə sürətli yüklənməsi və sıralanması.
* **Global Exception Handling**: Xətaların mərkəzləşdirilmiş idarə olunması və xüsusi xəta mesajları.
* **Validation**: `@Valid` annotasiyaları ilə daxil edilən məlumatların doğruluğunun yoxlanılması.

## 🛠 Texnologiyalar
* **Java 17**
* **Spring Boot 3.4.1**
* **Spring Data JPA** (PostgreSQL)
* **MapStruct** (Entity-DTO mapping)
* **Lombok** (Kodun sadələşdirilməsi üçün)
* **Swagger/OpenAPI** (API sənədləşməsi və test interfeysi)

## 🚀 Başlamaq üçün

### Tələblər
* **JDK 17**
* **PostgreSQL** (Verilənlər bazası adı: `passportManagement`)
* **Gradle 8.x**

### Quraşdırılma və İşə Salma
1. Proyekti GitHub-dan klonlayın:
   ```bash
   git clone [https://github.com/teymurovtural/passport-management-system.git](https://github.com/teymurovtural/passport-management-system.git)
2. src/main/resources/application.properties faylında verilənlər bazası giriş məlumatlarını (username/password) öz mühitinizə uyğun dəyişin.
3. Proyekti terminal vasitəsilə işə salın: ./gradlew bootRun -x test

API Sənədləşməsi (Swagger UI)
Proqram işə düşdükdən sonra bütün API endpoint-lərini görmək, sorğular göndərmək və test etmək üçün brauzerdə bu ünvana daxil olun: http://localhost:8080/swagger-ui/index.html

Məlumat Modeli
Proyekt One-to-One (biri-birə) əlaqəsi üzərində qurulub:

Hər bir Person-un (Şəxs) yalnız bir aktiv Passport-u ola bilər.

Pasportlar üçün 4 əsas növ dəstəklənir: ORDINARY, DIPLOMATIC, SERVICE, OFFICIAL.

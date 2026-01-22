# CaseFlow Backend ⚖️

![Java CI with Maven](https://github.com/gunerHakan/CaseFlow/actions/workflows/maven.yml/badge.svg)

CaseFlow, hukuk büroları ve müvekkiller arasındaki dava takip süreçlerini dijitalleştiren, güvenli ve performanslı bir RESTful API projesidir.

Bu proje, modern yazılım geliştirme prensipleri, **Clean Architecture**, **Event-Driven Architecture**, **CI/CD** süreçleri ve **Spring Boot** ekosistemi kullanılarak geliştirilmiştir.

## 🚀 Özellikler

*   **Dava Yönetimi (Case Management):** Avukatlar için dava oluşturma, güncelleme, listeleme ve silme (CRUD) işlemleri.
*   **Müvekkil Portalı:** Müvekkillerin kendilerine ait davaları güvenli bir şekilde görüntüleyebilmesi.
*   **Event-Driven Mimari:** **RabbitMQ** kullanılarak asenkron mesajlaşma ve bildirim (Notification) altyapısı.
*   **Güvenlik & Yetkilendirme:** Spring Security ile korunan endpoint'ler ve rol tabanlı erişim kontrolleri.
*   **Performans & Caching:** Sık erişilen veriler (Dava listeleri vb.) için **Redis** tabanlı önbellekleme mekanizması.
*   **CI/CD Pipeline:** **GitHub Actions** ile her push işleminde otomatik derleme ve test koşumu.
*   **Logging:** **Slf4j** ile yapılandırılmış, seviye bazlı (INFO, WARN, ERROR) loglama altyapısı.
*   **Test Kapsamı:** İş mantığı katmanı (Service Layer) için **JUnit 5** ve **Mockito** ile yazılmış birim testleri (Unit Tests).
*   **API Dokümantasyonu:** **Swagger UI (OpenAPI)** ile interaktif API dokümantasyonu.
*   **Veri Bütünlüğü:** Transaction yönetimi (`@Transactional`) ve veri doğrulama (`Jakarta Validation`).
*   **Modern Java:** DTO'lar için Java `Record` yapılarının kullanımı.

## 🛠 Teknolojiler ve Araçlar

Projede kullanılan temel teknoloji yığını:

*   **Dil:** Java 17
*   **Framework:** Spring Boot 3.x
*   **Veri Erişimi:** Spring Data JPA (Hibernate)
*   **Güvenlik:** Spring Security
*   **Veritabanı:** PostgreSQL (veya H2 in-memory)
*   **Caching:** Spring Cache & Redis
*   **Message Broker:** RabbitMQ
*   **DevOps:** GitHub Actions (CI/CD), Docker & Docker Compose
*   **Testing:** JUnit 5, Mockito
*   **Logging:** Slf4j
*   **API Dokümantasyonu:** SpringDoc OpenAPI (Swagger)
*   **Build Tool:** Maven

## 🏗 Mimari Yapı

Proje, sürdürülebilirlik ve test edilebilirlik için katmanlı mimari (Layered Architecture) prensiplerine uygun olarak tasarlanmıştır:

1.  **Controller Layer:** HTTP isteklerini karşılar, validasyon yapar.
2.  **Service Layer:** İş mantığını (Business Logic) barındırır.
3.  **Repository Layer:** Veritabanı ile iletişimi sağlar.
4.  **DTO & Mapper:** Entity nesnelerini doğrudan dışarı açmak yerine, `Record` tabanlı DTO'lar ve Mapper sınıfları kullanılarak veri transferi güvenli hale getirilmiştir.

## 📦 Kurulum ve Çalıştırma

Projeyi yerel ortamınızda çalıştırmak için aşağıdaki adımları izleyin.

### Ön Gereksinimler
*   Java 17
*   Docker & Docker Compose

### Adımlar

1.  **Repoyu klonlayın:**
    ```bash
    git clone https://github.com/gunerHakan/CaseFlow.git
    cd CaseFlow
    ```

2.  **Altyapı Servislerini Başlatın (PostgreSQL, Redis, RabbitMQ):**
    ```bash
    docker-compose up -d
    ```

3.  **Uygulamayı Başlatın:**
    ```bash
    ./mvnw spring-boot:run
    ```

4.  **Testleri Çalıştırın:**
    ```bash
    ./mvnw test
    ```

## 📖 API Dokümantasyonu

Uygulama çalıştıktan sonra aşağıdaki adresten interaktif API dokümantasyonuna erişebilirsiniz:

👉 **Swagger UI:** `http://localhost:8080/swagger-ui.html`

## 🐇 RabbitMQ Yönetim Paneli

Mesaj kuyruklarını izlemek için:
👉 **URL:** `http://localhost:15672`
👉 **Kullanıcı/Şifre:** `guest` / `guest`

## 🔍 Örnek API İstekleri

**Yeni Dava Oluşturma:**
```http
POST /api/cases?clientId={uuid}
Content-Type: application/json

{
  "caseNumber": "2023/101",
  "title": "Tazminat Davası",
  "description": "İş kazası kaynaklı tazminat talebi."
}
```

**Dava Sorgulama (Cache Destekli):**
```http
GET /api/cases/2023/101?clientId={uuid}
```

## 🤝 İletişim

Proje hakkında sorularınız veya önerileriniz için benimle iletişime geçebilirsiniz.

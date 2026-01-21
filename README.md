# CaseFlow Backend ⚖️

CaseFlow, hukuk büroları ve müvekkiller arasındaki dava takip süreçlerini dijitalleştiren, güvenli ve performanslı bir RESTful API projesidir.

Bu proje, modern yazılım geliştirme prensipleri, **Clean Architecture** ve **Spring Boot** ekosistemi kullanılarak geliştirilmiştir.

## 🚀 Özellikler

*   **Dava Yönetimi (Case Management):** Avukatlar için dava oluşturma, güncelleme, listeleme ve silme (CRUD) işlemleri.
*   **Müvekkil Portalı:** Müvekkillerin kendilerine ait davaları güvenli bir şekilde görüntüleyebilmesi.
*   **Güvenlik & Yetkilendirme:** Spring Security ile korunan endpoint'ler ve rol tabanlı erişim kontrolleri.
*   **Performans & Caching:** Sık erişilen veriler (Dava listeleri vb.) için **Redis** tabanlı önbellekleme mekanizması.
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
*   **API Dokümantasyonu:** (Planlanan: Swagger/OpenAPI)
*   **Build Tool:** Maven/Gradle

## 🏗 Mimari Yapı

Proje, sürdürülebilirlik ve test edilebilirlik için katmanlı mimari (Layered Architecture) prensiplerine uygun olarak tasarlanmıştır:

1.  **Controller Layer:** HTTP isteklerini karşılar, validasyon yapar.
2.  **Service Layer:** İş mantığını (Business Logic) barındırır.
3.  **Repository Layer:** Veritabanı ile iletişimi sağlar.
4.  **DTO & Mapper:** Entity nesnelerini doğrudan dışarı açmak yerine, `Record` tabanlı DTO'lar ve Mapper sınıfları kullanılarak veri transferi güvenli hale getirilmiştir.

## 📦 Kurulum

Projeyi yerel ortamınızda çalıştırmak için:

1.  Repoyu klonlayın:
    ```bash
    git clone https://github.com/kullaniciadi/caseflow-backend.git
    ```
2.  Proje dizinine gidin:
    ```bash
    cd caseflow-backend
    ```
3.  Gerekli bağımlılıkları yükleyin ve projeyi başlatın:
    ```bash
    ./mvnw spring-boot:run
    ```

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

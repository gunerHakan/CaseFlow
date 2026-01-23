# CaseFlow - Full-Stack Dava Yönetim Sistemi ⚖️

![Java CI with Maven](https://github.com/gunerHakan/CaseFlow/actions/workflows/maven.yml/badge.svg)

**CaseFlow**, avukatlar ve müvekkiller için tasarlanmış, modern ve ölçeklenebilir bir dava yönetim platformudur. Bu proje, **Spring Boot** tabanlı güçlü bir backend ile **React Native** tabanlı bir mobil uygulamayı bir araya getiren bir monorepo yapısında geliştirilmiştir.

Projenin amacı, karmaşık hukuki süreçleri basitleştirmek, iletişimi merkezileştirmek ve performanstan ödün vermeden güvenli bir dijital deneyim sunmaktır.

---

## ✨ Teknoloji Haritası

Bu projede kullanılan teknolojiler, modern yazılım geliştirme standartları ve en iyi pratikler göz önünde bulundurularak seçilmiştir.

### **Backend**

| Kategori             | Teknoloji                                                                                                                            |
| -------------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
| **Framework**        | <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" />                     |
| **Dil**              | <img src="https://img.shields.io/badge/Java_17-007396?style=for-the-badge&logo=java&logoColor=white" />                                |
| **Veritabanı**       | <img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" />                      |
| **Güvenlik**         | <img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring&logoColor=white" /> **JWT**              |
| **Asenkron İşlemler**| <img src="https://img.shields.io/badge/RabbitMQ-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white" />                           |
| **Caching**          | <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white" />                                 |
| **API Dokümantasyonu**| <img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black" />                            |
| **DevOps & CI/CD**   | <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" /> <img src="https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white" /> |
| **Test**             | <img src="https://img.shields.io/badge/JUnit_5-25A162?style=for-the-badge&logo=junit5&logoColor=white" /> <img src="https://img.shields.io/badge/Mockito-4A4A4A?style=for-the-badge&logo=mockito&logoColor=white" /> |

### **Frontend (Mobile)**

| Kategori      | Teknoloji                                                                                                                              |
| ------------- | -------------------------------------------------------------------------------------------------------------------------------------- |
| **Framework** | <img src="https://img.shields.io/badge/React_Native-20232A?style=for-the-badge&logo=react&logoColor=61DAFB" />                          |
| **Dil**       | <img src="https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=typescript&logoColor=white" />                         |
| **Stil**      | <img src="https://img.shields.io/badge/StyleSheet-20232A?style=for-the-badge&logo=react&logoColor=61DAFB" /> (Native)                    |
| **Paket Yöneticisi** | <img src="https://img.shields.io/badge/NPM-CB3837?style=for-the-badge&logo=npm&logoColor=white" />                               |

---

## 🚀 Temel Özellikler

*   **Katmanlı & Olay Güdümlü Mimari:** Backend, sürdürülebilirlik için **Katmanlı Mimari** ve ölçeklenebilirlik için **Olay Güdümlü (Event-Driven)** prensiplerle tasarlanmıştır.
*   **Rol Tabanlı Güvenlik:** **Spring Security** ve **JWT** ile her endpoint için rol bazlı (Avukat, Müvekkil) erişim kontrolü.
*   **Asenkron Bildirimler:** **RabbitMQ** sayesinde, yeni bir dava oluşturulduğunda veya güncellendiğinde ilgili kişilere asenkron olarak e-posta/bildirim gönderimi.
*   **Yüksek Performanslı Sorgular:** Sık erişilen veriler, **Redis** ile önbelleğe alınarak veritabanı yükü azaltılmış ve API tepki süreleri iyileştirilmiştir.
*   **Kapsamlı API Dokümantasyonu:** **Swagger (OpenAPI)** ile otomatik olarak oluşturulan, interaktif ve kolay anlaşılır API dokümantasyonu.
*   **Konteynerize Altyapı:** **Docker & Docker Compose** sayesinde, tüm altyapı servisleri (PostgreSQL, Redis, RabbitMQ) tek bir komutla ayağa kaldırılabilir.
*   **Sürekli Entegrasyon (CI):** **GitHub Actions** ile her `push` işleminde otomatik olarak testlerin çalıştırılması ve projenin derlenmesi.

---

## 🖼️ Ekran Görüntüleri

*(Buraya mobil uygulamanın giriş ekranı ve ana ekranı gibi 1-2 görsel ekleyebilirsiniz.)*

<p align="center">
  <img src="/backend/src/main/resources/templates/caseflow_login_screen.png" width="300" alt="Login Screen">
  &nbsp; &nbsp; &nbsp;
  <img src="/backend/src/main/resources/templates/lawyer_main_screen.png" width="300" alt="Lawyer Main Screen">
</p>

---

## ⚡ Hızlı Başlangıç

Projeyi yerel makinenizde çalıştırmak için aşağıdaki adımları izleyin.

### **1. Backend'i Çalıştırma**

```bash
# Proje ana dizinine gidin
cd backend

# Gerekli servisleri (PostgreSQL, Redis, RabbitMQ) başlatın
docker-compose up -d

# Spring Boot uygulamasını çalıştırın
./mvnw spring-boot:run
```
> Backend API'sine `http://localhost:8080/swagger-ui.html` adresinden erişebilirsiniz.

### **2. Mobil Uygulamayı Çalıştırma**

*Detaylı ortam kurulumu (Android Studio, SDK, vb.) için `mobile/ANDROID_SETUP.md` dosyasına göz atın.*

```bash
# Proje ana dizinine gidin
cd mobile

# Bağımlılıkları yükleyin
npm install

# Uygulamayı Android emülatöründe başlatın
npm run android
```

---

## 🤝 İletişim

Proje hakkında sorularınız veya önerileriniz için benimle iletişime geçebilirsiniz.

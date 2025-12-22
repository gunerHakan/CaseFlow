# CaseFlow Backend

CaseFlow, avukatlık alanında **müvekkil ve dava yönetimi** yapan bir Spring Boot backend API projesidir.  
Projede, avukatlar müvekkillerin davalarını takip edebilir; müvekkiller yalnızca kendi davalarını görüntüleyebilir.

## Teknolojiler

- Java 17
- Spring Boot 3.5.x
- PostgreSQL (Docker ile)
- Maven
- Jakarta Validation
- UUID tabanlı ID sistemi

## Paket Yapısı

com.law.caseflow
├── domain
│    ├── entity         // CaseFile ve Client entity
│    └── enums          // CaseStatus enum
├── dto                 // DTO sınıfları (CreateRequest, Response)
├── repository          // JPA repository interface’leri
├── service
│    ├── mapper         // Entity ↔ DTO mapping
│    └── service sınıfları
└── controller          // REST API controller’ları
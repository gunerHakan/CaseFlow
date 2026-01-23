# 1. Altyapı olarak Java 17 kullan (Proje ayarımızla uyumlu)
FROM amazoncorretto:17

# 2. Çalışma klasörünü belirle
WORKDIR /app

# 3. Oluşan .jar dosyasını container içine kopyala
# (target klasöründeki jar ismini dinamik alıyoruz)
COPY target/*.jar app.jar

# 4. Uygulamanın çalışacağı portu belirt (Bilgi amaçlı)
EXPOSE 8080

# 5. Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "app.jar"]
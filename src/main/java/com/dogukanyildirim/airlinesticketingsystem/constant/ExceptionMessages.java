package com.dogukanyildirim.airlinesticketingsystem.constant;

public class ExceptionMessages {
    public static final String UNEXPECTED_ERROR = "Beklenmeyen bir hata oluştu!";

    public static final String ID_MUST_NOT_BE_NULL = "ID boş olmamalı!";
    public static final String AIRLINE_COMPANY_OBJECT_MUST_NOT_BE_NULL = "Havayolu şirket nesnesi boş olamaz!";
    public static final String AIRLINE_COMPANY_OBJECT_OR_ID_MUST_NOT_BE_NULL = "Havayolu şirketi nesnesi veya ID boş olamaz!";
    public static final String AIRLINE_COMPANY_NOT_FOUND = "Kaydedilecek havayolu şirket nesnesi boş olamaz!";

    public static final String AIRPLANE_OBJECT_MUST_NOT_BE_NULL = "Uçak nesnesi boş olamaz!";
    public static final String AIRPLANE_OBJECT_OR_ID_MUST_NOT_BE_NULL = "Uçak nesnesi veya ID boş olamaz!";
    public static final String AIRPLANE_NOT_FOUND = "Kaydedilecek uçak nesnesi boş olamaz!";

    public static final String AIRPORT_OBJECT_MUST_NOT_BE_NULL = "Havaalanı nesnesi boş olamaz!";
    public static final String AIRPORT_OBJECT_OR_ID_MUST_NOT_BE_NULL = "Havaalanı nesnesi veya ID boş olamaz!";
    public static final String AIRPORT_NOT_FOUND = "Kaydedilecek havaalanı nesnesi boş olamaz!";

    public static final String ROUTE_OBJECT_MUST_NOT_BE_NULL = "Rota nesnesi boş olamaz!";
    public static final String ROUTE_OBJECT_OR_ID_MUST_NOT_BE_NULL = "Rota nesnesi veya ID boş olamaz!";
    public static final String ROUTE_NOT_FOUND = "Kaydedilecek rota nesnesi boş olamaz!";
    public static final String ROUTE_IS_NULL = "Rota bulunamadı.";
    public static final String FLIGHT_DATE_IS_NULL = "Uçuş tarihi bulunamadı.";

    public static final String FLIGHT_OBJECT_MUST_NOT_BE_NULL = "Uçuş nesnesi boş olamaz!";
    public static final String FLIGHT_OBJECT_OR_ID_MUST_NOT_BE_NULL = "Uçuş nesnesi veya ID boş olamaz!";
    public static final String FLIGHT_NOT_FOUND = "Kaydedilecek uçuş nesnesi boş olamaz!";
    public static final String ANY_FLIGHT_NOT_FOUND = "Belirtilen bilgilerle herhangi bir uçuş bulunamadı.";

    public static final String PASSENGER_OBJECT_MUST_NOT_BE_NULL = "Yolcu nesnesi boş olamaz!";
    public static final String PASSENGER_OBJECT_OR_ID_MUST_NOT_BE_NULL = "Yolcu nesnesi veya ID boş olamaz!";
    public static final String PASSENGER_NOT_FOUND = "Kaydedilecek yolcu nesnesi boş olamaz!";

    public static final String TICKET_PURCHASE_OBJECT_MUST_NOT_BE_NULL = "Bilet satın alım nesnesi boş olamaz!";
    public static final String TICKET_PURCHASE_OBJECT_OR_ID_MUST_NOT_BE_NULL = "Bilet satın alım nesnesi veya ID boş olamaz!";
    public static final String TICKET_PURCHASE_NOT_FOUND = "Kaydedilecek bilet satın alım nesnesi boş olamaz!";

    public static final String IATA_CODE_HAVE_MUST_2_CHAR = "IATA kodu 2 karaktere sahip olmalıdır!";
    public static final String ICAO_CODE_HAVE_MUST_3_CHAR = "ICAO kodu 3 karaktere sahip olmalıdır!";
    public static final String IATA_CODE_HAVE_MUST_3_CHAR = "IATA kodu 3 karaktere sahip olmalıdır!";
    public static final String ICAO_CODE_HAVE_MUST_4_CHAR = "ICAO kodu 4 karaktere sahip olmalıdır!";
    public static final String INVALID_LATITUDE_VALUE = "Geçersiz bir enlem değeri!";
    public static final String INVALID_LONGITUDE_VALUE = "Geçersiz bir boylam değeri!";
    public static final String INVALID_ALTITUDE_VALUE = "Geçersiz bir boylam değeri!";
    public static final String INVALID_NUMBER_OF_SEATS_VALUE = "Geçersiz uçak koltuk sayısı değeri!";

    public static final String FLIGHT_ROUTE_MUST_NOT_BE_NULL = "Rota bilgisi boş olamaz!";
    public static final String FLIGHT_SOURCE_AP_MUST_NOT_BE_NULL = "Kalkış havaalanı bilgisi boş olamaz!";
    public static final String FLIGHT_DEST_AP_MUST_NOT_BE_NULL = "Varış havaalanı bilgisi boş olamaz!";
    public static final String FLIGHT_AIRLINE_MUST_NOT_BE_NULL = "Havayolu şirket bilgisi boş olamaz!";
    public static final String FLIGHT_AIRLINE_IATA_MUST_NOT_BE_NULL = "Havayolu şirketinin IATA kod bilgisi boş olamaz!";
    public static final String FLIGHT_PACKAGES_MUST_NOT_BE_NULL = "Uçuş paketleri bilgisi boş olamaz!";
    public static final String FLIGHT_DATE_MUST_NOT_BE_NULL = "Uçuş tarih bilgisi boş olamaz!";
    public static final String FLIGHT_DEPARTURE_MUST_NOT_BE_NULL = "Uçuş kalkış saati bilgisi boş olamaz!";
    public static final String FLIGHT_DURATION_MUST_NOT_BE_NULL = "Tahmini uçuş süresi bilgisi boş olamaz!";

    public static final String FLIGHT_PACKAGE_CLASS_MUST_NOT_BE_NULL = "Uçuş paketi sınıf bilgisi boş olamaz!";
    public static final String FLIGHT_BASE_QUOTA_MUST_NOT_BE_NULL = "Uçuş paketi taban kontenjan bilgisi boş olamaz!";
    public static final String FLIGHT_BASE_PRICE_MUST_NOT_BE_NULL = "Uçuş paketi taban fiyat bilgisi boş olamaz!";
    public static final String FLIGHT_TOTAL_QUOTA_EXCEEDED = "Toplam koltuk kontenjanında uçak koltuk sayısını aştınız!";

}

package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {
    private DataGenerator() {

    }

    static Faker faker = new Faker((new Locale("ru")));

    public static String generateDate(int daysToAdd) {
        // TODO: добавить логику для объявления переменной date и задания её значения, для генерации строки с датой
        // Вы можете использовать класс LocalDate и его методы для получения и форматирования даты
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(String locale) {
        String[] ArrayOfCities = {"Абакан", "Владикавказ", "Екатеринбург", "Йошкар-Ола", "Казань", "Калининград",
                "Калуга", "Краснодар", "Красноярск", "Курган", "Махачкала", "Москва", "Петропавловск-Камчатский",
                "Сыктывкар", "Чебоксары", "Анадырь", "Архангельск", "Астрахань", "Барнаул", "Абакан", "Владимир",
                "Нарьян-Мар", "Салехард", "Абакан", "Самара", "Санкт-Петербург", "Абакан", "Ставрополь", "Хабаровск"};

        Random random = new Random();
        int i = random.nextInt(ArrayOfCities.length);

        // TODO: добавить логику для объявления переменной city и задания её значения, генерацию можно выполнить
        // с помощью Faker, либо используя массив валидных городов и класс Random
        return ArrayOfCities[i];
    }

    public static String generateName(String locale) {
        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker
        return faker.name().fullName();
    }

    public static String generatePhone(String locale) {
        // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
        // использовать Faker
        return faker.phoneNumber().cellPhone();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
            // generateName(locale), generatePhone(locale)

            return new UserInfo(
                    generateCity("ru"),
                    generateName("ru"),
                    generatePhone("ru")
            );
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
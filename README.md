**This program accepts text files as input and filters the content into output files by data type.**

# Requirements
### JDK 17.0.10
### Maven 3.9.2

# Dependencies
### commons-cli 1.8.0
<!-- https://mvnrepository.com/artifact/commons-cli/commons-cli -->
        <dependency>
            <groupId>commons-cli</groupId>
            <artifactId>commons-cli</artifactId>
            <version>1.8.0</version>
        </dependency>
### projectLombok 1.18.34
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.34</version>
            <scope>provided</scope>
        </dependency>

# Usage

Вы можете использовать опцию **-o**, чтобы указать путь к результатам.

Опция **-p** указывает префикс имен выходных файлов. Например, **-o /some/path -p result_** устанавливает вывод в
файлы **/some/path/result_integers.txt**, **/some/path/result_strings.txt** и т. д.

В процессе фильтрации данных собирается статистика для каждого типа данных.
Статистика поддерживается в двух видах: краткая и полная. Выбор статистики
делается с помощью опций **-s** и **-f** соответственно. Краткая статистика содержит только
количество элементов, записанных в исходящие файлы. Полная статистика по числам
дополнительно содержит минимальное и максимальное значения, сумму и среднее значение.
Полная статистика по строкам, помимо их количества, содержит еще и размер самой строки.
самую короткую и самую длинную строки. Статистика по каждому типу данных отображается в консоли.

Пример запуска утилиты:
**java -jar ShiftTest.jar -s -a -p sample- in1.txt in2.txt**

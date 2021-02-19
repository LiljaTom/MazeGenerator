### Testaus

Testit voidaan suorittaa komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

[Testikattavuus](https://github.com/LiljaTom/MazeGenerator/blob/main/Documentation/Pictures/testikattavuus.png)

## Checkstyle

Checkstylen voi suorittaa komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```
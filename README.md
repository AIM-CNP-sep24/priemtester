# Priemtester

Dit is het project bij de Java/Spring-Boot variant van de tutorial ['Unit testing C# in .NET using dotnet test and xUnit'](<https://learn.microsoft.com/en-us/dotnet/core/testing/unit-testing-with-dotnet-test>) op Microsoft Learn (2024). De tutorial bij deze code staat in de vorm van een blog op de website van de minor DevOps van de HAN (van der Wal, 2024). Deze tutorial toont een project opzet met enkele projecten:

- Doen priemgetal checks vanaf command line (console package)
- Kern priemcheck functionaliteit (service package)
- JUnit test-project voor service package (unit test package)

## How to run?

Zoals elke goede README.md starten we met een 'How to run'. Zoals o.a. Microsoft aanraadt (Microsoft Learn, 2022). Zorg dat je **Maven** geïnstalleerd hebt. Maven is een populaire build tool voor Java-projecten. Je kunt Maven installeren via de officiële instructies [hier](https://maven.apache.org/install.html).

De front-end stappen zitten in de README.md van de React front-end in de [`priemtester-ui` folder](priemtester-ui/README.md).

Run dan deze Spring Boot (web) applicatie met:

```console
mvn spring-boot:run
```

En surf naar http://localhost:9000/ om de applicatie te gebruiken.

(Of wat je inregeld hebt in property `service.port`in `application.properties`, ik nu iets anders dan 8080 vanwege collision met andere services/POC's op mijn machine.)

Voor (simpelere) lint check gebruik de standaard (old skool) Sun Checkstyle (v9.3):
```console
mvn checkstyle:check
```

Om de unit tests uit te voeren:
```console
mvn test
```

Om de integratietests uit te voeren:
```console
mvn verify
```

NB: Deze opzet is volgends Spring Boot Convention Over Configuration dat unittests een postfix Test hebben  (`*Test.java` en acceptatie of integratie tests een post `IT` (*IT.java`).

## Code

Hier meer technische details over de code en structuur.

Dit project bestaat uit:

- unit test-project
- en een broncode-project bevat.

Er zijn twee implementaties, een eigen 'custom' en eentje die een Maven package gebruikt uit `commons-numbers-primes` package. Zie JavaDoc in [common-numbers-primes](https://commons.apache.org/proper/commons-numbers/commons-numbers-primes/javadocs/api-1.2/org/apache/commons/numbers/primes/Primes.html).

Deze ondersteunen beiden 'enkel' normale integers. Voor grotere getallen kunnen/moeten we `BigInteger`s priem functie gebruiken (apache.org, z.d.).

## Extra: Front-end en pipeline

In de folder `.github/workflows` folder zit een GitHub Actions pipeline bestand. Een scripted pipeline. Ofwel CI/CD-pipeline. Dit is de standaard folder waar GitHub deze verwacht. Er zit ook een dependabot bestand in, om deze security feature niet te veel je eigen pipeline runs te laten vervuilen. Dit is aan jezelf of je dit wilt veranderen. 
Deze converteren we naar een React front-end met een storybook test.

## Extra: ORM & BDD Opdracht

Voor de minor DevOps moet er nog ORM en BDD gedaan worden. Het ORM stuk zit nu alleen in de .NET-variant hiervan in een andere repo. Het BDD stuk is nog geheel todo.

## Bronnen

- Microsoft Learn (3-7-2024). *Unit testing with dotnet test.* Geraadpleegd november 2024 op <https://learn.microsoft.com/en-us/dotnet/core/testing/unit-testing-with-dotnet-test>
- Microsoft Learn (10-4-2022). *Create a README*. Geraadpleegd november 2024 op https://learn.microsoft.com/en-us/azure/devops/repos/git/create-a-readme
- https://stackoverflow.com/questions/1801003/java-biginteger-prime-numbers

- TODO: APAify:
- https://commons.apache.org/proper/commons-numbers/commons-numbers-primes/javadocs/api-1.2/org/apache/commons/numbers/primes/Primes.html

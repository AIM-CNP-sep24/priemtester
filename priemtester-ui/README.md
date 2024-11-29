# Getting Started with Create React App

Dit React project werkt met Vite.

## How to run

We gaan er vanuit dat je [Node.js](https://nodejs.org/en/download/prebuilt-installer/current) geinstalleerd hebt, met NPM, de Node.js package manager.

Om verdere geen npm installs te doen gebruiken we npx, een package runner die bij Node.js geleverd wordt.

In de project directory `priemtester-ui`, start je de front-end in development mode (-> hot reload) met

```console
npx vite dev
```


Open [http://localhost:3000](http://localhost:3000) om de web app te zien (of Vite opent deze automatisch).

Je kunt de [React Developer](https://chromewebstore.google.com/detail/react-developer-tools/fmkadmapgofadopljbjfkapdkoienihi) tools extensie in je browser installeren om de componenten te inspecteren. Je krijgt een extra tab 'Components' in de Chrome DevTools (druk op F12 om de DevTools te openen).

Omdat deze extensie alle rechten vraag, raden we aan om [Chrome Canary](https://www.google.com/chrome/canary/) te (installeren) en te gebruiken, een aparte browser voor development.

Voor de unit tests gebruiken we Vitest.

Run de unit tests met
```
npm test
```

Of voor de interactieve test runner:
```console
npm run test:ui
```

Voor een (productie) build:
```console  
npm run build
``` 

Gebruik dus niet direct zelf `vite build`. De NPM variant roept deze aan, maar kopieert daarna ook de gebuilde assets`public` folder naar de `resources` folder in de back-end Spring Boot MVC app. Dit via config in je `package.json` in het scripts gedeelte .

Zo kun je via de 'same origin policy' werken, omdat front-end op zelfde url en poort draait als back-end (localhost:9000)

Voor development is er WEL een CORS setup in de back-end gemaakt (ook localhost, maar verschillende poorten 9000 en 5173).

Run ook de Linting tool
```
mvn checkstyle:check
```

Dit lanceert de test runner in interactive watch mode.

```
npm run test-storybook
```

npstorybook@latest init

start-storybook --ci


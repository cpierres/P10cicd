# BobApp

Clone project:

> git clone XXXXX

## Exécution avec Docker Compose

La façon la plus simple d'exécuter la pile d'applications complète est d'utiliser Docker Compose :

```bash
# Construire et démarrer tous les services avec le nom de projet P10cicd
docker-compose -p P10cicd up -d

# Afficher les logs
docker-compose -p P10cicd logs -f

# Arrêter tous les services
docker-compose -p P10cicd down
```

L'application sera disponible aux adresses suivantes :
- Front-end : http://localhost
- API Back-end : http://localhost:8080/api

## Manual Setup

### Front-end 

Go inside folder the front folder:

> cd front

Install dependencies:

> npm install

Launch Front-end:

> npm run start;

#### Docker (Front-end only)

Build the container:

> docker build -t bobapp-front .  

Start the container:

> docker run -p 8080:80 --name bobapp-front -d bobapp-front

### Back-end

Go inside folder the back folder:

> cd back

Install dependencies:

> mvn clean install

Launch Back-end:

>  mvn spring-boot:run

Launch the tests:

> mvn clean install

#### Docker (Back-end only)

Build the container:

> docker build -t bobapp-back .  

Start the container:

> docker run -p 8080:8080 --name bobapp-back -d bobapp-back
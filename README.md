# BobApp

Clone project:

> git clone XXXXX

## Exécution avec Docker Compose

Le projet utilise plusieurs fichiers docker-compose pour différents besoins. Voici comment les utiliser correctement :

### Application Principale (docker-compose.yml)

Pour démarrer la stack d'application complète (backend + frontend) :

```bash
# Construire et démarrer tous les services avec le nom de projet cpierres-bobapp
docker-compose -p cpierres-bobapp up -d

# Afficher les logs
docker-compose -p cpierres-bobapp logs -f

# Arrêter tous les services
docker-compose -p cpierres-bobapp down
```

L'application sera disponible aux adresses suivantes :
- Front-end : http://localhost
- API Back-end : http://localhost:8080/api

### SonarQube (docker-compose-sonar.yml)

Pour démarrer l'environnement SonarQube séparément :

```bash
# Démarrer SonarQube et sa base de données
docker-compose -f docker-compose-sonar.yml -p sonar up -d

# Vérifier les logs de SonarQube
docker-compose -f docker-compose-sonar.yml -p sonar logs -f

# Arrêter SonarQube
docker-compose -f docker-compose-sonar.yml -p sonar down
```

SonarQube sera disponible à l'adresse :
- Interface web : http://localhost:9000

> **Note importante** : L'utilisation du paramètre `-p` (nom de projet) permet d'isoler complètement les différentes stacks et d'éviter tout conflit entre elles, même si elles sont exécutées simultanément.

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

## CI/CD et Images Docker Hub

Ce projet utilise GitHub Actions pour l'intégration continue et le déploiement continu.
Les workflows CI/CD construisent et publient automatiquement des images Docker sur Docker Hub.

### Images Docker précompilées

Des images Docker précompilées sont disponibles sur Docker Hub :

- **Backend** : [cpierres/bobapp-back](https://hub.docker.com/repository/docker/cpierres/bobapp-back/general)
- **Frontend** : [cpierres/bobapp-frontend](https://hub.docker.com/repository/docker/cpierres/bobapp-frontend/general)

#### Utilisation avec docker-compose-prebuilt.yml

Pour démarrer l'application avec les images précompilées de Docker Hub :

```bash
# Démarrer l'application avec les images précompilées de Docker Hub
docker-compose -f docker-compose-prebuilt.yml -p cpierres-bobapp-prebuilt up -d

# Afficher les logs
docker-compose -f docker-compose-prebuilt.yml -p cpierres-bobapp-prebuilt logs -f

# Arrêter tous les services
docker-compose -f docker-compose-prebuilt.yml -p cpierres-bobapp-prebuilt down
```

#### Utilisation manuelle des images

Pour utiliser ces images individuellement, exécutez :

```bash
# Télécharger l'image backend
docker pull cpierres/bobapp-back:latest

# Télécharger l'image frontend
docker pull cpierres/bobapp-frontend:latest

# Exécuter le backend
docker run -p 8080:8080 --name bobapp-back -d cpierres/bobapp-back:latest

# Exécuter le frontend
docker run -p 80:80 --name bobapp-front -d cpierres/bobapp-frontend:latest
```



## Releases & Changelog (release-please)

Ce dépôt utilise release-please pour générer automatiquement des PR de release, mettre à jour les CHANGELOG.md et créer des tags/Releases.

- Workflow: .github/workflows/release-please.yml
- Config: .release-please-config.json
- Manifest: .release-please-manifest.json
- Documentation: [release-please.md](./release-please.md)

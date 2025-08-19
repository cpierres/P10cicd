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

## Conventions de commits (Conventional Commits)

Il est important de respecter la norme Conventional Commits pour nommer les messages de commit (afin que ceux-ci apparaissent correctement dans le CHANGELOG).

Format recommandé:
<type>(<scope>): <description courte>
[ligne vide]
[corps optionnel sur une ou plusieurs lignes]
[ligne vide]
[footers optionnels: BREAKING CHANGE, références d’issues, co-auteurs, etc.]

Règles clés:
- type obligatoire parmi: feat, fix, docs, style, refactor, perf, test, build, ci, chore, revert
- scope optionnel (module, dossier, composant: back, front, api, ui, etc.)
- ! pour le breaking change, indiquer BREAKING CHANGE dans le footer
- description concise (≤ 72 caractères conseillé)
- corps et footers facultatifs, avec détails techniques et références

Exemples par type (avec et sans scope):
- feat: nouvelle fonctionnalité
  - feat: prise en charge du tri par date
  - feat(ui): ajout du bouton d’export CSV
  - feat(back)!: suppression du champ "legacyId" de l’API
- fix: correction de bug
  - fix: éviter la division par zéro dans le calcul
  - fix(front): corrige le crash au clic sur "Sauvegarder"
- docs: documentation uniquement
  - docs: ajoute la procédure d’onboarding
  - docs(readme): détaille les commandes docker-compose
- style: formatage, espaces, lint (sans changement fonctionnel)
  - style: reformate le code avec Prettier
  - style(front): aligne les imports et corrige l’indentation
- refactor: refactoring sans changement fonctionnel
  - refactor: amélioration de la gestion des erreurs
  - refactor(back): remplace Optional par un pattern clair
- perf: amélioration de performance
  - perf: évite la création d’objets dans la boucle chaude
  - perf(api): mise en cache des réponses GET /users
- test: ajout/mise à jour de tests
  - test: ajoute des tests e2e pour l’authentification
  - test(front): convertit les tests en Jest 29
- build: modifications du système de build, deps, outils
  - build: met à jour Node 14 et Angular 14.2.0
  - build(back): bump Maven Surefire plugin
- ci: intégration/déploiement continu
  - ci: ajoute le job Sonar sur les PR
  - ci: corrige le nom du secret Docker Hub
- chore: tâches diverses sans impact sur le code applicatif
  - chore: nettoie les fichiers temporaires
  - chore(repo): met à jour .gitignore
- revert: annulation d’un commit précédent
  - revert: "feat(api): ajoute l’endpoint /reports" (sha: abc123)

Exemples détaillés:
1) Commit standard avec scope
feat(front): ajoute le filtrage par statut dans la liste

2) Commit avec corps expliquant le pourquoi
fix(back): corrige la pagination sur /users
La taille de page n’était pas transmise au repository,
provoquant un retour de tous les éléments.

3) Commit avec BREAKING CHANGE en footer
feat(api): expose un endpoint de suppression de compte
BREAKING CHANGE: le champ "isActive" n’est plus renvoyé par /v1/users

4) Commit avec références d’issues
fix(ui): évite le double-clic sur le bouton "Payer"
Closes #123
Refs #120

5) Revert d’un commit précis
revert: "perf: mise en cache des réponses GET /users"
Ce revert annule abc1234 car c'était une erreur

Conseils pratiques:
- Un commit = une intention claire. Découpez les changements si nécessaire.
- Préférez un scope parlant: feat(auth), fix(router), docs(readme).
- Utilisez le corps pour expliquer le pourquoi, le comment si utile, et les impacts.
- Utilisez BREAKING CHANGE dès qu’il y a une incompatibilité ascendante (API, contrat, data).

## Publication des images Docker versionnées

Les images Docker back et front sont aussi publiées lors des tags créés par release-please (ex.: `back-vX.Y.Z`, `front-vX.Y.Z`). Un workflow dédié construit et pousse :
- `:latest`
- `:X.Y.Z` (tag de version issu du tag Git)

Workflow: `.github/workflows/release-please-backend.yml` et `.github/workflows/release-please-frontend.yml`

Exemples de pull:
- Backend: `docker pull cpierres/bobapp-back:1.2.3`
- Frontend: `docker pull cpierres/bobapp-frontend:1.2.3`

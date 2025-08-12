# Release-please (monorepo back/front)

Cette page documente l’emplacement des fichiers, le fonctionnement, et la cinématique de génération des changelogs et releases via release-please.

## Où sont les fichiers ?

- Racine du dépôt :
  - `.release-please-manifest.json` — tient à jour la version actuelle par composant (ici `back` et `front`).
  - `.release-please-config.json` — configuration monorepo :
    - back → release-type `java` (met à jour `back/pom.xml`, génère `back/CHANGELOG.md`)
    - front → release-type `node` (met à jour `front/package.json`, génère `front/CHANGELOG.md`)
- Workflow GitHub Actions :
  - `.github/workflows/release-please.yml`
- Diagramme explicatif :
  - `assets/release-please-sequence.puml`

## Quand et comment ces fichiers sont-ils mis à jour ?

- Vous poussez des commits sur `main` (ou lancez manuellement le workflow `Release Please`).
- Le job `release-please` lit la config et le manifest, détecte des changements par composant et **ouvre/actualise une Pull Request de release** par composant concerné.
- Dans cette PR, release-please :
  - met à jour la version dans `pom.xml` (back) ou `package.json` (front),
  - met à jour/ajoute `CHANGELOG.md` dans le dossier du composant (`back/CHANGELOG.md`, `front/CHANGELOG.md`),
  - met à jour `.release-please-manifest.json`.
- Quand vous **mergez la PR de release** :
  - release-please crée **un tag** et une **GitHub Release**,
  - les changements (CHANGELOG, versions, manifest) sont maintenant sur `main`.

Remarque : par défaut, release-please n’écrit pas directement sur `main` ; il passe par des PR de release que vous validez (conforme à vos règles CODEOWNERS/Branch Protection).

## Comment générer / piloter la cinématique

- Automatique : un **push sur `main`** qui modifie `back/**` ou `front/**` déclenchera le workflow et proposera/actualisera une PR de release si nécessaire.
- Manuel : via l’onglet **Actions → Release Please → Run workflow**.
- Après revue, **mergez la PR de release** pour créer le tag et la Release.

## FAQ rapide

- Puis-je publier back et front indépendamment ?
  - Oui : release-please analysera chacun et n’ouvrira une PR que pour le composant modifié.
- Où sont les changelogs ?
  - Dans chaque composant : `back/CHANGELOG.md` et `front/CHANGELOG.md`.
- Est-ce compatible avec vos protections de branches et CODEOWNERS ?
  - Oui : release-please crée des PR ; vous gardez la validation manuelle via vos règles existantes.
- Pour changer les types de commits pris en compte
  - adapter `changelog-types` dans `.release-please-config.json` ou adopter les Conventional Commits pour un changelog plus expressif.

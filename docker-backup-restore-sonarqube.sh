#!/bin/bash

# Répertoire où les sauvegardes sont stockées
BACKUP_DIR="/mnt/d/DockerVolumesBackup/sonar-stack/20250807"

# Vérification de l'existence du répertoire de sauvegarde
if [[ ! -d "$BACKUP_DIR" ]]; then
  echo "Erreur : Le répertoire de sauvegarde $BACKUP_DIR n'existe pas. Veuillez vérifier le chemin."
  exit 1
fi

# Liste des fichiers de sauvegarde attendus
declare -A VOLUMES=(
  [sonarqube_data]="sonarqube_data.tar.gz"
  [sonarqube_logs]="sonarqube_logs.tar.gz"
  [sonarqube_extensions]="sonarqube_extensions.tar.gz"
  [postgresql]="postgresql.tar.gz"
)

# Fonction pour restaurer un volume
restore_volume() {
  local volume_name=$1
  local backup_file=$2

  if [[ -f "$BACKUP_DIR/$backup_file" ]]; then
    echo "Restauration du volume $volume_name à partir de $backup_file..."
    docker run --rm \
      -v $volume_name:/volume-destination \
      -v "$BACKUP_DIR":/volume-backup \
      alpine \
      tar -xzf /volume-backup/$backup_file -C /volume-destination
    echo "Volume $volume_name restauré avec succès."
  else
    echo "Erreur : Le fichier de sauvegarde $BACKUP_DIR/$backup_file est manquant. Ignorer le volume $volume_name."
  fi
}

# Boucle pour restaurer chaque volume
for volume in "${!VOLUMES[@]}"; do
  restore_volume $volume "${VOLUMES[$volume]}"
done

echo "Restauration terminée."

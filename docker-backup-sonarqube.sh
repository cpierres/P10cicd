#!/bin/bash

BACKUP_DIR="/mnt/d/DockerVolumesBackup/sonar-stack/20251228"

mkdir -p "$BACKUP_DIR"

docker run --rm -v sonar_sonarqube_data:/volume-source -v "$BACKUP_DIR":/volume-backup alpine tar -czvf /volume-backup/sonarqube_data.tar.gz -C /volume-source .
docker run --rm -v sonar_sonarqube_logs:/volume-source -v "$BACKUP_DIR":/volume-backup alpine tar -czvf /volume-backup/sonarqube_logs.tar.gz -C /volume-source .
docker run --rm -v sonar_sonarqube_extensions:/volume-source -v "$BACKUP_DIR":/volume-backup alpine tar -czvf /volume-backup/sonarqube_extensions.tar.gz -C /volume-source .
docker run --rm -v sonar_postgresql:/volume-source -v "$BACKUP_DIR":/volume-backup alpine tar -czvf /volume-backup/postgresql.tar.gz -C /volume-source .

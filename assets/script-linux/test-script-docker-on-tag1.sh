#!/bin/bash
# test-script-docker-for-version1.sh

# Simuler les variables GitHub
export GITHUB_REF_NAME="back-v1.2.3"  # ou "front-v2.1.0"
export GITHUB_OUTPUT="/tmp/github_output"

# Nettoyer le fichier de sortie
> $GITHUB_OUTPUT

# Votre script à tester
REF_NAME="${GITHUB_REF_NAME}"
if [[ "$REF_NAME" == back-v* ]]; then echo "component=back" >> $GITHUB_OUTPUT; fi
if [[ "$REF_NAME" == front-v* ]]; then echo "component=front" >> $GITHUB_OUTPUT; fi
VERSION="${REF_NAME#*-v}"
echo "version=${VERSION}" >> $GITHUB_OUTPUT

# Vérifier les résultats
echo "=== Contenu de GITHUB_OUTPUT ==="
cat $GITHUB_OUTPUT
echo "=== Variables extraites ==="
echo "Component: ${REF_NAME%%-*}"
echo "Version: ${VERSION}"

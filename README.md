# Task Manager API

API REST de gestion de tâches développée avec Java et Spring Boot.

## Fonctionnalités

- Créer une tâche
- Consulter toutes les tâches
- Consulter une tâche par identifiant
- Modifier une tâche
- Supprimer une tâche
- Valider les données reçues
- Gérer les erreurs avec des réponses JSON
- Tester la logique métier automatiquement

## Technologies

- Java 25
- Spring Boot 4
- Spring Web
- Spring Data JPA
- Jakarta Validation
- H2 Database
- Maven
- JUnit et Mockito
- Git et GitHub

## Architecture

```text
Controller → Service → Repository → Base de données
```

## Lancer le projet

```powershell
.\mvnw.cmd spring-boot:run
```

L’API est accessible à l’adresse :

```text
http://localhost:8080/api/tasks
```

## Endpoints

| Méthode | Adresse           | Action                    |
| ------- | ----------------- | ------------------------- |
| POST    | `/api/tasks`      | Créer une tâche           |
| GET     | `/api/tasks`      | Obtenir toutes les tâches |
| GET     | `/api/tasks/{id}` | Obtenir une tâche         |
| PUT     | `/api/tasks/{id}` | Modifier une tâche        |
| DELETE  | `/api/tasks/{id}` | Supprimer une tâche       |

## Exemple de création

```json
{
  "title": "Apprendre Spring Boot",
  "description": "Construire une API professionnelle"
}
```

## Exemple de modification

```json
{
  "title": "Apprendre Spring Boot",
  "description": "Terminer le projet",
  "status": "IN_PROGRESS"
}
```

## Statuts disponibles

- `PENDING`
- `IN_PROGRESS`
- `COMPLETED`

## Lancer les tests

```powershell
.\mvnw.cmd test
```

## Auteur

Imene

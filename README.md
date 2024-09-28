# Projet : Server Web Socket

Ce projet a pour but d'illustrer le fonctionnement interne d'un serveur d'application Java. Il met en œuvre la gestion
des connexions à travers des `sockets multithread` et la `gestion dynamique des servlets` à l'aide de la
`réflexivité de Java` pour les instancier et gérer de manière flexible. Une des particularités de ce projet est
l'expérimentation approfondie du cycle de vie des servlets.

### Fonctionnalités principales :

* Création et gestion de sockets : Le serveur gère plusieurs connexions simultanées grâce au multithreading.
* Gestion dynamique des servlets : Utilisation de la réflexivité Java pour instancier les servlets au moment de
  l'exécution.
* Cycle de vie des servlets : Le projet explore les différentes phases du cycle de vie des servlets (initialisation,
  gestion des requêtes, destruction) pour mieux comprendre leur gestion au sein d'un serveur.
* Multithreading : Chaque connexion est traitée dans un thread distinct, simulant la gestion concurrente des requêtes.
* Modèle extensible : Ce projet est conçu comme une base évolutive pour approfondir les concepts de serveurs
  d'application.

### Objectifs du projet :

* Comprendre les bases de la **création d'un serveur d'application Java**.
* Explorer la **gestion des requêtes** via un système multithread.
* Expérimenter les étapes du **cycle de vie des servlets** (initialisation avec `init()`, traitement avec `service()`,
  destruction avec `destroy()`).
* Implémenter des mécanismes de **chargement dynamique des servlets** et leur gestion en mémoire.
* Approfondir les concepts de la **réflexivité en Java** pour le chargement dynamique des classes.

### Structure du projet :

* SocketManager.java : Gère les connexions clients et initialise les threads.
* ServletManager.java : Responsable de l'instanciation dynamique des servlets.
* Main.java : Point d'entrée du serveur d'application, où sont initialisés les sockets et les threads.
* ServletLifecycle.java : Classe expérimentant les différentes étapes du cycle de vie des servlets.

### Comment démarrer :

1. Cloner le dépôt :

````shell
git clone https://github.com/votre-utilisateur/votre-repo.git
````

2. Compiler le projet avec Maven :

````shell
mvn clean install
````

3. Lancer le serveur :

````shell
java -jar target/ServerWebSocket-1.0-SNAPSHOT.jar
````

### Importer le fichier .json dans Postman :

Le projet inclut un fichier d'export `.json` depuis Postman, contenant les configurations nécessaires pour tester
certaines requêtes sur le serveur.

#### Étapes pour importer le fichier dans Postman :

1. Ouvre Postman.
2. Dans la barre de navigation, clique sur Importer.
3. Sélectionne l'onglet Upload Files et choisis le fichier .json que tu as exporté depuis le projet.
4. Clique sur Import. Postman ajoutera automatiquement les requêtes et configurations du fichier à ta collection.
5. Tu pourras maintenant exécuter les requêtes définies pour tester le serveur.
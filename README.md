# Projet-Site-Web
On se propose de réaliser une application web J2EE, en respectant au mieux l'architecture MVC (DAO, JSP, Servlet).

On utilise la base de données "sample" fournie avec NetBeans.

Schéma de la base de données : les clients (CUSTOMER) passent des commandes (PURCHASE_ORDER), les commandes référencent des produits (PRODUCT), les produits appartiennent a des catégories (PRODUCT_CODE), les catégories sont associées à un taux de remise (DISCOUNT_CODE). Les clients sont localisés dans des zones géographiques (MICRO_MARKET).

L'application est destinée à deux catégories d'utilisateurs : Les clients et l'administrateur.

Pour les clients :

- Le client doit s'authentifier pour accéder à l'édition des bons de commandes.

La base sample ne contenant pas de mots de passe, on utilisera les champs suivants de la table CUSTOMER pour l'authentification :

login : EMAIL
password : CUSTOMER_ID
Une fois connecté, l'application doit permettre au client l'édition complète de ses commandes (ajout, modification, suppression).

Pour l'administrateur :

- L'administrateur s'authentifie en utilisant un login / password prédéfini dans l'application.
- ici on utilise login : admin@admin et passord: admin.

Une fois authentifié, l'administrateur a accès à une série de tableaux de bord graphiques qui lui permettent de visualiser des statistiques sur les commandes saisies :

Visualiser les chiffres d'affaire par catégorie d'article, en choisissant la période (date de début / date de fin) sur laquelle doit porter la statistique.
Visualiser les chiffres d'affaire par zone géographique, en choisissant la période (date de début / date de fin) sur laquelle doit porter la statistique.
Visualiser les chiffres d'affaire par client, en choisissant la période (date de début / date de fin) sur laquelle doit porter la statistique.
Pour l'affichage des graphiques, on utilisera la bibliothèque javascript ChartJS pour récupérer les données nécessaires à l'affichage du graĥique.

Livrable
Vous devez fournir un projet maven, déposé sur un dépôt github. Ce projet doit être téléchargeable par git clone et immédiatement exécutable sous netbeans. Les contributions des différents membres du groupe doivent être clairement visibles via l'historique des "commits".
<pre>Sur GitHub les nom utilisé pour commit sont :
  - Mark ou amaurygdc pour Amaury Goncalves de Carvalho.
  - ValentinDelpy pour Valentin Delpy.
  - BrynaCurtISIS pour Bryan Curt.</pre>


Evaluation
Le projet sera évalué par une présentation orale en groupe :

Démonstration
Examen du code source
Questions/Réponses collectives et individuelles

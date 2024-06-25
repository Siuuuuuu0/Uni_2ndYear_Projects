Kotkin Vadim, 22208844

Kostiuk Andrii, 22213405

Projet POGL, L2 Info

LE TRAVAIL

Dans ce travail on a accompli toutes les parties obligatoires du sujet, ainsi qu’on a ajouté des éléments a nous-même, comme des avatars pour les personnages. On a introduit des sliders pour choisir les paramètres du jeu avant qu’il ne commence (NB_BALLES, NB_WAGONS, NB_ACTIONS, NB_PERSONNAGES). A part la possibilité d’entrer les actions avec des boutons, on a ajouté des KeyListeners pour avoir la possibilité de le faire via le clavier. Toutes les actions choisis sont affiches sur l’écran, on peut supprimer la dernière action dans la queue (et comme ça jusqu’il n’en reste plus) et la remplacer par une autre, ainsi que en ayant choisi le nombre nécessaire d’actions il faut les confirmer avec le bouton. Toutes les infos sur les personnages sont affiches au cours du jeu, ainsi que les scores finaux à la fin de la partie.

REPARTITION DES ROLES

Le travail est structure en plusieurs classes : la classe Train qui sert de « colle » pour toutes les autres. La classe View sert de JFrame pour l'affichage et Controller sert de controlleur pour les action et les boutons du jeu. Une classe Wagon et Locomotive qui l’étend. Les Wagons ont des attributs prev et next pour naviguer plus facilement entre eux, ainsi relies sous forme de liste doublement chaînée. On a dû implémenter une classe Action afin de pouvoir sauvegarder les infos sur les actions dans la queue. Bandit et Marshall étendent la classe Personne pour avoir plus de flexibilité avec le déplacement et les actions de personnages et une classe Butin a été introduite. 
En ce qui concerne les rôles, Vadim a surtout travaille sur l’affichage (JFrame, boutons, KeyListeners) et les wagons avec le train, alors que Andrii a travaillé sur les personnages, les actions et les butins. 

PROBLEMES

Lors de la création du projet, nous avons réfléchi à la manière dont devraient fonctionner nos méthodes d'interaction entre les joueurs du jeu. Puisque nous avons un maximum de 5 personnages dans le jeu, nous avons décidé qu'il serait préférable d'augmenter légèrement la complexité temporelle de l'exécution de toute action plutôt que d'occuper constamment de l'espace supplémentaire quelque part dans les instances de nos classes.
Un des majeurs problèmes ont été les KeyListeners. Apres l’appui sur un bouton, le JFrame perdait le focus, et le requestFocus() ne marchait pas. Il m’a fallu du temps pour venir a la résolution d’ajouter les KeyListeners() avec toutes les combinations du clavier acceptés à chaque bouton, afin de garantir que le focus ne se perd jamais. Avant cela j’ai essayé de faire des KeyBindings, amis après plusieurs essais cela ne marchait pas non plus. 
Un autre petit problème a été de comment revenir au label précédent après avoir supprimé une commande (soit : « Monter -> Descendre -> Collecter », appui sur Delete, « Monter-> Descendre ») sans stocker les strings dans un tableau appart. Notre solution a été d’utiliser les regex, avec un motif qui renvoie la chaine de caractères sans le dernier « -> XYZ ». 

CODE EMPRUNTE

La seule partie du code emprunté sur internet a été le regex, fonction matcher()  avec le Pattern et le Matcher (StackOverflow). En rencontrant ces classes la première fois, on n’avait pas d’autre choix. A part de cela, tout a été écrit par nous-mêmes.


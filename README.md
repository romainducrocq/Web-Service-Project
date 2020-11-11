# Web-Service-Project

Pour installer le projet :
Récupérer le jar javax.mail.jar de Git.

Créer un nouveau Web Dynamic ProjectDans le répertoire src, créer 4 packages :
- rentalserver
- rentalclient
- servlets
- employeeDB
- shared
(a priori, on n'a plus besoin du package rentalclient étant donné qu'on a l'interface web)

Récupérer les fichiers java de Git et les copier dans chaque package.

Récupérer tous les fichiers du répertoire WEB-INF (.jsp et .html) et les copier dans votre répertoire WEB-INF.

Dans le Build Path (bouton droit sur le projet, Add Build Path), ajouter le path vers le jar javax.mail.jar (Add external JAR files).

Choisir le JDK 1.8.

Dans le menu Run >  Run configurations, vous ajoutez 2 "run configurations" (une pour chaque JVM) :
- une pour la classe RentalServer
- une pour la classe EmployeeDBServer

Vous pouvez lancer l'exécution de ces 2 JVM en les sélectionnant dans les Run configurations et en appuyant sur le bouton Run.
2 consoles vont être créées avec chacune un petit message disant que le serveur est lancé.

Puis vous right-cliquez sur le projet et choisissez : Run as server.
Une fenêtre s'ouvre vous demandant quel serveur utiliser. 
Choisissez Tomcat 7.Puis lancez l'exécution.Dans la console, vous allez peut-être avoir des Warnings qui vont s'afficher.

Puis vous ouvrez un explorer avec l'URL suivante : http://localhost:8080/RentalProject/authenticate
et vous tombez sur la page d'authentification.

Pour rappel, pour le moment, 3 employés sont créés :
- ngrumbach
- rducrocq
- atherond
avec comme password pour tous : "password" et mon adresse mail (vous pouvez mettre votre adresse pour tester et ne pas saturer ma boîte mail... ;)(fichier EmployeeDB.java).

Les véhicules sont identifiés par leur id unique. 5 véhicules sont actuellement créés avec des ids de 0 à 5. Quand vous retournez un véhicule, vous devez donner ce numéro.

Je crois n'avoir rien oublié, dites moi si ça fonctionne...

Good luck !

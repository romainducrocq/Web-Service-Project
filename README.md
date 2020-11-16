# Web-Service-Project

## Rental Project, @Natacha

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

Récupérer tous les fichiers du répertoire WEB-INF (.jsp et .html) et les copier dans votre répertoire WEB-INF.

Dans le Build Path (bouton droit sur le projet, Add Build Path), ajouter le path vers le jar javax.mail.jar (Add external JAR files).

Choisir le JDK 1.8.

Dans le menu Run >  Run configurations, vous ajoutez 2 "run configurations" (une pour chaque JVM) :
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

Les véhicules sont identifiés par leur id unique. 5 véhicules sont actuellement créés avec des ids de 0 à 5. Quand vous retournez un véhicule, vous devez donner ce numéro.

Je crois n'avoir rien oublié, dites moi si ça fonctionne...

Good luck !

## Ifs Cars Service, @Romain

1. Idem que pour le rental project, créer un Dynamic Web Service IfsCarsService.

2. Récupérer les fichiers java du /src et les jsp du /Webcontent/WEB-INF dans git, et les placer au bon endroit dans le WS. Il y a 4 servlets et 4 jsp, une paire pour chaque page du client.

3. Mettre à jour RentalProject, j'ai modifié les fichiers (voir plus bas la liste des changements, ou directement dans la magie de github):
    - RentalManager.java
    - Vehicle.java
    - VehiclesDBManager.java
    - IVehicle.java
    - IVehicleParkRentalManagement.java

4. Charger absolument le nouveau createDB.sql, j'ai changé la table des vehicles! (j'ai mis un removeDB.sql pour faire un clean delete de l'ancienne DB.)

5. Le WS ne dépend que de RentalServer, pas besoin de lancer EmployeeServer pour tester. (Il y a un main vide dans IfsCarsService, il ne sert à rien mais je le laisse comme espace de test.)

6. Le WS se trouve à l'adresse **http://localhost:8080/IfsCarsService/index**
    - Toutes les autres pages du webservice redirigent vers celle-là si vous tentez d'y accéder par url, donc vous devez suivre le parcours utilisateur (welcome/shop/payment/done).
    - (pour faciliter le parcours, on peux désactiver la vérif de validité des infos de paiement en tapant nocheck() dans la console du navigateur (ctrl+shift+k)).
    - **ATTENTION: j'ai codé sur firefox, je ne garantie pas la compatibilité avec d'autres navigateurs !!!**

7. J'ai aussi mis un script bash stoprmi.sh, qui tue les ports rmi 1099 et 2000. (Pour faciliter les tests de DB, quand il faut relancer les serveurs rmi.)

**@Natacha**
Modifs de tes fichiers: 
- createDB.sql:
	- Ajouté les champs all-notes, last_message, img_url, available_for_sale dans la table vehicles.
	- Ajouté des véhicules.
	- Ajouté un employé (moi).
- VehiclesDBManager.java:
	- Ajouté les gets de la DB pour les notes, le dernier message, l'url de l'image et la dispo de vente.
- Vehicle.java:
	- Ajouté les notes, le dernier message, l'url de l'image et la dispo de vente dans le constructeur.
	- Ajouté un attribut pour l'url de l'image et les get/set qui vont avec.
- RentalManager.java:
	- ajouté une méthode pour récupérer une voiture par son ID.
- IVehicle.java et IVehicleParkRentalManagement.java:
	- les interfaces correspondantes.

**@Alexandre**
J'ai besoin des 3 methodes suivantes de la banque:
1. Une méthode pour récupérer la liste de toutes les actives currencies (un long string de la forme "EUR","JPY","USD"...... , que je parse apres):
> //Get active currencies

> List<String> currencies = Arrays.asList(currencySystem.activeCurrencies("").split(";"));
2. Une méthode pour récupérer un taux de change en fonction de 1 EUR:
> //Get exchange rates

> String currency = "USD";

> double exchangerate = (double)currencySystem.convert("", "EUR", currency, (double)1, false, "", CurncsrvReturnRate.curncsrvReturnRateNumber, "", "");
3. une méthode qui prend les infos de paiement et qui renvoie un booléen (le paiement est accepté ou non), j'ai implémenté les infos de paiements suivantes:
	- prix en euro
	- taux de change
	- prix en monnaie souhaitée
	- nom prénom
	- numéro de CB
	- CVV
	- date d'expiration (mois et année)
        - T'es pas obligé de tout prendre, mais tu peux jouer avec tout ca, genre si tu veux faire un DB de clients de la banque et les identifier par leurs infos bancaires.

-    Dis moi quand les 3 méthodes sont pretes, j'aurai juste à les insérer dans les servlets (shopping pour la 1, payment pour les 2 et 3), rien ne doit changer dans le code normalement de mon côté.

A+, bonne lecture de mon javascript de la saleté.

****

# Build project from scratch

- **shell>** mkdir Web-Service-Project && cd Web-Service-Project && mkdir WS_Project
- **shell>** git init
- **shell>** git remote add origin https://github.com/romainducrocq/Web-Service-Project.git 
    > *(or git@github.com:romainducrocq/Web-Service-Project.git for ssh)*
- **eclipse-jee>** run in workspace: /path/to/Web-Service-Project/WS_Project:
- **eclipse-jee>** window > preferences > java > compiler > compiler compliance level > 1.8 > apply
- **eclipse-jee>** window > preferences > java > installed jres > add > /path/to/jdk1.8.0_271 > select > apply and close
- **eclipse-jee>** new > server > apache > tomcat v7.0 server > /path/to/apache-tomcat-7.0.106
- **eclipse-jee>** new > dynamic web project: RentalProject
- **eclipse-jee>** new > dynamic web project: IfsCarsService
- **shell>** git pull origin master
- **shell>** mysql --user="root" --password="1Rootpwd!" < createDB.sql
    > *mysql config:*  
    > install: https://dev.mysql.com/doc/mysql-apt-repo-quick-guide/en/  
    > **shell>** mysql -u root -p  
    > enter password:  
    > **mysql>** FLUSH PRIVILEGES;  
    > **mysql>** ALTER USER 'root'@'localhost' IDENTIFIED BY '1Rootpwd!';  
    > **mysql>** exit  
- **eclipse-jee>** RentalProject > properties > java build path > libraries > add external jars > /path/to/Web-Service-Project/{javax.mail.jar, javax.servlet-api-4.0.1.jar, mysql-connector-java-8.0.21.jar}
- **eclipse-jee>** IfsCarsService > properties > java build path > libraries > add external jars > /path/to/Web-Service-Project/{javax.mail.jar, javax.servlet-api-4.0.1.jar, mysql-connector-java-8.0.21.jar} 
- **eclipse-jee>** RentalProject > java resources > src > rentalserver > RentalServer.java > run as java application
- **eclipse-jee>** RentalProject > java resources > src > employees > EmployeesServer.java > run as java application
- **firefox>** http://localhost:8080/RentalProject/authenticate
- **firefox>** http://localhost:8080/IfsCarsService/index

****

**Useful commands**

- *run mysql*
    > **shell>** service mysql start  
    > **shell>** service mysql status  
    > **shell>** service mysql stop  
    > **shell>** mysql --user="root" --password="1Rootpwd!"  
    > **mysql>** SHOW DATABASES;  
- *delete db*
    > **shell>** mysql --user="root" --password="1Rootpwd!" < removeDB.sql
- *kill rmi*
    > **shell>** ./stoprmi.sh

****

**Links**
- github: https://github.com/romainducrocq/Web-Service-Project
- eclipse-jee: https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2020-09/R/eclipse-inst-jre-linux64.tar.gz
- jdk1.8.0_271: https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
- tomcat 7.0.106: https://tomcat.apache.org/download-70.cgi
- mysql: https://dev.mysql.com/doc/mysql-apt-repo-quick-guide/en/

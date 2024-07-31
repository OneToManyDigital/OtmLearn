# OtmLearn


## Étape 1 : Apprendre Git

1. **Installation de Git** :
   - Installez Git sur votre machine en suivant les instructions sur [git-scm.com](https://git-scm.com/).

2. **Cloner un dépôt** :
   - Choisissez un dépôt existant ou créez-en un sur GitHub ou une autre plateforme.
   - Clonez le dépôt sur votre machine locale :
     ```sh
     git clone https://github.com/OneToManyDigital/OtmLearn.git
     ```
   - Cela téléchargera une copie du dépôt dans un répertoire local.

3. **Créer une branche** :
   - Créez une nouvelle branche pour vos modifications :
     ```sh
     git checkout -b ma-nouvelle-branche
     ```
   - Cela crée et bascule vers une nouvelle branche nommée `ma-nouvelle-branche`.

4. **Apporter des modifications et les valider** :
   - Modifiez ou ajoutez des fichiers dans le répertoire cloné.
   - Ajoutez les fichiers modifiés à l'index :
     ```sh
     git add .
     ```
   - Commitez les changements avec un message descriptif :
     ```sh
     git commit -m "Ajout de la fonctionnalité X"
     ```

5. **Pousser les changements vers le dépôt distant** :
   - Poussez les changements de votre branche locale vers le dépôt distant :
     ```sh
     git push origin ma-nouvelle-branche
     ```

## Étape 2 : Apprendre Maven

1. **Création d'un projet Maven** :
   - Installez Maven sur votre machine si ce n'est pas déjà fait.
   - Créez un nouveau projet Maven avec la commande suivante :
     ```sh
     mvn archetype:generate -DgroupId=com.example -DartifactId=chatbot -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
     ```

    Sous windows : 
     ```sh
     mvn archetype:generate -D groupId=com.example -D artifactId=chatbot -D archetypeArtifactId=maven-archetype-quickstart -D interactiveMode=false
     ```

   - Cette commande génère un projet Maven minimal avec une structure de répertoires standard.

2. **Structure d'un projet Maven** :
   - **pom.xml** : Le fichier `pom.xml` est le cœur d'un projet Maven. Il contient des informations sur le projet et sa configuration de dépendances, plugins, et d'autres configurations.
   - **src/main/java** : Contient le code source principal du projet.
   - **src/test/java** : Contient les tests unitaires.

3. **Explication de `pom.xml`** :
   - Le `pom.xml` définit les coordonnées du projet (`groupId`, `artifactId`, `version`), ainsi que les dépendances nécessaires pour le projet.
   - Exemple de dépendance pour Kotlin :
     ```xml
     <dependency>
       <groupId>org.jetbrains.kotlin</groupId>
       <artifactId>kotlin-stdlib</artifactId>
       <version>1.6.0</version>
     </dependency>
     ```

4. **Construction et exécution du projet** :
   - Compilez le projet avec :
     ```sh
     mvn clean compile
     ```
   - Exécutez les tests :
     ```sh
     mvn test
     ```
   - Construisez le projet pour produire un fichier JAR :
     ```sh
     mvn package
     ```
    le jar est disponible dans le dossier target/
   - run the app : 
    ```
      java -cp .\target\chatbot-1.0-SNAPSHOT.jar  com.example.App
    ```

## Étape 3 : Apprendre Kotlin

1. **Installation de Kotlin** :
   -  Utilisez un IDE comme IntelliJ IDEA qui prend en charge Kotlin nativement.

2. **Création d'un projet Kotlin simple** :
   - Créez un fichier `Main.kt` dans le répertoire `src/main/kotlin/com/example` avec le contenu suivant :
     ```kotlin
     fun main() {
         println("Hello, World!")
     }
     ```

2. **Exécution du programme** :

2.1. **Assurez-vous que le projet Maven est correctement configuré pour Kotlin** :
   - Votre projet doit inclure les dépendances nécessaires pour Kotlin. Voici un exemple de dépendances à ajouter dans le fichier `pom.xml` :

     ```xml

        <properties>
          <kotlin.version>1.3.21</kotlin.version>
          <kotlin.compiler.incremental>true</kotlin.compiler.incremental>
          <junit-jupiter.version>5.4.2</junit-jupiter.version>
        </properties>
    <build>
        <sourceDirectory>${project.basedir}/src/main/kotlin</sourceDirectory>
        <testSourceDirectory>${project.basedir}/src/test/kotlin</testSourceDirectory>
        <plugins>
          <plugin>
              <artifactId>kotlin-maven-plugin</artifactId>
              <groupId>org.jetbrains.kotlin</groupId>
              <version>${kotlin.version}</version>

              <executions>
                  <execution>
                      <id>compile</id>
                      <goals>
                          <goal>compile</goal>
                      </goals>
                  </execution>

                  <execution>
                      <id>test-compile</id>
                      <goals>
                          <goal>test-compile</goal>
                      </goals>
                  </execution>
              </executions>
          </plugin>
          <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-surefire-plugin</artifactId>
              <version>2.22.1</version>
          </plugin>
      </plugins>
    </build>


     <dependencies>
        <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>3.8.1</version>
          <scope>test</scope>
        </dependency>
          <dependency>
            <groupId>org.jetbrains.kotlin</groupId>
            <artifactId>kotlin-stdlib</artifactId>
            <version>${kotlin.version}</version>
        </dependency>

        <!-- Testing Dependencies -->
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>3.12.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>${junit-jupiter.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>${junit-jupiter.version}</version>
            <scope>test</scope>
        </dependency>
     </dependencies>
     ```

3.2. **Création d'un test en Kotlin** :
   - Placez vos fichiers de test sous `src/test/kotlin` pour qu'ils soient reconnus par Maven.
   - Exemple de test unitaire pour une fonction Kotlin :

     ```kotlin
     // Chemin: src/test/kotlin/com/example/MainTest.kt
     package com.example

    import org.junit.jupiter.api.Assertions
    import org.junit.jupiter.api.Test

     class MainTest {

         @Test
         fun testMain() {
             assertEquals(2, 1 + 1)
         }
     }
     ```

3.3. **Exécution des tests avec Maven** :
   - Utilisez Maven pour compiler et exécuter les tests avec les commandes suivantes :
     - Pour compiler le projet, y compris les tests :
       ```sh
       mvn clean compile
       ```
     - Pour exécuter les tests :
       ```sh
       mvn test
       ```

Maven détectera automatiquement les tests dans le répertoire `src/test/kotlin` et les exécutera. Les tests doivent être annotés avec `@Test` pour être reconnus comme des tests unitaires.

## Étape 4 : Apprendre Quarkus et les APIs

1. **Création d'un projet Quarkus avec Maven** :
   - Utilisez la commande Maven pour générer un projet Quarkus :
     ```sh
     mvn io.quarkus:quarkus-maven-plugin:create \
         -DprojectGroupId=com.example \
         -DprojectArtifactId=chatbot \
         -DclassName="com.example.chatbot.GreetingResource" \
         -Dpath="/hello" \
         -Dextensions="kotlin, resteasy, resteasy-jsonb"
     ```

2. **Création d'une API simple "Hello, World"** :
   - Dans le fichier généré `GreetingResource.kt` sous `src/main/kotlin/com/example/chatbot`, implémentez une méthode REST qui retourne "Hello, World!" :
     ```kotlin
     package com.example.chatbot

     import javax.ws.rs.GET
     import javax.ws.rs.Path
     import javax.ws.rs.Produces
     import javax.ws.rs.core.MediaType

     @Path("/hello")
     class GreetingResource {

         @GET
         @Produces(MediaType.TEXT_PLAIN)
         fun hello() = "Hello, World!"
     }
     ```

3. **Exécution de l'application Quarkus** :
   - Exécutez l'application avec la commande suivante :
     ```sh
     ./mvnw compile quarkus:dev
     ```
   - L'API sera disponible à l'adresse [http://localhost:8080/hello](http://localhost:8080/hello).

## Étape 5 : Création d'une API POST et GET pour un chatbot

(un peu plus info ici => https://quarkus.io/guides/rest-json)

1. **Ajout de dépendances pour la base de données et Flyway** :
   - Ajoutez les dépendances pour PostgreSQL et Flyway dans le `pom.xml` :
     ```xml
     <dependency>
       <groupId>io.quarkus</groupId>
       <artifactId>quarkus-jdbc-postgresql</artifactId>
     </dependency>
     <dependency>
       <groupId>io.quarkus</groupId>
       <artifactId>quarkus-flyway</artifactId>
     </dependency>
     ```

2. **Configuration de la base de données** :
   - Ajoutez les configurations de la base de données dans `application.properties` :
     ```
     quarkus.datasource.db-kind=postgresql
     quarkus.datasource.username=postgres
     quarkus.datasource.password=password
     quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/chatbot
     quarkus.flyway.migrate-at-start=true
     ```

3. **Création de la migration Flyway** :
   - Dans `src/main/resources/db/migration`, créez un fichier `V1__Create_conversation_table.sql` avec le script suivant :
     ```sql
     CREATE TABLE conversation (
       id SERIAL PRIMARY KEY,
       user_message TEXT NOT NULL,
       bot_response TEXT NOT NULL,
       timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
     );
     ```

4. **Création des endpoints POST et GET** :
   - Modifiez ou créez une nouvelle ressource `ChatbotResource.kt` :
     ```kotlin
     package com.example.chatbot

     import javax.ws.rs.*
     import javax.ws.rs.core.MediaType
     import javax.ws.rs.core.Response
     import java.util.*
     import kotlin.collections.ArrayList

     @Path("/chat")
     class ChatbotResource {

         data class ChatMessage(val userMessage: String, val botResponse: String)

         private val responses = listOf("Hello!", "How can I help you?", "Goodbye!")

         @POST
         @Consumes(MediaType.APPLICATION_JSON)
         @Produces(MediaType.APPLICATION_JSON)
         fun chat(message: Map<String, String>): Response {
             val userMessage = message["userMessage"] ?: return Response.status(Response.Status.BAD_REQUEST).build()
             val botResponse = responses.random()
             // Enregistrer dans la base de données 
             return Response.ok(ChatMessage(userMessage, botResponse)).build()
         }

         @GET
         @Produces(MediaType.APPLICATION_JSON)
         fun getAllConversations(): Response {
             // Récupérer toutes les conversations depuis la base de données 
             val conversations = ArrayList<ChatMessage>()
             return Response.ok(conversations).build()
         }
     }
     ```
6. **Ajouter les appels à la base de données** :
   - Ajouter les appels à la base de données prendre exemple ici => https://quarkus.io/guides/hibernate-orm-panache

5. **Test des endpoints** :
   - Testez l'endpoint POST en envoyant un JSON avec `{"userMessage": "Hi"}` à `/chat` et vérifiez que le bot retourne une réponse aléatoire.
   - Testez l'endpoint GET pour récupérer toutes les conversations stockées.


## Étape 6 : Transformation en reactive

Suivre cette étape ici pour utiliser les Uni<*> => https://quarkus.io/guides/getting-started-reactive

## Étape 7 : Appeler un ia pour générer les réponses: 

etape ici => https://quarkus.io/blog/quarkus-meets-langchain4j/ 

Demander pour avoir les accès au apis OpenAi ou HuggingFace


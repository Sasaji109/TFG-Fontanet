package com.example.tfgfontanet.common.configuracion;

import com.example.tfgfontanet.common.utiles.Constantes;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConfig {


    public static MongoClient createMongoClient() {
        String connectionString = Constantes.DATABASE_HOST + Constantes.DATABASE_PORT;
        return MongoClients.create(connectionString);
    }

    public static MongoDatabase getMongoDatabase() {
        MongoClient mongoClient = createMongoClient();
        return mongoClient.getDatabase(Constantes.DATABASE_NAME);
    }

    public static void closeMongoClient(MongoClient mongoClient) {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}

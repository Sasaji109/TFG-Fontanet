package com.example.tfgfontanet.data.dao.implementaciones;

import com.example.tfgfontanet.common.configuracion.MongoDBConfig;
import com.example.tfgfontanet.common.utiles.Constantes;
import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.dao.DAOValoraciones;
import com.example.tfgfontanet.data.modelo.ProfesionalMongo;
import com.example.tfgfontanet.data.modelo.ValoracionMongo;
import com.example.tfgfontanet.data.modelo.adapter.ObjectIdAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.time.LocalDate;
import java.util.List;

public class DAOValoracionesImpl implements DAOValoraciones {

    private final MongoDatabase mongoDatabase;
    private final Gson gson = new GsonBuilder().registerTypeAdapter(ObjectId.class, new ObjectIdAdapter()).create();

    @Inject
    public DAOValoracionesImpl() {
        this.mongoDatabase = MongoDBConfig.getMongoDatabase();
    }

    @Override
    public Either<ErrorC, ProfesionalMongo> getAllByProf(int profesionalId) {
        Either<ErrorC, ProfesionalMongo> either;

        try {
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("profesionales");
            Document profesionalDocument = mongoCollection.find(Filters.eq("profesionalId", profesionalId)).first();

            if (profesionalDocument != null) {
                ProfesionalMongo profesionalMongo = new Gson().fromJson(profesionalDocument.toJson(), ProfesionalMongo.class);
                either = Either.right(profesionalMongo);
            } else {
                either = Either.left(new ErrorC(404, "Profesional no encontrado", LocalDate.now()));
            }

        } catch(Exception e) {
            either = Either.left(new ErrorC(5, Constantes.MONGO_ERROR + e.getMessage(), LocalDate.now()));
        }

        return either;
    }

    @Override
    public Either<ErrorC, Integer> addValoracion(int profesionalId, ValoracionMongo valoracion) {
        Either<ErrorC, Integer> either;

        try {

            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("profesionales");
            Document query = new Document("profesionalId", profesionalId);
            Document profesionalDoc = mongoCollection.find(query).first();

            if (profesionalDoc != null) {

                List<Document> valoracionesDoc = (List<Document>) profesionalDoc.get("valoraciones");

                String orderJson = gson.toJson(valoracion);
                Document newValDoc = Document.parse(orderJson);
                valoracionesDoc.add(newValDoc);
                mongoCollection.updateOne(query, new Document("$set", new Document("valoraciones", valoracionesDoc)));

                either = Either.right(1);
            } else {
                either = Either.left(new ErrorC(404, "Profesional no encontrado", LocalDate.now()));
            }
        } catch (Exception e) {
            either = Either.left(new ErrorC(5, Constantes.MONGO_ERROR + e.getMessage(), LocalDate.now()));
        }

        return either;
    }
}

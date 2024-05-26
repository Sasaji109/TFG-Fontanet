package com.example.tfgfontanet.data.dao.implementaciones;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.ui.errores.CustomError;
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
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DAOValoracionesImpl implements DAOValoraciones {

    private final MongoDatabase mongoDatabase;
    private final Gson gson;

    @Autowired
    public DAOValoracionesImpl(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
        this.gson = new GsonBuilder().registerTypeAdapter(ObjectId.class, new ObjectIdAdapter()).create();
    }

    @Override
    public Either<CustomError, List<ValoracionMongo>> getAllByProf(int profesionalId) {
        Either<CustomError, List<ValoracionMongo>> either;

        try {
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(Constantes.PROFESIONALES_MONGO_DB);
            Document profesionalDocument = mongoCollection.find(Filters.eq(Constantes.PROFESIONAL_ID, profesionalId)).first();

            if (profesionalDocument != null) {
                ProfesionalMongo profesionalMongo = new Gson().fromJson(profesionalDocument.toJson(), ProfesionalMongo.class);
                either = Either.right(profesionalMongo.getValoraciones());
            } else {
                either = Either.left(new CustomError(404, Constantes.PROFESIONAL_NOT_FOUND, LocalDate.now()));
            }

        } catch(Exception e) {
            either = Either.left(new CustomError(5, Constantes.MONGO_ERROR + e.getMessage(), LocalDate.now()));
        }

        return either;
    }

    @Override
    public Either<CustomError, Integer> addValoracion(int profesionalId, ValoracionMongo valoracion) {
        Either<CustomError, Integer> either;

        try {

            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(Constantes.PROFESIONALES_MONGO_DB);
            Document query = new Document(Constantes.PROFESIONAL_ID, profesionalId);
            Document profesionalDoc = mongoCollection.find(query).first();

            if (profesionalDoc != null) {

                List<Document> valoracionesDoc = (List<Document>) profesionalDoc.get(Constantes.VALORACIONES);

                String orderJson = gson.toJson(valoracion);
                Document newValDoc = Document.parse(orderJson);
                valoracionesDoc.add(newValDoc);
                mongoCollection.updateOne(query, new Document(Constantes.$_SET_MONGO, new Document(Constantes.VALORACIONES, valoracionesDoc)));

                either = Either.right(1);
            } else {
                either = Either.left(new CustomError(404, Constantes.PROFESIONAL_NOT_FOUND, LocalDate.now()));
            }
        } catch (Exception e) {
            either = Either.left(new CustomError(5, Constantes.MONGO_ERROR + e.getMessage(), LocalDate.now()));
        }

        return either;
    }
}

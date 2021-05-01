package br.com.carlosmelocursos.escolaAlura.repository;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.carlosmelocursos.escolaAlura.codecs.AlunoCodec;
import br.com.carlosmelocursos.escolaAlura.models.Aluno;

@Repository
public class AlunoRepository {
	
	public void salvar(Aluno aluno) {
		
		  Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
		  AlunoCodec alunoCodec = new AlunoCodec(codec);

		  CodecRegistry registro = CodecRegistries.fromRegistries(
		  MongoClient.getDefaultCodecRegistry(), 
		  CodecRegistries.fromCodecs(alunoCodec));

		  MongoClientOptions options = MongoClientOptions.builder()	
				  .maxWaitTime(10)
				  //.put("waitQueueMultiple", 10000)
				  .codecRegistry(registro).build();
		  
		  
		
		  MongoClient cliente = new MongoClient("localhost:27017", options);
		  MongoDatabase bancoDeDados = cliente.getDatabase("config");
		  MongoCollection<Document> alunos = bancoDeDados.getCollection("alunos");

		  /*MongoClient client = new MongoClient();
		  MongoDatabase bancoDeDados = client.getDatabase("config");
		  MongoCollection<Aluno> alunos = bancoDeDados.getCollection("alunos", Aluno.class);
		  alunos.insertOne(aluno);*/
	}

}

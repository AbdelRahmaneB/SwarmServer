package swarm.server.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import swarm.server.domains.Product;
import swarm.server.domains.Session;
import swarm.server.domains.Task;
import swarm.server.domains.Type;

@RepositoryRestResource(collectionResourceRel = "types", path = "types")
public interface TypeRepository extends PagingAndSortingRepository<Type, Long> {
	
	@Query("Select t from Type t Where t.session.id = :sessionId")
	List<Type> findBySessionId(@Param("sessionId") Long sessionId);

	List<Type> findBySession(@Param("session") Session session);
	
	@Query("Select t from Type t Where t.session.task = :task order by t.fullName")
	List<Type> findByTask(@Param("task") Task task);

	@Query("Select t.fullName from Type t Where t.session.task.product = :product group by fullName order by t.fullName")
	List<String> findFullNamesByProduct(@Param("product") Product product);

}
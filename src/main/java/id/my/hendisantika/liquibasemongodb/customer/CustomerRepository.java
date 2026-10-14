package id.my.hendisantika.liquibasemongodb.customer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : liquibase-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/01/25
 * Time: 07.24
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}

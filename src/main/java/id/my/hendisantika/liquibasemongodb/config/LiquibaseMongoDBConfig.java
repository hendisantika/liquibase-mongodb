package id.my.hendisantika.liquibasemongodb.config;

import liquibase.command.CommandResults;
import liquibase.command.CommandScope;
import liquibase.command.core.UpdateCommandStep;
import liquibase.command.core.helpers.DbUrlConnectionCommandStep;
import liquibase.exception.LiquibaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : liquibase-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/01/25
 * Time: 07.21
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@Slf4j
public class LiquibaseMongoDBConfig {
    @Value("${spring.data.mongodb.uri}")
    private String connectionUrl;

    @Value("${changeLogFile}")
    private String changeLogFile;

    /**
     * Execute the update when start the application.
     */
    @Bean
    public CommandResults liquibaseUpdate() throws LiquibaseException {
        return new CommandScope("update")
                .addArgumentValue(UpdateCommandStep.CHANGELOG_FILE_ARG, changeLogFile)
                .addArgumentValue(DbUrlConnectionCommandStep.URL_ARG, connectionUrl)
                .execute();
    }
}

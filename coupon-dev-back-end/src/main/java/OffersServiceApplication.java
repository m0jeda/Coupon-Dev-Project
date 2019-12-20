import com.opencsv.CSVReader;
import dao.OfferDetailsMapper;
import dao.OffersDBLoader;
import dao.OffersDao;
import dao.OffersMapper;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlite3.SQLitePlugin;
import resources.OffersResource;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.io.*;
import java.util.EnumSet;

public class OffersServiceApplication extends Application<OffersServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new OffersServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "offers";
    }

    @Override
    public void initialize(final Bootstrap<OffersServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<OffersServiceConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(OffersServiceConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(OffersServiceConfiguration configuration,
                    Environment environment) {
        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");


        String sql = "select DISTINCT o.id, o.name, o.description, o.image_url, r.name from offers o, retailers r, retailer_offers ro where ro.retailer_id = r.id and ro.offer_id = o.id";
        // Connect and create a new table in the database
        Jdbi jdbi = Jdbi.create("jdbc:sqlite:src/main/resources/development.sqlite3")
                .installPlugin(new SQLitePlugin());

        jdbi.useHandle(handle -> {
            // delete the database tables if they exist
            handle.execute("DROP TABLE IF EXISTS offers");
            handle.execute("DROP TABLE IF EXISTS retailers");
            handle.execute("DROP TABLE IF EXISTS retailer_offers");

            handle.execute("CREATE TABLE offers (id integer PRIMARY KEY NOT NULL, name varchar, description text, terms text, image_url varchar, expiration datetime)");
            handle.execute("CREATE TABLE retailers (id integer PRIMARY KEY NOT NULL, name varchar)");
            handle.execute("CREATE TABLE retailer_offers (id integer PRIMARY KEY NOT NULL, retailer_id integer, offer_id integer)");

            // Inline positional parameters
            try {
                CSVReader csvReader = new CSVReader(new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/main/resources/offers.seed.csv")))));
                String[] line;
                csvReader.readNext();
                while ((line = csvReader.readNext()) != null) {
                    handle.execute("INSERT INTO offers(id, name, description, terms, image_url, expiration) VALUES (?,?,?,?,?,?)", line[0], line[1], line[2], line[3], line[4], line[5]);
                }

                csvReader = new CSVReader(new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/main/resources/retailers.seed.csv")))));
                csvReader.readNext();
                while ((line = csvReader.readNext()) != null) {
                    handle.execute("INSERT INTO retailers(id, name) VALUES (?,?)", line[0], line[1]);
                }

                csvReader = new CSVReader(new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/main/resources/retailer_offers.seed.csv")))));
                csvReader.readNext();
                while ((line = csvReader.readNext()) != null) {
                    handle.execute("INSERT INTO retailer_offers(id, retailer_id, offer_id) VALUES (?,?,?)", line[0], line[1], line[2]);
                }

            } catch (IOException e) {

            }
        });

        OffersDao offersDao = new OffersDBLoader(jdbi, new OffersMapper(), new OfferDetailsMapper());

        final OffersResource resource = new OffersResource(offersDao);
        environment.jersey().register(resource);
    }
}

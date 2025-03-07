package kpi.hlibsukhoruchkin.kursova;
import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;
import org.jooq.meta.jaxb.Generator;
import at.favre.lib.crypto.bcrypt.BCrypt;
public class JOOQ_Gen {
    public static void main(String[] args) throws Exception {
        GenerationTool.generate(new org.jooq.meta.jaxb.Configuration()

                // Configure the database connection here
                .withJdbc(new Jdbc()
                        .withDriver("com.mysql.cj.jdbc.Driver")
                        .withUrl("jdbc:mysql://localhost:3306/kursova?allowPublicKeyRetrieval=true&useSSL=false")
                        .withUser("root")
                        .withPassword("root")

                        // You can also pass user/password and other JDBC properties in the optional properties tag:
//                        .withProperties(
//                                new Property()
//                                        .withKey("user")
//                                        .withValue("[db-user]"),
//                                new Property()
//                                        .withKey("password")
//                                        .withValue("[db-password]")
//                        )
                )
                .withGenerator(new Generator()
                        .withDatabase(new Database()

                                // The database dialect from jooq-meta. Available dialects are
                                // named org.jooq.meta.[database].[database]Database.
                                //
                                // Natively supported values are:
                                //
                                // org.jooq.meta.ase.ASEDatabase
                                // org.jooq.meta.auroramysql.AuroraMySQLDatabase
                                // org.jooq.meta.aurorapostgres.AuroraPostgresDatabase
                                // org.jooq.meta.cockroachdb.CockroachDBDatabase
                                // org.jooq.meta.db2.DB2Database
                                // org.jooq.meta.derby.DerbyDatabase
                                // org.jooq.meta.firebird.FirebirdDatabase
                                // org.jooq.meta.h2.H2Database
                                // org.jooq.meta.hana.HANADatabase
                                // org.jooq.meta.hsqldb.HSQLDBDatabase
                                // org.jooq.meta.ignite.IgniteDatabase
                                // org.jooq.meta.informix.InformixDatabase
                                // org.jooq.meta.ingres.IngresDatabase
                                // org.jooq.meta.mariadb.MariaDBDatabase
                                // org.jooq.meta.mysql.MySQLDatabase
                                // org.jooq.meta.oracle.OracleDatabase
                                // org.jooq.meta.postgres.PostgresDatabase
                                // org.jooq.meta.redshift.RedshiftDatabase
                                // org.jooq.meta.snowflake.SnowflakeDatabase
                                // org.jooq.meta.sqldatawarehouse.SQLDataWarehouseDatabase
                                // org.jooq.meta.sqlite.SQLiteDatabase
                                // org.jooq.meta.sqlserver.SQLServerDatabase
                                // org.jooq.meta.sybase.SybaseDatabase
                                // org.jooq.meta.teradata.TeradataDatabase
                                // org.jooq.meta.trino.TrinoDatabase
                                // org.jooq.meta.vertica.VerticaDatabase
                                //
                                // This value can be used to reverse-engineer generic JDBC DatabaseMetaData (e.g. for MS Access)
                                //
                                // org.jooq.meta.jdbc.JDBCDatabase
                                //
                                // This value can be used to reverse-engineer standard jOOQ-meta XML formats
                                //
                                // org.jooq.meta.xml.XMLDatabase
                                //
                                // This value can be used to reverse-engineer schemas defined by SQL files
                                // (requires jooq-meta-extensions dependency)
                                //
                                // org.jooq.meta.extensions.ddl.DDLDatabase
                                //
                                // This value can be used to reverse-engineer schemas defined by JPA annotated entities
                                // (requires jooq-meta-extensions-hibernate dependency)
                                //
                                // org.jooq.meta.extensions.jpa.JPADatabase
                                //
                                // This value can be used to reverse-engineer schemas defined by Liquibase migration files
                                // (requires jooq-meta-extensions-liquibase dependency)
                                //
                                // org.jooq.meta.extensions.liquibase.LiquibaseDatabase
                                //
                                // You can also provide your own org.jooq.meta.Database implementation
                                // here, if your database is currently not supported
                                .withName("org.jooq.meta.mysql.MySQLDatabase")

                                // All elements that are generated from your schema (A Java regular expression.
                                // Use the pipe to separate several expressions) Watch out for
                                // case-sensitivity. Depending on your database, this might be
                                // important!
                                //
                                // You can create case-insensitive regular expressions using this syntax: (?i:expr)
                                //
                                // Whitespace is ignored and comments are possible.
                                .withIncludes(".*")

                                // All elements that are excluded from your schema (A Java regular expression.
                                // Use the pipe to separate several expressions). Excludes match before
                                // includes, i.e. excludes have a higher priority

                                // The schema that is used locally as a source for meta information.
                                // This could be your development schema or the production schema, etc
                                // This cannot be combined with the schemata element.
                                //
                                // If left empty, jOOQ will generate all available schemata. See the
                                // manual's next section to learn how to generate several schemata
                                .withInputSchema("kursova")
                        )

                        // Generation flags: See advanced configuration properties
                        .withTarget(new Target()

                                // The destination package of your generated classes (within the
                                // destination directory)
                                //
                                // jOOQ may append the schema name to this package if generating multiple schemas,
                                // e.g. org.jooq.your.packagename.schema1
                                // org.jooq.your.packagename.schema2
                                .withPackageName("kpi.hlibsukhoruchkin.kursova.jooq")

                                // The destination directory of your generated classes
                                .withDirectory("D:\\UNIVERSITY\\Lab\\Y1S2\\Programming\\kursova\\src\\main\\java")
                        )
                ));
    }
}

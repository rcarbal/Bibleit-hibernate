package co.bibleit.springboot.database.mysql;

import co.bibleit.springboot.database.interfaces.DatabaseConnection;
import co.bibleit.springboot.database.mysql.entities.BibleSection;
import co.bibleit.springboot.database.mysql.entities.DatabaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MySQLConnection implements DatabaseConnection {
    @Override
    public void connect() {
        
    }

    @Override
    public void testConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bible_it?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "2Qepxniw";

        try{
            System.out.println("Connection to database. " + jdbcUrl);
            Connection connection= DriverManager.getConnection(jdbcUrl, user, password);

            System.out.println("Connection successful!!!");
        }
        catch (SQLException e){
            System.out.println("Something went wrong connection to MySQL database. " + e.getMessage());
        }
    }

    @Override
    public void testCreateRowInTable(Object object) {
        SessionFactory factory = null;

        if (object instanceof SessionFactory){
            factory = (SessionFactory) object;
        }

        Session session = null;
        if (factory != null) {
            session = factory.getCurrentSession();
        }

        try {
            // create the BibleSections object
            System.out.println("Creating a new BibleSections object.");
            BibleSection section = new BibleSection("Old Testament");
            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the bile section");
            session.save(section);

            //commit
            session.getTransaction().commit();
            System.out.println("done");

        }
        finally {
            factory.close();
        }
    }

    @Override
    public int saveMultipleObjects(SessionFactory factory, List<DatabaseEntity> entities) {
        if (factory == null || entities == null){
            return -1;
        }
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        try{
            for(int i = 0; i < entities.size(); i ++){

                DatabaseEntity entity = entities.get(i);
                session.save(entity);
                System.out.println();
            }
        }finally {
            session.getTransaction().commit();
        }

        return 1;
    }

    @Override
    public BibleSection getFromIndexDatabase(Object factory, int index) {

        /*
        This session factory is not required. The class that calls this method is already passing in a Session Factory
         */
        SessionFactory sessionFactory = null;

        if (factory instanceof SessionFactory){
            sessionFactory = (SessionFactory) factory;
        }
        Session session  = sessionFactory.getCurrentSession();
        BibleSection mySection;

        try{
            session.beginTransaction();

            mySection = session.get(BibleSection.class, index);
            System.out.println("Get complete section: "+ mySection);

            session.getTransaction().commit();
            System.out.println("Done");
        }finally {
            System.out.println();
            sessionFactory.close();
        }

        return mySection;
    }

    @Override
    public List<BibleSection> queryListFromSQLString(Object factory, String sqlString) {

        List<BibleSection> list;

        if (!(factory instanceof SessionFactory)){
            return null;
        }
        Session session = ((SessionFactory) factory).getCurrentSession();

        try{
            session.beginTransaction();
            list = session.createQuery( sqlString).getResultList();
            session.getTransaction().commit();
        }finally {
            System.out.println("Finished querying SQL string");
        }

        return list;
    }

    @Override
    public void updateSingleBibleSectionWithSQLString(Object factory, String updatedString, int index) {
        if (!(factory instanceof SessionFactory)){

        }
        Session session = ((SessionFactory) factory).getCurrentSession();

        try {
            session.beginTransaction();
            BibleSection mySection = session.get(BibleSection.class, index);

            System.out.println("Updating BibleSection..");
            mySection.setName(updatedString);

            session.getTransaction().commit();
        } finally {
            System.out.println("Finished updating BibleSections row number: " + index);
        }
    }
}

module com.mycompany.projectplantif {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.hibernate.orm.core;
requires java.naming;
requires java.persistence;
requires java.sql;
    opens com.mycompany.projectplantif to javafx.fxml,org.hibernate.orm.core;
    exports com.mycompany.projectplantif;
}

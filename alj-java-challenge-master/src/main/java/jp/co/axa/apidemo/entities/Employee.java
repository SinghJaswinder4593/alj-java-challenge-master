package jp.co.axa.apidemo.entities;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="EMPLOYEE")
public class Employee {

    @Getter
    @Setter
    @Id
    @CsvBindByName
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @CsvBindByName
    @Column(name="NAME")
    private String name;

    @Getter
    @Setter
    @CsvBindByName
    @Column(name="SALARY")
    private Integer salary;

    @Getter
    @Setter
    @CsvBindByName
    @Column(name="DEPARTMENT")
    private String department;


}

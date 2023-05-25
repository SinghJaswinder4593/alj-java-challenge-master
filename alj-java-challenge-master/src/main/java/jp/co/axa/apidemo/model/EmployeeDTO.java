package jp.co.axa.apidemo.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name="EMPLOYEE_DATA")
public class EmployeeDTO {

    @Id
    @Getter
    @Setter
    @CsvBindByName
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

    @CsvBindByName
    @Getter
    @Setter
    @Column(name="STATUS")
    private String status;

}

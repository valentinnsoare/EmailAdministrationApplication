package io.emailadministration.entities.companyemployees.employeedefinitionwithdetails;

import io.emailadministration.entities.digitalcomponents.User;
import io.emailadministration.entities.companyemployees.DepartmentType;
import io.emailadministration.printing.CustomPrinting;
import io.emailadministration.customdatastructureandoperationsonthem.operationswithdatastructures.OperationsOnMap;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@Entity(name = "employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "employee", schema = "employee")
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.STRING,
        name = "employee_type"
)
@DiscriminatorValue("none")
public abstract class Employee implements Comparable<Employee> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Embedded
    private Address address;

    @Column(name = "employee_id")
    private String employeeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "department_where_i_am_working")
    private DepartmentType departmentWhereIamWorking;

    @Embedded
    private WorkDetails workDetails;

    @Embedded
    private TimeAndDateInformation timeAndDateInformation;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    protected Employee() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;

        return employeeId.equals(employee.employeeId);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();

        result = 31 * result + lastName.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + employeeId.hashCode();

        return result;
    }

    @Override
    public int compareTo(@NotNull Employee o) {
        return o.employeeId.compareTo(this.employeeId);
    }

    @Override
    public String toString() {
        Map<String, ?> characteristics = OperationsOnMap.putObjectAttributes(this);
        return CustomPrinting.of(characteristics, "Employee [");
    }
}

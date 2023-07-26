package io.undefvar.springbatchdemo.batch;

import io.undefvar.springbatchdemo.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee> {
    @Override
    public Employee process(Employee item) throws Exception {
        log.info("Processing employee: {} {}", item.getFirstName(), item.getLastName());
        return item;
    }
}

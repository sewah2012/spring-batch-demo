package io.undefvar.springbatchdemo.batch;

import io.undefvar.springbatchdemo.domain.Employee;
import io.undefvar.springbatchdemo.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class EmployeeItemWriter implements ItemWriter<Employee> {
    private final EmployeeRepository employeeRepository;
    @Override
    public void write(Chunk<? extends Employee> chunk) throws Exception {
        employeeRepository.saveAll(chunk.getItems());
    }
}

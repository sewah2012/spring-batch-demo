package io.undefvar.springbatchdemo.batch;

import io.undefvar.springbatchdemo.domain.Employee;
import io.undefvar.springbatchdemo.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeItemWriter implements ItemWriter<Employee> {
    private final EmployeeRepository repository;
    @Override
    public void write(Chunk<? extends Employee> chunk) throws Exception {
        repository.saveAll(chunk.getItems());
    }
}

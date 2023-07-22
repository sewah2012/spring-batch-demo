package io.undefvar.springbatchdemo.batch;

import io.undefvar.springbatchdemo.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    private final EmployeeItemWriter employeeItemWriter;
    private final EmployeeItemProcessor employeeItemProcessor;

    @Bean
    public FlatFileItemReader<Employee> employeeFlatFileItemReader() {
        return new FlatFileItemReaderBuilder<Employee>()
                .name("employeeItemReader")
                .resource(new ClassPathResource("employees.csv"))
                .delimited()
                .names("First Name", "Last Name", "Email", "Phone", "Gender", "Department", "Job Title", "Years Of Experience", "Salary")
                .linesToSkip(1)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Employee.class);
                }})
                .build();
    }




    @Bean(name = "importEmployeesStep")
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .<Employee, Employee> chunk(10, transactionManager)
                .reader(employeeFlatFileItemReader())
                .processor(employeeItemProcessor)
                .writer(employeeItemWriter)
                .build();
    }

    @Bean("importEmployeesJob")
    public Job importEmployeesJob(JobRepository jobRepository, @Qualifier("importEmployeesStep") Step step) {
        return new JobBuilder("importEmployeesJob", jobRepository)
                .incrementer(new RunIdIncrementer())
//                .listener(listener)
                .flow(step)
                .end()
                .build();
    }


}

package com.example;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController()
@RequestMapping("api/counter")
public class CounterController {

    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    //Создание счетчика с уникальным именем
    @PostMapping("/create")
    public String createCounter(@RequestParam(name = "name") String name){
        if(name == null || name.isEmpty() || name.isBlank())
            return ("Name can't be empty");
        Counter counter = new Counter(0, name);
        if(counterService.addCounter(counter))
            return HttpStatus.resolve(HttpStatus.OK.value()).getReasonPhrase();
        else return "Counter with this name already exist!";
    }

    //Инкрементация счетчика с уникальным именем
    @PostMapping("/increment")
    public void incCounter(@RequestParam(name = "name") String name){
        counterService.getCounterByName(name).inc();
    }

    // Получение значения счетчика с указанным именем
    @GetMapping("/{name}")
    public Integer getCounterInc(@PathVariable String name) {
        return counterService.getCounterByName(name).getCounter();
    }

    // Удаление счетчика с указанным именем
    @DeleteMapping("/{name}")
    public String deleteCounter(@PathVariable String name){
        if(counterService.deleteCounter(name))
            return HttpStatus.OK.getReasonPhrase();
        return "Counter not found";
    }

    // Получение суммарного значения всех счетчиков
    @GetMapping("/summary")
    public String getSummary() {
        Long result = counterService.getSummaryCount();
        if (result == -1L)
            return "Too much value...";
        return result.toString();
    }

    // Получение уникальных имен счетчиков
    @GetMapping("/names")
    public Set<String> getNames() {
        return counterService.getNames();
    }
}
